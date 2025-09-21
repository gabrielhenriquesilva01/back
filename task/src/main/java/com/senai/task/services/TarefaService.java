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
        Optional<UsuarioModel> model = usuarioRepository.findByEmail(dados.getEmailUsuario());

        if (model.isPresent()) {
            if (model.get().equals(dados.getDataAgendamento())) {
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("Este usuário já possuí agenda para a data informada!");
                return respostaDTO;
            }

            Optional<TarefaModel> tarefaOP = tarefaRepository.findByTitulo(dados.getTitulo());
            if (tarefaOP.isPresent()) {
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("Esta tarefa já esta vinculada!");
                return respostaDTO;
            }

            TarefaModel tarefaModel = new TarefaModel();
            tarefaModel.setTitulo(dados.getTitulo());
            tarefaModel.setDescricao(dados.getDescricao());
            tarefaModel.setDataAgendamento(dados.getDataAgendamento());
            tarefaModel.setStatus(dados.getStatus());
            tarefaModel.setUsuario(tarefaOP.get().getUsuario());
            tarefaRepository.save(tarefaModel);
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("sucesso");
            return respostaDTO;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário da tarefa não encontrado!");
        return respostaDTO;
    }

    public RespostaDTO atualizarTarefa(Long id, TarefaDTO dados){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByEmail(dados.getEmailUsuario());

        if (usuarioModel.isPresent()) {
            Optional<TarefaModel> model = tarefaRepository.findById(id);

            if (model.isPresent()) {
                TarefaModel tarefaModel = model.get();
                tarefaModel.setTitulo(dados.getTitulo());
                tarefaModel.setDescricao(dados.getDescricao());
                tarefaModel.setDataAgendamento(dados.getDataAgendamento());
                tarefaModel.setStatus(dados.getStatus());
                tarefaModel.setUsuario(model.get().getUsuario());
                tarefaRepository.save(tarefaModel);
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("sucesso");
                return respostaDTO;
            }else{
                RespostaDTO respostaDTO = new RespostaDTO();
                respostaDTO.setMensagem("Não foi possível atualizar a tarefa!");
                return respostaDTO;
            }
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setMensagem("Usuário não existe!");
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
        respostaDTO.setMensagem("Não foi possível excluir a tarefa!");
        return respostaDTO;
    }

    public List<TarefaDTO> listarTarefas(){
        List<TarefaDTO> tarefaDTOs = new ArrayList<>();
        List<TarefaModel> tarefaModels = tarefaRepository.findAll();

        if (tarefaModels.isEmpty()) {
            RespostaDTO respostaDTO = new RespostaDTO();
            respostaDTO.setMensagem("Lista vazia!");
            return tarefaDTOs;
        }

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

