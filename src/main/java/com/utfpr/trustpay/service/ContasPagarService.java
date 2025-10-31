package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.dtos.ContasPagarResponseDTO;
import com.utfpr.trustpay.model.enums.SituacaoContas;
import com.utfpr.trustpay.repository.ContasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasPagarService {

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    public List<ContasPagarResponseDTO> findMyContasAbertasAndAtrasadas(Long id) {
        List<SituacaoContas> situacoes = List.of(
                SituacaoContas.ABERTA,
                SituacaoContas.ATRASADA
        );
        return contasPagarRepository.findContasByClienteIdAndSituacoes(id, situacoes);
    }

}
