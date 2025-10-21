# 🍳 Taller Evaluativo – Semana de Receso  
## Proyecto: API de Gestión de Recetas – *Máster Chef Celebrity*  

### 👩‍💻 Desarrollado por:
**Elizabeth Correa Suárez**  
Escuela Colombiana de Ingeniería Julio Garavito – DOSW  

---

## 📘 Descripción General  

Un importante programa de telerrealidad de cocina, **Máster Chef Celebrity**, busca lanzar un sitio web donde los televidentes puedan **consultar y publicar recetas** que hayan aparecido durante las temporadas.  

Nuestra empresa **DOSW Company** ha sido seleccionada para desarrollar la **fase inicial** del proyecto, creando una **API REST** que permita gestionar las recetas de cocina, incluyendo aquellas de **concursantes, jurados y televidentes**.  

---

## 🎯 Objetivo del Taller  

Desarrollar una **API de gestión de recetas de cocina**, aplicando buenas prácticas de desarrollo backend con **Spring Boot**, **MongoDB** como base de datos, e integrando **Swagger**, **CI/CD en GitHub Actions** y **despliegue en Azure**.  

---

## 🧩 Requerimientos Funcionales  

La API debe permitir:

## 🧩 Requerimientos Funcionales

| Nº  | Funcionalidad                        | Descripción                                              |
|-----|--------------------------------------|----------------------------------------------------------|
| 1   | Registrar receta de un televidente   | Crear receta con nombre del autor común.                 |
| 2   | Registrar receta de un participante  | Asociar receta a la temporada del programa.              |
| 3   | Registrar receta de un chef          | Registrar recetas de los jurados o chefs invitados.      |
| 4   | Listar todas las recetas             | Retornar todas las recetas almacenadas.                  |
| 5   | Consultar receta por ID              | Buscar receta por su número consecutivo.                 |
| 6   | Listar recetas por tipo de autor     | Filtrar por televidentes, chefs o participantes.         |
| 7   | Filtrar recetas por temporada        | Retornar recetas de una temporada específica.            |
| 8   | Buscar recetas por ingrediente       | Consultar recetas que contengan un ingrediente.          |
| 9   | Eliminar receta                      | Borrar una receta por ID.                                |
| 10  | Actualizar receta                    | Editar información de una receta existente.              |

---

## 🧠 Requerimientos Técnicos  

- **Lenguaje:** Java 17  
- **Framework:** Spring Boot  
- **Persistencia:** MongoDB  
- **Testing:** JUnit 5 + Mockito  
- **CI/CD:** GitHub Actions  
- **Despliegue:** Azure App Service  
- **Documentación:** Swagger UI  

---

## Arquitectura del Proyecto

📁 src/main/java/edu/dosw/taller/
│
├── 📁 controller/        # 🌐 Controladores REST (manejo de endpoints)
├── 📁 model/             # 📊 Entidades y modelos de datos (Receta, Chef, etc.)
├── 📁 persistence/       # 🗄️ Repositorios e interfaces de acceso a MongoDB
├── 📁 services/          # 🔧 Lógica de negocio
├── 📁 configs/           # ⚙️ Configuraciones globales (Swagger, CORS)
└── 📄 Application.java   # 🚀 Clase principal de Spring Boot

--- 

## 🧪 Pruebas Unitarias Requeridas  

La API debe contener pruebas que validen:

| Prueba | Descripción |
|--------|--------------|
| ✅ Registro de receta | Verifica que se pueda registrar una receta exitosamente. |
| ✅ Búsqueda por ingrediente | Comprueba que el filtro devuelva resultados correctos. |
| ✅ Manejo de error | Valida que se lance un error al consultar una receta inexistente. |

---

## ⚙️ Instalación y Ejecución Local  

### 1️⃣ Clonar el repositorio  
```bash
git clone https://github.com/<tu_usuario>/Taller_MasterChef_ElizabethCorrea.git
cd Taller_MasterChef_ElizabethCorrea
