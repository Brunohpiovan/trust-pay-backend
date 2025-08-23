package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.UsuarioCreateDTO;
import com.utfpr.trustpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UsuarioCreateDTO usuario) {
        usuarioService.create(usuario);
        return ResponseEntity.ok().build();

    }
}
