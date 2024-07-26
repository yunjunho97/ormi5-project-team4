package com.example.ormi5projectteam4.controller.thymeleaf_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping("/")
  public String main() {
    return "index";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @GetMapping("/register-success")
  public String registerSuccess() {
    return "register-success";
  }

  @GetMapping("/find-password")
  public String findPassword() {
    return "find-password";
  }
}
