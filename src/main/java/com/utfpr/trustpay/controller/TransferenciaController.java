package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.TransferenciaRequestDTO;
import com.utfpr.trustpay.model.dtos.TransferenciaResponseDTO;
import com.utfpr.trustpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        TransferenciaResponseDTO dto = transferenciaService.transferencia(transferenciaRequestDTO);
        return ResponseEntity.ok(dto);
    }
}
