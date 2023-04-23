package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.repositories.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public AlunoModel save(AlunoModel alunoModel) {
        return alunoRepository.save(alunoModel);
    }


    public Page<AlunoModel> findAll(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    public Optional<AlunoModel> findById(UUID id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public void delete(AlunoModel alunoModel) {
        alunoRepository.delete(alunoModel);
    }
}