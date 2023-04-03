package com.gym.gymmembership.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
@Slf4j
public class SecurityController {

    @GetMapping("/")
    public String home(){
        return ("<h1>Welcome<h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User<h1>");
    }

    @GetMapping
    public String admin(){
        return ("<h1>Welcome Admin<h1>");
    }
}
