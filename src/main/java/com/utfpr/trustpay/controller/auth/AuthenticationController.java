package com.utfpr.trustpay.controller.auth;

import com.utfpr.trustpay.model.dtos.AuthenticationDTO;
import com.utfpr.trustpay.model.dtos.RegisterDTO;
import com.utfpr.trustpay.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok(service.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        service.register(data);
        return ResponseEntity.ok().build();
    }
}
