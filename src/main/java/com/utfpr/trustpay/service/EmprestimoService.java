package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Emprestimo;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.EmprestimoAllDTO;
import com.utfpr.trustpay.model.dtos.EmprestimoByIdDto;
import com.utfpr.trustpay.model.dtos.EmprestimoRequestDTO;
import com.utfpr.trustpay.model.enums.SituacaoEmprestimo;
import com.utfpr.trustpay.repository.EmprestimoRepository;
import com.utfpr.trustpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void solicitar(EmprestimoRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getClienteId()).orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(usuario);
        emprestimo.setValor(dto.getValor());
        emprestimo.setDataHora(LocalDateTime.now());
        emprestimo.setJuros(verificaJuros(dto.getNumeroParcelas()));
        emprestimo.setNumeroParcelas(dto.getNumeroParcelas());
        emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.ABERTO);
        emprestimoRepository.save(emprestimo);
    }

    private Double verificaJuros(Integer numeroParcelas) {
        if (numeroParcelas == null || numeroParcelas < 1 || numeroParcelas > 24) {
            throw new IllegalArgumentException("Número de parcelas inválido");
        }

        double juros = 3.0 + (numeroParcelas - 1);

        return juros;
    }

    public List<EmprestimoAllDTO> findAllEmprestimoAberto() {
        return emprestimoRepository.findAllEmprestimoAberto()
                .stream()
                .map(e -> new EmprestimoAllDTO(
                        e.getId(),
                        e.getCliente().getNome(),
                        e.getDataHora(),
                        e.getValor(),
                        e.getNumeroParcelas(),
                        e.getPorcentagemSucesso()
                ))
                .toList();
    }

    public EmprestimoByIdDto findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + id));

        return new EmprestimoByIdDto(
                emprestimo.getCliente().getNome(),
                emprestimo.getCliente().getCpf(),
                emprestimo.getDataHora(),
                emprestimo.getValor(),
                emprestimo.getJuros(),
                emprestimo.getNumeroParcelas(),
                emprestimo.getPorcentagemSucesso()
        );
    }


}
