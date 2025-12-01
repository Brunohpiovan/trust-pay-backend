package com.utfpr.trustpay.model;

import com.utfpr.trustpay.model.enums.SituacaoContas;
import com.utfpr.trustpay.model.enums.SituacaoEmprestimo;
import jakarta.persistence.*;
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
@Entity
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "remetente_id", nullable = false)
    private Usuario cliente;

    @Column(name = "vencimento", nullable = false)
    private LocalDate vencimento;

    @Column(name = "numero_parcela", nullable = false)
    private Integer numeroParcela;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_contas",nullable = false)
    private SituacaoContas situacaoContas;
}
