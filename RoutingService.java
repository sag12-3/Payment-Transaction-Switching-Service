package com.sagar.service;

import com.sagar.PaymentRequest.PaymentRequest;
import com.sagar.Repo.PaymentTransactionRepository;
import com.sagar.ResponseDto.PaymentResponse;
import com.sagar.ResponseEntity.PaymentTransaction;
import com.sagar.config.RoutingRuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class RoutingService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RoutingRuleProperties routingProp;

    @Autowired
    private PaymentTransactionRepository transactionRepo;

    public Mono<PaymentResponse> routePayment(PaymentRequest request) {

        
        UUID transactionId = (request.getTransactionId() != null)
                ? request.getTransactionId()
                : UUID.randomUUID();
        
        
        if (request.getMerchantId() == null || request.getMerchantId().isBlank()) {
            throw new IllegalArgumentException("merchantId is required");
        }

       
        if (request.getCardType() == null) {
            throw new IllegalArgumentException("cardType is required");
        }

      
        if (request.getAmount() == null || request.getAmount().signum() <= 0 ) {
            throw new IllegalArgumentException("amount must be a positive number");
        }


       
        List<RoutingRuleProperties.Rule> rules = routingProp.getRules();
        RoutingRuleProperties.Rule matchedRule = null;

        for (RoutingRuleProperties.Rule rule : rules) {
            if (rule.getRouteTo() != null) {
                if (rule.getMerchantId() != null &&
                        rule.getMerchantId().equalsIgnoreCase(request.getMerchantId())) {
                    matchedRule = rule;
                    break;
                }
                if (rule.getCardType() != null &&
                        rule.getCardType().equalsIgnoreCase(request.getCardType().name()) &&
                        rule.getMinAmount() != null &&
                        request.getAmount().compareTo(rule.getMinAmount()) > 0) {
                    matchedRule = rule;
                    break;
                }
            }
        }

      
        String endpoint = (matchedRule != null) ? matchedRule.getRouteTo() : routingProp.getDefaultRoute();

    
        PaymentTransaction txn = new PaymentTransaction();
        txn.setTransactionId(transactionId);
        txn.setAmount(request.getAmount());
        txn.setCurrency(request.getCurrency());
        txn.setMerchantId(request.getMerchantId());
        txn.setCardType(request.getCardType());
        txn.setProcessorUsed(endpoint);
        txn.setRouteTo(extractRouteName(endpoint));
        transactionRepo.save(txn);

       
        PaymentRequest updatedRequest = new PaymentRequest(
                transactionId,
                request.getMerchantId(),
                request.getCardType(),
                request.getAmount(),
                request.getCurrency()
        );

        return webClientBuilder.build()
                .post()
                .uri(endpoint)
                .bodyValue(updatedRequest)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .doOnNext(response -> {
                    txn.setStatus(response.getStatus());
                    txn.setMessage(response.getMessage());
                    transactionRepo.save(txn);
                })
                .map(response -> new PaymentResponse( 
                        transactionId,
                        response.getStatus(),
                        response.getMessage()
                ))
                .onErrorResume(ex -> {
                    txn.setStatus("FAILURE");
                    txn.setMessage("Processor error: " + ex.getMessage());
                    transactionRepo.save(txn);
                    return Mono.just(new PaymentResponse( 
                            transactionId,
                            "FAILURE",
                            "Processor error: " + ex.getMessage()
                    ));
                });
    }
    private String extractRouteName(String endpoint) {
        if (endpoint == null) return "unknown";
        if (endpoint.contains("processor-a")) return "processorA";
        if (endpoint.contains("processor-b")) return "processorB";
        if (endpoint.contains("processor-c")) return "processorC";
        return "unknown";
    }
}
