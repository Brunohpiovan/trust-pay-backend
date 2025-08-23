package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.UsuarioCreateDTO;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
