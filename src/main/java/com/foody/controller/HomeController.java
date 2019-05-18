package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController
{
    @RequestMapping(value = { "/index" }, method = { RequestMethod.GET })
    public String loadIndexPage() {
        return "index";
    }
    
    @RequestMapping(value = { "/*" } ,method = { RequestMethod.GET, RequestMethod.POST }) 
    public String getErrorPage() { 
    	return "forward:/static/general-error.html";
    }
}
