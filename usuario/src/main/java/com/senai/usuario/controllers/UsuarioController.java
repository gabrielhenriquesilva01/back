package com.senai.usuario.controllers;

import com.senai.usuario.dtos.RequisicaoDTO;
import com.senai.usuario.dtos.RespostaDTO;
import com.senai.usuario.dtos.UsuarioDTO;
import com.senai.usuario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @PostMapping("/usuario")
    public ResponseEntity<RespostaDTO> criar(@RequestBody RequisicaoDTO dados){
        RespostaDTO respostaDTO = service.criar(dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Usuário criado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<RespostaDTO> atualizar(@PathVariable Long id, @RequestBody RequisicaoDTO dados){
        RespostaDTO respostaDTO = service.atualizar(id, dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Usuário atualizado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<UsuarioDTO>> localizar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.localizar(id));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> localizarUsuarios(){
        return ResponseEntity.ok().body(service.localizarUsuarios());
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<RespostaDTO> deleta(@PathVariable int id){
        RespostaDTO respostaDTO = service.deleta(id);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Usuário deletado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }
}