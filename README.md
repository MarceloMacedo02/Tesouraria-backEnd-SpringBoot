# Aplicativo de Gerenciamento de Associados para Centro Espírita

Este é um aplicativo back-end desenvolvido em Java com o framework Spring Boot e utiliza consultas e persistência de dados em um banco de dados PostgreSQL através de uma RESTful API. O objetivo do aplicativo é realizar o gerenciamento dos associados de um Centro Espírita, incluindo o cadastro de associados, recolhimento e baixa de mensalidades, recebimento de doações e outros valores, pagamento de contas a pagar, além de gerar relatórios mensais e balancetes.

## Tecnologias Utilizadas

- Java
- Spring Boot
- PostgreSQL
- RESTful API

## Funcionalidades

O aplicativo possui as seguintes funcionalidades:

- Cadastro de Associados: Permite o cadastro de novos associados com informações como nome, endereço, telefone, entre outros dados relevantes.
- Recolhimento e Baixa de Mensalidades: Realiza o registro e controle das mensalidades pagas pelos associados, permitindo a baixa das mesmas quando efetuadas.
- Recebimento de Doações e Outros Valores: Permite registrar as doações e outros valores recebidos pelo Centro Espírita, mantendo um controle detalhado dessas transações.
- Pagamento de Contas a Pagar: Realiza o registro e controle dos pagamentos das contas a pagar do Centro Espírita, garantindo que as obrigações financeiras sejam cumpridas.
- Relatórios Mensais e Balancetes: Gera relatórios mensais que fornecem informações sobre as atividades financeiras do Centro Espírita, incluindo o balancete com o resumo das receitas e despesas.

## Como Executar o Aplicativo

1. Certifique-se de ter o Java instalado em sua máquina.
2. Clone o repositório do aplicativo.
3. Configure as informações de conexão com o banco de dados PostgreSQL no arquivo de configuração `application.properties`.
4. Execute o aplicativo utilizando uma IDE ou através do comando `mvn spring-boot:run`.
5. O aplicativo estará disponível na porta configurada (por padrão: 8080).

## Contribuições

Contribuições para o desenvolvimento e aprimoramento do aplicativo são bem-vindas. Caso queira contribuir, siga as etapas abaixo:

1. Faça um fork do repositório.
2. Crie uma nova branch com o nome descritivo da sua contribuição.
3. Realize as alterações e melhorias necessárias.
4. Faça um pull request com uma descrição clara das alterações realizadas.

## Contato

Em caso de dúvidas ou sugestões, sinta-se à vontade para entrar em contato através do e-mail marcelo_macedo01@hotmail.com.

Esperamos que este aplicativo seja útil para o gerenciamento eficiente dos associados e atividades financeiras do seu Centro Espírita. Obrigado por utilizar esta aplicação!