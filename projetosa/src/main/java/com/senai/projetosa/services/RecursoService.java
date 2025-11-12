package com.senai.projetosa.services;

import com.senai.projetosa.dtos.*;
import com.senai.projetosa.models.RecursoModel;
import com.senai.projetosa.repositorys.RecursoRepository;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecursoService {

    private List<RecursoModel> listaRecurso = new ArrayList<>();

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


}
