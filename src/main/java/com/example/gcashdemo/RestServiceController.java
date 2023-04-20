package com.example.gcashdemo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestServiceController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<Object> getHello() {
       return new ResponseEntity<>("Sample", HttpStatus.OK);
    }


}