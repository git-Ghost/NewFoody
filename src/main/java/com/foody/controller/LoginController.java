package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
    @RequestMapping(value = { "/login" }, method = {RequestMethod.GET} )
    public String printHello(final ModelMap model) {
        return "login";
    }
    
    @RequestMapping(value = { "/loginFailed" } ,method = {RequestMethod.GET}) 
    public String getErrorPage() { 
    	return "forward:/static/loginFailed.html";
    }
}