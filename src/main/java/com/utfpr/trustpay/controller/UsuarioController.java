package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.UsuarioCreateDTO;
import com.utfpr.trustpay.model.dtos.UsuarioMenuResponseDto;
import com.utfpr.trustpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping(value = "/menu/{id}")
    public ResponseEntity<?> findByUsuarioMenuId(@PathVariable Long id) {
        UsuarioMenuResponseDto resposta = usuarioService.findByUsuarioMenuId(id);
        return ResponseEntity.ok(resposta);
    }

}
