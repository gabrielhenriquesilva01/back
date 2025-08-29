package com.senai.usuario.controllers;

import com.senai.usuario.dtos.ProdutoDTO;
import com.senai.usuario.dtos.ProdutoRequestDTO;
import com.senai.usuario.dtos.ProdutoResponseDTO;
import com.senai.usuario.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO dados){
        ProdutoResponseDTO respostaDTO = service.criarProduto(dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Produto cadastrado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        return ResponseEntity.ok().body(service.listarProdutos());
    }
}