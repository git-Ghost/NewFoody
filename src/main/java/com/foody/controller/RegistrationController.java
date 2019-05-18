package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController
{
    @RequestMapping(value = { "/register" }, method = { RequestMethod.GET })
    public String showRegistrationPage() {
        System.out.println("Showing View");
        return "register";
    }
    
}