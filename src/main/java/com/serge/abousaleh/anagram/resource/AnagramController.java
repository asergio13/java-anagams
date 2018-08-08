package com.serge.abousaleh.anagram.resource;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnagramController {
	
	// inject via application.properties
	@Value("${anagram.message:test}")
	private String message = "Hello Anagram User";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "anagram";
	}

}
