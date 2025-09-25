package com.senai.task.controllers;

import com.senai.task.dtos.RespostaDTO;
import com.senai.task.dtos.TarefaDTO;
import com.senai.task.dtos.UsuarioDTO;
import com.senai.task.services.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> criarTarefa(@RequestBody TarefaDTO dados) {
        try {
            RespostaDTO respostaDTO = service.criarTarefa(dados);
            return ResponseEntity.ok(respostaDTO.getMensagem());
        } catch (RuntimeException e) {
            if ("Usuário não encontrado!".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
            }
            if ("Usuário já possui agenda para a data informada!".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já possui agenda para a data informada!");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dados) {
        RespostaDTO respostaDTO = service.atualizarTarefa(id, dados);
        if ( respostaDTO.getMensagem().equals("sucesso") ) {
            respostaDTO.setMensagem("Tarefa atualizada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        } else if ( respostaDTO.getMensagem().equals("Tarefa não encontrada!") ) {
            respostaDTO.setMensagem("Tarefa não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
            }else{
            respostaDTO.setMensagem("Usuário da tarefa não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> deletarTarefa(@PathVariable Long id){
        RespostaDTO respostaDTO = service.deletarTarefa(id);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Tarefa deletada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Tarefa não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> obterTarefas(){
        List<TarefaDTO> tarefaDTOS = service.listarTarefas();

        if (tarefaDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<TarefaDTO>());
        }
        return ResponseEntity.ok().body(service.listarTarefas());
    }
}
