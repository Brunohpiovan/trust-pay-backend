package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Cartao;
import com.utfpr.trustpay.model.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasPagarRepository  extends JpaRepository<ContasPagar,Long> {
}
