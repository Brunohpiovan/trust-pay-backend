package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Transferencia;
import com.utfpr.trustpay.model.dtos.TransferenciaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {
    List<TransferenciaResponseDTO> findByRemetenteIdOrDestinatarioId(Long remetenteId, Long destinatarioId);

    Page<TransferenciaResponseDTO> findByRemetenteIdOrDestinatarioId(Long remetenteId, Long destinatarioId, Pageable pageable);
}
