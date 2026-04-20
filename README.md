# Sistema de Gestão de Pedidos (Desafio Técnico - ANBIMA)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0+-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Status](https://img.shields.io/badge/Status-Backend_Concluído-success?style=for-the-badge)

Este projeto é uma API  desenvolvida em **Spring Boot** para a receção, processamento e gestão de pedidos de uma lanchonete. O sistema é capaz de interpretar ficheiros de texto em formato de **String Posicional** (layout de tamanho fixo), calcular regras de negócio de preços/descontos e utilizar mensageria (RabbitMQ) para simular o tempo de preparação de forma desacoplada.

---

## Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot (Web, Data JPA, AMQP)
* **Banco de Dados:** PostgreSQL
* **Mensageria:** RabbitMQ
* **Utilitários:** Lombok, Jackson (JSON Parsing)

---

## Pré-requisitos para executar o projeto localmente

* Java 17
* Maven
* Docker e Docker Compose (para subir o PostgreSQL e o RabbitMQ)

---
## Próximos Passos

* [x] Construção da API REST e modelagem do banco de dados relacional.
* [x] Processamento da string posicional de tamanho fixo.
* [x] Integração com fila RabbitMQ para processamento assíncrono do status.
* [ ] Construção da interface gráfica, utilizando Angular,  para colar a string posicional e visualizar a tabela de pedidos a ser atualizada em tempo real. (Atualmente estudando a framework).

---

Desenvolvido por Lucas Bernardes.
