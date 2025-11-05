package com.senai.projetosa.repositorys;

import com.senai.projetosa.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository <ReservaModel, Long> {
}
