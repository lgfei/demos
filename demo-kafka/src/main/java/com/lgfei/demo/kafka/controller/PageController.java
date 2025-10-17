package com.lgfei.demo.kafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lgfei
 * @date 2025/10/17 17:35
 */
@Controller
@RequestMapping("")
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
