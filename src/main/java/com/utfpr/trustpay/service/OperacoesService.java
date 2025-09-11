package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.DepositoRequest;
import com.utfpr.trustpay.model.dtos.SaqueRequest;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperacoesService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void depositar(DepositoRequest depositoRequest){
        Usuario usuario = usuarioRepository.findById(depositoRequest.getUserId()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        usuario.setSaldo(usuario.getSaldo().add(depositoRequest.getValor()));
        usuarioRepository.save(usuario);
    }

    public void sacar(SaqueRequest saqueRequest){
        Usuario usuario = usuarioRepository.findById(saqueRequest.getUserId()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        if (usuario.getSaldo().compareTo(saqueRequest.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar o saque.");
        }
        usuario.setSaldo(usuario.getSaldo().subtract(saqueRequest.getValor()));
        usuarioRepository.save(usuario);
    }
}
