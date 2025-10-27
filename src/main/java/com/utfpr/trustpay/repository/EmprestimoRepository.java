package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Emprestimo;
import com.utfpr.trustpay.model.dtos.EmprestimoAllDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    @Query("""
    SELECT e
    FROM Emprestimo e
    WHERE e.situacaoEmprestimo = com.utfpr.trustpay.model.enums.SituacaoEmprestimo.ABERTO
    """)
    List<Emprestimo> findAllEmprestimoAberto();


}
