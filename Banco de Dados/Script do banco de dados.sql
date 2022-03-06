-- ****************************************************************************************************************************
-- *                                                    CONSIDERAÇÕES                                                         *
-- ****************************************************************************************************************************
-- * LUIZ GUSTAVO DA SILVA - 06/03/2022                                                                                       *
-- *--------------------------------------------------------------------------------------------------------------------------*
-- * TELEFONE DA TELA DE CADASTRO SERÁ ATRIBUIDO À TABELA CONTATO COM "TIPO CONTATO - TELEFONE" "CONTEUDO CONTATO - TELEFONE" *
-- * --> E-MAIL DA TELA DE CADASTRO SERÁ ATRIBUIDO TAMBÉM A TABELA CONTATO?                                                   *
-- * --> SENHORES, criar 3 radio buttons com valores (básico, médio e avançado) para o atributo especialidade (PRECISA?).     *
-- * --> O USUÁRIO PODE APAGAR UMA AVALIAÇÃO QUE ELE FEZ À ALGUEM?                                                            *
-- * --> Gente, não fiz um delete imagem por que ao meu ver um projeto não pode existir sem foto, concordam?                  *
-- * --> No pesquisa de mercado o usuário pode editar ou apagar?
-- ****************************************************************************************************************************
CREATE DATABASE toWork;
USE toWork;
-- ****************************************************************************************************************************
-- *                                                       TABELAS                                                            *
-- ****************************************************************************************************************************
-- Criação da tabela usuario
CREATE TABLE usuario(
idusuario INT AUTO_INCREMENT,
nomeUsuario VARCHAR(60) NOT NULL,
emailUsuario VARCHAR(60) NOT NULL,
senhaUsuario VARCHAR(60) NOT NULL,
dataNascimentoUsuario DATE NOT NULL,
biografiaUsuario VARCHAR(1000) NOT NULL,
avaliacaoUsuario TINYINT NOT NULL,
cpfUsuario BIGINT NOT NULL,
cidadeUsuario VARCHAR(60) NOT NULL,
ufUsuario VARCHAR(60) NOT NULL,
planoUsuario VARCHAR(10) NOT NULL,
PRIMARY KEY (idUsuario)
);
-- Criação da tabela especialidade
CREATE TABLE especialidade(
nomeEspecialidade VARCHAR(60) NOT NULL,
nivelEspecialidade VARCHAR(60) NOT NULL,
fkUsuario INT,
FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario)
);
-- Criação da tabela contato
CREATE TABLE contato(
tipoContato VARCHAR(40) NOT NULL,
conteudoContato VARCHAR(60) NOT NULL,
fkUsuario INT,
FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario)
);
-- Criação da tabela avaliacao
CREATE TABLE avaliacao(
idAvaliacao INT AUTO_INCREMENT,
quantidadeEstrelas TINYINT NOT NULL,
fkUsuarioAvaliador INT,
fkUsuarioAvaliado INT,
PRIMARY KEY (idAvaliacao),
FOREIGN KEY (fkUsuarioAvaliador) REFERENCES usuario(idUsuario),
FOREIGN KEY (fkUsuarioAvaliado) REFERENCES usuario(idUsuario)
);
-- Criação da tabela projeto
CREATE TABLE projeto(
idProjeto INT AUTO_INCREMENT,
tituloProjeto VARCHAR(60) NOT NULL,
descricaoProjeto VARCHAR(1000) NOT NULL,
dataHoraProjeto DATETIME NOT NULL,
totalVisualizacoesProjeto INT NOT NULL,
totalCurtidasProjeto INT NOT NULL,
fkUsuario INT,
PRIMARY KEY (idProjeto),
FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario)
);
-- Criação da tabela projetosCurtidos
CREATE TABLE projetosCurtidos(
fkUsuario INT,
dataHoraCurtido DATETIME NOT NULL,
fkProjeto INT,
FOREIGN KEY (fkProjeto) REFERENCES projeto(idProjeto)
);
-- Criação da tabela imagem
CREATE TABLE imagem(
idImagem INT AUTO_INCREMENT,
imageUrl VARCHAR(1000) NOT NULL,
PRIMARY KEY (idImagem),
fkProjeto INT,
FOREIGN KEY (fkProjeto) REFERENCES projeto(idProjeto)
);
-- Criação da tabela pesquisa_de_mercado
CREATE TABLE pesquisaDeMercado(
idPesquisa INT AUTO_INCREMENT,
primeiraPergunta BOOLEAN NOT NULL,
segundaPergunta BOOLEAN NOT NULL,
terceiraPergunta BOOLEAN NOT NULL,
PRIMARY KEY (idPesquisa),
fkProjeto INT,
FOREIGN KEY (fkProjeto) REFERENCES projeto(idProjeto)
);
-- Fim da área de criação de tabelas
-- ****************************************************************************************************************************
-- *                                                        QUERY'S                                                           *
-- ****************************************************************************************************************************
-- *                                                          CRUD                                                            *
-- ****************************************************************************************************************************
-- Inserindo um usuário (cadastro);
insert into usuario values 
(null, 'usuarioTeste1', 'usuarioTeste1@teste.com', 'UsuarioTeste1', '2000-01-01' , 'Olá, me chamo usuarioTeste1', 5.0, '10000000000', 'cidadeTeste1', 'T1', 'basic');
-- Essa query está cadastrando mais de um usuário (servirá mais para massa de teste mesmo).
insert into usuario values
(null, 'usuarioTeste2', 'usuarioTeste2@teste.com', 'UsuarioTeste2', '2000-01-02' , 'Olá, me chamo usuarioTeste2', 4.0, '20000000000', 'cidadeTeste2', 'T2', 'pro'),
(null, 'usuarioTeste3', 'usuarioTeste3@teste.com', 'UsuarioTeste3', '2000-01-03' , 'Olá, me chamo usuarioTeste3', 3.0, '30000000000', 'cidadeTeste3', 'T3', 'business');
-- Select em um usuário (como se estivesse indo ver o perfil do usuário)
select * from usuario where idUsuario = 1;
-- Exibir todos os usuários (servirá só para teste)
select * from usuario;
-- Atualizar os dados do usuário (editar pefil)
update usuario set nomeUsuario = 'usuarioTeste' where idUsuario = 3;
-- Deletando o usuário (Quando ele fizer o cancelamento da conta)
delete from usuario where idUsuario = 3;
-- ****************************************************************************************************************************
-- *                                                    Especialidade                                                         *
-- ****************************************************************************************************************************
-- Inserindo uma especialidade
insert into especialidade values ('Robotic Process Automation', 'Avançado', 1);
-- Inserindo especialidades (massa de teste)
insert into especialidade values 
('Agile', 'Avançado', 1),
('Computation Engineer', 'Avançado', 2);
-- selecionando a especialidade
select * from especialidade;
-- atualizando a especialidade
update especialidade set nomeEspecialidade = 'Scrum' where fkUsuario = 2;
-- deletando a especialidade
delete from especialidade where nomeEspecialidade = 'Agile' and fkUsuario = 1;
-- ****************************************************************************************************************************
-- *                                                      Contato                                                             *
-- ****************************************************************************************************************************
-- Criando um contato
insert into contato value ('Telefone', '(11)4555-4555', 1);
-- Criando contatos (massa de teste)
insert into contato values ('LinkedIn', 'Usuário Teste 1', 1),
('LinkedIn', 'Usuário Teste 2', 2),
('Github', 'Usuário-Teste-2', 2);
-- Exibindo os contatos do usuário 1
select * from contato where fkUsuario = 1;
-- Exibindo todos os contatos (para teste)
select * from contato;
-- Atualizando o valor de algum contato
update contato set conteudoContato = '(11)4555-8383' where fkUsuario = 1 and tipoContato = 'Telefone' and conteudoContato = '(11)4555-4555';
-- Deletando um valor de algum contato
delete from contato where fkUsuario = 1 and tipoContato = 'Telefone' and conteudoContato = '(11)4555-4555';
-- ****************************************************************************************************************************
-- *                                                      Avaliação                                                           *
-- ****************************************************************************************************************************
-- Avaliando o usuário
insert into avaliacao value (null, 5, 1, 2);
-- Avaliando outro usuário
insert into avaliacao value (null, 4, 2, 1);
-- exibindo as avaliações (enquanto avaliador)
select * from avaliacao where fkUsuarioAvaliador = 1;
-- exibindo as avaliações (enquanto avaliado)
select * from avaliacao where fkUsuarioAvaliado = 1;
-- Exibindo todas as avaliações (para teste)
select * from avaliacao;
-- Atualizar a avaliação a um determinado usuário
update avaliacao set quantidadeEstrelas = '5' where fkUsuarioAvaliador = 2 and fkUsuarioAvaliado = 1;
-- Deletar uma avaliação
delete from avaliacao where fkUsuarioAvaliador = 2 and fkUsuarioAvaliado = 1;
-- ****************************************************************************************************************************
-- *                                                      Projetos                                                            *
-- ****************************************************************************************************************************
-- Criando um projeto
insert into projeto value (null, 'projetoTeste1', 'este é meu projetoTeste1', '2022-01-01 00:00', 1000, 10, 1);
-- Criando outro para massa de teste
insert into projeto values 
(null, 'projetoTeste2', 'este é meu projetoTeste2', '2022-01-01 00:00', 2000, 20, 2),
(null, 'Novo ProjetoTeste2', 'este é meu projetoTeste2 novo', '2022-01-01 00:00', 2000, 20, 2);
-- Procurando um projeto
select * from projeto where idProjeto = 1;
-- Servirá para teste
select * from projeto;
-- Atualizar a informação do projeto
update projeto set tituloProjeto = 'projetoTeste' where idProjeto = 2;
-- Deletar um projeto
delete from projeto where idProjeto = 2;
-- ****************************************************************************************************************************
-- *                                                 Projetos Curtidos                                                        *
-- ****************************************************************************************************************************
-- Curtindo um projeto
insert into projetosCurtidos value ( 1, '2022-01-01 00:01', 1);
-- **************************** PONTO IMPORTANTE, VARIAS QUERYS PARA DESCOBRIR QUAL PROJETO O USUÁRIO CURTIU OU NÃO
-- Selecionará quais projetos ele curtiu com o select abaixo:
select * from projetosCurtidos where fkUsuario = 1;
-- Armazenar os valores retornados em um list ou vetor (dependerá da linguagem do back end)
-- Fazer um laço de repetição que fará selects trocando o idProjeto do select abaixo para o valor armazenado no list|vetor
select * from projeto where idProjeto = 1;
select * from projeto where idProjeto = 3;
-- Selecionando todos os projetos curtidos (apenas teste)
select * from projetosCurtidos;
-- Descurtindo um projeto (é deletando mesmo);
delete from projetosCurtidos where fkUsuario = 1 and fkProjeto = 1;
-- ****************************************************************************************************************************
-- *                                                 Imagem                                                        *
-- ****************************************************************************************************************************
-- Inserindo uma imagem ao projeto
insert into imagem value (null, 'teste1.png', 1);
-- Pegando a imagem do banco e jogando no front
select * from imagem where fkProjeto = 1;
-- Selecionando todas as imagens (apenas teste)
select * from imagem;
-- Alterando a url da imagem;
update imagem set imageUrl = 'teste.png' where idImagem = 1;
-- Não adicionei o deletar, pois não deveria haver projeto sem foto.
-- ****************************************************************************************************************************
-- *                                                 Pesquisa de Mercado                                                      *
-- ****************************************************************************************************************************
-- Inserindo uma pesquisa de mercado em um projeto
insert into pesquisaDeMercado value (null, true, true, true, 1);
-- Selecionando a pesquisa de mercado de um projeto em específico
select * from pesquisaDeMercado where fkProjeto = 1;
-- Selecionando todas as pesquisas de mercado (apenas teste)
select * from pesquisaDeMercado;
-- ****************************************************************************************************************************
-- *                                                     DROP DATABASE                                                        *
-- ****************************************************************************************************************************
-- DROP DATABASE toWork;