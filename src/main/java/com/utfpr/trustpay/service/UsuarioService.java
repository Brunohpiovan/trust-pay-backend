package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.*;
import com.utfpr.trustpay.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public UsuarioByIdDTO findById(Long id){
        UsuarioByIdDTO usuario = usuarioRepository.findUsuarioById(id).orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado"));
        return usuario;
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

    public String findChavePixAtual(Long id){
        return usuarioRepository.findChavePixById(id);
    }

    public UsuarioChavesDTO findChavesPix(Long id){
        return usuarioRepository.findUsuarioChavesById(id);
    }

    public void updateChave(ChaveUpdateDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getId()).orElseThrow(()->new RuntimeException("Usuario nao encontrado"));
        usuario.setChavePix(dto.getChave());
        usuarioRepository.save(usuario);
    }

    public boolean verificarSenhaTransferencia(Long userId) {
        return usuarioRepository.hasSenhaTransferencia(userId);
    }

    @Transactional
    public void salvarSenhaTransferencia(UsuarioSenhaTransacaoDTO dto) {
        if(!dto.getSenha().equals(dto.getSenhaConfirm())){
            throw new RuntimeException("As senhas nao conferem");
        }
        Usuario usuario = usuarioRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String hashedSenha = encoder.encode(dto.getSenha());
        usuario.setSenhaTransferencia(hashedSenha);

        usuarioRepository.save(usuario);
    }

    public void update(Long id, UsuarioUpdateDTO dto){
        Usuario usuarioDoBanco = usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario nao encontrado"));
        validatePassword(dto);
        verificarAlteracaoCpfOuLogin(dto, usuarioDoBanco, id);
        Usuario newUser  = preencheUsuarioEntity(dto, usuarioDoBanco);
        usuarioRepository.save(newUser );
    }


    private Usuario preencheUsuarioEntity(UsuarioUpdateDTO usuarioRequest, Usuario usuario) {
        usuario.setNome(usuarioRequest.getNome());
        usuario.setLogin(usuarioRequest.getLogin().toLowerCase());
        if(usuarioRequest.getSenha()!=null){
            usuario.setSenha(encoder.encode(usuarioRequest.getSenha()));
        }
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setCelular(usuarioRequest.getCelular());
        return usuario;
    }

    private void validatePassword(UsuarioUpdateDTO dto) {
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            if (!dto.getSenha().equals(dto.getSenha2())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas não coincidem");
            }
        }
    }

    private void verificarAlteracaoCpfOuLogin(UsuarioUpdateDTO dto, Usuario usuarioExistente, Long id) {
        if (!usuarioExistente.getCpf().equals(dto.getCpf())
                || !usuarioExistente.getLogin().equals(dto.getLogin())) {
            this.validarDuplicidadeCpfELogin(dto, id);
        }
    }

    private void validarDuplicidadeCpfELogin(UsuarioUpdateDTO dto, Long idAtual) {
        Optional<UserDetails> usuarioMesmoLogin = usuarioRepository.findByLogin(dto.getLogin().toLowerCase());
        if (usuarioMesmoLogin.isPresent()) {
            Usuario usuario = (Usuario) usuarioMesmoLogin.get();
            if (!usuario.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
            }
        }

        Optional<UserDetails> usuarioMesmoCpf = usuarioRepository.findByCpf(dto.getCpf());
        if (usuarioMesmoCpf.isPresent()) {
            Usuario usuario = (Usuario) usuarioMesmoCpf.get();
            if (!usuario.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
            }
        }
    }

}
