<div align="center">

# 🍽️ Taller MasterChef — API de Gestión de Recetas

## 🛠️ **Stack Tecnológico**

<table>
<tr>
<td align="center"><img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17"/></td>
<td align="center"><img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/></td>
<td align="center"><img src="https://img.shields.io/badge/MongoDB-Atlas-47A248?style=for-the-badge&logo=mongodb&logoColor=white" alt="MongoDB"/></td>
</tr>
<tr>
<td align="center"><img src="https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/></td>
<td align="center"><img src="https://img.shields.io/badge/Azure-App_Service-0078D4?style=for-the-badge&logo=microsoftazure&logoColor=white" alt="Azure"/></td>
<td align="center"><img src="https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger"/></td>
</tr>
<tr>
<td align="center"><img src="https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit"/></td>
<td align="center"><img src="https://img.shields.io/badge/GitHub_Actions-CI/CD-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" alt="GitHub Actions"/></td>
<td align="center"><img src="https://img.shields.io/badge/JaCoCo-Coverage-red?style=for-the-badge" alt="JaCoCo"/></td>
</tr>
</table>

---

🌟 **API REST moderna para gestionar recetas de cocina con Spring Boot + MongoDB Atlas**  
🚀 **Desplegada en Azure App Service usando Docker y GitHub Actions CI/CD**

</div>

---

## 📑 **Tabla de Contenidos**

<div align="center">

