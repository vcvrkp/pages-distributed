package org.dell.edu.kube.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeBusinessController {
    Logger loger = LoggerFactory.getLogger(WelcomeBusinessController.class);
    @Value("${welcome.message:Welcome to Kubernetes Business Application}")
    private String message;
    @GetMapping
    public String index(){
        loger.debug("Welcome to Kubernetes Business Application Message Generated");
        loger.info("Welcome to Kubernetes Business Application Message Generated");
        loger.warn("Welcome to Kubernetes Business Application Message Generated");
        loger.trace("Welcome to Kubernetes Business Application Message Generated");
        loger.error("Welcome to Kubernetes Business Application Message Generated");
        return message;
    }
}