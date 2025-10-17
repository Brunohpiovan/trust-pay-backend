package com.utfpr.trustpay.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioByIdDTO {
    private String nome;
    private String login;
    private String cpf;
    private String celular;
}
