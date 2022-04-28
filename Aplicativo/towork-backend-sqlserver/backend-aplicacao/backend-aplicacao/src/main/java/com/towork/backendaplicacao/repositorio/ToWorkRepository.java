package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToWorkRepository extends JpaRepository<Usuario, Integer> {

}