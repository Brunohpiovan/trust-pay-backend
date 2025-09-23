package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.enums.TipoCartao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequestDTO {

    @NotBlank
    @NotNull
    private String numero;
    @NotBlank
    @NotNull
    private String titular;
    @NotBlank
    @NotNull
    private String validade;
    @NotBlank
    @NotNull
    private String cvv;

    @NotNull
    private TipoCartao tipo;

    @NotNull
    private Long userId;
}
