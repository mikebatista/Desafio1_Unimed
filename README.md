
# Desafio1_Unimed
Projeto base para testes automatizados em BDD.

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com/), [Eclipse IDE](https://www.eclipse.org/) ou [Maven](https://maven.apache.org/)


### Como executar os testes
Clone este repositório:
```sh
$ git clone https://github.com/mikebatista/Desafio1_Unimed.git
```
**Executando no Eclipse** -  Abra o Eclipse e importe o projeto como "Projeto existente Maven". Aguarde o Maven baixar as dependências contidas no pom.xml<br>
Clique com o botão direito na classe: src/test/java/com.noesis.runners/ UnimedTest.java > Run As 'JUnit Test'.<br>
As evidencias dos testes serão armazenadas no diretório "Evidencias" localizado na raiz do projeto.<br>
**Executando por linha de comando** - abra o cmd na raiz do projeto e digite o comando
```sh
mvn surefire:test -Dtest=UnimedTest
```
OBS: para esta execução é necessário ter o Maven definido como variavel de ambiente
### Tecnologias
As seguintes ferramentas foram usadas na construção do projeto:
 - [Cucumber](https://cucumber.io/) - Suporte de BDD para testes automatizados
 - [JUnit](https://junit.org/) - Suporte à criação de testes automatizados na linguagem de programação Java.
 - [JAVA](https://www.oracle.com/technetwork/pt/java/javase/downloads/index.html) - Linguagem de programação orientada a objetos
