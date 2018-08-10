package com.serge.abousaleh.anagram.resource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnagramController {
	
	Logger logger = LoggerFactory.getLogger(AnagramController.class);
	
	// inject via application.properties
	@Value("${anagram.message:test}")
	private String message = "Hello Anagram User";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		logger.debug("--- welcome");
		model.put("message", this.message);
		return "anagram";
	}

}
