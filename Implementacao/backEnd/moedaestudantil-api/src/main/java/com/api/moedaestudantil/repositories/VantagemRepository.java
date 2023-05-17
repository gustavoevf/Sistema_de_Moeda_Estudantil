package com.api.moedaestudantil.repositories;

import com.api.moedaestudantil.models.VantagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VantagemRepository extends JpaRepository<VantagemModel, UUID> {

    @Query("SELECT t FROM VantagemModel t WHERE t.empresa.id = :empresa")
    List<VantagemModel> findByEmpresa(@Param("empresa") UUID id);
}
