# Relato sobre o projeto Lista de tarefas

Em julho do ano de 2024, eu, Lorenzo Fernandes Aguiar, concluí minha graduação em Análise e Desenvolvimento de Sistemas. Esse curso, que foi ofertado pela Universidade Estácio de Sá, permitiu que eu obtivesse os conhecimentos necessários para analisar, desenvolver, testar e manutenir um software.

Atualmente, eu realizo projetos pessoais voltados para a área de desenvolvimento de software. Faço isso com o intuito de ganhar a experiência que eu preciso para ingressar no mercado de trabalho como desenvolvedor de software.

Um desses projetos pessoais eu denominei de Lista de tarefas. Nesse projeto, eu utilizei habilidades e tecnologias que, segundo minhas pesquisas, estão sendo bastante requisitadas pelo atual mercado de trabalho. Entre as habilidades e as tecnologias que eu utilizei durante o projeto Lista de tarefas, eu posso citar: programação orientada a objetos, API REST, Java, Spring Boot, bancos de dados relacionais, aplicação web e Angular.

Feita a introdução acima, agora eu explicarei o motivo pelo qual eu escrevo o presente texto. Eu escrevo o presente texto como forma de relatar as etapas que eu segui para finalizar o projeto Lista de tarefas. Através do presente texto eu busco comprovar minhas habilidades e meus conhecimentos para as empresas nas quais eu desejo trabalhar.

Vale ressaltar que, por questões de organização, eu dividi o presente texto em tópicos e subtópicos. Esses tópicos e subtópicos podem ser vistos abaixo.

* Tópico 1: Levantamento de requisitos
* Tópico 2: Git e GitHub
* Tópico 3: Desenvolvimento da API REST
    * Subtópico 3.1: Spring Initializr e Spring Tool Suite
    * Subtópico 3.2: Classe Tarefa

## Tópico 1: Levantamento de requisitos

O projeto Lista de tarefas surgiu da minha necessidade em contar com um software que me permitisse salvar tarefas em uma lista. Tendo isso em mente, eu comecei a refletir sobre as funcionalidades que deveriam ser apresentadas pelo software a ser desenvolvido. Foi por meio dessa reflexão que eu realizei o levantamento de requisitos do projeto Lista de tarefas.

Abaixo estão listados os requisitos que eu identifiquei para o projeto Lista de tarefas.

* Requisito de número 1: O projeto Lista de tarefas deverá consistir no desenvolvimento de uma aplicação web.
* Requisito de número 2: O front-end da aplicação web deverá ser construído com a utilização do framework Angular.
* Requisito de número 3: O front-end da aplicação web deverá ser composto pela página principal, a página de adição de tarefa, a página de edição de tarefa e a página de exclusão de tarefa.
* Requisito de número 4: A página principal terá como sua principal responsabilidade a exibição da lista contendo as tarefas salvas pelo usuário. A página principal também deve permitir que o usuário marque uma tarefa como concluída ou como não concluída.
* Requisito de número 5: A página de adição de tarefa deverá permitir que o usuário adicione uma tarefa na lista.
* Requisito de número 6: A página de edição de tarefa deverá permitir que o usuário edite uma tarefa da lista.
* Requisito de número 7: A descrição de uma tarefa deve conter, no mínimo, 3 caracteres e, no máximo, 25 caracteres.
* Requisito de número 8: A página de exclusão de tarefa deverá permitir que o usuário exclua uma tarefa da lista.
* Requisito de número 9: O back-end da aplicação web deverá envolver um banco de dados criado no PostgreSQL e uma API REST, a qual também deverá ser desenvolvida.
* Requisito de número 10: Cada tarefa tem um id, uma descrição e uma indicação se ela está ou não concluída.
* Requisito de número 11: A API REST deverá ser construída com a utilização do Spring Framework em conjunto com a ferramenta Spring Boot.
* Requisito de número 12: A API REST deverá ser capaz de fazer operações no banco de dados criado no PostgreSQL. Essas operações incluem a busca por todas as tarefas que estão salvas no banco de dados, a busca por uma tarefa específica que está salva no banco de dados, a adição de uma tarefa no banco de dados, a edição de uma tarefa que está salva no banco de dados e a exclusão de uma tarefa que está salva no banco de dados.
* Requisito de número 13: O front-end da aplicação web deverá ser capaz de se comunicar com a API REST através de requisições HTTP. Essas requisições levarão a API REST a realizar as operações citadas no requisito de número 12.

