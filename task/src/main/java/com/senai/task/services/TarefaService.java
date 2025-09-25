package com.senai.task.services;

import com.senai.task.Models.*;
import com.senai.task.dtos.*;
import com.senai.task.repositorys.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final UsuarioRepository usuarioRepository;
    private final TarefaRepository tarefaRepository;

    public TarefaService(UsuarioRepository usuarioRepository, TarefaRepository tarefaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public RespostaDTO criarTarefa(TarefaDTO dados){

        UsuarioModel usuario = usuarioRepository.findByEmail(dados.getEmailUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        boolean existe = tarefaRepository.existsByUsuarioEmailAndDataAgendamento(dados.getEmailUsuario(), dados.getDataAgendamento());
        if (existe) {
            throw new RuntimeException("Usuário já possui agenda para a data informada!");
        }
            TarefaModel tarefaModels = new TarefaModel();
            tarefaModels.setTitulo(dados.getTitulo());
            tarefaModels.setDescricao(dados.getDescricao());
            tarefaModels.setDataAgendamento(dados.getDataAgendamento());
            tarefaModels.setStatus(dados.getStatus());
            tarefaModels.setUsuario(usuario);
            tarefaRepository.save(tarefaModels);
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("Tarefa inserida com sucesso!");
            return respostaDTO;
        }

    public RespostaDTO atualizarTarefa(Long id, TarefaDTO dados){
        Optional<UsuarioModel> usuarioOP = usuarioRepository.findByEmail(dados.getEmailUsuario());

        if (usuarioOP.isPresent()) {
            Optional<TarefaModel> OP = tarefaRepository.findById(id);

            if (OP.isPresent()) {
                TarefaModel tarefaModel = OP.get();
                tarefaModel.setTitulo(dados.getTitulo());
                tarefaModel.setDescricao(dados.getDescricao());
                tarefaModel.setDataAgendamento(dados.getDataAgendamento());
                tarefaModel.setStatus(dados.getStatus());
                tarefaModel.setUsuario(usuarioOP.get());
                tarefaRepository.save(tarefaModel);
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("sucesso");
                return respostaDTO;
            }else{
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("Tarefa não encontrada!");
                return respostaDTO;
            }
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário da tarefa não encontrado!");
        return respostaDTO;
    }

    public RespostaDTO deletarTarefa(Long id){
        Optional<TarefaModel> model =  tarefaRepository.findById(id);

        if (model.isPresent()){
            TarefaModel tarefaModel = model.get();
            tarefaRepository.delete(tarefaModel);
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("sucesso");
            return respostaDTO;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Tarefa não encontrada!");
        return respostaDTO;
    }

    public List<TarefaDTO> listarTarefas(){
        List<TarefaDTO> tarefaDTOs = new ArrayList<>();
        List<TarefaModel> tarefaModels = tarefaRepository.findAll();

        for (TarefaModel tarefaModel : tarefaModels) {
            TarefaDTO tarefaDTO = new TarefaDTO();
            tarefaDTO.setId(tarefaModel.getId());
            tarefaDTO.setTitulo(tarefaModel.getTitulo());
            tarefaDTO.setDescricao(tarefaModel.getDescricao());
            tarefaDTO.setDataAgendamento(tarefaModel.getDataAgendamento());
            tarefaDTO.setStatus(tarefaModel.getStatus());
            tarefaDTO.setEmailUsuario(tarefaModel.getUsuario().getEmail());
            tarefaDTOs.add(tarefaDTO);
            }
        return tarefaDTOs;
    }
}

