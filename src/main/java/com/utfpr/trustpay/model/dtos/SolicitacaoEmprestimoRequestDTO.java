package com.utfpr.trustpay.model.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoEmprestimoRequestDTO {
    private Long clienteId;
    private BigDecimal valor;
}
