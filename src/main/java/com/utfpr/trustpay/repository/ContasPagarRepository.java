package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.ContasPagar;
import com.utfpr.trustpay.model.dtos.ContasPagarResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContasPagarRepository  extends JpaRepository<ContasPagar,Long> {

    @Query("""
    SELECT new com.utfpr.trustpay.model.dtos.ContasPagarResponseDTO(
        c.id,
        c.valor,
        c.vencimento,
        c.numeroParcela,
        c.situacaoContas
    )
    FROM ContasPagar c
    WHERE c.cliente.id = :clienteId
      AND c.situacaoContas IN :situacoes
    ORDER BY c.vencimento DESC
""")
    List<ContasPagarResponseDTO> findContasByClienteIdAndSituacoes(
            @Param("clienteId") Long clienteId,
            @Param("situacoes") List<com.utfpr.trustpay.model.enums.SituacaoContas> situacoes
    );


}
