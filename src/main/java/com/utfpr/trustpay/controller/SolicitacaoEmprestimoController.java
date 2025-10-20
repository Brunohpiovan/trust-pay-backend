package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.SolicitacaoEmprestimoRequestDTO;
import com.utfpr.trustpay.service.SolicitacaoEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoEmprestimoController {

    @Autowired
    private SolicitacaoEmprestimoService solicitacaoEmprestimoService;

    @PostMapping
    public ResponseEntity<?> solicitar(@RequestBody SolicitacaoEmprestimoRequestDTO dto) {
        solicitacaoEmprestimoService.solicitar(dto);
        return ResponseEntity.ok().build();
    }
}
