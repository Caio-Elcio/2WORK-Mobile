package com.towork.backendaplicacao.controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.towork.backendaplicacao.dominio.*;
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
    private List<ProjetosCurtidos> projetosCurtidos = new ArrayList<>();
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

    @GetMapping("/projetos")// Retornar todos os projetos do banco de dados
    public List<Projeto> getProjetos(){//
        projetos = projetoRepository.findAll();
        return projetos;
    }

    @GetMapping("/projetos-curtidos")// Retornar todos os projetos do banco de dados
    public List<ProjetosCurtidos> getProjetosCurtidos(){//
        projetosCurtidos = projetosCurtidosRepository.findAll();
        return projetosCurtidos;
    }

    @GetMapping("/imagens")
    public List<Imagem> getImagens(){
        imagens = imagemRepository.findAll();
        return imagens;
    }
    @PostMapping("/postar-projeto/{idUsuario}")// Postando um projeto
    public ResponseEntity postarProjeto(@RequestBody Projeto novoProjeto,@PathVariable Integer idUsuario){
        DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setQuantidadeDeProjetos(usuario.getIdUsuario()+1);
        novoProjeto.setDataHoraProjeto(dtf4.format(LocalDateTime.now()));
        novoProjeto.setUsuario(usuario);
        novoProjeto.setNomeUsuario(usuario.getNomeUsuario());
        String letra = usuario.getNomeUsuario().substring(0,1);
        novoProjeto.setPrimeiraLetraNome(letra);
        novoProjeto.setImagem(imagemRepository.findByFkProjeto(projetoRepository.findFirstByOrderByIdProjetoDesc().getIdProjeto()+1));
        projetoRepository.save(novoProjeto);// Está dando o INSERT do projeto
        //pesquisaDeMercadoRepository.save(pesquisaDeMercado);// Está dando o INSERT da pesquisa de mercado
        //imagemRepository.save(imagem);//Está dando o Insert da pesquisa de mercado
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/atualizar-dados/{idUsuario}")// Atualiza apenas o nome e o e-mail do usuário
    public ResponseEntity atualizarDados(@PathVariable Integer idUsuario, @RequestBody Usuario atualizarUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOptional.get();
        if (!atualizarUsuario.getNomeUsuario().isEmpty()){// Checa se o valor não é apenas vazio "" (NÃO PASSAR NULO PARA CÁ)
            usuario.setNomeUsuario(atualizarUsuario.getNomeUsuario());
        }
        if (!atualizarUsuario.getEmailUsuario().isEmpty()){// Checa se o valor não é apenas vazio "" (NÃO PASSAR NULO PARA CÁ)
            usuario.setEmailUsuario(atualizarUsuario.getEmailUsuario());
        }
        if (!atualizarUsuario.getTelefoneUsuario().isEmpty()){// Checa se o valor não é apenas vazio "" (NÃO PASSAR NULO PARA CÁ)
            usuario.setTelefoneUsuario(atualizarUsuario.getTelefoneUsuario());
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/trocar-plano-basic/{idUsuario}/{planoUsuario}")// Atualiza apenas o plano do usuário
    public ResponseEntity atualizarPlano(@PathVariable Integer idUsuario, @PathVariable String planoUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        Usuario usuario = usuarioOptional.get();
        System.out.println(usuario);
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario2 = usuarioOptional.get();
        System.out.println(usuario);
        if (!planoUsuario.isEmpty()){// Se o planoUsuario não vir com ""
            usuario.setPlanoUsuario(planoUsuario);// Atualize o planoUsuario
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/retornar-ultimos-projetos")//Traz os últimos projetos
    public ResponseEntity projetosRecentes(){
        Boolean notFound = false;
        Integer contador = 0;
        List<Projeto> ultimosProjetos = new ArrayList<>();
        while(contador < 3){
            Optional<Projeto> projetoOptional = projetoRepository.findById(
                    projetoRepository.findFirstByOrderByIdProjetoDesc().getIdProjeto()-contador);
            if (projetoOptional.isEmpty()){
                contador=3;
                notFound = true;
            }else {
                Projeto projeto = projetoOptional.get();
                ultimosProjetos.add(projeto);
                contador++;
            }
        }
        if (notFound){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(ultimosProjetos);
    }

    @GetMapping("/ver-projeto/{idProjeto}")
    public ResponseEntity verProjeto(@PathVariable Integer idProjeto){
        Optional<Projeto> projetoOptional = projetoRepository.findById(idProjeto);
        if(projetoOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Projeto projeto = projetoOptional.get();
        return ResponseEntity.status(200).body(projeto);
    }

    @GetMapping("/ver-usuario/{idUsuario}")
    public ResponseEntity verUsuario(@PathVariable Integer idUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOptional.get();
        return ResponseEntity.status(200).body(usuario);
    }

    @GetMapping("/meus-projetos/{idUsuario}")
    public ResponseEntity meusProjetos(@PathVariable Integer idUsuario){
        List<Projeto> meusProjetos = projetoRepository.findByUsuarioIdUsuario(idUsuario);
        return ResponseEntity.status(200).body(meusProjetos);
    }

    @PostMapping("/curtir-projeto/{idProjeto}/{idUsuario}")
    public ResponseEntity curtirProjeto(ProjetosCurtidos projetosCurtidos, @PathVariable Integer idProjeto, @PathVariable Integer idUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        Optional<Projeto> projetoOptional = projetoRepository.findById(idProjeto);
        if (usuarioOptional.isEmpty() || projetoOptional.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        Usuario usuario = usuarioOptional.get();
        Projeto projeto = projetoOptional.get();
        projetosCurtidos.setProjeto(projeto);
        projetosCurtidos.setUsuario(usuario);
        projetosCurtidosRepository.save(projetosCurtidos);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/meus-projetos-curtidos/{idUsuario}")
    public ResponseEntity meusProjetosCurtidos (@PathVariable Integer idUsuario){
        List<ProjetosCurtidos> meusProjetosCurtidos = projetosCurtidosRepository.findByUsuarioIdUsuario(idUsuario);
        return ResponseEntity.status(200).body(meusProjetosCurtidos);
    }

}