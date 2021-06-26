package org.james.anotherdemo.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyContoller {

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }
}
