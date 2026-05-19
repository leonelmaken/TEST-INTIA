# INTIA Assurance — Plateforme de Gestion Clients & Assurances

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/HTML5%2FCSS3%2FJS-Vanilla-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"/>
</p>

---

## Table des matières

1. [Présentation du projet](#1-présentation-du-projet)
2. [Architecture de la solution](#2-architecture-de-la-solution)
3. [Prérequis](#3-prérequis)
4. [Clonage des dépôts](#4-clonage-des-dépôts)
5. [Installation et lancement du Backend](#5-installation-et-lancement-du-backend)
6. [Installation et lancement du Frontend](#6-installation-et-lancement-du-frontend)
7. [Vérification que tout fonctionne](#7-vérification-que-tout-fonctionne)
8. [Endpoints API disponibles](#8-endpoints-api-disponibles)
9. [Documentation Swagger](#9-documentation-swagger)
10. [Structure des dépôts](#10-structure-des-dépôts)
11. [Dépannage](#11-dépannage)
12. [Informations de contact](#12-informations-de-contact)

---

## 1. Présentation du projet

INTIA Assurance est une plateforme web de gestion des **clients**, **assurances** et **succursales**
pour la société INTIA, composée d'une direction générale et de deux succursales (INTIA siège et INTIA-Yaoundé).

La plateforme est divisée en **deux composants indépendants** :

| Composant | Technologie | Dépôt GitHub |
|-----------|-------------|--------------|
| **Backend** — API REST | Spring Boot 3 + SQLite | [leonelmaken/TEST-INTIA](https://github.com/leonelmaken/TEST-INTIA) |
| **Frontend** — Interface Web | HTML5 + CSS3 + JavaScript (Vanilla) | [leonelmaken/front-end-INTIA](https://github.com/leonelmaken/front-end-INTIA) |

### Fonctionnalités couvertes

- ✅ Gestion complète des **clients** (CRUD + recherche + filtrage par succursale)
- ✅ Gestion complète des **assurances** (CRUD + suivi actives/expirées + liaison clients)
- ✅ Gestion complète des **succursales** (CRUD)
- ✅ API REST documentée via **Swagger UI**
- ✅ Monitoring de santé via **Spring Actuator**
- ✅ Base de données embarquée **SQLite** — aucune installation de SGBD requise

---

## 2. Architecture de la solution

```
┌─────────────────────────────────────────────────────────────────┐
│                        NAVIGATEUR WEB                           │
│                                                                 │
│   Frontend (HTML/CSS/JS)                                        │
│   Fichiers statiques — ouverts directement dans le navigateur  │
│   OU servis par un serveur HTTP local (port 5500 recommandé)   │
│                                                                 │
│   Appels API REST → http://localhost:8088/api/...              │
└───────────────────────────────┬─────────────────────────────────┘
                                │ HTTP/JSON
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                     BACKEND Spring Boot                         │
│                                                                 │
│   Port : 8088                                                   │
│   Package : Projet_INTIA.INTIA                                  │
│                                                                 │
│   ┌───────────┐   ┌───────────┐   ┌──────────────┐            │
│   │Controller │──▶│  Service  │──▶│  Repository  │            │
│   └───────────┘   └───────────┘   └──────┬───────┘            │
│                                          │ Spring Data JPA      │
│                                          ▼                      │
│                                   ┌──────────────┐             │
│                                   │  intia.db    │             │
│                                   │  (SQLite)    │             │
│                                   └──────────────┘             │
└─────────────────────────────────────────────────────────────────┘
```

---

## 3. Prérequis

Avant de commencer, assurez-vous que les outils suivants sont installés sur votre machine.

### Vérification rapide

Ouvrez un terminal et exécutez ces commandes :

```bash
java -version
mvn -version
git --version
```

### Ce dont vous avez besoin

| Outil | Version minimale | Téléchargement | Obligatoire |
|-------|-----------------|----------------|-------------|
| **Java JDK** | 17 ou supérieur | [adoptium.net](https://adoptium.net) | ✅ Oui |
| **Maven** | 3.6 ou supérieur | [maven.apache.org](https://maven.apache.org/download.cgi) | ✅ Oui |
| **Git** | toute version récente | [git-scm.com](https://git-scm.com) | ✅ Oui |
| **Navigateur web** | Chrome, Firefox, Edge… | — | ✅ Oui |
| **VS Code** *(optionnel)* | toute version | [code.visualstudio.com](https://code.visualstudio.com) | ⬜ Recommandé |

> **Note :** SQLite est **embarqué** dans le projet — vous n'avez rien à installer pour la base de données.

---

## 4. Clonage des dépôts

Ouvrez un terminal dans le répertoire de votre choix et exécutez :

```bash
# Créer un dossier de travail
mkdir INTIA-ASSURANCE && cd INTIA-ASSURANCE

# Cloner le backend
git clone https://github.com/leonelmaken/TEST-INTIA.git backend

# Cloner le frontend
git clone https://github.com/leonelmaken/front-end-INTIA.git frontend
```

Après clonage, votre arborescence doit ressembler à ceci :

```
INTIA-ASSURANCE/
├── backend/          ← projet Spring Boot (TEST-INTIA)
│   ├── INTIA/        ← sources Maven
│   ├── intia.db      ← base de données SQLite (auto-créée)
│   └── README.md
└── frontend/         ← interface web (front-end-INTIA)
    ├── index.html
    ├── app.js
    ├── style.css
    └── README.md
```

---

## 5. Installation et lancement du Backend

### Étape 1 — Se placer dans le bon répertoire

Le projet Maven se trouve dans le sous-dossier `INTIA/` du dépôt backend :

```bash
cd backend/INTIA
```

> **Important :** le `pom.xml` est dans `backend/INTIA/`, pas à la racine du dépôt.

### Étape 2 — Compiler le projet

```bash
mvn clean install -DskipTests
```

Cette commande :
- télécharge toutes les dépendances Maven
- compile le code source Java
- génère le fichier `.jar` exécutable dans `target/`

### Étape 3 — Lancer l'application

```bash
mvn spring-boot:run
```

**Ou bien**, lancer le `.jar` directement (après compilation) :

```bash
java -jar target/*.jar
```

### Étape 4 — Vérifier que le backend est démarré

Vous devez voir dans la console une ligne similaire à :

```
Started INTIAApplication in X.XXX seconds (JVM running for X.XXX)
```

Le backend écoute sur : **http://localhost:8088**

---

## 6. Installation et lancement du Frontend

Le frontend est une application statique — **aucune installation npm requise**.

### Option A — Ouverture directe dans le navigateur *(débutant)*

```bash
cd frontend
```

Double-cliquez simplement sur le fichier `index.html` pour l'ouvrir dans votre navigateur.

> ⚠️ Selon la configuration de votre navigateur, certaines requêtes API peuvent être bloquées par CORS
> si vous ouvrez le fichier en `file://`. La méthode B est préférable.

### Option B — Via Live Server (VS Code) *(recommandé)*

1. Ouvrez VS Code
2. Installez l'extension **Live Server** (Ritwick Dey)
3. Ouvrez le dossier `frontend/` dans VS Code
4. Faites un clic droit sur `index.html` → **"Open with Live Server"**
5. Le frontend s'ouvre sur **http://127.0.0.1:5500**

### Option C — Via un serveur HTTP Python *(universel)*

```bash
cd frontend

# Python 3
python3 -m http.server 5500

# Python 2 (si Python 3 non disponible)
python -m SimpleHTTPServer 5500
```

Ouvrez ensuite **http://localhost:5500** dans votre navigateur.

---

## 7. Vérification que tout fonctionne

Une fois le backend et le frontend lancés, vérifiez chaque point :

### Backend — Health Check

```bash
curl http://localhost:8088/actuator/health
```

Réponse attendue :
```json
{ "status": "UP" }
```

### Backend — Test d'un endpoint API

```bash
# Récupérer tous les clients
curl http://localhost:8088/api/clients

# Récupérer toutes les succursales
curl http://localhost:8088/api/succursales

# Récupérer toutes les assurances
curl http://localhost:8088/api/assurances
```

### Frontend

Ouvrez votre navigateur sur l'URL du frontend (5500 ou directement `index.html`).
L'interface doit charger correctement et afficher les données depuis l'API.

### Récapitulatif des URLs

| Service | URL |
|---------|-----|
| Backend API | http://localhost:8088 |
| Swagger UI | http://localhost:8088/swagger-ui/index.html |
| API Docs (JSON) | http://localhost:8088/api-docs |
| Health Check | http://localhost:8088/actuator/health |
| Métriques | http://localhost:8088/actuator/metrics |
| Frontend | http://localhost:5500 *(ou ouvrir index.html)* |

---

## 8. Endpoints API disponibles

### Clients — `/api/clients`

| Méthode | URL | Description |
|---------|-----|-------------|
| `GET` | `/api/clients` | Récupérer tous les clients |
| `GET` | `/api/clients/{id}` | Récupérer un client par son ID |
| `GET` | `/api/clients/succursale/{id}` | Clients d'une succursale |
| `GET` | `/api/clients/search?query=...` | Recherche par nom / email / téléphone |
| `POST` | `/api/clients` | Créer un nouveau client |
| `PUT` | `/api/clients/{id}` | Modifier un client existant |
| `DELETE` | `/api/clients/{id}` | Supprimer un client |

### Assurances — `/api/assurances`

| Méthode | URL | Description |
|---------|-----|-------------|
| `GET` | `/api/assurances` | Récupérer toutes les assurances |
| `GET` | `/api/assurances/{id}` | Récupérer une assurance par ID |
| `GET` | `/api/assurances/client/{id}` | Assurances d'un client |
| `GET` | `/api/assurances/expirees` | Assurances expirées |
| `POST` | `/api/assurances` | Créer une assurance |
| `PUT` | `/api/assurances/{id}` | Modifier une assurance |
| `DELETE` | `/api/assurances/{id}` | Supprimer une assurance |

### Succursales — `/api/succursales`

| Méthode | URL | Description |
|---------|-----|-------------|
| `GET` | `/api/succursales` | Récupérer toutes les succursales |
| `GET` | `/api/succursales/{id}` | Récupérer une succursale par ID |
| `POST` | `/api/succursales` | Créer une succursale |
| `PUT` | `/api/succursales/{id}` | Modifier une succursale |
| `DELETE` | `/api/succursales/{id}` | Supprimer une succursale |

---

## 9. Documentation Swagger

Le backend intègre **Swagger UI** pour explorer et tester l'API directement depuis le navigateur.

Accédez à : **http://localhost:8088/swagger-ui/index.html**

Vous pouvez y :
- consulter tous les endpoints disponibles
- tester chaque requête en temps réel
- visualiser les modèles de données (DTO, entités)

---

## 10. Structure des dépôts

### Backend (`TEST-INTIA`)

```
INTIA/
├── src/
│   └── main/
│       └── java/Projet_INTIA/INTIA/
│           ├── controller/          ← Contrôleurs REST (Client, Assurance, Succursale)
│           ├── service/             ← Logique métier
│           ├── repository/          ← Accès base de données (Spring Data JPA)
│           ├── model/               ← Entités JPA (Client, Assurance, Succursale)
│           ├── dto/                 ← Objets de transfert (ClientDTO, AssuranceDTO)
│           └── config/              ← Configuration Swagger + ExceptionHandler
│   └── resources/
│       └── application.properties  ← Configuration (port 8088, SQLite, Swagger, Actuator)
├── pom.xml                          ← Dépendances Maven
└── intia.db                         ← Base de données SQLite (générée au premier démarrage)
```

### Frontend (`front-end-INTIA`)

```
front-end-INTIA/
├── index.html      ← Page principale de l'application
├── app.js          ← Logique JavaScript (appels API, rendu dynamique)
└── style.css       ← Feuille de styles
```

---

## 11. Dépannage

### Le backend ne démarre pas

**Erreur :** `java: command not found`
→ Java n'est pas installé ou n'est pas dans le PATH. Installez Java 17+ depuis [adoptium.net](https://adoptium.net).

**Erreur :** `mvn: command not found`
→ Maven n'est pas installé ou n'est pas dans le PATH. Suivez le guide [maven.apache.org](https://maven.apache.org/install.html).

**Erreur :** `Port 8088 already in use`
→ Un autre processus utilise le port 8088. Pour le libérer :
```bash
# Sur Linux / macOS
kill -9 $(lsof -ti:8088)

# Sur Windows (PowerShell)
netstat -ano | findstr :8088
taskkill /PID <PID> /F
```

**Erreur :** `No suitable driver found for jdbc:sqlite`
→ Vérifiez que la dépendance SQLite est bien dans le `pom.xml` et relancez `mvn clean install`.

### Le frontend ne charge pas les données

**Symptôme :** page blanche ou liste vide
→ Vérifiez que le backend est bien démarré sur le port 8088.

**Symptôme :** erreur CORS dans la console du navigateur
→ Utilisez la méthode B ou C pour servir le frontend (Live Server ou Python HTTP). N'ouvrez pas `index.html` directement en `file://`.

**Symptôme :** `Failed to fetch` dans la console
→ Le backend n'est pas accessible. Vérifiez qu'il tourne bien avec :
```bash
curl http://localhost:8088/actuator/health
```

### La base de données semble vide

Le fichier `intia.db` est auto-généré par Hibernate au premier démarrage (`ddl-auto=update`).
Si vous voulez repartir de zéro, supprimez simplement le fichier :

```bash
rm backend/intia.db
```

Puis relancez le backend — la base sera recréée automatiquement.

---

## 12. Informations de contact

| Élément | Détail |
|---------|--------|
| **Organisation** | INTIA Assurance |
| **Dépôt Backend** | https://github.com/leonelmaken/TEST-INTIA |
| **Dépôt Frontend** | https://github.com/leonelmaken/front-end-INTIA |
| **Auteur** | [@leonelmaken](https://github.com/leonelmaken) |

---

<p align="center">
  <i>INTIA Assurance — Plateforme de Gestion Clients & Assurances — v1.0</i>
</p>
