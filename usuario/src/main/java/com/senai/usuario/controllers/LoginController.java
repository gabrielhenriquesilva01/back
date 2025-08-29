package com.senai.usuario.controllers;

import com.senai.usuario.dtos.RequisicaoDTO;
import com.senai.usuario.dtos.RespostaDTO;
import com.senai.usuario.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaDTO> login(@RequestBody RequisicaoDTO dados){
        RespostaDTO respostaDTO = service.login(dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Login efetuado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("LOGIN OU SENHA INCORRETA!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }
}
