package com.senai.projetosa.repositorys;

import com.senai.projetosa.models.RecursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<RecursoModel, Long> {
}
