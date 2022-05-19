package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Projeto;
import com.towork.backendaplicacao.dominio.ProjetosCurtidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetosCurtidosRepository extends JpaRepository<ProjetosCurtidos, Integer> {

    List<ProjetosCurtidos> findByUsuarioIdUsuario(Integer idUsuario);
}
