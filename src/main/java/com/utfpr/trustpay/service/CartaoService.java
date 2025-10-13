package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Cartao;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.CartaoAllResponseDTO;
import com.utfpr.trustpay.model.dtos.CartaoRequestDTO;
import com.utfpr.trustpay.repository.CartaoRepository;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CartaoAllResponseDTO> findAllByUserId(Long userId) {
        return cartaoRepository.findAllByUsuarioId(userId);
    }

    public String bloqueiaCartao(Long cartaoId){
        Cartao cartao = cartaoRepository.findById(cartaoId).orElseThrow(()->new RuntimeException("Cartao nao encontrado"));
        String mensagem;
        if(cartao.getBloqueado().equals(Boolean.TRUE)){
            cartao.setBloqueado(Boolean.FALSE);
            mensagem = "Cartão desbloqueado com sucesso!";
        }else{
            cartao.setBloqueado(Boolean.TRUE);
            mensagem = "Cartão bloqueado com sucesso!";
        }
        cartaoRepository.save(cartao);
        return mensagem;
    }

    public void deletarCartao(Long id){
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(()->new RuntimeException("Cartao nao encontrado"));
        cartaoRepository.delete(cartao);
    }

    public void update(CartaoRequestDTO dto,Long id){
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(()->new RuntimeException("Cartao nao encontrado"));
        cartaoRepository.save(dtoToCartao(cartao,dto));
    }

    private Cartao dtoToCartao(Cartao cartao,CartaoRequestDTO dto){
        cartao.setNumero(dto.getNumero());
        cartao.setNomeTitular(dto.getTitular());
        cartao.setValidade(dto.getValidade());
        cartao.setCvv(dto.getCvv());
        cartao.setTipo(dto.getTipo());
        return cartao;
    }
}
