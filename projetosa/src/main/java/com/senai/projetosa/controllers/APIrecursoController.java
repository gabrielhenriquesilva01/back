package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.services.RecursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/recurso")
public class APIrecursoController {

    private final RecursoService recursoService;

    public APIrecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> cadastrar(@RequestBody RecursoDTO recursoDTO) {
        RespostaDTO respostaDTO = recursoService.cadastrar(recursoDTO);

        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Recurso cadastrado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDTO> atualizar(@PathVariable Long id, @RequestBody RecursoDTO recursoDTO) {
        RespostaDTO respostaDTO = recursoService.atualizar(id, recursoDTO);

        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Recurso atualizado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> excluir(@PathVariable Long id){
        RespostaDTO respostaDTO = recursoService.excluir(id);

        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Recurso excluido com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> obterRecursos(@PathVariable Long id){
        return ResponseEntity.ok().body(recursoService.obterRecurso(id));
    }

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> obterRecurso(){
        return ResponseEntity.ok().body(recursoService.obterRecursos());
    }
}