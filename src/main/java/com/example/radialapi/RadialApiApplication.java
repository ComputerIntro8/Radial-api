package com.example.radialapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import static com.example.radialapi.webcrawling.JsoupComponentLocalMain.getDataList;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RadialApiApplication {

    public static void main(String[] args) {

        getDataList();
        SpringApplication.run(RadialApiApplication.class, args);
    }

}
