package com.senai.usuario.controllers;

import com.senai.usuario.dtos.CategoriaDTO;
import com.senai.usuario.dtos.CategoriaRequestDTO;
import com.senai.usuario.dtos.CategoriaResponseDTO;
import com.senai.usuario.dtos.ProdutoRequestDTO;
import com.senai.usuario.services.CategoriaService;
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
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDTO dados){
        CategoriaResponseDTO respostaDTO = service.criarCategoria(dados);
        if ( respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Categoria cadastrada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        return ResponseEntity.ok().body(service.listarCategorias());
    }
}