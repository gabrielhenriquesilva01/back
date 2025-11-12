package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.services.RecursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recurso")
public class RecursoController {

    private final RecursoService service;

    public RecursoController(RecursoService service) {
        this.service = service;
    }

    @PostMapping
    public String cadastrar(@ModelAttribute("recursoDto") RecursoDTO recursoDto){
       service.cadastrar(recursoDto);
       return "redirect:/recursolista";
    }

    @PostMapping("/{id}")
    public String atualizar(@ModelAttribute("recursoDto") RecursoDTO recursoDTO, @PathVariable Long id) {
        service.atualizar(id, recursoDTO);
        return "redirect:/recursolista";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> excluir(@PathVariable Long id){
        RespostaDTO resposta = service.excluir(id);

        if (resposta.getMensagem().equals("sucesso")) {
            resposta.setMensagem("Recurso excluído com sucesso!");
            return ResponseEntity.ok().body(resposta);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}