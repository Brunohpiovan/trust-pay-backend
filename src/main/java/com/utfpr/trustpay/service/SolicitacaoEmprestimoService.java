package com.utfpr.trustpay.service;

import com.utfpr.trustpay.repository.SolicitacaoEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoEmprestimoService {

    @Autowired
    private SolicitacaoEmprestimoRepository solicitacaoEmprestimoRepository;
}
