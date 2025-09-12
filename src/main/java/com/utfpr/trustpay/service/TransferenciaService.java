package com.utfpr.trustpay.service;

import com.utfpr.trustpay.model.Transferencia;
import com.utfpr.trustpay.model.Usuario;
import com.utfpr.trustpay.model.dtos.TransferenciaRequestDTO;
import com.utfpr.trustpay.model.dtos.TransferenciaResponseDTO;
import com.utfpr.trustpay.repository.TransferenciaRepository;
import com.utfpr.trustpay.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public TransferenciaResponseDTO transferencia(TransferenciaRequestDTO transferenciaRequestDTO){
        Usuario remetente = usuarioRepository.findById(transferenciaRequestDTO.getUserId()).orElseThrow(()->new RuntimeException("Usuario remetente nao encontrado"));
        if (!passwordEncoder.matches(transferenciaRequestDTO.getSenha(), remetente.getSenhaTransferencia())) {
            throw new RuntimeException("Senha incorreta!");
        }
        Usuario destinatario = usuarioRepository.findByChavePix(transferenciaRequestDTO.getChave()).orElseThrow(()-> new RuntimeException("Destinatário com a chave pix " +transferenciaRequestDTO.getChave()+ ",não encontrado."));
        BigDecimal valorTransferencia = transferenciaRequestDTO.getValor();
        if (remetente.getSaldo().compareTo(valorTransferencia) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transferência.");
        }
        remetente.setSaldo(remetente.getSaldo().subtract(valorTransferencia));
        destinatario.setSaldo(destinatario.getSaldo().add(valorTransferencia));
        usuarioRepository.save(remetente);
        usuarioRepository.save(destinatario);
        Transferencia transferencia = new Transferencia();
        transferencia.setRemetente(remetente);
        transferencia.setDestinatario(destinatario);
        transferencia.setValor(valorTransferencia);
        transferencia.setDataHora(LocalDateTime.now());
        transferenciaRepository.save(transferencia);
        return new TransferenciaResponseDTO(transferencia);
    }

    public Page<TransferenciaResponseDTO> findAllExtrato(Long userId, Pageable pageable){
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(()->new RuntimeException("Usuario nao encontrado"));
        return transferenciaRepository.findByRemetenteIdOrDestinatarioId(userId,userId,pageable);
    }
}
