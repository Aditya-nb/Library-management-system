package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.entity.Request;
import com.auth.entity.Response;
import com.auth.service.JwtService;

@RestController
@CrossOrigin
//@RequestMapping("/")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public Response createJwtToken(@RequestBody Request request) throws Exception {
        return jwtService.createJwtToken(request);
    }
}