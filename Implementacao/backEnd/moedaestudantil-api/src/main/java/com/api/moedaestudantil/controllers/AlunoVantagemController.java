package com.api.moedaestudantil.controllers;

import com.api.moedaestudantil.dtos.AlunoVantagemDto;
import com.api.moedaestudantil.models.AlunoVantagemModel;
import com.api.moedaestudantil.services.AlunoVantagemService;
import com.api.moedaestudantil.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/alunoVantagem")
public class AlunoVantagemController {

    final AlunoVantagemService alunoVantagemService;
    final UsuarioService usuarioService;

    public AlunoVantagemController(AlunoVantagemService alunoVantagemService, UsuarioService usuarioService) {
        this.alunoVantagemService = alunoVantagemService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAlunoVantagem(@RequestBody @Valid AlunoVantagemDto alunoVantagemDto){
        var alunoVantagemModel = new AlunoVantagemModel();
        BeanUtils.copyProperties(alunoVantagemDto, alunoVantagemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoVantagemService.save(alunoVantagemModel));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoVantagemModel>> getAllAlunoVantagems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(alunoVantagemService.findAll(pageable));
    }

    @GetMapping("/por-aluno/{id}")
    public ResponseEntity<List<AlunoVantagemModel>> buscarPorAluno(@PathVariable(value = "id") UUID id) {
        List<AlunoVantagemModel> alunoVantagem = alunoVantagemService.findByAluno(id);
        if (alunoVantagem != null) {
            return ResponseEntity.ok(alunoVantagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAlunoVantagem(@PathVariable(value = "id") UUID id){
        Optional<AlunoVantagemModel> alunoVantagemModelOptional = alunoVantagemService.findById(id);
        if (!alunoVantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AlunoVantagem not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoVantagemModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAlunoVantagem(@PathVariable(value = "id") UUID id){
        Optional<AlunoVantagemModel> alunoVantagemModelOptional = alunoVantagemService.findById(id);
        if (!alunoVantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AlunoVantagem not found.");
        }
        alunoVantagemService.delete(alunoVantagemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("AlunoVantagem deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlunoVantagem(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid AlunoVantagemDto alunoVantagemDto){
        Optional<AlunoVantagemModel> alunoVantagemModelOptional = alunoVantagemService.findById(id);
        if (!alunoVantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AlunoVantagem not found.");
        }
        var alunoVantagemModel = new AlunoVantagemModel();
        BeanUtils.copyProperties(alunoVantagemDto, alunoVantagemModel);
        alunoVantagemModel.setId(alunoVantagemModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(alunoVantagemService.save(alunoVantagemModel));
    }



}
