package com.utfpr.trustpay.model.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSenhaTransacaoDTO {

    private Long userId;
    @Size(min = 6, max = 6, message = "A senha ter entre 6 e 6 caracteres")
    private String senha;
    @Size(min = 6, max = 6, message = "A senhaConfirm ter entre 6 e 6 caracteres")
    private String senhaConfirm;
}
