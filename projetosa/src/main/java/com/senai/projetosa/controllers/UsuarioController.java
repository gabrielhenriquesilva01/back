package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public String cadastrar(@ModelAttribute("usuarioDto")RequisicaoDTO usuarioDTO){
        RespostaDTO respostaDTO = service.cadastrar(usuarioDTO);
        return "redirect:/usuariolista";
    }

    @PutMapping("/{id}")
    public String atualizar(@ModelAttribute("usuarioDto")UsuarioDTO usuarioDTO, @PathVariable Long id){
        service.atualizar(id, usuarioDTO);
        return "redirect:/usuariolista";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> excluir(@PathVariable Long id){

        RespostaDTO resposta = service.excluir(id);

        if (resposta.getMensagem().equals("sucesso")) {
            resposta.setMensagem("Usuário excluído com sucesso!");
            return ResponseEntity.ok().body(resposta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }
}