| 📌 **Sección Principal** | 🔗 **Enlace Directo** | 📌 **Sección Principal** | 🔗 **Enlace Directo** |
|-------------------------|----------------------|-------------------------|----------------------|
| 👩‍💻 **Descripción** | [Ver](#1️⃣-👩‍💻-descripción-del-proyecto) | 🌐 **API en Azure** | [Ver](#5️⃣-🌐-api-desplegada-en-azure) |
| ⚙️ **Tecnologías** | [Ver](#2️⃣-⚙️-tecnologías-utilizadas) | 📘 **Endpoints** | [Ver](#6️⃣-📘-endpoints-request--response) |
| 🧩 **Estructura** | [Ver](#3️⃣-🧩-estructura-del-proyecto) | 🧪 **Testing** | [Ver](#7️⃣-🧪-testing-y-calidad) |
| 🚀 **Instalación Local** | [Ver](#4️⃣-🚀-instalación-y-ejecución-local) | ☁️ **CI/CD Azure** | [Ver](#8️⃣-☁️-despliegue-y-cicd-en-azure) |
| 🤝 **Autor** | [Ver](#9️⃣-🤝-autor-y-créditos) | | |

</div>

### 📋 **Navegación Detallada:**

<div align="center">

| 🎯 **Sección** | 📝 **Contenido Incluido** |
|---------------|---------------------------|
| **1️⃣ Descripción** | Características principales • Contexto MasterChef • Modelo de datos |
| **2️⃣ Tecnologías** | Stack completo • Uso de cada herramienta |
| **3️⃣ Estructura** | Arquitectura • Organización del código |
| **4️⃣ Instalación** | Prerequisitos • Configuración • Ejecución • Troubleshooting |
| **5️⃣ Azure** | URL de la API • Swagger en la nube |
| **6️⃣ Endpoints** | Documentación completa • Request/Response • Códigos HTTP |
| **7️⃣ Testing** | Pruebas unitarias • JaCoCo • SonarQube |
| **8️⃣ CI/CD** | Pipeline • Evidencias • Infraestructura |
| **9️⃣ Autor** | Información personal • Contacto |

</div>

---

## 1️⃣ 👩‍💻 Descripción del Proyecto

**Taller MasterChef** es un backend que expone una API REST para administrar recetas de cocina.

- Permite **crear recetas**, ver recetas existentes, actualizar información y eliminarlas.
- Cada receta incluye nombre, ingredientes, pasos y autor.
- La información se guarda en una base de datos **MongoDB Atlas**.
- El servicio corre dentro de un contenedor **Docker**, y está desplegado en **Azure App Service**.
- Se implementó un pipeline de **CI/CD con GitHub Actions**, para que cada cambio se compile, se pruebe y se despliegue automáticamente.
- La API está documentada con **Swagger/OpenAPI**, publicada públicamente en Azure.

Este proyecto demuestra:
- Conexión a base de datos en la nube (MongoDB Atlas).
- Backend productivo con despliegue en la nube.
- Buenas prácticas de control de errores.
- Pruebas unitarias con reporte de cobertura (JaCoCo).
- Pipeline de despliegue continuo.

---

## 🎯 **Caso de Estudio: MasterChef Celebrity**

### 📺 **Contexto del Proyecto**

<div align="center">

🌟 **Un importante programa de telerrealidad de cocina** quiere lanzar su sitio web oficial donde los televidentes puedan:

</div>

- 📖 **Consultar y aprender** sobre las recetas que han aparecido a lo largo de las temporadas
- 🤝 **Interactuar** publicando sus propias recetas
- 🍳 **Conectar** con chefs, participantes y otros televidentes

---

## 📋 **Modelo de Datos**

### 🍽️ **Estructura de una Receta**

<div align="center">

| 📊 **Campo** | 📝 **Descripción** | 🔧 **Tipo** |
|-------------|-------------------|-------------|
| **🏷️ Título** | Nombre de la receta | `String` |
| **🥕 Ingredientes** | Lista con cantidades y unidades | `List<Ingredient>` |
| **👨‍🍳 Autor** | Chef, participante o televidente | `Author` |
| **📅 Temporada** | Solo para recetas de participantes | `Integer` |
| **📝 Pasos** | Instrucciones ordenadas de preparación | `List<Step>` |

</div>

### 👥 **Tipos de Autores**

<div align="center">

| 🎭 **Tipo** | 👤 **Descripción** | 📺 **Contexto** |
|------------|-------------------|-----------------|
| **👨‍🍳 Chef Juez** | `CHEF_JUDGE` | Chefs profesionales del programa |
| **🏆 Participante** | `PARTICIPANT` | Concursantes de las temporadas |
| **📺 Televidente** | `VIEWER` | Público general del programa |

</div>

---

## ⚙️ **Funcionalidades de la API**

### 📝 **Gestión de Recetas por Tipo de Autor**

<div align="center">

| 🟢 **Crear** | 🔵 **Consultar** | 🟡 **Gestionar** |
|-------------|-----------------|-----------------|
| ➕ Registrar receta de televidente | 📋 Listar todas las recetas | 🔄 Actualizar receta |
| ➕ Registrar receta de participante | 🔍 Buscar receta por ID | 🗑️ Eliminar receta |
| ➕ Registrar receta de chef | 📊 Filtrar por tipo de autor | |

</div>

### 🔍 **Búsquedas Especializadas**

<div align="center">

| 🎯 **Filtro** | 📋 **Descripción** | 🔗 **Endpoint** |
|--------------|-------------------|----------------|
| **👨‍🍳 Por Chef** | Solo recetas de chefs jueces | `GET /api/recetas/chefs` |
| **🏆 Por Participante** | Solo recetas de concursantes | `GET /api/recetas/participantes` |
| **📺 Por Televidente** | Solo recetas del público | `GET /api/recetas/televidentes` |
| **📅 Por Temporada** | Recetas de una temporada específica | `GET /api/recetas/temporada/{season}` |
| **🥕 Por Ingrediente** | Recetas que contienen un ingrediente | `GET /api/recetas/ingrediente/{ingredient}` |

</div>

---

## 💡 **Valor del Proyecto**

### 🏗️ **Para el Desarrollo Técnico**

<div align="center">

| ⚙️ **Aspecto Técnico** | ✅ **Implementación** |
|-----------------------|----------------------|
| **🏗️ Arquitectura REST** | API escalable y bien documentada |
| **☁️ Despliegue en la Nube** | Azure App Service con alta disponibilidad |
| **🔄 CI/CD** | Pipeline automatizado con GitHub Actions |
| **🧪 Calidad** | Pruebas unitarias y análisis de cobertura |
| **📘 Documentación** | Swagger UI para testing interactivo |

</div>

---

## 2️⃣ ⚙️ Tecnologías Utilizadas

| Tecnología / Herramienta | Uso en el proyecto |
|---------------------------|--------------------|
| ☕ **Java 17** | Lenguaje principal del backend |
| 🌱 **Spring Boot 3.x** | Framework para crear la API REST |
| 🔗 **Spring Web** | Controladores HTTP / endpoints |
| 🍃 **Spring Data MongoDB** | Acceso y persistencia en MongoDB |
| 🍃 **MongoDB Atlas** | Base de datos NoSQL alojada en la nube |
| 🧠 **Lombok** | Reduce código repetitivo con anotaciones como `@Getter`, `@Setter`, `@Builder` |
| 🧪 **JUnit 5 / Mockito** | Pruebas automatizadas |
| 📊 **JaCoCo** | Reporte de cobertura de pruebas |
| 🐳 **Docker** | Contenerización del servicio |
| ☁️ **Azure App Service** | Hosting / despliegue en la nube |
| 📦 **Azure Container Registry (ACR)** | Registry privado de imágenes Docker |
| 🔁 **GitHub Actions** | CI/CD automatizado |
| 📘 **Swagger (OpenAPI 3)** | Documentación interactiva de la API |
| 🛡️ **Spring Security (básico)** | Seguridad del servicio (si aplica en esta versión) |

---

## 3️⃣ 🧩 Estructura del Proyecto

### 🏗️ Arquitectura General

```
📌 Patrón seguido: Controller → Service → Repository → MongoDB

🌐 Controller   ──➤   🔧 Service   ──➤   🗄️ Repository   ──➤   🍃 MongoDB Atlas
   (HTTP)              (Lógica)           (Persistencia)         (Base de Datos)
```

### 📂 Organización del Código

```
📁 src/main/java/edu/dosw/taller/
├── 📄 Application.java                    # 🚀 Clase principal Spring Boot
├── 📁 config/                            # ⚙️ Configuraciones globales (Swagger, seguridad, MongoDB)
├── 📁 controller/                        # 🌐 Controladores REST y manejo de excepciones
│   ├── 📁 dtos/                          # 📋 Data Transfer Objects para requests/responses
│   └── 📁 exception/                     # 🚨 Excepciones personalizadas y manejo global de errores
└── 📁 model/                             # 📊 Módulo de modelos y lógica de negocio
    ├── 📁 entities/                      # 📝 Entidades de dominio (Recipe, Author, Ingredient, etc.)
    ├── 📁 persistence/                   # 🗄️ Capa de persistencia y acceso a datos
    │   └── 📁 repository/                # 🔗 Interfaces de repositorios MongoDB
    └── 📁 services/                      # 🔧 Servicios de negocio e implementaciones

📁 src/test/java/edu/dosw/taller/
└── 📁 controller/                        # 🧪 Tests unitarios de controladores con @WebMvcTest

📁 src/main/resources/
└── 📄 application*.properties            # ⚙️ Configuraciones por ambiente (dev, prod, test)

📁 Archivos del proyecto raíz:
├── 📄 README.md                          # 📚 Documentación del proyecto
├── 📄 pom.xml                            # 🔧 Configuración Maven y dependencias
├── 📄 Dockerfile                         # 🐳 Configuración para contenerización
└── 📁 .github/workflows/                 # 🔁 Pipelines CI/CD (tests automáticos y despliegue)
```

---

## 4️⃣ 🚀 Instalación y Ejecución Local

> 💡 **Para ejecutar esta API localmente**, seguir estos pasos paso a paso.

### 📋 **Prerequisitos**

Antes de comenzar, verificar que se tengan instalados:

| Herramienta | Versión Mínima | Verificar instalación |
|-------------|----------------|----------------------|
| ☕ **Java** | 17 o superior | `java -version` |
| 🔧 **Maven** | 3.6+ | `mvn -version` |
| 🐳 **Docker** (opcional) | 20.10+ | `docker --version` |

> ⚠️ **Si no se tiene Java 17**, descargarlo desde [aquí](https://adoptium.net/)

---

### 📥 **Paso 1: Descargar el proyecto**

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/Taller_MasterChef.git

# Entrar al directorio
cd Taller_MasterChef
```

---

### 🔧 **Paso 2: Configurar la base de datos**

Esta aplicación usa **MongoDB Atlas** (MongoDB en la nube). Es necesario configurar la conexión:

#### **Opción A: Usar la base de datos del proyecto (para pruebas rápidas)**

**En Windows (PowerShell):**
```powershell
$env:MONGODB_URI="mongodb+srv://elizabethcorreas_db_user:WdbDQsGH6JYgkonX@masterchef.71qa5j4.mongodb.net/?appName=MasterChef"
```

**En Windows (CMD):**
```cmd
set MONGODB_URI=mongodb+srv://elizabethcorreas_db_user:WdbDQsGH6JYgkonX@masterchef.71qa5j4.mongodb.net/?appName=MasterChef
```

**En Linux/Mac:**
```bash
export MONGODB_URI="mongodb+srv://elizabethcorreas_db_user:WdbDQsGH6JYgkonX@masterchef.71qa5j4.mongodb.net/?appName=MasterChef"
```

#### **Opción B: Crear una base de datos MongoDB Atlas propia**

1. Ir a [MongoDB Atlas](https://www.mongodb.com/atlas)
2. Crear una cuenta gratuita
3. Crear un cluster gratuito
4. Obtener el connection string
5. Configurar la variable de entorno con la URL propia

---

### ▶️ **Paso 3: Ejecutar la aplicación**

#### **Método 1: Con Maven (recomendado)**

```bash
# Compilar y ejecutar
mvn spring-boot:run
```

#### **Método 2: Con Docker**

```bash
# Construir la imagen
docker build -t masterchef-api .

# Ejecutar el contenedor
docker run -p 8080:8080 \
  --env MONGODB_URI="el-connection-string-aquí" \
  masterchef-api
```

---

### ✅ **Paso 4: Verificar funcionamiento**

Si todo salió bien, se debería ver este mensaje en la consola:
```
Started Application in X.XXX seconds (JVM running for X.XXX)
```

**Servicios disponibles:**

| 🌐 **Servicios** | URL | Descripción |
|------------------|-----|-------------|
| **🏠 API Principal** | `http://localhost:8080` | Página de inicio |
| **📘 Documentación Swagger** | `http://localhost:8080/swagger-ui/index.html` | Interfaz para probar la API |
| **🍽️ Lista de Recetas** | `http://localhost:8080/api/recetas` | JSON con las recetas |
| **❤️ Health Check** | `http://localhost:8080/actuator/health` | Estado: `{"status":"UP"}` |

---

### 🧪 **Paso 5: Probar la API**

#### **Opción A: Usar Swagger UI (más fácil)**
1. Ir a `http://localhost:8080/swagger-ui/index.html`
2. Expandir cualquier endpoint (ej: `POST /api/recetas/chef`)
3. Hacer click en "Try it out"
4. Llenar los datos y presionar "Execute"

#### **Opción B: Usar curl (línea de comandos)**
```bash
# Crear una receta de prueba
curl -X POST "http://localhost:8080/api/recetas/chef" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Pasta Carbonara",
    "author": {
      "name": "Chef Mario",
      "type": "CHEF_JUDGE"
    },
    "ingredients": [
      {
        "name": "pasta",
        "quantity": "200 gramos",
        "unit": "gramos"
      }
    ],
    "steps": [
      {
        "order": 1,
        "description": "Hervir agua con sal"
      }
    ]
  }'

# Ver todas las recetas
curl http://localhost:8080/api/recetas
```

---

### 🆘 **Problemas comunes**

| Problema | Posible causa | Solución |
|----------|---------------|----------|
| **Error: Cannot connect to MongoDB** | Variable de entorno mal configurada | Revisar el Paso 2 |
| **Error: Port 8080 already in use** | Otro programa usa el puerto 8080 | Detener proceso: `lsof -ti:8080 \| xargs kill -9` |
| **Error: Java version** | Versión incorrecta de Java | Instalar Java 17+ |
| **Maven not found** | Maven no instalado | Instalar Maven o usar `./mvnw` |

#### **🔧 Ejecutar tests (opcional)**
```bash
# Ejecutar todas las pruebas
mvn test

# Ver reporte de cobertura
mvn jacoco:report
# El reporte estará en: target/site/jacoco/index.html
```

---

> 🎉 **Al completar estos pasos** se tendrá la API de MasterChef ejecutándose localmente.  

---

## 5️⃣ 🌐 API Desplegada en Azure

> 🚀 **¡La API ya está funcionando en la nube!** 

### 📍 **URL**

| 🌐 **Servicio** | 🔗 **URL** | 📋 **Descripción** |
|-----------------|------------|-------------------|
| **📘 Swagger UI** | [`https://masterchef-api.azurewebsites.net/swagger-ui/index.html`](https://masterchef-api.azurewebsites.net/swagger-ui/index.html) | 🎯 Documentación interactiva - **¡Prueba aquí!** |

---

## 6️⃣ 📘 Endpoints (Request / Response)

> 📊 **Documentación completa de todos los endpoints disponibles en la API**

### 📸 **Vista General de Endpoints**

![Endpoints Generales](docs/imagenes/EndpointsGeneral.png)

---

### 📋 **Resumen de Endpoints**

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| 🟢 **POST** | `/api/recetas/chef` | Crear receta de chef juez |
| 🟢 **POST** | `/api/recetas/participante` | Crear receta de participante del concurso |
| 🟢 **POST** | `/api/recetas/televidente` | Crear receta de televidente |
| 🔵 **GET** | `/api/recetas` | Obtener todas las recetas |
| 🔵 **GET** | `/api/recetas/{id}` | Obtener receta específica por ID |
| 🔵 **GET** | `/api/recetas/chefs` | Obtener recetas de chefs jueces |
| 🔵 **GET** | `/api/recetas/participantes` | Obtener recetas de participantes |
| 🔵 **GET** | `/api/recetas/televidentes` | Obtener recetas de televidentes |
| 🔵 **GET** | `/api/recetas/temporada/{season}` | Obtener recetas por temporada |
| 🔵 **GET** | `/api/recetas/ingrediente/{ingredient}` | Buscar recetas por ingrediente |
| 🟡 **PUT** | `/api/recetas/{id}` | Actualizar receta existente |
| 🔴 **DELETE** | `/api/recetas/{id}` | Eliminar receta por ID |

---

## 📝 **Detalles de cada Endpoint**

### 🟢 **POST /api/recetas/chef** - Crear Receta de Chef

| **Request** | **Response** |
|-------------|--------------|
| ```json<br>{<br>  "title": "Pasta Carbonara Gourmet",<br>  "author": {<br>    "name": "Chef Mario Batali",<br>    "type": "CHEF_JUDGE"<br>  },<br>  "ingredients": [<br>    {<br>      "name": "pasta",<br>      "quantity": "200 gramos",<br>      "unit": "gramos"<br>    }<br>  ],<br>  "steps": [<br>    {<br>      "order": 1,<br>      "description": "Hervir agua con sal"<br>    }<br>  ]<br>}<br>``` | ```json<br>{<br>  "id": "507f1f77bcf86cd799439011",<br>  "title": "Pasta Carbonara Gourmet",<br>  "author": {<br>    "name": "Chef Mario Batali",<br>    "type": "CHEF_JUDGE"<br>  },<br>  "ingredients": [...],<br>  "steps": [...],<br>  "createdAt": "2023-12-15T10:30:00Z",<br>  "updatedAt": "2023-12-15T10:30:00Z"<br>}<br>``` |

---

### 🟢 **POST /api/recetas/participante** - Crear Receta de Participante

| **Request** | **Response** |
|-------------|--------------|
| ```json<br>{<br>  "title": "Tacos al Pastor Caseros",<br>  "author": {<br>    "name": "Ana García",<br>    "type": "PARTICIPANT"<br>  },<br>  "season": 3,<br>  "ingredients": [<br>    {<br>      "name": "carne de cerdo",<br>      "quantity": "500 gramos",<br>      "unit": "gramos"<br>    }<br>  ],<br>  "steps": [<br>    {<br>      "order": 1,<br>      "description": "Marinar la carne"<br>    }<br>  ]<br>}<br>``` | ```json<br>{<br>  "id": "507f1f77bcf86cd799439012",<br>  "title": "Tacos al Pastor Caseros",<br>  "author": {<br>    "name": "Ana García",<br>    "type": "PARTICIPANT"<br>  },<br>  "season": 3,<br>  "ingredients": [...],<br>  "steps": [...],<br>  "createdAt": "2023-12-15T11:00:00Z",<br>  "updatedAt": "2023-12-15T11:00:00Z"<br>}<br>``` |

---

### 🟢 **POST /api/recetas/televidente** - Crear Receta de Televidente

| **Request** | **Response** |
|-------------|--------------|
| ```json<br>{<br>  "title": "Galletas de Chocolate",<br>  "author": {<br>    "name": "María Rodríguez",<br>    "type": "VIEWER"<br>  },<br>  "ingredients": [<br>    {<br>      "name": "harina",<br>      "quantity": "300 gramos",<br>      "unit": "gramos"<br>    }<br>  ],<br>  "steps": [<br>    {<br>      "order": 1,<br>      "description": "Mezclar ingredientes"<br>    }<br>  ]<br>}<br>``` | ```json<br>{<br>  "id": "507f1f77bcf86cd799439013",<br>  "title": "Galletas de Chocolate",<br>  "author": {<br>    "name": "María Rodríguez",<br>    "type": "VIEWER"<br>  },<br>  "ingredients": [...],<br>  "steps": [...],<br>  "createdAt": "2023-12-15T12:00:00Z",<br>  "updatedAt": "2023-12-15T12:00:00Z"<br>}<br>``` |

---

### 🔵 **GET /api/recetas** - Obtener Todas las Recetas

| **Request** | **Response** |
|-------------|--------------|
| `GET /api/recetas`<br><br>*(No requiere body)* | ```json<br>[<br>  {<br>    "id": "507f1f77bcf86cd799439011",<br>    "title": "Pasta Carbonara Gourmet",<br>    "author": {<br>      "name": "Chef Mario Batali",<br>      "type": "CHEF_JUDGE"<br>    },<br>    "ingredients": [...],<br>    "steps": [...],<br>    "createdAt": "2023-12-15T10:30:00Z",<br>    "updatedAt": "2023-12-15T10:30:00Z"<br>  },<br>  {<br>    "id": "507f1f77bcf86cd799439012",<br>    "title": "Tacos al Pastor",<br>    "author": {...},<br>    "season": 3,<br>    "ingredients": [...],<br>    "steps": [...]<br>  }<br>]<br>``` |

---

### 🔵 **GET /api/recetas/{id}** - Obtener Receta por ID

| **Request** | **Response** |
|-------------|--------------|
| `GET /api/recetas/507f1f77bcf86cd799439011`<br><br>*(Path parameter: id)* | **✅ Éxito (200):**<br>```json<br>{<br>  "id": "507f1f77bcf86cd799439011",<br>  "title": "Pasta Carbonara Gourmet",<br>  "author": {<br>    "name": "Chef Mario Batali",<br>    "type": "CHEF_JUDGE"<br>  },<br>  "ingredients": [...],<br>  "steps": [...],<br>  "createdAt": "2023-12-15T10:30:00Z",<br>  "updatedAt": "2023-12-15T10:30:00Z"<br>}<br>```<br><br>**❌ Error (404):**<br>```json<br>{<br>  "status": 404,<br>  "error": "Not Found",<br>  "message": "Receta no encontrada con ID: ...",<br>  "timestamp": "2023-12-15T15:30:00Z"<br>}<br>``` |

---

### 🔵 **GET /api/recetas/temporada/{season}** - Recetas por Temporada

| **Request** | **Response** |
|-------------|--------------|
| `GET /api/recetas/temporada/3`<br><br>*(Path parameter: season)* | ```json<br>[<br>  {<br>    "id": "507f1f77bcf86cd799439012",<br>    "title": "Tacos al Pastor Caseros",<br>    "author": {<br>      "name": "Ana García",<br>      "type": "PARTICIPANT"<br>    },<br>    "season": 3,<br>    "ingredients": [...],<br>    "steps": [...],<br>    "createdAt": "2023-12-15T11:00:00Z",<br>    "updatedAt": "2023-12-15T11:00:00Z"<br>  }<br>]<br>``` |

---

### 🔵 **GET /api/recetas/ingrediente/{ingredient}** - Buscar por Ingrediente

| **Request** | **Response** |
|-------------|--------------|
| `GET /api/recetas/ingrediente/pasta`<br><br>*(Path parameter: ingredient)* | ```json<br>[<br>  {<br>    "id": "507f1f77bcf86cd799439011",<br>    "title": "Pasta Carbonara Gourmet",<br>    "author": {<br>      "name": "Chef Mario Batali",<br>      "type": "CHEF_JUDGE"<br>    },<br>    "ingredients": [<br>      {<br>        "name": "pasta",<br>        "quantity": "200 gramos",<br>        "unit": "gramos"<br>      }<br>    ],<br>    "steps": [...],<br>    "createdAt": "2023-12-15T10:30:00Z",<br>    "updatedAt": "2023-12-15T10:30:00Z"<br>  }<br>]<br>``` |

---

### 🟡 **PUT /api/recetas/{id}** - Actualizar Receta

| **Request** | **Response** |
|-------------|--------------|
| `PUT /api/recetas/507f1f77bcf86cd799439011`<br><br>```json<br>{<br>  "title": "Pasta Carbonara Actualizada",<br>  "author": {<br>    "name": "Chef Mario Batali",<br>    "type": "CHEF_JUDGE"<br>  },<br>  "ingredients": [<br>    {<br>      "name": "pasta integral",<br>      "quantity": "250 gramos",<br>      "unit": "gramos"<br>    }<br>  ],<br>  "steps": [<br>    {<br>      "order": 1,<br>      "description": "Usar pasta integral"<br>    }<br>  ]<br>}<br>``` | ```json<br>{<br>  "id": "507f1f77bcf86cd799439011",<br>  "title": "Pasta Carbonara Actualizada",<br>  "author": {<br>    "name": "Chef Mario Batali",<br>    "type": "CHEF_JUDGE"<br>  },<br>  "ingredients": [...],<br>  "steps": [...],<br>  "createdAt": "2023-12-15T10:30:00Z",<br>  "updatedAt": "2023-12-15T16:45:00Z"<br>}<br>``` |

---

### 🔴 **DELETE /api/recetas/{id}** - Eliminar Receta

| **Request** | **Response** |
|-------------|--------------|
| `DELETE /api/recetas/507f1f77bcf86cd799439011`<br><br>*(Path parameter: id)* | **✅ Éxito (204):**<br>*(No Content - Eliminación exitosa)*<br><br>**❌ Error (404):**<br>```json<br>{<br>  "status": 404,<br>  "error": "Not Found",<br>  "message": "No se puede eliminar. Receta no encontrada con ID: ...",<br>  "timestamp": "2023-12-15T17:00:00Z"<br>}<br>``` |

---

### 📊 **Códigos de Estado HTTP**

| Código | Descripción | Cuándo ocurre |
|--------|-------------|---------------|
| **200** | ✅ OK | Operación exitosa (GET, PUT) |
| **201** | ✅ Created | Receta creada exitosamente (POST) |
| **204** | ✅ No Content | Receta eliminada exitosamente (DELETE) |
| **400** | ❌ Bad Request | Datos inválidos en el request |
| **404** | ❌ Not Found | Receta no encontrada |
| **500** | ❌ Internal Server Error | Error interno del servidor |

---

## 7️⃣ 🧪 Testing y Calidad

> 🎯 **Garantizando la calidad del código** mediante pruebas automatizadas y análisis de cobertura.

### 🔬 **Tipos de Pruebas Implementadas**

| 🧪 **Tipo de Prueba** | 📂 **Capa** | 🛠️ **Herramientas** | 📋 **Qué se prueba** |
|----------------------|-------------|---------------------|---------------------|
| **🎯 Unitarias** | Controladores | JUnit 5 + Mockito + @WebMvcTest | Endpoints HTTP, validaciones, respuestas |
| **🔧 Unitarias** | Servicios | JUnit 5 + Mockito | Lógica de negocio, manejo de excepciones |

### ✅ **Evidencia de Pruebas logradas**

![Tests Pasando](docs/imagenes/jacoco.png)

### 📊 **Cobertura con JaCoCo**

> 📈 **Medición de cobertura de código** para asegurar que las pruebas cubren la funcionalidad implementada.

#### **🎯 Métricas de Cobertura**

![Reporte JaCoCo](docs/imagenes/JacocoReporte.png)

| 📊 **Métrica** | 🎯 **Objetivo** | ✅ **Alcanzado** | 📈 **Estado** |
|---------------|----------------|------------------|---------------|
| **Líneas de Código** | > 80% | 92% | ✅ Excelente |
| **Branches** | > 70% | 66% | ⚠️ Cerca del objetivo |
| **Métodos** | > 80% | 100% | ✅ Perfecto |
| **Clases** | > 90% | 100% | ✅ Perfecto |

> 📊 **Cobertura General:** **92%** - Excelente calidad de pruebas

---

### 🔍 **Análisis de Calidad con SonarQube**

![SonarQube Análisis](docs/imagenes/SonarQubeReporte.png)

| 🏆 **Métrica** | 📊 **Resultado** | 🎯 **Calificación** |
|---------------|------------------|-------------------|
| **🐛 Bugs** | 0 | ✅ A |
| **🔒 Vulnerabilidades** | 0 | ✅ A |
| **👃 Code Smells** | 2 | ✅ A |
| **📊 Cobertura** | 85.2% | ✅ A |
| **🔄 Duplicación** | 0.0% | ✅ A |

> 🎉 **Quality Gate:** ✅ **PASSED** - El código cumple con todos los estándares de calidad.

---

## 8️⃣ ☁️ Despliegue y CI/CD en Azure

> 🚀 **Despliegue automatizado** usando GitHub Actions + Azure App Service con contenedores Docker.

### 🔄 **Flujo de CI/CD**

```mermaid
graph LR
    A[📝 Push to main] --> B[🔄 GitHub Actions]
    B --> C[🔨 Build Maven]
    C --> D[🧪 Run Tests]
    D --> E[📊 JaCoCo Report]
    E --> F[🐳 Build Docker]
    F --> G[📦 Push to ACR]
    G --> H[🚀 Deploy to Azure]
    H --> I[✅ Production Ready]
```

### 📋 **Pipeline Detallado**

| 🔢 **Paso** | 🛠️ **Acción** | ⏱️ **Duración** | 📋 **Descripción** |
|------------|---------------|-----------------|-------------------|
| **1** | 📥 Checkout | ~10s | Descarga código fuente |
| **2** | ☕ Setup JDK 17 | ~20s | Configura entorno Java |
| **3** | 🔨 Maven Build | ~2m | Compila y empaqueta aplicación |
| **4** | 🧪 Run Tests | ~1m | Ejecuta pruebas unitarias |
| **5** | 📊 JaCoCo Report | ~15s | Genera reporte de cobertura |
| **6** | 🐳 Docker Build | ~3m | Construye imagen Docker |
| **7** | 🔐 ACR Login | ~5s | Autenticación en Azure Container Registry |
| **8** | 📦 Docker Push | ~2m | Publica imagen en ACR |
| **9** | 🚀 Azure Deploy | ~1m | Despliega contenedor en Azure App Service |

### ✅ **Evidencia de Despliegue Exitoso**

#### **🔄 GitHub Actions - Pipeline Exitoso**
![GitHub Actions Success](docs/imagenes/GitHubActionsPipeline.png)

#### **☁️ Azure App Service - Aplicación Desplegada**
![Azure App Service](docs/imagenes/AzureAppService.png)
![Azure Swagger ](docs/imagenes/AzureSwagger.png)

#### **📦 Azure Container Registry - Imagen Publicada**
![Azure Container Registry](docs/imagenes/AzureContainerRegistry.png)

### 🏗️ **Infraestructura en Azure**

| 🧩 **Componente** | 📋 **Servicio Azure** | 💰 **Tier** | 🎯 **Función** |
|------------------|----------------------|--------------|----------------|
| **🌐 Web App** | Azure App Service (Linux) | B1 Basic | Hosting de la aplicación Spring Boot |
| **🍃 Base de Datos** | MongoDB Atlas | M0 Free | Almacenamiento de recetas |
| **📦 Container Registry** | Azure Container Registry | Basic | Registry privado de imágenes Docker |
| **🔄 CI/CD** | GitHub Actions | Free | Pipeline de despliegue automático |

### 🔧 **Variables de Entorno en Producción**

| 🌍 **Variable** | 📋 **Descripción** | 🔒 **Configurada en** |
|----------------|-------------------|---------------------|
| `MONGODB_URI` | Conexión a MongoDB Atlas | Azure App Service Settings |
| `SPRING_PROFILES_ACTIVE` | Perfil activo (prod) | Azure App Service Settings |
| `ACR_LOGIN_SERVER` | URL del Container Registry | GitHub Secrets |
| `ACR_USERNAME` | Usuario ACR | GitHub Secrets |
| `ACR_PASSWORD` | Contraseña ACR | GitHub Secrets |

---

## 9️⃣ 🤝 Autor y Créditos

### 👩‍💻 **Desarrollado por:**

<div align="center">

![Profile](https://img.shields.io/badge/👩‍💻-Elizabeth%20Correa%20Suárez-blue?style=for-the-badge)

**Elizabeth Correa Suárez**  
🎓 *Estudiante de Ingeniería de Sistemas*  
🏛️ *Escuela Colombiana de Ingeniería Julio Garavito*  
📚 *Curso: Desarrollo y Operaciones de Software (DOSW) – 2025-1*

</div>

---



