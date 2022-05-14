package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
