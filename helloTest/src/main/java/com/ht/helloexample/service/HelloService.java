package com.ht.helloexample.service;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
	
	public String hello() {
		return "Hello World";
	}
}
