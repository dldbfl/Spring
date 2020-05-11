package com.spring.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
@Controller
public class SummernoteActionController {

	
	@Resource(name="imgPath")
	String uploadPath;
}
