package com.senai.crud.models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id // Indica que o ID é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica o auto-incremento
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "senha")
    private String senha;

    public UsuarioModel() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
