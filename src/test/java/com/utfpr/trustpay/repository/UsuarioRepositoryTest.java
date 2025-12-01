package com.utfpr.trustpay.repository;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.RegisterDTO;
import com.utfpr.trustpay.model.enums.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager entityManager;

    private Usuario createUser(RegisterDTO dto){
        Usuario usuario = new Usuario(dto);
        usuario.setChavePix(dto.getEmail());
        usuario.setCargo(UserRole.CLIENTE);
        usuario.setSaldo(BigDecimal.ZERO);

        this.entityManager.persist(usuario);
        return usuario;
    }


    @Test
    @DisplayName("Deve retornar o usuario com sucesso do DB")
    void findByLoginSuccess() {
        String login = "bruno@gmail.com";
        RegisterDTO dto = new RegisterDTO("Bruno",login,"14495802976","43999677890","12345aaa","12345aaa");
        this.createUser(dto);

        Optional<UserDetails> result = this.usuarioRepository.findByLogin(login);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Nao deve retornar nenhum usuario do DB")
    void findByLoginFailed() {
        String login = "bruno@gmail.com";

        Optional<UserDetails> result = this.usuarioRepository.findByLogin(login);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByCpf() {
    }

    @Test
    void findSaldoById() {
    }

    @Test
    void findChavePixById() {
    }

    @Test
    void findChavesPixById() {
    }

    @Test
    void findUsuarioChavesById() {
    }

    @Test
    void findByChavePix() {
    }

    @Test
    void hasSenhaTransferencia() {
    }

    @Test
    void findUsuarioById() {
    }

    @Test
    void findAllUsuariosDTO() {
    }

    @Test
    void findUsuarioAllById() {
    }
}