package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.enums.TipoCartao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoAllResponseDTO {

    private String numero;
    private String nomeTitular;
    private String validade;
    private String cvv;
    private TipoCartao tipo;
    private Boolean bloqueado;
}
