# Um miniprojeto para aperfeiçoar as noções de relacionamento entre entidades utilizando o framework Spring Data JPA e de documentação de API com o Swagger
## Objetivo do projeto
O objetivo deste projeto é aprofundar os conhecimentos em Spring Data JPA, especialmente no que diz respeito ao relacionamento entre entidades, mapeamento desses relacionamentos e consultas a banco de dados utilizando as funcionalidades disponibilizadas pelo Spring Data JPA. Além disso, o projeto visa também praticar o desenvolvimento de APIs e sua documentação com o Swagger.
Para isso, foi desenvolvida uma API REST para gerenciar as operações de uma locadora de veículos fictícia, por meio da qual é possível cadastrar clientes, veículos e locações, bem como consultar, atualizar e apagar esses dados (CRUD).
Por se tratar de um projeto de estudo, não foi implementada nenhuma camada de segurança, ou seja, qualquer pessoa pode acessar a API e realizar as operações disponíveis. Pelo mesmo motivo, não há previsão para criação de novas funcionalidades, atualizações nas já existentes ou implementação de testes automatizados.
## Breve exposição conceitual
Um banco de dados é um conjunto de dados relacionados entre si, que podem ser armazenados e recuperados de forma eficiente. 
Conforme ELMASRI e NAVATHE (2019), o modelo relacional é um modelo de dados que permite a representação dos dados na forma de tabelas, sendo que cada qual representa uma entidade e cada linha representa uma instância dessa entidade. Na terminologia formal, cada linha é chamada de tupla, o cabeçalho da coluna é chamado de atributo e a tabela em si é chamda de relação.  
Uma entidade "é uma coisa ou objeto no mundo real com existência independente" e pode existir fisicamente (como um carro) ou apenas conceitualmente (como uma locação). 
Essas entidades são dotadas de atributos (características) que as descrevem, dentre os quais existem identificadores únicos, que permitem identificar uma instância de uma entidade de forma exclusiva. Esses identificadores são chamados de chaves primárias e é através deles que as instâncias de uma entidade são relacionadas entre si.
Para se relacionarem, as entidades utilizam-se do conceito de chaves estrangeiras, que são as chaves primárias de outras entidades.
A relação entre entidades pode ser de um para um (1:1), de um para muitos (1:N) ou de muitos para muitos (N:M).
O diagrama entidade-relacionamento (DER) é uma representação gráfica de um banco de dados relacional, que permite visualizar as entidades, seus atributos e relações.
Segundo ELMASRI e NAVATHE (2019), por normalização entende-se o processo de analisar as relações para atingir os objetivos de minimizar a redundância de dados e as anomalias de atualização, inserção e exclusão. É um processo de "filtragem" ou "purificação", sendo que as relações insatisfatórias, que não atendam a certas condições, devem ser sucessivamente decompostas em tabelas menores.
Por normalização, entende-se a divisão de uma tabela em outras menores, evitando a duplicação de dados e a inconsistência de informações.
A dita "forma normal", portanto, refere-se ao grau de normalização da tabela. Tradicionalmente, a doutrina prevê três formas normais, sendo elas:
- Primeira forma normal (1FN): os atributos devem ter apenas valores atômicos (ou indivisíveis) e únicos;
- Segunda forma normal (2FN): uma tabela está na segunda forma normal se estiver na primeira forma normal e seus atributos não chave dependam apenas e totalmente da chave primária;
- Terceira forma normal (3FN): uma tabela está na terceira forma normal se estiver na segunda forma normal e não contiver atributos não chave com dependência transitiva sobre a chave primária.
Há também a forma normal de Boyce-Codd, que é a 3FN mais rigorosa, a 4FN, que visa eliminar a dependência multivalorada, e a 5FN, que trata das dependências de junção.
## Casos de uso
- A API deve permitir a criação, leitura, atualização e exclusão (CRUD) de dados sobre os clientes, veículos e locações da empresa;
- Todos os IDs são gerados automaticamente pelo sistema de gerenciamento de banco de dados (SGBD);
  - Os IDs constituem chaves primárias de cada entidade e são utilizados como chave estrangeira nas entidades relacionadas;
