package com.pi3.scorewizard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/index")
	public String login() {
		return "index";
	}
	
	@RequestMapping("/indexerror")
	public String loginerror() {
		return "indexerror";
	}
	
}