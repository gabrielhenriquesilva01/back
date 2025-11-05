package com.senai.projetosa.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RecursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descrição", nullable = false)
    private String descricao;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "dias da semana disponível")
    private String diasDisponivel;

    @Column(name = "data inicial de agendamento")
    private LocalDate dataInicialAgendamento;

    @Column(name = "data final de agendamento")
    private LocalDate dataFinalAgendamento;

    @Column(name = "hora inicial de agendamento")
    private String horaInicialAgendamento;

    @Column(name = "hora final de agendamento")
    private String horaFinalAgendamento;

    public RecursoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiasDisponivel() {
        return diasDisponivel;
    }

    public void setDiasDisponivel(String diasDisponivel) {
        this.diasDisponivel = diasDisponivel;
    }

    public LocalDate getDataInicialAgendamento() {
        return dataInicialAgendamento;
    }

    public void setDataInicialAgendamento(LocalDate dataInicialAgendamento) {
        this.dataInicialAgendamento = dataInicialAgendamento;
    }

    public LocalDate getDataFinalAgendamento() {
        return dataFinalAgendamento;
    }

    public void setDataFinalAgendamento(LocalDate dataFinalAgendamento) {
        this.dataFinalAgendamento = dataFinalAgendamento;
    }

    public String getHoraInicialAgendamento() {
        return horaInicialAgendamento;
    }

    public void setHoraInicialAgendamento(String horaInicialAgendamento) {
        this.horaInicialAgendamento = horaInicialAgendamento;
    }

    public String getHoraFinalAgendamento() {
        return horaFinalAgendamento;
    }

    public void setHoraFinalAgendamento(String horaFinalAgendamento) {
        this.horaFinalAgendamento = horaFinalAgendamento;
    }
}