- Quando um novo cliente -- ```customer``` -- for criado, os dados do endereço -- ```address``` -- podem ser simultaneamente informados e serão persistidos no banco de dados em tabela separada;
  - Um cliente possui apenas um endereço, mas um endereço pode ser utilizado por vários clientes. 
    - Portanto, o relacionamento entre cliente e endereço é de um para um (one-to-one) e o relacionamento entre endereço e cliente é de um para muitos (one-to-many);
  - Se os campos forem informados, espera-se que o endereço seja persistido antes do cliente, pois o cliente dependerá do endereço para ser criado. 
    - A persistência nesse caso ocorre em cascata; 
  - Caso contrário, se o cliente for criado sem informar dados de endereço, poderá ser atualizado posteriormente com a inclusão de um endereço já previamente cadastrado;
- No momento da criação do endereço poderão ser informados todos os campos ou apenas o CEP, número e complemento. Neste caso, o endereço completo será buscado por consulta a uma API externa e os campos restantes serão preenchidos automaticamente;
  - A API externa utilizada para consulta de endereço é da AwesomeAPI, que pode ser acessada através do link ```https://cep.awesomeapi.com.br/```, seguido do CEP desejado;
    - A API externa retorna um JSON com os dados do endereço, que serão mapeados para um objeto ```DTO```;
    - Caso o CEP informado não seja encontrado, a API externa retornará a mensagem ```CEP não encontrado```;
- Um cliente deve obrigatoriamente possuir nome e e-mail, sendo que o e-mail é validado por meio da anoção ```@Email``` do Spring;
- No momento da criação de um novo veículo -- ```car``` --, o atributo ```licenseNumber``` é obrigatório e deve ser único;
  - A validação é feita através de Regex e no momento permite apenas sete caracteres (três letras seguidas por hífen e quatro números);
  - Os atributos ```brand``` e ```model``` foram implementados como ```enum```, ou seja, apenas aceitarão valores pré-definidos;
- No momento da criação de uma nova locação -- ```rental``` --, é necessário informar a placa do veículo -- ```licenseNumber``` -- e o ID do cliente -- ```customerId```;
  - Somente é possível alugar um veículo que esteja disponível -- atributo ```available == true``` --;
    - Se o cliente não for encontrado ou o veículo não estiver disponível, a API retornará uma mensagem de erro;
  - Conforme as regras de negócio, um cliente pode realizar várias locações, mas somente poderá alugar um veículo por vez;
    - Neste modelo, o relacionamento entre cliente e locação é de um para muitos (one-to-many) e o relacionamento entre locação e cliente é de muitos para um (many-to-one);
    - Ainda, o relacionamento entre veículo e locação é de um para um (one-to-one);
  - Quando uma nova locação é criada, a data de início -- ```startDate``` -- é automaticamente preenchida com a data atual e a data de término -- ```endDate``` -- setada como ```null```. Ainda, o atributo ```available``` do veículo é atualizado para ```false```;
  - Quando uma locação é finalizada, a data de término -- ```endDate``` -- é atualizada com a data atual e o atributo ```available``` do veículo é atualizado para ```true```;
- Todos os métodos de consulta do tipo ````findAll()```` implementam paginação e ordenação;
  - A ordenação padrão é por ID, mas pode ser alterada para qualquer outro campo mediante a passagem de um parâmetro ```sort``` na requisição;
  - A paginação padrão é de 5 registros por página, mas pode ser alterada para qualquer outro valor mediante a passagem de um parâmetro ```size``` na requisição;
  - Por padrão, a primeira página é a de número 0, mas pode ser alterada para qualquer outra mediante a passagem de um parâmetro ```page``` na requisição;
  - Paginação e ordenação foram implementadas através da interface ```Pageable``` do Spring;
