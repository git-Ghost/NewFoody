package com.foody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
	
	@RequestMapping(value = { "/myProfile" }, method = { RequestMethod.GET })
	public String showProfile() {
		return "myProfile";
	}
    
	@RequestMapping(value = { "/updatePwd" }, method = { RequestMethod.POST})
	public String updatePwd() {
		return "updatePwd";
	}
	
	@RequestMapping(value = { "/updateAdd" }, method = { RequestMethod.POST})
	public String updateAdd() {
		return "updateAdd";
	}
}
