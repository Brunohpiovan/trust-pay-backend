package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.SolicitacaoEmprestimo;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.SolicitacaoEmprestimoRequestDTO;
import com.utfpr.trustpay.repository.SolicitacaoEmprestimoRepository;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SolicitacaoEmprestimoService {

    @Autowired
    private SolicitacaoEmprestimoRepository solicitacaoEmprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void solicitar(SolicitacaoEmprestimoRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getClienteId()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        SolicitacaoEmprestimo solicitacaoEmprestimo = new SolicitacaoEmprestimo();
        solicitacaoEmprestimo.setCliente(usuario);
        solicitacaoEmprestimo.setValor(dto.getValor());
        solicitacaoEmprestimo.setDataHora(LocalDateTime.now());
        solicitacaoEmprestimoRepository.save(solicitacaoEmprestimo);
    }
}