- Na classe ```CarRepository```, foi implementado o método ```findByLicenseNumber()```, que busca um veículo por sua placa;
- Apenas alguns atributos específicos das entidades podem ser atualizados e todos os métodos do tipo ```update``` devem permitir a atualização parcial dos dados, ou seja, não é necessário informar todos os dados para atualizar um registro;
  - Espera-se, nesse caso, que os atributos não informados permaneçam com os valores inalterados;
  - Os dados informados para atualização sujeitam-se às mesmas regras de validação dos dados informados para criação;
- Todas as operações do tipo ```delete``` implementam apenas a exclusão lógica, isto é, o registro não é efetivamente apagado do banco de dados, mas apenas marcado como inativo/indisponível.
## Normalização do Banco de Dados
As tabelas do banco de dados foram parcialmente normalizadas da seguinte forma:
- A tabela ```customer``` está na 3ª forma normal (3FN), porque não há repetição de dados, não há atributos multivalorados e não há dependência funcional parcial ou transitiva;
- A tabela ```address``` não foi normalizada, pois há possibilidade de repetição de dados (CEP, por exemplo);
- A tabela ```car``` também não foi normalizada, pois há possibilidade de repetição de dados no campo modelo (marca e cor foram implementados como ```enum``` e portanto podem ser considerados normalizados);
- A tabela ```rental``` está na 3ª forma normal (3FN), porque não há repetição de dados, não há atributos multivalorados e não há dependência funcional parcial ou transitiva.
## Endpoints
### Customer
- ```GET /customers``` - Retorna todos os clientes cadastrados;
- ```GET /customers/{id}``` - Retorna um cliente específico;
- ```POST /customers``` - Cria um novo cliente;
- ```PUT /customers/{id}``` - Atualiza um cliente específico;
- ```DELETE /customers/{id}``` - Exclui um cliente específico.
### Address
- ```GET /addresses``` - Retorna todos os endereços cadastrados;
- ```GET /addresses/{id}``` - Retorna um endereço específico;
- ```POST /addresses``` - Cria um novo endereço;
- ```PUT /addresses/{id}``` - Atualiza um endereço específico.
### Car
- ```GET /cars``` - Retorna todos os veículos cadastrados;
- ```GET /cars/{id}``` - Retorna um veículo específico;
- ```POST /cars``` - Cria um novo veículo;
- ```PUT /cars/{id}``` - Atualiza um veículo específico;
- ```PUT /cars/{id}/activate``` - Atualiza o atributo ```available``` do veículo para ```true```;
- ```DELETE /cars/{id}``` - Exclui um veículo específico.
### Rental
- ```GET /rentals``` - Retorna todas as locações cadastradas;
- ```POST /rentals``` - Cria uma nova locação;
- ```PUT /rentals/{id}``` - Encerra uma locação específica.
## Swagger
Esta API utilizou o Swagger para geração automática de documentação, a qual pode ser acessada em ```http://localhost:8080/swagger-ui.html```.

<img width="1435" alt="Captura de Tela 2023-01-02 às 18 45 02" src="https://user-images.githubusercontent.com/70707151/210365827-43784758-8acb-4b7d-b4eb-038873164102.png">
<img width="1435" alt="Captura de Tela 2023-01-02 às 18 45 18" src="https://user-images.githubusercontent.com/70707151/210365853-6d2990cd-f416-4146-aedd-a420a254ef73.png">
<img width="1436" alt="Captura de Tela 2023-01-02 às 18 45 32" src="https://user-images.githubusercontent.com/70707151/210365983-3526d654-4ada-482a-b1c7-b51a482bdd0c.png">
<img width="1439" alt="Captura de Tela 2023-01-02 às 18 45 47" src="https://user-images.githubusercontent.com/70707151/210365994-ab2ddad8-efb3-4e04-a308-f67a8640d3c4.png">

----------

