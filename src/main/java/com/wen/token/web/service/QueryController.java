package com.wen.token.web.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/query")
public class QueryController {
    @GetMapping("/test")
    public String test(String name){
        return   name;
    }
}
