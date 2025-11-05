package com.senai.projetosa.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "colaborador", nullable = false)
    private String colaborador;

    @Column(name = "recurso", nullable = false)
    private String recurso;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "hora inicial")
    private String horaInicio;

    @Column(name = "hora final")
    private String horaFinal;

    @Column(name = "cancelamento")
    private LocalDate cancelamento;

    @Column(name = "observação")
    private String observacao;

    public ReservaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getCancelamento() {
        return cancelamento;
    }

    public void setCancelamento(LocalDate cancelamento) {
        this.cancelamento = cancelamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