# A mini-project to improve notions of entity relationships using the Spring Data JPA framework and API documentation with Swagger
## Project's goal
The objective of this project is to deepen knowledge in Spring Data JPA, especially regarding the relationship between entities, mapping these relationships and database queries using the features provided by Spring Data JPA. In addition, the project also aims to practice developing APIs and their documentation with Swagger.
In order to do so, a REST API was developed to manage the operations of a fictional car rental company, which implements features to register customers, vehicles and rentals, as well the other CRUD operations.
As it is a study project, no security layer was implemented, that is, anyone can access the API and perform the available operations. For the same reason, there is no provision for creating new features, updating existing ones or implementing automated tests.
## Brief conceptual exposition
A database is a set of related data that can be stored and retrieved efficiently.
According to ELMASRI and NAVATHE (2019), the relational model is a data model that allows the representation of data in the form of tables, each of which represents an entity and each line represents an instance of that entity. In formal terminology, each row is called a tuple, the column header is called an attribute, and the table itself is called a relation.
An entity "is a thing or object in the real world that exists independently" and can exist physically (like a car) or only conceptually (like a location).
These entities are endowed with attributes (characteristics) that describe them, among which there are unique identifiers, which allow identifying an instance of an entity in an exclusive way. These identifiers are called primary keys and it is through them that instances of an entity are related to each other.
To relate, entities use the concept of foreign keys, which are the primary keys of other entities.
The relationship between entities can be one to one (1:1), one to many (1:N) or many to many (N:M).
The entity-relationship diagram (ERD) is a graphical representation of a relational database, which allows us to visualize the entities, their attributes and relationships.
According to ELMASRI and NAVATHE (2019), normalization means the process of analyzing relationships to achieve the objectives of minimizing data redundancy and updating, insertion and deletion anomalies. It is a "filtering" or "purification" process, in which unsatisfactory relationships, which do not meet certain conditions, must be successively decomposed into smaller tables.
By normalization is meant the division of a table into smaller ones, avoiding data duplication and information inconsistency.
The so-called "normal form", therefore, refers to the degree of normalization of the table. Traditionally, the doctrine provides for three normal forms, namely:
- First normal form (1NF): attributes must have only atomic (or indivisible) and unique values;
- Second normal form (2NF): a table is in second normal form if it is in first normal form and its non-key attributes depend only and totally on the primary key;
- Third normal form (3NF): a table is in third normal form if it is in second normal form and does not contain non-key attributes with transitive dependence on the primary key.
There is also Boyce-Codd normal form, which is the strictest 3NF, 4NF, which aims to eliminate multivalued dependency, and 5NF, which deals with join dependencies.
## Use cases
- The API must allow the creation, reading, updating and deletion (CRUD) of data about the company's customers, vehicles and locations;
- All IDs are automatically generated by the database management system (DBMS);
  - IDs are the primary keys of each entity and are used as a foreign key in related entities;
- When a new ```customer```  is created, the ```address``` data can be simultaneously informed and will be persisted in the database in a separate table;
  - A customer has only one address, but one address can be used by several customers.
    - Therefore, the relationship between customer and address is one to one (one-to-one) and the relationship between address and customer is one to many (one-to-many);
  - If the fields are informed, it is expected that the address will be persisted before the client, as the client will depend on the address to be created.
    - Persistence in this case occurs in cascade;
  - Otherwise, if the customer is created without informing address data, it can be updated later with an address previously registered;
- When creating the ```address```, all fields or just the zip code, number and complement can be entered. In this case, the complete address will be fetched by querying an external API and the remaining fields will be filled in automatically;
  - The external API used for address lookup is AwesomeAPI, which can be accessed through the link ```https://cep.awesomeapi.com.br/```, followed by the desired zip code;
    - The external API returns a JSON with the address data, which will be mapped to a ```DTO``` object;
    - If the zip code entered is not found, the external API will return a ```not found``` message;
- A customer must have a ```name``` and ```e-mail```
  - The e-mail is validated using the Spring annotation ```@Email```;
