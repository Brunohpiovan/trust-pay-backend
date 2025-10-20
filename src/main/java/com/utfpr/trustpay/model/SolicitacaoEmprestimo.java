package com.utfpr.trustpay.model;

import jakarta.persistence.*;
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
@Entity
public class SolicitacaoEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false)
    private Usuario cliente;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Transient
    private BigDecimal porcentagemSucesso;


    @Transient
    public BigDecimal getPorcentagemSucesso() {
        if (cliente == null || cliente.getSaldo() == null || valor == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal saldo = cliente.getSaldo();
        BigDecimal cem = new BigDecimal("100");

        // Exemplo de regra:
        // Se o saldo for igual ou maior que o valor solicitado, 100%
        // Caso contrário, uma porcentagem proporcional
        BigDecimal porcentagem = saldo
                .divide(valor, 2, java.math.RoundingMode.HALF_UP)
                .multiply(cem);

        if (porcentagem.compareTo(cem) > 0) {
            porcentagem = cem;
        } else if (porcentagem.compareTo(BigDecimal.ZERO) < 0) {
            porcentagem = BigDecimal.ZERO;
        }

        return porcentagem;
    }

}
