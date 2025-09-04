package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioMenuResponseDto {

    private String nome;
    private String login;

    public UsuarioMenuResponseDto(Usuario usuario){
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();

    }
}
