package com.senai.task.repositorys;

import com.senai.task.Models.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
    boolean existsByUsuarioEmailAndDataAgendamento(String email, Date dataAgendamento);
    boolean existsByUsuarioEmail(String email);
}
