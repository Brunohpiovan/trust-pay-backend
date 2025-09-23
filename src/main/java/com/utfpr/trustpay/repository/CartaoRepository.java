package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository  extends JpaRepository<Cartao,Long> {
}
