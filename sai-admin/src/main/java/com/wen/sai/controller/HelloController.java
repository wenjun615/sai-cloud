package com.wen.sai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * HelloController
 * </p>
 *
 * @author wenjun
 * @since 2021-06-30
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