## Tópico 2: Git e GitHub

Através do Git eu realizei o controle de versão dos arquivos relacionados ao projeto Lista de tarefas. Regularmente, eu enviava alterações de meu repositório local para um repositório que eu possuo dentro do GitHub.

Vale destacar que, em relação ao projeto Lista de tarefas, eu criei dois repositórios dentro da minha conta do GitHub. O primeiro repositório se chama projeto-lista-de-tarefas-api-rest, sendo que tal repositório contém os arquivos relacionados com a API REST do projeto Lista de tarefas. O segundo repositório se chama projeto-lista-de-tarefas-frontend, sendo que tal repositório contém os arquivos relacionados com o front-end da aplicação web do projeto Lista de tarefas.

O presente texto está localizado dentro do repositório chamado projeto-lista-de-tarefas-api-rest. Já o repositório chamado projeto-lista-de-tarefas-frontend pode ser acessado através do link <https://github.com/lorenzofernandesaguiar/projeto-lista-de-tarefas-frontend>.

## Tópico 3: Desenvolvimento da API REST

Os subtópicos seguintes relatam as etapas que eu segui para desenvolver a API REST do projeto Lista de tarefas.

### Subtópico 3.1: Spring Initializr e Spring Tool Suite

Eu acessei o site Spring Initializr e promovi as configurações que podem ser vistas na imagem abaixo.

![Configurações promovidas dentro do Spring Initializr](https://github.com/user-attachments/assets/6b452eae-52fe-458c-83f1-241dcd8f7a2b)

Sobre a imagem acima, vale destacar que:

* Eu escolhi a dependência Spring Web porque ela me permite lidar com requisições HTTP.
* Eu escolhi a dependência Spring Data JPA porque ela facilita a realização do mapeamento objeto-relacional. Além disso, através da referida dependência, eu conto com métodos capazes de criar, ler, atualizar e remover registros de um banco de dados.
* Eu escolhi a dependência PostgreSQL Driver porque ela permite que a API REST se comunique com um banco de dados criado no PostgreSQL.
* Eu escolhi a dependência H2 Database porque ela me permite trabalhar com um banco de dados em memória.

Após promover as configurações mostradas na imagem acima, o Spring Initializr me gerou uma pasta, chamada projetolistadetarefasapirest. Eu importei essa pasta para o meu workspace do Spring Tool Suite, IDE que eu utilizei para desenvolver a API REST. Foi dentro da pasta projetolistadetarefasapirest que eu incluí os arquivos que iriam compor a API REST.

### Subtópico 3.2: Classe Tarefa

Dentro do pacote principal da pasta projetolistadetarefasapirest, eu criei o pacote entities contendo a classe Tarefa.

[Clique aqui](https://github.com/lorenzofernandesaguiar/projeto-lista-de-tarefas-api-rest/blob/main/src/main/java/com/projetolistadetarefasapirest/entities/Tarefa.java) para ver o código Java que eu escrevi para a classe Tarefa.

Sobre o código Java que eu escrevi para a classe Tarefa, vale destacar que:

* Por meio da anotação @Entity, eu indiquei que a classe Tarefa é uma entidade, ou seja, é uma classe que representa uma tabela no banco de dados. O nome dessa tabela é tarefas, conforme eu indiquei por meio da anotação @Table(name="tarefas").
* A classe Tarefa possui os atributos id, descricao e concluida. De forma análoga, no banco de dados, a tabela tarefas possui as colunas id, descricao e concluida.
* Por meio das anotações @Id e @GeneratedValue(strategy = GenerationType.IDENTITY), eu indiquei que a coluna id é a chave primária e o seu valor será gerado por auto-incremento.
* Por meio da anotação @Column(nullable = false, length = 25), eu indiquei que a coluna descricao não pode ser nula e que seu tamanho é 25. No caso da coluna concluida, eu simplesmente indiquei que ela não pode ser nula.
* Eu criei os métodos getters e setters para todos os atributos da classe Tarefa. Isso vai de encontro ao pilar encapsulamento da programação orientada a objetos.
* Eu sobrescrevi os métodos hashCode e equals para que eles levassem em consideração somente o atributo id.