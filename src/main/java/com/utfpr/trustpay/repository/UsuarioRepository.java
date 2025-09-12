package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.UsuarioChavesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<UserDetails> findByLogin(String login);
    Optional<UserDetails> findByCpf(String cpf);

    @Query("SELECT u.saldo FROM Usuario u WHERE u.id = :id")
    BigDecimal findSaldoById(@Param("id") Long id);

    @Query("SELECT u.chavePix FROM Usuario u WHERE u.id = :id")
    String findChavePixById(@Param("id") Long id);

    @Query("SELECT u.chavePix FROM Usuario u WHERE u.id = :id")
    String findChavesPixById(@Param("id") Long id);

    @Query("SELECT new com.utfpr.trustpay.model.dtos.UsuarioChavesDTO(u.cpf, u.login, u.celular) " +
            "FROM Usuario u WHERE u.id = :id")
    UsuarioChavesDTO findUsuarioChavesById(@Param("id") Long id);


    Optional<Usuario> findByChavePix(String chavePix);

    @Query("SELECT CASE WHEN u.senhaTransferencia IS NULL OR u.senhaTransferencia = '' THEN false ELSE true END " +
            "FROM Usuario u WHERE u.id = :id")
    boolean hasSenhaTransferencia(@Param("id") Long id);


}
