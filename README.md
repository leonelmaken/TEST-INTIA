# 🛡️ INTIA Assurance API

API REST de gestion des clients, assurances et succursales développée avec **Spring Boot**, **Hibernate** et **SQLite**.

Ce projet fait partie de la plateforme INTIA permettant de digitaliser la gestion des assurances.

---

## 🚀 Fonctionnalités

### 👥 Gestion des clients
- Création, modification, suppression
- Recherche par nom/email/téléphone
- Filtrage par succursale

### 🛡️ Gestion des assurances
- Création / mise à jour / suppression
- Suivi des assurances actives et expirées
- Liaison avec les clients

### 🏢 Gestion des succursales
- CRUD complet des agences
- Attribution des clients

---

## 🧱 Architecture du projet
Projet_INTIA.INTIA
├── controller → APIs REST
├── service → logique métier
├── repository → accès base de données
├── model → entités JPA
├── dto → objets de transfert
├── config → Swagger + Exception Handler

---

## ⚙️ Technologies utilisées

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- SQLite
- Spring Validation
- Springdoc OpenAPI (Swagger)
- Maven

---

## 🗄️ Base de données

Configuration SQLite :

```properties
spring.datasource.url=jdbc:sqlite:intia.db
spring.jpa.hibernate.ddl-auto=update
