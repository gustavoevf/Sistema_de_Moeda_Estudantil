package com.api.moedaestudantil.repositories;

import com.api.moedaestudantil.models.AlunoVantagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoVantagemRepository extends JpaRepository<AlunoVantagemModel, UUID> {

    @Query("SELECT t FROM AlunoVantagemModel t WHERE t.aluno.id = :aluno")
    List<AlunoVantagemModel> findByAluno(@Param("aluno") UUID id);
}
