package com.utfpr.trustpay.model;

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
@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, length = 100)
    private String nomeTitular;

    @Column(name = "validade", nullable = false, length = 5)
    private String validade;

    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoCartao tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private Boolean bloqueado;
}
