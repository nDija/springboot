package io.hullaert.springboot.controller;

import io.hullaert.springboot.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	DateService dateService;

	@Autowired
	public HelloController() {
		this.dateService = new DateService();
	}

	@RequestMapping("/")
	public String index() {
		String day = getWelcomeMsg();
		System.out.println(day);
		return day;
	}

	private String getWelcomeMsg() {
		String day = "<span>Greetings from Spring Boot! We are "
				+ dateService.getCurrentDay() + "</span><br><span>"
				+ dateService.getDateTime() + "</span>";
		return day;
	}
}