package com.gdxh.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.weibo.api.User;
import org.springframework.social.weibo.api.Weibo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gdxh.web.services.FeedService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private final Weibo weibo;

	@Autowired
	FeedService feedService;

	@Autowired
	public HomeController(Weibo weibo) {
		this.weibo = weibo;
	}

	@ExceptionHandler(ExpiredAuthorizationException.class)
	public String handleExpiredToken() {
		return "redirect:/signout";
	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e) {
		e.printStackTrace();
	}

	@RequestMapping(value="/", method=GET)
	public ModelAndView home() {
		
		User profile = weibo.accountOperations().getUser();
		
		return new ModelAndView("profile", "profile", profile);
	}

}
