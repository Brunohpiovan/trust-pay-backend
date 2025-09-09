package com.utfpr.trustpay.model;

import com.utfpr.trustpay.model.dtos.RegisterDTO;
import com.utfpr.trustpay.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Email
    @Column(unique = true)
    private String login;

    @NotBlank(message = "Informe um cpf")
    @Column(name = "cpf",nullable = false, unique = true)
    @Size(max = 14, message = "O cpf deve ter no maximo 14 caracteres")
    private String cpf;

    @NotBlank(message = "Informe uma senha")
    @Column(name = "senha",nullable = false)
    @Size(min = 8, max = 255, message = "O nome deve ter entre 8 e 255 caracteres")
    private String senha;

    @NotBlank(message = "Informe um numero de celular")
    @Column(name = "celular",nullable = false)
    @Size(max = 15, message = "O telefone deve ter no maximo 15 caracteres")
    private String celular;

    @Column(name = "cargo",nullable = false)
    private UserRole cargo;

    @Column(name = "saldo",nullable = false)
    public BigDecimal saldo;

    public Usuario(RegisterDTO dto){
        this.nome = dto.getUsername();
        this.cpf = dto.getCpf();
        this.celular = dto.getCelular();
        this.login = dto.getEmail();
        this.senha = dto.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (this.cargo == UserRole.FUNCIONARIO) {
            authorities.add(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        } else{
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
