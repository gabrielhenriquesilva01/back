package com.senai.task.dtos;

import jakarta.persistence.Column;

public class UsuarioDTO {
    @Column(nullable = false)
    private String nome;
    private String email;

    public UsuarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
