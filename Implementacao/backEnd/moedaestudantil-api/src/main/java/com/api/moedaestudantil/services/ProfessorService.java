package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.ProfessorModel;
import com.api.moedaestudantil.repositories.ProfessorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public ProfessorModel save(ProfessorModel professorModel) {
        return professorRepository.save(professorModel);
    }


    public Page<ProfessorModel> findAll(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }

    public Optional<ProfessorModel> findById(UUID id) {
        return professorRepository.findById(id);
    }

    @Transactional
    public void delete(ProfessorModel professorModel) {
        professorRepository.delete(professorModel);
    }
}