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

import com.api.moedaestudantil.dtos.ProfessorDto;
import com.api.moedaestudantil.models.ProfessorModel;
import com.api.moedaestudantil.services.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProfessor(@RequestBody @Valid ProfessorDto professorDto){
        var professorModel = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProfessorModel>> getAllProfessors(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProfessor(@PathVariable(value = "id") UUID id){
        Optional<ProfessorModel> professorModelOptional = professorService.findById(id);
        if (!professorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professorModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable(value = "id") UUID id){
        Optional<ProfessorModel> professorModelOptional = professorService.findById(id);
        if (!professorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found.");
        }
        professorService.delete(professorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessor(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid ProfessorDto professorDto){
        Optional<ProfessorModel> professorModelOptional = professorService.findById(id);
        if (!professorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor not found.");
        }
        var professorModel = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorModel);
        professorModel.setId(professorModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorModel));
    }



}
