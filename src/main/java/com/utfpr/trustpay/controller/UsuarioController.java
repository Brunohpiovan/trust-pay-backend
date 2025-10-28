package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.*;
import com.utfpr.trustpay.service.UsuarioService;
import jakarta.validation.Valid;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping(value = "/menu/{id}")
    public ResponseEntity<?> findByUsuarioMenuId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findByUsuarioMenuId(id));
    }

    @GetMapping(value = "/saldo/{id}")
    public ResponseEntity<?> findSaldoUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findSaldoUsuario(id));
    }

    @GetMapping(value = "/chaveAtual/{id}")
    public ResponseEntity<?> findChaveAtualPix(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findChavePixAtual(id));
    }

    @GetMapping(value = "/chaves/{id}")
    public ResponseEntity<?> findChavesPix(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findChavesPix(id));
    }

    @PutMapping(value = "/updateChave")
    public ResponseEntity<?> updateChave(@RequestBody ChaveUpdateDTO chaveUpdateDTO) {
        usuarioService.updateChave(chaveUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/senha-transferencia/{id}")
    public ResponseEntity<Boolean> verificarSenhaTransferencia(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.verificarSenhaTransferencia(id));
    }

    @PostMapping("/senha-transferencia")
    public ResponseEntity<?> salvarSenhaTransferencia(@RequestBody @Valid UsuarioSenhaTransacaoDTO dto) {
        usuarioService.salvarSenhaTransferencia(dto);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody UsuarioUpdateDTO usuarioRequest) {
        usuarioService.update(id, usuarioRequest);
        return ResponseEntity.ok().build();

    }

}
