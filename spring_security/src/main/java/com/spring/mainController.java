package com.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
	
	@RequestMapping("/admin/main")
	public void adminMain() {}
	
	@RequestMapping("/home/main")
	public void homeMain() {}
	
	@RequestMapping("/manager/main")
	public void managerMain() {}
	
	@RequestMapping("/member/main")
	public void memberMain() {}
}
