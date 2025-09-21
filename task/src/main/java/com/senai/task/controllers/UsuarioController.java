package com.senai.task.controllers;

import com.senai.task.dtos.*;
import com.senai.task.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> criarUsuario(@RequestBody UsuarioDTO dados) {
        boolean criado = (service.criarUsuario(dados));
        RespostaDTO respostaDTO = new RespostaDTO();
        if (criado) {
            respostaDTO.setMensagem("Usuario criado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        } else {
            respostaDTO.setMensagem("Este usuário já existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<RespostaDTO> atualizarUsuario(@PathVariable String email, @RequestBody UsuarioDTO dados) {
        boolean atualizado = service.atualizarUsuario(email, dados);
        RespostaDTO respostaDTO = new RespostaDTO();
        if (atualizado) {
            respostaDTO.setMensagem("Usuario atualizado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
        respostaDTO.setMensagem("Usuário não encontrado!");
        return ResponseEntity.badRequest().body(respostaDTO);
    }
}

    @DeleteMapping("/{email}")
    public ResponseEntity<RespostaDTO> deletarUsuario(@PathVariable String email) {
        boolean criado = service.deletarUsuario(email);
        RespostaDTO respostaDTO = new RespostaDTO();
        if (criado) {
            respostaDTO.setMensagem("Usuario deletado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Usuário não existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity <List<UsuarioDTO>> obterUsuarios() {
        RespostaDTO respostaDTO = new RespostaDTO();
        if (service.obterUsuarios() == null) {
            respostaDTO.setMensagem("Lista vazia!");
            return ResponseEntity.ok().body(service.obterUsuarios());
        }
        return ResponseEntity.ok().body(service.obterUsuarios());
    }
}

