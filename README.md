# 💍 TrueUnion - Sistema de Gestão de Casamentos

## 📌 Sobre o Projeto
O **TrueUnion** é um sistema desenvolvido para auxiliar no **planejamento, organização e execução de casamentos**, centralizando informações de **convidados, fornecedores, orçamento, cronograma, tarefas e relatórios**.

Embora tenha surgido como um **projeto acadêmico (3º semestre)**, foi construído com **foco em arquitetura profissional**, aplicando **boas práticas de engenharia de software** e tecnologias utilizadas no mercado, como **Spring Boot**, **Hibernate** e **SQL avançado**.

---

## ⚙️ Tecnologias Utilizadas
- ☕ **Java 17**
- 🧩 **Spring Boot** – Desenvolvimento de APIs REST
- 🗃️ **Hibernate / JPA** – ORM e persistência de dados
- 🐬 **MySQL** – Triggers, Views e Procedures
- 🧱 **Arquitetura em Camadas:**
  - Controller  
  - Service  
  - Repository  
  - Entities  

---

## 📋 Funcionalidades

### 👤 Módulo de Usuários
- Cadastro, autenticação e gerenciamento de usuários  
- Criptografia e tratamento de dados sensíveis  
- Controle de status (ativo/inativo) com triggers SQL  

### 💒 Planejamento do Evento
- Cadastro e gerenciamento de casamentos  
- Definição e acompanhamento de **orçamento**  
- Cronograma de atividades com prazos e lembretes  

### 💌 RSVP (Confirmação de Presença)
- Envio de convites com confirmação online  
- Controle de status: **Confirmado, Pendente, Recusado**

### 💰 Gestão Financeira
- Registro de **despesas por categoria**
- Relatórios orçamentários (**planejado x gasto**)  
- Geração de **gráficos de distribuição** de despesas  

### ✅ Tarefas e Checklist
- Criação de **checklists personalizados**
- Atribuição de responsáveis  
- Notificações automáticas de atividades próximas ao vencimento  

---

## 🏗️ Arquitetura
O sistema segue o padrão de **arquitetura em camadas**, garantindo modularidade e fácil manutenção:

Controller → expõe endpoints REST
Service → concentra regras de negócio
Repository → abstrai o acesso ao banco via JPA/Hibernate
Database → implementa triggers, views e procedures para automação e relatórios


Também são aplicados princípios de **Clean Architecture**, **boas práticas de código (SOLID)** e **tratamento de exceções padronizado**.

---

## 🧠 Banco de Dados
- **Triggers:** automatizam processos como hash de dados ao inativar usuários.  
- **Views:** otimizam consultas e relatórios financeiros.  
- **Procedures:** centralizam operações complexas de negócio.  

---

## 📊 Status do Projeto
🚧 **Em desenvolvimento**  
Novas funcionalidades estão sendo implementadas, incluindo aprimoramentos de segurança com **Spring Security** e integração com **relatórios dinâmicos**.

---

## 🤝 Contribuição
Projeto desenvolvido por **Gabriel Lima de Oliveira**, com foco em aplicar **boas práticas de backend, segurança e integração de dados**.  
Aberto para sugestões, melhorias e trocas de conhecimento.

---

## 🧩 Tecnologias Futuras
- Spring Security (autenticação e autorização avançadas)  
- Docker (ambiente de containerização)  
- Swagger (documentação de APIs)  
- Deploy em ambiente cloud (AWS / Render)

---

### 📫 Contato
**Gabriel Lima de Oliveira**  
🔗 [LinkedIn] www.linkedin.com/in/gabriel-lima-892682213 | 💻 [GitHub](https://github.com/CodeByGabrielDev)
