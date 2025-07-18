package com.sagar.RestController;

import com.sagar.PaymentRequest.PaymentRequest;
import com.sagar.ResponseDto.PaymentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/mock")  
public class ProcessorController {

    private final Random random = new Random();

    @PostMapping("/processor-a")
    public PaymentResponse processA(@RequestBody PaymentRequest request) throws InterruptedException {
        return simulateProcessing("Processor A", request);
    }

    @PostMapping("/processor-b")
    public PaymentResponse processB(@RequestBody PaymentRequest request) throws InterruptedException {
        return simulateProcessing("Processor B", request);
    }

    @PostMapping("/processor-c")
    public PaymentResponse processC(@RequestBody PaymentRequest request) throws InterruptedException {
        return simulateProcessing("Processor C", request);
    }

    private PaymentResponse simulateProcessing(String processorName, PaymentRequest request) throws InterruptedException {
        // Simulate delay between 50–200ms
        Thread.sleep(50 + random.nextInt(150));

        // Random success/failure
        boolean isSuccess = random.nextBoolean();
        String status = isSuccess ? "SUCCESS" : "FAILURE";
        String message = isSuccess
                ? processorName + " processed successfully."
                : processorName + " failed to process or TimedOut.";

        return new PaymentResponse(request.getTransactionId(), status, message);
    }
}
