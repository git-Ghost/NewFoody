package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuccessController
{
    @RequestMapping(value = { "/CreateDone" }, method = { RequestMethod.GET })
    public String showDonePage() {
        return "CreateDone";
    }
    
    @RequestMapping(value = { "/CreateFail" }, method = { RequestMethod.GET })
    public String showFailPage() {
        return "CreateFail";
    }
}