package com.movies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCOntroller {

    @RequestMapping("/hello")
    public  String sayHello(@RequestParam("name") String name){
        return "Hello " + name + "!";

    }

}
