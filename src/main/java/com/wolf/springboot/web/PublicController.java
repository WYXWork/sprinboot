package com.wolf.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicController extends BaseController {

	@RequestMapping("/sys/index")
	public String index(ModelMap modelMap) {
		return "index";
	}

	@RequestMapping("/sys/login")
	public String login() {
		return "login";
	}

	@RequestMapping("{page}.htm")
	public String page(@PathVariable String page) {
		return page;
	}

}
