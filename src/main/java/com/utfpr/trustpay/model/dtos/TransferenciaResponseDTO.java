package com.utfpr.trustpay.model.dtos;

import com.utfpr.trustpay.model.Transferencia;
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
public class TransferenciaResponseDTO {
    private String nomeRemetente;
    private String nomeDestinatario;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public TransferenciaResponseDTO(Transferencia transferencia){
        this.nomeRemetente = transferencia.getRemetente().getNome();
        this.nomeDestinatario = transferencia.getDestinatario().getNome();
        this.valor = transferencia.getValor();
        this.dataHora = transferencia.getDataHora();
    }
}
