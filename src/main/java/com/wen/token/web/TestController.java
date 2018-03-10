package com.wen.token.web;

import com.wen.token.service.TokenService;
import com.wen.token.util.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @Autowired
    TokenService tokenService;
    @PostMapping("/login")
    public DataResponse<String> login(String userName, String password){
        return    tokenService.login(userName,password);
    }

}
