package com.senai.projetosa.services;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.models.RecursoModel;
import com.senai.projetosa.repositorys.RecursoRepository;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    private final RecursoRepository repository;

    public RecursoService(RecursoRepository repository) {
        this.repository = repository;
    }

    public RespostaDTO cadastrar(RecursoDTO recursoDto) {

        RecursoModel recursoModel = new RecursoModel();
        recursoModel.setDescricao(recursoDto.getDescricao());
        recursoModel.setTipo(recursoDto.getTipo());
        recursoModel.setDiasDisponivel(recursoDto.getDiasDisponivel());
        recursoModel.setDataInicialAgendamento(recursoDto.getDataInicialAgendamento());
        recursoModel.setDataFinalAgendamento(recursoDto.getDataFinalAgendamento());
        recursoModel.setHoraInicialAgendamento(recursoDto.getHoraInicialAgendamento());
        recursoModel.setHoraFinalAgendamento(recursoDto.getHoraFinalAgendamento());
        repository.save(recursoModel);

        RespostaDTO resposta = new RespostaDTO();
        resposta.setMensagem("sucesso");
        return resposta ;
    }

    public RespostaDTO atualizar(Long id, RecursoDTO recursoDTO){
        Optional<RecursoModel> recursoOP = repository.findById(id);

        if (recursoOP.isPresent()){
            RecursoModel usuario = recursoOP.get();
            usuario.setDescricao(recursoDTO.getDescricao());
            usuario.setTipo(recursoDTO.getTipo());
            usuario.setDiasDisponivel(recursoDTO.getDiasDisponivel());
            usuario.setDataInicialAgendamento(recursoDTO.getDataInicialAgendamento());
            usuario.setDataFinalAgendamento(recursoDTO.getDataFinalAgendamento());
            usuario.setHoraInicialAgendamento(recursoDTO.getHoraInicialAgendamento());
            usuario.setHoraFinalAgendamento(recursoDTO.getHoraFinalAgendamento());
            repository.save(usuario);

            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("sucesso");
            return resposta ;
        }
            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("NÃO FOI POSSÍVEL ATUALIZAR O RECURSO! ID = " + id);
            return resposta ;
    }

    public RespostaDTO excluir(Long id){
        Optional<RecursoModel> recursoOP = repository.findById(id);

        if (recursoOP.isPresent()){
            repository.deleteById(id);
            RespostaDTO resposta = new RespostaDTO();
            resposta.setMensagem("sucesso");
            return resposta ;
        }
        RespostaDTO resposta = new RespostaDTO();
        resposta.setMensagem("NÃO FOI POSSÍVEL EXCLUIR O RECURSO! ID = " + id);
        return resposta ;
    }

    public RecursoDTO obterRecurso(Long id){
        RecursoDTO recursoDTO = new RecursoDTO();
        Optional<RecursoModel> recursoOP = repository.findById(id);

        if (recursoOP.isPresent()){
            recursoDTO.setId(recursoOP.get().getId());
            recursoDTO.setDescricao(recursoOP.get().getDescricao());
            recursoDTO.setTipo(recursoOP.get().getTipo());
            recursoDTO.setDiasDisponivel(recursoOP.get().getDiasDisponivel());
            recursoDTO.setDataInicialAgendamento(recursoOP.get().getDataInicialAgendamento());
            recursoDTO.setDataFinalAgendamento(recursoOP.get().getDataFinalAgendamento());
            recursoDTO.setHoraInicialAgendamento(recursoOP.get().getHoraInicialAgendamento());
            recursoDTO.setHoraFinalAgendamento(recursoOP.get().getHoraFinalAgendamento());
            return recursoDTO;
        }
        return recursoDTO;
    }

    public List<RecursoDTO> obterRecursos(){
        List<RecursoDTO> recursoDTO = new ArrayList<>();
        List<RecursoModel> recursoModels = repository.findAll();

        for (RecursoModel recursoModel : recursoModels){
            RecursoDTO recurso = new RecursoDTO();
            recurso.setId(recursoModel.getId());
            recurso.setDescricao(recursoModel.getDescricao());
            recurso.setTipo(recursoModel.getTipo());
            recurso.setDiasDisponivel(recursoModel.getDiasDisponivel());
            recurso.setDataInicialAgendamento(recursoModel.getDataInicialAgendamento());
            recurso.setDataFinalAgendamento(recursoModel.getDataFinalAgendamento());
            recurso.setHoraInicialAgendamento(recursoModel.getHoraInicialAgendamento());
            recurso.setHoraFinalAgendamento(recursoModel.getHoraFinalAgendamento());
            recursoDTO.add(recurso);
        }
        return recursoDTO;
    }
}
