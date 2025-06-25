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

import com.api.moedaestudantil.dtos.AlunoDto;
import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.services.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAluno(@RequestBody @Valid AlunoDto alunoDto){
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.save(alunoModel));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoModel>> getAllAlunos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAluno(@PathVariable(value = "id") UUID id){
        Optional<AlunoModel> alunoModelOptional = alunoService.findById(id);
        if (!alunoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable(value = "id") UUID id){
        Optional<AlunoModel> alunoModelOptional = alunoService.findById(id);
        if (!alunoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found.");
        }
        alunoService.delete(alunoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid AlunoDto alunoDto){
        Optional<AlunoModel> alunoModelOptional = alunoService.findById(id);
        if (!alunoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno not found.");
        }
        var alunoModel = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoModel);
        alunoModel.setId(alunoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.save(alunoModel));
    }



}
