package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.UsuarioCreateDTO;
import com.utfpr.trustpay.model.dtos.UsuarioMenuResponseDto;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void create(UsuarioCreateDTO dto) {
        Usuario user = new Usuario();
        user.setNome(dto.getNome());
        user.setLogin(dto.getLogin());
        user.setSenha(encoder.encode(dto.getSenha()));
        usuarioRepository.save(user);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioMenuResponseDto findByUsuarioMenuId(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado"));

        if (!usuario.getLogin().equals(authenticatedUsername)) {
            throw new AccessDeniedException("Você não tem permissão para acessar este usuário.");
        }

        UsuarioMenuResponseDto resposta = new UsuarioMenuResponseDto(usuario);
        return resposta;
    }

    public BigDecimal findSaldoUsuario(Long id) {
        return usuarioRepository.findSaldoById(id);
    }
}
