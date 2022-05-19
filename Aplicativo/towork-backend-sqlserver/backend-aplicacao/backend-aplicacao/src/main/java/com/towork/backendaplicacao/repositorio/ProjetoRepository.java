package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    Projeto findFirstByOrderByIdProjetoDesc();

    List<Projeto> findByUsuarioIdUsuario(Integer idUsuario);

}
