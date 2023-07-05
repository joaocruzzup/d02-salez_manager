<h1 align="center">
📊<br>Sistema SaleZ Manager - D02 Catalisa
</h1>

## Sumário
1. [O que é o SaleZ Manager?](#-o-que-é-o-salez-manager)
2. [Funcionalidades do SaleZ Manager](#-funcionalidades-do-salez-manager)
3. [Entendendo o Workflow do Projeto - Github Workflow](#-entendendo-o-workflow-do-projeto-github-workflow)
4. [Como executar?](#-como-executar)
5. [Tutorial do Sistema](#-tutorial-do-sistema)
6. [Assuntos do java utilizados](#-assuntos-do-java-utilizados)
7. [Testes Unitários implementados](#-testes-unitários-implementados)
8. [Possíveis Melhorias](#-possíveis-melhorias)
9. [Autor](#-autor)

---

## 🪪 O que é o SaleZ Manager?

SaleZ Manager é o nome de um sistema criado com o objetivo de facilitar o gerenciamento de vendas, vendedores e clientes em terminal criado com a partir do desafio proposto do Programa Catalisa. 

E não se confunda: o nome SaleZ não foi escrito errado, o "Z" é marca registrada da Zup, empresa a qual eu faço parte! #AutoridadeTech⚡

Assim o objetivo do desafio era :
1. Criar um sistema de gerenciamento de vendas
2. Permitir cadastrar, listar e buscar Clientes, Vendedores e Vendas
3. Realizar validações de erros para o sistema não quebrar enquanto roda
4. Realizar testes unitários

---

## 🧩 Funcionalidades do SaleZ Manager

As funcionalidades do Sistema estão de acordo o enunciado, então segue abaixo:

📘 Funcionalidades Entrega Mínima
🔹 O projeto permite cadastrar diversas vendas no sistema. 
🔹 O sistema não permite cadastrar vendas para clientes não cadastrados. 
🔹 O sistema não permite cadastrar vendas de vendedores não cadastrados.
🔹 O sistema permite listar todas as vendas cadastradas. 
🔹 O sistema permite listar todos os vendedores cadastrados. 
🔹 O sistema permite listar todos os clientes cadastrados.

📙 Funcionalidades Entrega Média
🔸 O sistema não permite cadastrar clientes com e-mail inválido (sem @). 
🔸 O sistema não permite cadastrar vendedores com e-mail inválido (sem @). 
🔸 O sistema não permite cadastrar clientes com CPFs repetidos. 
🔸 O sistema não permite cadastrar vendedores com CPFs repetidos. 
🔸 O sistema não permite cadastrar clientes com e-mails repetidos. 
🔸 O sistema não permite cadastrar vendedores com e-mails repetidos.

📘 Funcionalidades Entrega Máxima
🔹 O sistema permite pesquisar todas as compras de um cliente em específico através de seu CPF. 
🔹 O sistema permite pesquisar todas as vendas de um vendedor em específico através de seu e-mail. 
🔹 O sistema contém uma cobertura de testes de 100%.

---

## 🔀 Entendendo o WorkFlow do Projeto - Github Workflow

Para o workflow do projeto eu escolhi fazer pelo Github Worflow tendo em vista ser um workflow mais simples, 
e como esse projeto é o primeiro que tentei implementar algum tipo de workflow acredito que ele foi ideal para isso.

O Github Worflow se basea na criação de branchs para que os commits sejam feitos nelas antes de serem realizadas na branch main, a fim de proteger ela.
Assim, há a criação de duas branchs:
- branch feature: responsável por receber os commits das features e após isso lançar pull request para a branch main;
- branch fix: responsável por receber os commits das fix e após isso lançar pull request para a branch main;

Porém, como o desafio se baseia na criação de entregas (mínima, média e máxima) decidi criar branchs respectivas para as entregas e assim aplicar 
o Github Workflow a fim de me desafiar um pouco mais. (espero ter conseguido implementá-lo bem, acredito que consegui).

Assim o Workflow do projeto se baseia em: 
1. Criar branch da entrega (mínima, média, máxima)
2. Para commits feature, criar branch de feature da respectiva entrega;
3. Após o commit feature, realizar merge na branch da entrega respectiva;
4. Para commits fix, criar branch de fix da respectiva entrega a partir da branch feature;
5. Após o commit fix, realizar merge nas branch feature e entrega;
6. Por fim ao finalizar cada entrega, realizar o merge da entrega à main.

OBS.: Normalmente as branchs feature e fix são excluidas após realizar o merge, e assim a cada feature e fix são criadas novas. 
Porém, como não queria me arriscar a excluir uma branch errada decidi mantê-las a fim de aprendizado.

Segue abaixo a imagem do Github Worflow do Projeto:

![Imagem worfklow](https://github.com/joaocruzzup/d02-salez_manager/blob/main/imgbranch.png)

---

## ⏯️ Como executar?

- Você precisará ter o [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11) instalado no seu computador;
- Baixe o repositório do projeto;
- Abra o terminal e navegue até o diretório view, onde haverá o arquivo Main.java;
- Compile o arquivo executando o comando `javac` no arquivo [Main](https://github.com/joaocruzzup/d02-salez_manager/blob/main/src/main/java/org/example/view/Main.java):
```
javac Main.java
```
- Após compilar, execute o comando `java`, como mostra abaixo:
```
java Main.java
```

---

## 📋 Tutorial do Sistema

1. Inicie o sistema e pressione ENTER para ir ao Menu Principal;
2. No Menu Principal selecione qual seção você deseja acessar (Clientes, vendedores ou vendas);
3. Ao acessar as seções irá ser sugerido opções para você selecionar como ( Cadastrar, Listar, Buscar, Sair para o Menu Principal )
4. Lembre-se: Só é possível cadastrar uma venda após ter um vendedor e cliente cadastrado.
5. Atente-se aos erros que podem surgir no programa, você será avisado quando isso ocorrer.
6. Prontinho, com isso você já consegue utilizar o sistema :D

---

## ☕ Assuntos do Java Utilizados

- Java básico/intermediário
- Fundamentos de POO
- Fundamentos de SOLID
- Fundamentos de arquietura
- Testes Unitários com JUNIT e Mockito

---

## 🧪 Testes Unitários Implementados

Os testes unitários foram implementados utilizando o JUnit 5 e o Mockito.
Foi possível realizar 85% dos métodos do programa, sendo alguns não possíveis devido apenas imprimirem algo relacionado ao menu ou aplicação.

Além disso, vale salientar que em algumas classes não consegui implementar com a junção do Mockito e do Junit, tendo assim o teste apenas com os Assertions do JUnit.
Porém, apesar disso todos os testes rodaram.

---

## 🚧 Possíveis Melhorias

- Melhorar a aplicação de SOLID no programa, algumas classes possuem muitas responsabilidades como é o caso de VendaService.
- Melhorar a arquitetura do projeto, a princípio pensei em algo como MVC mas acabou fugindo um pouco desse escopo.
- Utilizar bancos de dados externos para gerenciar os dados presentes no programa

---

## 👨‍💻 Autor

Nome: João Cruz<br>Linkedin: https://www.linkedin.com/in/joaosilvacruz/

---

<h4 align=center>Made with 💚 by <a href="https://github.com/joaocruzzup">João Cruz</a></h4>




