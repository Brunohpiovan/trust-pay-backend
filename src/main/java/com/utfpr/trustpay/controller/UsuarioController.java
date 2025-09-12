package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.ChaveUpdateDTO;
import com.utfpr.trustpay.model.dtos.UsuarioChavesDTO;
import com.utfpr.trustpay.model.dtos.UsuarioCreateDTO;
import com.utfpr.trustpay.model.dtos.UsuarioMenuResponseDto;
import com.utfpr.trustpay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @GetMapping(value = "/saldo/{id}")
    public ResponseEntity<?> findSaldoUsuario(@PathVariable Long id) {
        BigDecimal resposta = usuarioService.findSaldoUsuario(id);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/chaveAtual/{id}")
    public ResponseEntity<?> findChaveAtualPix(@PathVariable Long id) {
        String resposta = usuarioService.findChavePixAtual(id);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/chaves/{id}")
    public ResponseEntity<?> findChavesPix(@PathVariable Long id) {
        UsuarioChavesDTO resposta = usuarioService.findChavesPix(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping(value = "/updateChave")
    public ResponseEntity<?> updateChave(@RequestBody ChaveUpdateDTO chaveUpdateDTO) {
        usuarioService.updateChave(chaveUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/senha-transferencia/{id}")
    public ResponseEntity<Boolean> verificarSenhaTransferencia(@PathVariable Long id) {
        boolean existe = usuarioService.verificarSenhaTransferencia(id);
        return ResponseEntity.ok(existe);
    }

}
