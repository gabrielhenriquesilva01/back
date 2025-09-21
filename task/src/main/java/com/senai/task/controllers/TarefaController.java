package com.senai.task.controllers;

import com.senai.task.dtos.RespostaDTO;
import com.senai.task.dtos.TarefaDTO;
import com.senai.task.services.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RespostaDTO> criarTarefa(@RequestBody TarefaDTO dados){
        RespostaDTO respostaDTO = service.criarTarefa(dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Tarefa criada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else if (respostaDTO.getMensagem().equals("Este usuário já possuí agenda para a data informada!")) {
            respostaDTO.setMensagem("Este usuário já possuí agenda para a data informada!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }else if(respostaDTO.getMensagem().equals("Esta tarefa já esta vinculada!")){
            respostaDTO.setMensagem("Esta tarefa já esta vinculada!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Usuário não encontrado!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dados){
        RespostaDTO respostaDTO = service.atualizarTarefa(id, dados);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Tarefa atualizada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            respostaDTO.setMensagem("Essa tarefa já existe!");
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespostaDTO> deletarTarefa(@PathVariable Long id){
        RespostaDTO respostaDTO = service.deletarTarefa(id);
        if (respostaDTO.getMensagem().equals("sucesso")){
            respostaDTO.setMensagem("Tarefa deletada com sucesso!");
            return ResponseEntity.ok().body(respostaDTO);
        }else{
            return ResponseEntity.badRequest().body(respostaDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> obterTarefas(){
        return ResponseEntity.ok().body(service.listarTarefas());
    }
}
