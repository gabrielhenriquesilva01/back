package com.senai.task.controllers;

import com.senai.task.dtos.*;
import com.senai.task.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        RespostaDTO respostaDTO = (service.criarUsuario(dados));
        if (respostaDTO.getMensagem().equals("sucesso")) {
            respostaDTO.setMensagem("Usuario criado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Este usuário já existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<RespostaDTO> atualizarUsuario(@PathVariable String email, @RequestBody UsuarioDTO dados) {
        RespostaDTO respostaDTO = service.atualizarUsuario(email, dados);
        if (respostaDTO.getMensagem().equals("sucesso")) {
            respostaDTO.setMensagem("Usuario atualizado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else if (respostaDTO.getMensagem().equals("email")) {
            respostaDTO.setMensagem("Este E-mail já existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }else{
        respostaDTO.setMensagem("Usuário não encontrado!");
        return ResponseEntity.badRequest().body(respostaDTO);
    }
}

    @DeleteMapping("/{email}")
    public ResponseEntity<RespostaDTO> deletarUsuario(@PathVariable String email) {
        RespostaDTO respostaDTO = service.deletarUsuario(email);
        if (respostaDTO.getMensagem().equals("sucesso")) {
            respostaDTO.setMensagem("Usuario deletado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Usuário não existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obterUsuarios() {
        List<UsuarioDTO> usuarioDTOS = service.obterUsuarios();

        if (usuarioDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<UsuarioDTO>());
        }
        return ResponseEntity.ok().body(service.obterUsuarios());
    }
}

