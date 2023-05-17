package com.api.moedaestudantil.repositories;

import com.api.moedaestudantil.models.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoModel, UUID> {

    @Query("SELECT t FROM TransacaoModel t WHERE t.remetente.id = :remetente")
    List<TransacaoModel> findByRemetente(@Param("remetente") UUID id);

    @Query("SELECT t FROM TransacaoModel t WHERE t.destinatario.id = :destinatario")
    List<TransacaoModel> findByDestinatario(@Param("destinatario") UUID id);
}
