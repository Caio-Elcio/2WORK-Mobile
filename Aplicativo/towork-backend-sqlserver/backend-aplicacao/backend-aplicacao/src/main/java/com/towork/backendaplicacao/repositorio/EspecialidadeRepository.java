package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
}
