package com.api.moedaestudantil.controllers;

import com.api.moedaestudantil.dtos.AlunoDto;
import com.api.moedaestudantil.models.AlunoModel;
import com.api.moedaestudantil.services.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
