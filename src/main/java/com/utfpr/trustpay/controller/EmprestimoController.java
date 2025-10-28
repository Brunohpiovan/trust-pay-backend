package com.utfpr.trustpay.controller;

import com.utfpr.trustpay.model.dtos.EmprestimoAllDTO;
import com.utfpr.trustpay.model.dtos.EmprestimoRequestDTO;
import com.utfpr.trustpay.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<?> solicitar(@RequestBody EmprestimoRequestDTO dto) {
        emprestimoService.solicitar(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/abertos")
    public ResponseEntity<List<EmprestimoAllDTO>> listarEmprestimosAbertos() {
        return ResponseEntity.ok(emprestimoService.findAllEmprestimoAberto());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.findById(id));
    }

    @PutMapping(value = "/aprovar/{id}")
    public ResponseEntity<?> aprovarEmprestimo(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
}
