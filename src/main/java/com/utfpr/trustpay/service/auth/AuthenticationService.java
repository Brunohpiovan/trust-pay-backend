package com.utfpr.trustpay.service.auth;

import com.utfpr.trustpay.infra.security.TokenService;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.AuthenticationDTO;
import com.utfpr.trustpay.model.dtos.LoginResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private HttpServletRequest request;

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
}
