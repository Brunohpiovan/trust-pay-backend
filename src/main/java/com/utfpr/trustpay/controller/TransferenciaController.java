package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.TransferenciaRequestDTO;
import com.utfpr.trustpay.model.dtos.TransferenciaResponseDTO;
import com.utfpr.trustpay.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        TransferenciaResponseDTO dto = transferenciaService.transferencia(transferenciaRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/extratos")
    public Page<TransferenciaResponseDTO> findAllExtrato(@RequestParam Long userId,
                                                         @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return transferenciaService.findAllExtrato(userId, pageable);
    }
}
