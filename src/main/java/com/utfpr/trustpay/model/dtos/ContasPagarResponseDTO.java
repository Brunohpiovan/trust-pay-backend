package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.enums.SituacaoContas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagarResponseDTO {
    private Long id;
    private BigDecimal valor;
    private LocalDate vencimento;
    private Integer numeroParcela;
    private SituacaoContas situacaoContas;
}
