package com.example.gcashdemo.deliverycost;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.gcashdemo.deliverycost.DeliveryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class DeliveryCostService {

private static final double HEAVY_PARCEL_PRICE_COST = 20;
private static final double SMALL_PARCEL_PRICE_COST = 0.03;
private static final double MEDIUM_PARCEL_PRICE_COST = 0.04;
private static final double LARGE_PARCEL_PRICE_COST = 0.05;
    
    public DeliveryResponse calculateCostWithRules(Parcel parcel, Optional<String> voucherCode) throws JsonMappingException, JsonProcessingException {
        DeliveryResponse response;
        double volumeCost = parcel.getHeight() * parcel.getWidth() * parcel.getLength();
        Object voucher = new Object();
        double voucherDiscount = 0;
        double totalCost;
        if (voucherCode != null){
            String url = "https://mynt-exam.mocklab.io/voucher/" + voucherCode.get() +"?key=apikey";
 
            RestTemplate restTemplate = new RestTemplate();
     
           ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
  
           ObjectMapper objectMapper = new ObjectMapper();
           JsonNode jsonNode = objectMapper.readTree(forEntity.getBody());

            voucherDiscount = Double.parseDouble(jsonNode.get("discount").toString());

    
        }


        //PRIORITY 1
        if(parcel.getWeight() > 50 ){
         response = new DeliveryResponse("Reject","Weight exceeded 50KG", "N/A", 0, voucherCode,"0");
        }

        //PRIORITY 2
        else if ( parcel.getWeight() > 10 &&  parcel.getWeight() <= 50 ){
            totalCost = parcel.getWeight() * HEAVY_PARCEL_PRICE_COST;
            if(voucher != null)  totalCost = totalCost - (totalCost * voucherDiscount/100);
            response =new DeliveryResponse("Heavy Parcel", "Weight exceeded 10KG", "PHP 20 * Weight (kg)",  totalCost,voucherCode,String.valueOf(voucherDiscount));
        }


       //PRIORITY 3
        else if ( volumeCost < 1500){
            totalCost = volumeCost * SMALL_PARCEL_PRICE_COST;
            if(voucher != null)  totalCost = totalCost - (totalCost * voucherDiscount/100);
       
            response =new DeliveryResponse("Small Parcel", "Volume is less than 1500 cm3", "PHP 0.03 * Volume", totalCost,voucherCode,String.valueOf(voucherDiscount));
        }

        //PRIORITY 4
        else if (volumeCost < 2500 &&  volumeCost >=1500 ){
          
            totalCost = volumeCost * MEDIUM_PARCEL_PRICE_COST;
            if(voucher != null)  totalCost = totalCost - (totalCost * voucherDiscount/100);
            response =new DeliveryResponse("Medium Parcel", "Volume is less than 2500 cm3", "PHP 0.04 * Volume",  totalCost,voucherCode,String.valueOf(voucherDiscount));
        }

         //PRIORITY 5
        else{
            totalCost = volumeCost * LARGE_PARCEL_PRICE_COST;
            if(voucher != null)  totalCost = totalCost - (totalCost * voucherDiscount/100);
            response =new DeliveryResponse("Large Parcel", "Last priority , no condition", "PHP 0.05 * Volume",  totalCost,voucherCode,String.valueOf(voucherDiscount) );
        }

       
        
        return response;
    }
}