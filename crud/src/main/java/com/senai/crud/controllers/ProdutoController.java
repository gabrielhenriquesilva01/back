package com.senai.crud.controllers;

import com.senai.crud.dtos.ProdutoDTO;
import com.senai.crud.dtos.ProdutoRequisiçãoDTO;
import com.senai.crud.dtos.RespostaDto;
import com.senai.crud.services.ProdutoService;
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
    public ResponseEntity<RespostaDto> criarProduto(@RequestBody ProdutoDTO dados){
        RespostaDto respostaDto = service.criarProduto(dados);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Produto criado com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dados){
        RespostaDto respostaDto = service.atualizarProduto(id, dados);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Produto atualizado com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDto> deletarProduto(@PathVariable Long id){
        RespostaDto respostaDto = service.excluirProduto(id);

        if (respostaDto.getMensagem().equals("sucesso")){
            respostaDto.setMensagem("Produto deletado com sucesso!");
            return ResponseEntity.ok().body(respostaDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaDto);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id){
        return ResponseEntity.ok().body(service.obterProduto(id));
    }

    @GetMapping("/obter")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutos(){
        return ResponseEntity.ok().body(service.obterProdutos());
    }
}