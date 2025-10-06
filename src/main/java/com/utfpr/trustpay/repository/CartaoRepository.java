package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Cartao;
import com.utfpr.trustpay.model.dtos.CartaoAllResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository  extends JpaRepository<Cartao,Long> {
    @Query("SELECT new com.utfpr.trustpay.model.dtos.CartaoAllResponseDTO(" +
            "c.numero, c.nomeTitular, c.validade, c.cvv, c.tipo, c.bloqueado) " +
            "FROM Cartao c WHERE c.usuario.id = :userId")
    Page<CartaoAllResponseDTO> findAllByUsuarioId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new com.utfpr.trustpay.model.dtos.CartaoAllResponseDTO(" +
            "c.numero, c.nomeTitular, c.validade, c.cvv, c.tipo, c.bloqueado) " +
            "FROM Cartao c WHERE c.usuario.id = :userId")
    List<CartaoAllResponseDTO> findAllByUsuarioId(@Param("userId") Long userId);


}
