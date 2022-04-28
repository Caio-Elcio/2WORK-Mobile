package com.towork.backendaplicacao.controlador;

import com.towork.backendaplicacao.dominio.Usuario;
import com.towork.backendaplicacao.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/2Work")
public class UsuarioController {

    @Autowired
    // Está criando o banco de dados (No caso da Azure, está chamando o banco de dados).
    private UsuarioRepository repository;

    // Está criando uma lista, para podermos manipular os dados do Usuário pelo JAVA.
    private List<Usuario> usuarios = new ArrayList<>();

    @GetMapping // Comentar esse método no dia da Sprint
    public List<Usuario> getUsuario() {
        usuarios = repository.findAll(); // Está pegando todos os dados do banco (se não tiver nada, dará NullPointer)
        return usuarios;
    } // Checar os usuarios da nossa lista

    @PostMapping("/cadastrar-usuario") // Cadastrando um usuário
    public ResponseEntity cadastroUsuario(@RequestBody Usuario novoUsuario) { // Está pegando o Body
        novoUsuario.setAvaliacaoUsuario(0.0); // Quando o usuário se cadastra, sua avaliação começa com 0.0
        novoUsuario.setPlanoUsuario("Basic"); // Quando o usuário se cadastra, seu plano padrão é o Basic
        System.out.println(novoUsuario);
        repository.save(novoUsuario); // Está dando o INSERT no banco de dados com os dados que estão escritos no JSON
        return ResponseEntity.status(201).build(); // Retorna o Status 201 (Criado)
    }

    @GetMapping("/login-usuario/{email}/{senha}") // Logando um usuário
    // Está pegando os 2 valores passados na URL
    public ResponseEntity loginUsuario(@PathVariable String email, @PathVariable String senha) {
        // Usuario usuario = repository.findByEmailUsuarioAndSenhaUsuario(email, senha); // Está pegando o e-mail e
        // a senha atribuido ao PathVariable

        // if(usuario == null){ // Se não encontrou nenhum usuário com esse email e senha
        //    return ResponseEntity.status(200).build(); // Retorna o Status 204, funcionou mas não encontrou
        // }
        // return ResponseEntity.status(400).build(); // Retorna o Status 200 (OK).
        List<Usuario> usuario = repository.findAll(); // Está pegando o e-mail e a senha atribuido ao PathVariable
        for (Usuario u : usuario) {
            System.out.println(u);
            System.out.println(u.getEmailUsuario());
            System.out.println(email);
            if (u.getEmailUsuario().equals(email) && u.getSenhaUsuario().equals(senha)) {
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(400).build();
    }
}