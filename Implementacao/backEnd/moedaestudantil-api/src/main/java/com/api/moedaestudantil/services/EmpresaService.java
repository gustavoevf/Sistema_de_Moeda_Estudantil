package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.EmpresaModel;
import com.api.moedaestudantil.repositories.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpresaService {

    final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public EmpresaModel save(EmpresaModel empresaModel) {
        empresaModel.setTipo("Empresa");
        return empresaRepository.save(empresaModel);
    }


    public Page<EmpresaModel> findAll(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    public Optional<EmpresaModel> findById(UUID id) {
        return empresaRepository.findById(id);
    }

    @Transactional
    public void delete(EmpresaModel empresaModel) {
        empresaRepository.delete(empresaModel);
    }
}