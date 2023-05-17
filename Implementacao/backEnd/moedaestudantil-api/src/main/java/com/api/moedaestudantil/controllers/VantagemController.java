package com.api.moedaestudantil.controllers;

import com.api.moedaestudantil.dtos.VantagemDto;
import com.api.moedaestudantil.models.TransacaoModel;
import com.api.moedaestudantil.models.VantagemModel;
import com.api.moedaestudantil.services.VantagemService;
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
@RequestMapping("/vantagem")
public class VantagemController {

    final VantagemService vantagemService;

    public VantagemController(VantagemService vantagemService) {
        this.vantagemService = vantagemService;
    }

    @PostMapping
    public ResponseEntity<Object> saveVantagem(@RequestBody @Valid VantagemDto vantagemDto){
        var vantagemModel = new VantagemModel();
        BeanUtils.copyProperties(vantagemDto, vantagemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(vantagemService.save(vantagemModel));
    }

    @GetMapping("/por-empresa/{id}")
    public ResponseEntity<List<VantagemModel>> buscarPorEmpresa(@PathVariable(value = "id") UUID id) {
        List<VantagemModel> vantagens = vantagemService.findByEmpresa(id);
        if (vantagens != null) {
            return ResponseEntity.ok(vantagens);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<VantagemModel>> getAllVantagems(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vantagemService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVantagem(@PathVariable(value = "id") UUID id){
        Optional<VantagemModel> vantagemModelOptional = vantagemService.findById(id);
        if (!vantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vantagem not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vantagemModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVantagem(@PathVariable(value = "id") UUID id){
        Optional<VantagemModel> vantagemModelOptional = vantagemService.findById(id);
        if (!vantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vantagem not found.");
        }
        vantagemService.delete(vantagemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vantagem deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVantagem(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid VantagemDto vantagemDto){
        Optional<VantagemModel> vantagemModelOptional = vantagemService.findById(id);
        if (!vantagemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vantagem not found.");
        }
        var vantagemModel = new VantagemModel();
        BeanUtils.copyProperties(vantagemDto, vantagemModel);
        vantagemModel.setId(vantagemModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(vantagemService.save(vantagemModel));
    }



}
