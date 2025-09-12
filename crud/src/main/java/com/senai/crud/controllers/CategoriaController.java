package com.senai.crud.controllers;

import com.senai.crud.dtos.*;
import com.senai.crud.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RespostaDto> criar(@RequestBody CategoriaRequisiçãoDTO dados){
        RespostaDto respostaDto = service.criarCategoria(dados);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Categoria cadastrada com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDto> atualizar(@PathVariable Long id, @RequestBody CategoriaRequisiçãoDTO dados){
        RespostaDto respostaDto = service.atualizarCategoria(id, dados);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Categoria atualizada com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDto> deletar(@PathVariable Long id){
        RespostaDto respostaDto = service.excluirCategoria(id);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Categoria deletada com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obter(@PathVariable Long id){
        return ResponseEntity.ok().body(service.obterCategoria(id));
    }

    @GetMapping("/obter")
    public ResponseEntity<List<CategoriaDTO>> obterCategorias(){
        return ResponseEntity.ok().body(service.listarCategorias());
    }
}
