package com.utfpr.trustpay.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {
    private String nome;
    private String login;
    private String cpf;
    private String celular;
    private String senha;
    private String senha2;
}
