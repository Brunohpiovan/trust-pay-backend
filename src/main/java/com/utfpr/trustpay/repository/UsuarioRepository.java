package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<UserDetails> findByLogin(String login);
    Optional<UserDetails> findByCpf(String cpf);

    @Query("SELECT u.saldo FROM Usuario u WHERE u.id = :id")
    BigDecimal findSaldoById(@Param("id") Long id);
}
