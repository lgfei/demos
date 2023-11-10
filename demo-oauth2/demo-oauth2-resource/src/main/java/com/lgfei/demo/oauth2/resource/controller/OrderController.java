package com.lgfei.demo.oauth2.resource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@GetMapping("/query")
	public ResponseEntity<String> query() {
		return ResponseEntity.ok("order query " + System.currentTimeMillis());
	}
}
