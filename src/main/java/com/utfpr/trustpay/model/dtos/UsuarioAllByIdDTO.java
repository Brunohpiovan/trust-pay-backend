package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAllByIdDTO {

    private Long id;
    private String nome;
    private String login;
    private String cpf;
    private String celular;
    private UserRole cargo;
}
