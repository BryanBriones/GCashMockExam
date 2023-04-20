package com.example.gcashdemo.deliverycost;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootApplication
@RestController
public class DeliveryCostController {
    private final DeliveryCostService deliveryCostService;
    
    @Autowired
    public DeliveryCostController(DeliveryCostService deliveryCostService) {
        this.deliveryCostService = deliveryCostService;
    }
    
    @GetMapping("/delivery-cost")
    public DeliveryResponse getDeliveryCost(@RequestParam double weight, @RequestParam double height,
                                  @RequestParam double width, @RequestParam double length, @RequestParam Optional<String> voucherCode) throws JsonMappingException, JsonProcessingException {
        Parcel parcel = new Parcel(weight, height, width, length);
        if (!voucherCode.isPresent()) voucherCode = null; 
        return deliveryCostService.calculateCostWithRules(parcel, voucherCode);
    }

    @GetMapping("/voucher")
    public Object getVoucher(@RequestParam String voucherCode) {
        String url = "https://mynt-exam.mocklab.io/voucher/" + voucherCode +"?key=apikey";
        RestTemplate restTemplate = new RestTemplate();

        Object voucher=restTemplate.getForObject(url,Object.class);
        return voucher;
    }
}