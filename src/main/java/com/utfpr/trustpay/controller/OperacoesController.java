package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.DepositoRequest;
import com.utfpr.trustpay.model.dtos.SaqueRequest;
import com.utfpr.trustpay.service.OperacoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    @Autowired
    private OperacoesService operacoesService;

    @PostMapping(value = "/deposito")
    public ResponseEntity<?> deposito(@RequestBody @Valid DepositoRequest depositoRequest) {
        operacoesService.depositar(depositoRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/saque")
    public ResponseEntity<?> saque(@RequestBody @Valid SaqueRequest saqueRequest) {
        operacoesService.sacar(saqueRequest);
        return ResponseEntity.ok().build();
    }
}
