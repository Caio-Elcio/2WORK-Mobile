package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}
