package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.CartaoRequestDTO;
import com.utfpr.trustpay.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CartaoRequestDTO cartaoRequestDTO) {
        cartaoService.create(cartaoRequestDTO);
        return ResponseEntity.ok().build();
    }
}
