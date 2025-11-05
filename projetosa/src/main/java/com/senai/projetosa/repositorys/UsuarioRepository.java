package com.senai.projetosa.repositorys;

import com.senai.projetosa.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findById(Long id);
    Optional<UsuarioModel> findByEmailAndSenha(String email, String senha);
}
