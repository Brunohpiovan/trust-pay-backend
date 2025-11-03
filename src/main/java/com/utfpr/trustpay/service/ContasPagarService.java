package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.ContasPagar;
import com.utfpr.trustpay.model.Usuario;
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

    @Autowired
    private UsuarioService usuarioService;

    public List<ContasPagarResponseDTO> findMyContasAbertasAndAtrasadas(Long id) {
        List<SituacaoContas> situacoes = List.of(
                SituacaoContas.ABERTA,
                SituacaoContas.ATRASADA
        );
        return contasPagarRepository.findContasByClienteIdAndSituacoes(id, situacoes);
    }

    public ContasPagarResponseDTO buscarContaPorId(Long id) {
        return contasPagarRepository.findContaDtoById(id);
    }

    public void pagarConta(Long id){
        ContasPagar conta = contasPagarRepository.findById(id).orElseThrow(()->new RuntimeException("Conta nao encontrada"));
        if (conta.getCliente().getSaldo().compareTo(conta.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar o pagamento desta parcela.");
        }
        conta.setSituacaoContas(SituacaoContas.PAGA);
        conta.getCliente().setSaldo(conta.getCliente().getSaldo().subtract(conta.getValor()));
        contasPagarRepository.save(conta);
    }

}
