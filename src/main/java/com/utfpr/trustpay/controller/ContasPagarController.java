package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.ContasPagarResponseDTO;
import com.utfpr.trustpay.service.ContasPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas-pagar")
public class ContasPagarController {

    @Autowired
    private ContasPagarService contasPagarService;

    @GetMapping("/minhas/{clienteId}")
    public ResponseEntity<List<ContasPagarResponseDTO>> getMinhasContas(
            @PathVariable Long clienteId) {
        return ResponseEntity.ok(contasPagarService.findMyContasAbertasAndAtrasadas(clienteId));
    }
}