- When creating a new ```car```, the ```licenseNumber``` attribute is mandatory and must be unique;
  - Validation is done through Regex and currently only allows seven characters (three letters followed by a hyphen and four numbers);
  - The ```brand``` and ```model``` attributes were implemented as ```enum```, that is, they will only accept predefined values;
- When creating a new ```rental```, it is mandatory to inform the vehicle license plate -- ```licenseNumber``` -- and the customer ID -- ```customerId ```;
- It is only possible to rent a vehicle that is available -- ```available attribute == true``` --;
  - If the customer is not found or the vehicle is not available, the API will return an error message;
- According to the business rules, a customer can make several rentals, but can only rent one vehicle at a time;
  - In this model, the relationship between client and rental is one to many and the relationship between rental and client is many-to-one;
  - Also, the relationship between vehicle and rental is one to one;
- When a new ```rental``` is created, the ```startDate``` is automatically filled in with the current date and the ```endDate``` is set to ```null```. Also, the ```available``` attribute of the vehicle is updated to ```false```;
- When a rental ends, the ```endDate``` is updated with the current date and the ```available``` attribute of the vehicle is updated to ```true``` ;
- All ```findAll()``` query methods implement paging and sorting;
  - The default ordering is by ID, but it can be changed to any other field by passing a ```sort``` parameter in the request;
  - The default pagination is 5 records per page, but it can be changed to any other value by passing a ```size``` parameter in the request;
  - By default, the first page is number 0, but it can be changed to any other by passing a ```page``` parameter in the request;
  - Pagination and sorting were implemented through Spring's ```Pageable``` interface;
- In the ```CarRepository``` class, the ```findByLicenseNumber()``` method was implemented, which searches for a vehicle by its license plate;
- Only some specific attributes of entities can be updated and all methods of type ```update``` must allow partial updating of data, that is, it is not necessary to inform all data to update a record;
  - It is expected, in this case, that the attributes not informed remain with its values unchanged;
  - The updating data must pass the same validation rules as the data informed for creation;
- All ```delete``` operations implement logical deletion, that is, the record is not effectively erased from the database, but only marked as inactive/unavailable.
## Database Normalization
The database tables were partially normalized as follows:
- The ```customer``` table is in 3rd normal form (3NF), because there is no repetition of data, there are no multivalued attributes and there is no partial or transitive functional dependency;
- The ```address``` table was not normalized, as there is the possibility of data repetition (zip code, for example);
- The ```car``` table was also not normalized, as there is the possibility of repeating data in the model field (brand and color were implemented as ```enum``` and therefore can be considered normalized);
- The ```rental``` table is in 3rd normal form (3NF), because there is no data repetition, no multivalued attributes and no partial or transitive functional dependency.
## Endpoints
### Customer
- ```GET /customers``` - Returns all registered customers;
- ```GET /customers/{id}``` - Returns a specific customer;
- ```POST /customers``` - Creates a new customer;
- ```PUT /customers/{id}``` - Updates a specific customer;
- ```DELETE /customers/{id}``` - Deletes a specific customer.
### Address
- ```GET /addresses``` - Returns all registered addresses;
- ```GET /addresses/{id}``` - Returns a specific address;
- ```POST /addresses``` - Creates a new address;
- ```PUT /addresses/{id}``` - Updates a specific address.
### Car
- ```GET /cars``` - Returns all registered vehicles;
- ```GET /cars/{id}``` - Returns a specific vehicle;
- ```POST /cars``` - Creates a new vehicle;
- ```PUT /cars/{id}``` - Updates a specific vehicle;
- ```PUT /cars/{id}/activate``` - Updates the ```available``` attribute of the vehicle to ```true```;
- ```DELETE /cars/{id}``` - Deletes a specific vehicle.
### Rental
- ```GET /rentals``` - Returns all registered rentals;
- ```POST /rentals``` - Creates a new rental;
- ```PUT /rentals/{id}``` - Terminates a specific rental.
## Swagger
This API used Swagger to automatically generate documentation, which can be accessed at ```http://localhost:8080/swagger-ui.html```.
