package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<UserDetails> findByLogin(String login);
    Optional<UserDetails> findByCpf(String cpf);
}
