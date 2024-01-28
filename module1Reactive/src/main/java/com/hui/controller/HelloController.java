package com.hui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eirk
 * @Description
 * @Date 2024/1/20 0:25
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "ok";
    }
    @RequestMapping("/hello2")
    public String hello2() {
        return "ok";
    }
    @RequestMapping("/hello3")
    public String hello3() {
        return "ok";
    }
    @RequestMapping("/hello4")
    public String hello4() {
        return "ok";
    }
}
