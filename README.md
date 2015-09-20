# Projeto Terra
Teste de performance entre os bancos MySQL (Relacional) e MongoDB (NoSQL) escrito em Java

### Requisitos
1) Banco de dados MySQL instalado

2) Banco de dados MongoDB instalado

3) IDE Eclipse

4) Java 8

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
Não é preciso realizar nenhum procedimento de criação das estruturas na base MongoDB, quando o comando de INSERT for executado o MongoDB automaticamente irá criar as estruturas.

#### No Eclipse
Clone este repo na IDE Eclipse e importe o projeto "Terra" em seu workspace

### Testes de performance
Os testes de performance foram realizados utilizando dados Geolife disponível em: http://research.microsoft.com/apps/pubs/?id=152883

Configure o arquivo "configuracoes.properties" com os dados de conexão dos bancos MySQL e MongoDB, informe também neste arquivo o caminho onde o TXT Geolife foi baixado e escolha a quantidade de concorrência das requisições

Por fim, execute os Launches (InserirMySQL, InserirMongoDB, ConsultarMySQL, ConsultarMongoDB) que realizarão os testes de performance. Os resultados são apresentados no console

Os testes foram realizados medindo o tempo de inserção de um bloco de coordenadas (latitude e longitude) e medido também o tempo médio de uma consulta em determinada latitude e longitude.

### Resultado dos testes
Segue os resultados do teste que foi realizado em uma VM Debian com 2G de memória, 2 processadores e sem concorrência

Tempo de inserção
```
Quantidade (inserts)    MySQL              MongoDB
100                     675 ms (0s)        457 ms (0s)
1.000                   3 s                1 s
10.000                  25 s               3 s
90.000                  238 s (3m)         22 s
```

Tempo médio por uma consulta
```
Quantidade (selects)      MySQL                   MongoDB
100                       670.000 ns (0s)         90.000 ns (0s)
1.000                     618.000 ns (0s)         17.000 ns (0s)
10.000                    1 ms (0s)               3.000 ns (0s)
90.000                    5 ms (0s)               477 ns (0s)
```

Legenda
```
m - minutos
s - segundos
ms - milissegundos
ns - nanosegundos
```
