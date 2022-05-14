package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

    Imagem findFirstByOrderByIdImagemDesc();

    Imagem findByFkProjeto(Integer fkProjeto);
}