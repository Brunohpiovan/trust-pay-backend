package com.utfpr.trustpay.service.auth;

import com.utfpr.trustpay.infra.security.TokenService;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.AuthenticationDTO;
import com.utfpr.trustpay.model.dtos.LoginResponseDTO;
import com.utfpr.trustpay.model.dtos.RegisterDTO;
import com.utfpr.trustpay.model.enums.UserRole;
import com.utfpr.trustpay.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public LoginResponseDTO login(AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var usuario = (Usuario) auth.getPrincipal();
            var token = tokenService.generateToken(usuario);

            return new LoginResponseDTO(token);
        } catch (InternalAuthenticationServiceException ex) {
            throw new InternalAuthenticationServiceException("Erro na autenticação");
        }
    }

    public void register(RegisterDTO data) {
       if(!data.getPassword().equals(data.getConfirmPassword())){
           throw new RuntimeException("As senhas nao coincidem");
       }
       Usuario register = new Usuario(data);
       validaPorCpfeEmail(register);
       register.setSenha(encoder.encode(data.getPassword()));
       register.setCargo(UserRole.CLIENTE);
       register.setSaldo(BigDecimal.ZERO);
       usuarioRepository.save(register);
    }

    private void validaPorCpfeEmail(Usuario objDTO) {
        Optional<UserDetails> usuarioComMesmoLogin = usuarioRepository.findByLogin(objDTO.getLogin().toLowerCase());
        if (usuarioComMesmoLogin.isPresent()) {
            Usuario usuario = (Usuario) usuarioComMesmoLogin.get();
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
        }

        Optional<UserDetails> usuarioComMesmoCpf = usuarioRepository.findByCpf(objDTO.getCpf());
        if (usuarioComMesmoCpf.isPresent()) {
            Usuario usuario = (Usuario) usuarioComMesmoCpf.get();
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }
    }
}
