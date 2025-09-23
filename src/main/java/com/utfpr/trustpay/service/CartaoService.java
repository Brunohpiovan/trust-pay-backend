package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Cartao;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.CartaoRequestDTO;
import com.utfpr.trustpay.repository.CartaoRepository;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void create(CartaoRequestDTO cartaoRequestDTO){
        Usuario usuario = usuarioRepository.findById(cartaoRequestDTO.getUserId()).orElseThrow(()->new RuntimeException("Usuario nao encontrado"));
        Cartao cartao = criaCartao(cartaoRequestDTO,usuario);
        cartaoRepository.save(cartao);
    }
    private Cartao criaCartao(CartaoRequestDTO dto,Usuario usuario){
        Cartao cartao = new Cartao();
        cartao.setBloqueado(Boolean.FALSE);
        cartao.setNumero(dto.getNumero());
        cartao.setNomeTitular(dto.getTitular());
        cartao.setUsuario(usuario);
        cartao.setTipo(dto.getTipo());
        cartao.setCvv(dto.getCvv());
        cartao.setValidade(dto.getValidade());
        return cartao;
    }
}
