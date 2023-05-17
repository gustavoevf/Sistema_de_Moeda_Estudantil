package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.TransacaoModel;
import com.api.moedaestudantil.models.UsuarioModel;
import com.api.moedaestudantil.repositories.TransacaoRepository;
import com.api.moedaestudantil.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {

    final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public TransacaoModel save(TransacaoModel transacaoModel) {
        Optional<UsuarioModel> remetenteOpt = usuarioRepository.findById(transacaoModel.getRemetente().getId());
        Optional<UsuarioModel> destinatarioOpt = usuarioRepository.findById(transacaoModel.getDestinatario().getId());

        if (remetenteOpt.isPresent() && destinatarioOpt.isPresent()) {
            UsuarioModel remetente = remetenteOpt.get();
            UsuarioModel destinatario = destinatarioOpt.get();

            if (remetente.getValorCarteira() < transacaoModel.getValor()) {
                throw new RuntimeException("Saldo insuficiente");
            }

            transacaoModel.setRemetente(remetente);
            transacaoModel.setDestinatario(destinatario);

            double valor = transacaoModel.getValor();
            remetente.setValorCarteira(remetente.getValorCarteira() - valor);
            destinatario.setValorCarteira(destinatario.getValorCarteira() + valor);

            usuarioRepository.save(remetente);
            usuarioRepository.save(destinatario);

            Optional<TransacaoModel> save = Optional.ofNullable(transacaoRepository.save(transacaoModel));
            return save.orElseGet(TransacaoModel::new);


        } else {
            throw new RuntimeException("Remetente ou destinatário não encontrado");
        }
    }

    public Page<TransacaoModel> findAll(Pageable pageable) {
        return transacaoRepository.findAll(pageable);
    }

    public Optional<TransacaoModel> findById(UUID id) {
        return transacaoRepository.findById(id);
    }

    public List<TransacaoModel> findByRemetente(UUID remetente) {
        return transacaoRepository.findByRemetente(remetente);
    }

    public List<TransacaoModel> findByDestinatario(UUID destinatario) {
        return transacaoRepository.findByDestinatario(destinatario);
    }

    @Transactional
    public void delete(TransacaoModel transacaoModel) {
        transacaoRepository.delete(transacaoModel);
    }

}