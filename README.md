# Projeto Terra
Teste de performance entre os bancos MySQL (Relacional) e MongoDB (NoSQL) escrito em Java

### Requisitos
1) Banco de dados MySQL instalado
2) Banco de dados MongoDB instalado
3) IDE Eclipse

### Preparando o ambiente MySQL
Execute os comandos abaixo no servidor MySQL
```
CREATE SCHEMA `terra` ;

CREATE TABLE `terra`.`coordenadas` (
  `idcoordenadas` INT NULL AUTO_INCREMENT,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  PRIMARY KEY (`idcoordenadas`));
```

### Preparando o ambiente MongoDB
Não é preciso realizar nenhum passo para a criação das estruturas da base MongoDB, quando o comando de INSERT for executado o MongoDB automaticamente irá criar as estruturas.

#### No Eclipse
Clone o repo na IDE Eclipse e importe o projeto Terra em seu workspace
```
https://github.com/jclaudiocf/terra.git
```

### Testes de performance

### Resultado dos testes
