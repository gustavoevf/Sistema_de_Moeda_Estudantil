package com.api.moedaestudantil.controllers;

import com.api.moedaestudantil.dtos.EnviarTransacaoDto;
import com.api.moedaestudantil.dtos.TransacaoDto;
import com.api.moedaestudantil.models.TransacaoModel;
import com.api.moedaestudantil.models.UsuarioModel;
import com.api.moedaestudantil.services.TransacaoService;
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
@RequestMapping("/transacao")
public class TransacaoController {

    final TransacaoService transacaoService;
    final UsuarioService usuarioService;

    public TransacaoController(TransacaoService transacaoService, UsuarioService usuarioService) {
        this.transacaoService = transacaoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTransacao(@RequestBody @Valid TransacaoDto transacaoDto){
        var transacaoModel = new TransacaoModel();
        BeanUtils.copyProperties(transacaoDto, transacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.save(transacaoModel));
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoModel>> getAllTransacaos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.findAll(pageable));
    }

    @GetMapping("/por-remetente/{id}")
    public ResponseEntity<List<TransacaoModel>> buscarPorRemetente(@PathVariable(value = "id") UUID id) {
        List<TransacaoModel> transacao = transacaoService.findByRemetente(id);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/por-destinatario/{id}")
    public ResponseEntity<List<TransacaoModel>> buscarPorDestinatario(@PathVariable(value = "id") UUID id) {
        List<TransacaoModel> transacao = transacaoService.findByDestinatario(id);
        if (transacao != null) {
            return ResponseEntity.ok(transacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTransacao(@PathVariable(value = "id") UUID id){
        Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
        if (!transacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(transacaoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransacao(@PathVariable(value = "id") UUID id){
        Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
        if (!transacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao not found.");
        }
        transacaoService.delete(transacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Transacao deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransacao(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid TransacaoDto transacaoDto){
        Optional<TransacaoModel> transacaoModelOptional = transacaoService.findById(id);
        if (!transacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transacao not found.");
        }
        var transacaoModel = new TransacaoModel();
        BeanUtils.copyProperties(transacaoDto, transacaoModel);
        transacaoModel.setId(transacaoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.save(transacaoModel));
    }



}
