package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.CartaoAllResponseDTO;
import com.utfpr.trustpay.model.dtos.CartaoRequestDTO;
import com.utfpr.trustpay.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CartaoRequestDTO cartaoRequestDTO) {
        cartaoService.create(cartaoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/bloqueio")
    public ResponseEntity<?> bloqueiaCartao(@RequestBody Long id) {
        String mensagem = cartaoService.bloqueiaCartao(id);
        return ResponseEntity.ok(mensagem);
    }

    @GetMapping
    public ResponseEntity<List<CartaoAllResponseDTO>> findAllCartao(@RequestParam Long userId
                                                    ) {
        return ResponseEntity.ok(cartaoService.findAllByUserId(userId));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deleteCartao(@PathVariable Long id
    ) {
        cartaoService.deletarCartao(id);
        return ResponseEntity.ok().build();
    }
}
