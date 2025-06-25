package com.api.moedaestudantil.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.moedaestudantil.dtos.EmpresaDto;
import com.api.moedaestudantil.models.EmpresaModel;
import com.api.moedaestudantil.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEmpresa(@RequestBody @Valid EmpresaDto empresaDto){
        var empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(empresaDto, empresaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresaModel));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaModel>> getAllEmpresas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEmpresa(@PathVariable(value = "id") UUID id){
        Optional<EmpresaModel> empresaModelOptional = empresaService.findById(id);
        if (!empresaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(empresaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable(value = "id") UUID id){
        Optional<EmpresaModel> empresaModelOptional = empresaService.findById(id);
        if (!empresaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa not found.");
        }
        empresaService.delete(empresaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Empresa deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid EmpresaDto empresaDto){
        Optional<EmpresaModel> empresaModelOptional = empresaService.findById(id);
        if (!empresaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa not found.");
        }
        var empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(empresaDto, empresaModel);
        empresaModel.setId(empresaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(empresaService.save(empresaModel));
    }



}
