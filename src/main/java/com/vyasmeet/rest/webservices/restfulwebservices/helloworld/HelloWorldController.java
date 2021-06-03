package com.vyasmeet.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

//Controller
@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/{name}")
    public HelloWorldBean helloWorldWithPath(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello %s!",name));
    }
}
