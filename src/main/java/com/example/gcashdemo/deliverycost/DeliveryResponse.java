package com.example.gcashdemo.deliverycost;

import java.util.Optional;

public class DeliveryResponse {
    public String ruleName;
    public String condition;
    public String costCalculationUsed;
    public double cost=0;
    public Optional<String> voucherCode;
    public String discount;


    public DeliveryResponse(String ruleName, String condition, String costCalculationUsed, double cost, Optional<String> voucherCode, String discount) {
        this.ruleName = ruleName;
        this.condition = condition;
        this.costCalculationUsed = costCalculationUsed;
        this.cost = cost;
        this.voucherCode = voucherCode;
        this.discount = discount;
    

    }

    // Constructor, getters, and setters

    public String getruleName() {
        return ruleName;
    }

    public String getcondition() {
        return condition;
    }
    
    public String getcostCalculationUsed() {
        return costCalculationUsed;
    }
    public double getcost() {
        return cost;
    }
}