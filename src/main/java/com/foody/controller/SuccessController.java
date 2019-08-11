package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuccessController
{
  
    @RequestMapping(value = { "/CreateFail" }, method = { RequestMethod.POST })
    public String showFailPage() {
        return "CreateFail";
    }
}