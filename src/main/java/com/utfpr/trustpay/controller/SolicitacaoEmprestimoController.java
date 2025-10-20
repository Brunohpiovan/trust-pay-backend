package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.service.SolicitacaoEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoEmprestimoController {

    @Autowired
    private SolicitacaoEmprestimoService solicitacaoEmprestimoService;
}
