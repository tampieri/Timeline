# Timeline

Desenvolvido utilizando Spring boot, Spring data, JDK 12, Mavem, Postgresql 11, H2, MockMvc

Consiste em uma Api com serviços baseados na estrutura Rest que visam a inserção de uma lista de eventos, listagem de eventos, paginação, busca por evento.

Get /events/ : lista todos eventos cadastrados
Post /events/ : Cadastra um novo evento ou uma lista de eventos.
Get /events/event : Busca por nome do evento um evento cadastrado.
Get /events/Page : Faz a paginação de uma lista de eventos. 
Get /timeline/ : Lista uma compra agrupada com seus produtos vinculados a partir do seu numero de transaction_id

As estruturas utilizadas para armazenamento foram o Postgresql 11 e o H2 (armazenamento em memória). 
A classe serviço DBService é a responsável por popular o banco H2 em test ou popular o banco Postgresql caso ele não tenha sido criado.
No pacote config tem um arquivo com extensão .json chamado Desafio_Dito.postman_collection que é referente  a collection testes realizados com o Postman .
Foi criado dois arquivos de properties para controle de desenvolvimento utilizando bancos distintos. O arquivo de test está utilizando o banco em memória, e o arquivo dev está utilizando o postgresql.
