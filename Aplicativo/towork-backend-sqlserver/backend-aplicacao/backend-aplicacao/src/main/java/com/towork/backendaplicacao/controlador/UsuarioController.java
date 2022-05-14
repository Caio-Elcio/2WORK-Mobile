package com.towork.backendaplicacao.controlador;

import com.towork.backendaplicacao.dominio.Imagem;
import com.towork.backendaplicacao.dominio.PesquisaDeMercado;
import com.towork.backendaplicacao.dominio.Projeto;
import com.towork.backendaplicacao.dominio.Usuario;
import com.towork.backendaplicacao.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/2Work")
public class UsuarioController {

    @Autowired//Ele inicializa a AvaliacaoRepository (evitando nullpointer)
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired//Ele inicializa a ContatoRepository (evitando nullpointer)
    private ContatoRepository contatoRepository;
    @Autowired//Ele inicializa a EspecialidadeRepository (evitando nullpointer)
    private EspecialidadeRepository especialidadeRepository;
    @Autowired//Ele inicializa a ImagemRepository (evitando nullpointer)
    private ImagemRepository imagemRepository;
    @Autowired//Ele inicializa a PesquisaDeMercadoRepository (evitando nullpointer)
    private PesquisaDeMercadoRepository pesquisaDeMercadoRepository;
    @Autowired//Ele inicializa a ProjetoRepository (evitando nullpointer)
    private ProjetoRepository projetoRepository;
    @Autowired//Ele inicializa a ProjetosCurtidosRepository (evitando nullpointer)
    private ProjetosCurtidosRepository projetosCurtidosRepository;
    @Autowired//Ele inicializa a UsuarioRepository (evitando nullpointer)
    // Está criando a entidade Usuario (No caso da Azure, está chamando a tabela).
    private UsuarioRepository usuarioRepository;

    // Está criando uma lista, para podermos manipular os dados do Usuário pelo JAVA.
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Projeto> projetos = new ArrayList<>();
    private List<Imagem> imagens = new ArrayList<>();

    @GetMapping // Comentar esse método no dia da Sprint
    public List<Usuario> getUsuario() {
        usuarios = usuarioRepository.findAll(); // Está pegando todos os dados do banco (se não tiver nada, dará NullPointer)
        return usuarios;
    } // Checar os usuarios da nossa lista



    @PostMapping("/cadastrar-usuario") // Cadastrando um usuário
    public ResponseEntity cadastroUsuario(@RequestBody Usuario novoUsuario) { // Está pegando o Body
        novoUsuario.setAvaliacaoUsuario(3.3); // Quando o usuário se cadastra, sua avaliação começa com 0.0
        novoUsuario.setPlanoUsuario("Basic"); // Quando o usuário se cadastra, seu plano padrão é o Basic
        novoUsuario.setQuantidadeDeFavoritos(3); // Zerando a quantidade de projetos favoritos
        novoUsuario.setQuantidadeDeProjetos(3); // Zerando a quantidade de projetos postados
        usuarioRepository.save(novoUsuario); // Está dando o INSERT no banco de dados com os dados que estão escritos no JSON
        return ResponseEntity.status(201).build(); // Retorna o Status 201 (Criado)
    }

    @PostMapping("/upload-imagem") // Upload de uma Imagem
    public ResponseEntity cadastroImagem(@RequestBody Imagem novaImagem) { // Está pegando o Body
        /*Integer contador = 0;//Aqui serve para descobrirmos qual será a FKUsuario
        List<Usuario> usuarios = usuarioRepository.findAll();//Está pegando todos usuarios do banco
        for (Usuario u : usuarios){//Está percorrendo toda lista
            contador++;//Contando quantos usuários tem
        }
        novaImagem.setFkProjeto(1+contador);//Está prevendo qual será o próximo id do projeto*/
        Boolean sairDoLooping;//Essa lógica deve ser posta no Kotlin
        do {
            sairDoLooping = true;
            Projeto ultimoProjeto = projetoRepository.findFirstByOrderByIdProjetoDesc();
            Imagem ultimaImagem = imagemRepository.findFirstByOrderByIdImagemDesc();
            if(ultimaImagem.getFkProjeto() > ultimoProjeto.getIdProjeto()){
                sairDoLooping = false;
                imagemRepository.deleteById(ultimaImagem.getIdImagem());
            }
        }while(!sairDoLooping);
        novaImagem.setFkProjeto(projetoRepository.findFirstByOrderByIdProjetoDesc().getIdProjeto()+1);
        novaImagem.setImageUrl("https://alimentos.com.br/wp-content/uploads/2018/08/beneficios_da_maca_292140977.jpg");//Subindo a imagem da maçã
        imagemRepository.save(novaImagem); // Está dando o INSERT no banco de dados com os dados que estão escritos no JSON
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
        List<Usuario> usuario = usuarioRepository.findAll(); // Está pegando o e-mail e a senha atribuido ao PathVariable
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

    @GetMapping("/tela-perfil/{email}/{senha}")// Pegando os dados necessários para a tela de perfil do usuário.
    public ResponseEntity telaPerfil(@PathVariable String email, @PathVariable String senha){//Está pegando os 2 valores.
        Usuario usuario = usuarioRepository.findByEmailUsuarioAndSenhaUsuario(email,senha);//Está pegando um usuário pelo email e senha.
        if(usuario == null){//Se não achar o usuário.
            return ResponseEntity.status(404).build();//Retorna 404 (Para cair na condição negativa de isSucessfull).
        }
        return ResponseEntity.status(200).body(usuario);//Retorna 200 e o body contendo os dados do usuário.
    }

    @GetMapping("/projetos")
    public List<Projeto> getProjeto(){
        projetos = projetoRepository.findAll();
        return projetos;
    }

    @GetMapping("/imagens")
    public List<Imagem> getImagem(){
        imagens = imagemRepository.findAll();
        return imagens;
    }
    @PostMapping("/postar-projeto/{idUsuario}")// Postando um projeto
    public ResponseEntity postarProjeto(@RequestBody Projeto novoProjeto,@PathVariable Integer idUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOptional.get();
        novoProjeto.setUsuario(usuario);
        System.out.println(imagemRepository.findByFkProjeto(projetoRepository.findFirstByOrderByIdProjetoDesc().getIdProjeto()+1));
        novoProjeto.setImagem(imagemRepository.findByFkProjeto(projetoRepository.findFirstByOrderByIdProjetoDesc().getIdProjeto()+1));
        projetoRepository.save(novoProjeto);// Está dando o INSERT do projeto
        //pesquisaDeMercadoRepository.save(pesquisaDeMercado);// Está dando o INSERT da pesquisa de mercado
        //imagemRepository.save(imagem);//Está dando o Insert da pesquisa de mercado
        return ResponseEntity.status(201).build();
    }
}