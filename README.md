# 💍 TrueUnion - Sistema de Gestão de Casamentos  

## 📌 Sobre o Projeto  
O **TrueUnion** é um sistema desenvolvido para auxiliar no **planejamento, organização e execução de casamentos**, centralizando informações de convidados, fornecedores, orçamento, cronograma, tarefas e relatórios.  

O projeto foi desenvolvido como parte do **3º semestre da faculdade**, mas com foco em aplicar **arquitetura profissional e práticas do mercado**, utilizando **Spring Boot, Hibernate e SQL avançado**.  

---

## ⚙️ Tecnologias Utilizadas  

- **Java 17**  
- **Spring Boot** (API REST)  
- **Hibernate / JPA** (ORM e persistência de dados)  
- **MySQL** (Triggers, Views e Procedures)  
- **Arquitetura em camadas**:  
  - Controller  
  - Service  
  - Repository  
  - Entities  

---

## 📋 Funcionalidades  

### Módulo de Usuários  
- Cadastro e autenticação de usuários  

### Planejamento do Evento  
- Cadastro de eventos (casamentos).  
- Definição e acompanhamento de orçamento.  
- Cronograma com atividades e prazos.  


###RSVP   
- Envio de convites com confirmação online (RSVP).  
- Status: confirmado, pendente, recusado.  

### Gestão Financeira  
- Registro de despesas por categoria.  
- Relatórios orçamentários (orçado x gasto).  
- Gráficos de distribuição de despesas.  

### Tarefas e Checklist  
- Criação de checklists personalizados.  
- Atribuição de responsáveis.  
- Notificações de atividades próximas ao vencimento.  

---

## 🏗️ Arquitetura  

O sistema segue uma **arquitetura em camadas**, desacoplando responsabilidades:  

Controller -> Service -> Repository -> Database

markdown
Copiar código

- **Controller** → expõe endpoints REST.  
- **Service** → concentra regras de negócio.  
- **Repository** → abstrai acesso ao banco via JPA/Hibernate.  
- **Database** → MySQL com triggers e views para integridade e relatórios.  

---



📊 Status do Projeto
🔨 Em desenvolvimento – novas funcionalidades sendo implementadas.

