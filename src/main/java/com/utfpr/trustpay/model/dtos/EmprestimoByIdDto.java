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
public class EmprestimoByIdDto {

    private String nomeCliente;
    private String cpfCliente;
    private LocalDateTime dataHora;
    private BigDecimal valorSolicitado;
    private Double juros;
    private Integer numeroParcelas;
    private BigDecimal porcentagemSucesso;
}
