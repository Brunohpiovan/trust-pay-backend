package com.utfpr.trustpay.model.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaqueRequest {
    private Long userId;
    @Positive(message = "O valor do depósito deve ser maior que zero")
    private BigDecimal valor;
}
