package com.utfpr.trustpay.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoAllDTO {

    private String nomeCliente;
    private LocalDateTime dataHora;
    private BigDecimal valor;
    private Integer numeroParcelas;
    private BigDecimal porcentagemSucesso;
}
