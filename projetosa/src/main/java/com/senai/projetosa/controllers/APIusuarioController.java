package com.senai.projetosa.controllers;

import com.senai.projetosa.dtos.RequisicaoDTO;
import com.senai.projetosa.dtos.RespostaDTO;
import com.senai.projetosa.dtos.UsuarioDTO;
import com.senai.projetosa.services.UsuarioService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class APIusuarioController {

    private final UsuarioService service;

    public APIusuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> cadastrar(@RequestBody RequisicaoDTO requisicaoDTO){
        RespostaDTO respostaDTO = service.cadastrar(requisicaoDTO);

        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Usuário cadastrado com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
    RespostaDTO respostaDTO = service.atualizar(id, usuarioDTO);

    if (respostaDTO.getMensagem().equals("sucesso")){
        respostaDTO.setMensagem("Usuário atualizado com sucesso!");
        return ResponseEntity.ok().body(respostaDTO);
    }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> excluir(@PathVariable Long id){
        RespostaDTO respostaDTO = service.excluir(id);

        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Usuário excluido com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable Long id){
        return ResponseEntity.ok().body(service.obterUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obterUsuarios(){
        return ResponseEntity.ok().body(service.obterUsuarios());
    }
}
