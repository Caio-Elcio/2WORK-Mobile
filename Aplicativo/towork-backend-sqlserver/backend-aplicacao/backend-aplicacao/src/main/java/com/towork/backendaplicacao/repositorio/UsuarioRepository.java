package com.towork.backendaplicacao.repositorio;

import com.towork.backendaplicacao.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmailUsuarioAndSenhaUsuario(String emailUsuario, String senhaUsuario);

}