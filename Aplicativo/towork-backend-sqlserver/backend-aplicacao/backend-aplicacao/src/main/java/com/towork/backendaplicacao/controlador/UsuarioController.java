package com.towork.backendaplicacao.controlador;

import com.towork.backendaplicacao.dominio.Usuario;
import com.towork.backendaplicacao.repositorio.ToWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/2Work")
public class UsuarioController {

    @Autowired
    private ToWorkRepository repository;//Está criando o banco de dados (No caso da Azure, está chamando o banco de dados).

    private List<Usuario> usuarios = new ArrayList<>();//Está criando uma lista, para podermos manipular os dados do Usuário pelo JAVA

    @GetMapping//Comentar esse método no dia da Sprint
    public List<Usuario> getUsuario() {
        usuarios = repository.findAll();//Está pegando todos os dados do banco (se não tiver nada, dará NullPointer)
        return usuarios;
    }//Checar os usuarios da nossa lista

    @PostMapping("/cadastrar-usuario")// Cadastrando um usuário
    public ResponseEntity cadastroUsuario(@RequestBody Usuario novoUsuario){//Está pegando o Body
        novoUsuario.setAvaliacaoUsuario(0.0);//Quando o usuário se cadastra, sua avaliação começa com 0.0
        novoUsuario.setPlanoUsuario("Basic");//Quando o usuário se cadastra, seu plano padrão é o Basic
        System.out.println(novoUsuario);
        repository.save(novoUsuario);//Está dando o INSERT no banco de dados com os dados que estão escritos no JSON
        return ResponseEntity.status(201).build();//Retorna o Status 201 (Criado)
    }

    @PostMapping("/login-usuario")// Logando um usuário
    public ResponseEntity loginUsuario(@RequestBody Usuario usuarioLogin){//Está pegando o Body
        usuarios = repository.findAll();//Está pegando todos os dados do banco (se não tiver nada, dará NullPointer)
        for(Usuario usuario : usuarios){//Está percorrendo toda nossa lista de usuários no Banco
            if(Objects.equals(usuario.getEmailUsuario(), usuarioLogin.getEmailUsuario()) &&
            Objects.equals(usuario.getSenhaUsuario(), usuarioLogin.getSenhaUsuario())){//Está comparando se a senha e o email existem no banco
                return ResponseEntity.status(200).build();//Retorna o Status 200 (OK)
            }
        }
        return ResponseEntity.status(204).build();//Retorna o Status 204 (Funcionou, mas está vazio).
    }
}
