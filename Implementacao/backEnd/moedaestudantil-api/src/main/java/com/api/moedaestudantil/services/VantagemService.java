package com.api.moedaestudantil.services;

import com.api.moedaestudantil.models.EmpresaModel;
import com.api.moedaestudantil.models.TransacaoModel;
import com.api.moedaestudantil.models.VantagemModel;
import com.api.moedaestudantil.models.UsuarioModel;
import com.api.moedaestudantil.repositories.EmpresaRepository;
import com.api.moedaestudantil.repositories.VantagemRepository;
import com.api.moedaestudantil.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VantagemService {

    final VantagemRepository vantagemRepository;
    final EmpresaRepository empresaRepository;

    public VantagemService(VantagemRepository vantagemRepository, EmpresaRepository empresaRepository) {
        this.vantagemRepository = vantagemRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public VantagemModel save(VantagemModel vantagemModel) {

        Optional<EmpresaModel> empresaOpt = empresaRepository.findById(vantagemModel.getEmpresa().getId());

        if (empresaOpt.isPresent()) {
            EmpresaModel empresa = empresaOpt.get();

            vantagemModel.setEmpresa(empresa);

            empresaRepository.save(empresa);

            Optional<VantagemModel> save = Optional.ofNullable(vantagemRepository.save(vantagemModel));
            return save.orElseGet(VantagemModel::new);


        } else {
            throw new RuntimeException("Empresa não encontrada");
        }
    }

    public Page<VantagemModel> findAll(Pageable pageable) {
        return vantagemRepository.findAll(pageable);
    }

    public Optional<VantagemModel> findById(UUID id) {
        return vantagemRepository.findById(id);
    }

    public List<VantagemModel> findByEmpresa(UUID empresa) {
        return vantagemRepository.findByEmpresa(empresa);
    }

    @Transactional
    public void delete(VantagemModel vantagemModel) {
        vantagemRepository.delete(vantagemModel);
    }

}