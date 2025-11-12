package com.senai.projetosa.dtos;

import java.time.LocalDate;

public class RecursoDTO {

    private Long id;
    private String descricao;
    private String tipo;
    private String diasDisponivel;
    private LocalDate dataInicialAgendamento;
    private LocalDate dataFinalAgendamento;
    private String horaInicialAgendamento;
    private String horaFinalAgendamento;

    public RecursoDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
