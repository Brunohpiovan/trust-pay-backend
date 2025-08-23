package com.utfpr.trustpay.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    private String nome;
    private String login;
    private String senha;
}
