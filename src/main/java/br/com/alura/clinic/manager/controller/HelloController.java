package br.com.alura.clinic.manager.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @Secured("ROLE_ADMIN")
    public String olaMundo() {
        return "Hello World Spring!";
    }

}
