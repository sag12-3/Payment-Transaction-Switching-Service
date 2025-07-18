package com.sagar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "routing")
public class RoutingRuleProperties {

    private List<Rule> rules;
    private String defaultRoute;

    public static class Rule {
        private String merchantId;
        private String cardType;
        private BigDecimal minAmount;
        private String routeTo;

        // Getters and setters
        public String getMerchantId() { return merchantId; }
        public void setMerchantId(String merchantId) { this.merchantId = merchantId; }

        public String getCardType() { return cardType; }
        public void setCardType(String cardType) { this.cardType = cardType; }

        public BigDecimal getMinAmount() { return minAmount; }
        public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }

        public String getRouteTo() { return routeTo; }
        public void setRouteTo(String routeTo) { this.routeTo = routeTo; }
    }

    // Getters and setters
    public List<Rule> getRules() { return rules; }
    public void setRules(List<Rule> rules) { this.rules = rules; }

    public String getDefaultRoute() { return defaultRoute; }
    public void setDefaultRoute(String defaultRoute) { this.defaultRoute = defaultRoute; }
}
