package com.javatechie.crud.example.controller;

import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class AnotherController {
    private String name;

    public void updateUser() {
        System.out.println("New world");
    }

    public void lambdas() {
        // Remove redundant types
        Function<Function, Function> f3 = function -> {
            return function.compose(function);
        };
    }

}
