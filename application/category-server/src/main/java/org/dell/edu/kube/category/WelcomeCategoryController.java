package org.dell.edu.kube.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeCategoryController {
    Logger loger = LoggerFactory.getLogger(WelcomeCategoryController.class);
    @Value("${welcome.message:Welcome to Kubernetes Category Application}")
    private String message;
    @GetMapping
    public String index(){
        loger.debug("Welcome Message Generated");

        return message;
    }
}
