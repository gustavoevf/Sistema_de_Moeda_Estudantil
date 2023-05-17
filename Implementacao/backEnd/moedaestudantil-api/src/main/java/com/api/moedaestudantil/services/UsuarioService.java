package com.api.moedaestudantil.services;

import com.api.moedaestudantil.dtos.LoginDto;
import com.api.moedaestudantil.models.UsuarioModel;
import com.api.moedaestudantil.repositories.TransacaoRepository;
import com.api.moedaestudantil.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UsuarioService {

    final UsuarioRepository usuarioRepository;

    final TransacaoRepository transacaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, TransacaoRepository transacaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }


    public Page<UsuarioModel> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void delete(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
    }

    public UsuarioModel login(LoginDto loginDto) {
        UsuarioModel user = usuarioRepository.findByLogin(loginDto.getLogin());
        if(user == null) {
            throw new RuntimeException("Usuário nao existe.");
        }
        if(!user.getSenha().equals(loginDto.getSenha())){
            throw new RuntimeException("Senha incorreta!");
        }

        return user;
    }

}