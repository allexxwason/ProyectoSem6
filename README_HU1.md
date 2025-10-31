# 🧩 HU1 – CRUD de Eventos con Swagger

## 📘 Descripción
Esta historia de usuario corresponde a la **HU1 del ProyectoSem6**, donde se desarrolló un **CRUD completo para gestionar eventos**, utilizando **Spring Boot 3**, **Java 17**, y **Swagger (OpenAPI)** para la documentación interactiva.

El objetivo principal fue implementar una API REST sencilla con almacenamiento en memoria (sin base de datos) que permita crear, listar, actualizar y eliminar eventos.

---

## 🚀 Tecnologías Utilizadas
- **Java 17**
- **Spring Boot 3.2.4**
- **Spring Web**
- **Spring Validation**
- **SpringDoc OpenAPI (Swagger UI)**
- **Maven**

---

## 📂 Estructura del Proyecto
```
src/main/java/com/proyectosem6/
│
├── controller/
│   ├── EventController.java      → Controlador principal del CRUD
│
├── dto/
│   ├── EventDTO.java             → Objeto de transferencia de datos (DTO)
│
├── service/
│   ├── EventService.java         → Lógica de negocio in-memory
│
└── ProyectoSem6Application.java  → Clase principal de arranque
```

---

## ⚙️ Ejecución del Proyecto

1️⃣ Compilar y ejecutar con Maven:
```bash
mvn spring-boot:run
```

2️⃣ Una vez iniciado, acceder a:
- API base → [http://localhost:8080/events](http://localhost:8080/events)
- Swagger UI → [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🧠 Endpoints del CRUD

| Método | Endpoint | Descripción | Ejemplo |
|--------|-----------|--------------|----------|
| `GET` | `/events` | Obtiene todos los eventos | — |
| `GET` | `/events/{id}` | Obtiene un evento por ID | `/events/1` |
| `POST` | `/events` | Crea un nuevo evento | Ver ejemplo JSON |
| `PUT` | `/events/{id}` | Actualiza un evento existente | `/events/1` |
| `DELETE` | `/events/{id}` | Elimina un evento | `/events/1` |

---

### 📦 Ejemplo de cuerpo JSON para POST o PUT:
```json
{
  "name": "Concierto Or Navi",
  "date": "2025-11-10",
  "venue": "Templo Dorado"
}
```

---

## 🧾 Validaciones y Errores
- El campo `name` es obligatorio (si está vacío retorna **400 – Bad Request**).
- Si se intenta acceder a un ID inexistente retorna **404 – Not Found**.
- En Swagger se visualizan todos los endpoints documentados.

---

## 👨‍💻 Autor
**Alex Vasquez – allexxwason**  
Repositorio: [https://github.com/allexxwason/ProyectoSem6](https://github.com/allexxwason/ProyectoSem6)  
Módulo: **Spring Avanzado – Semana 1**

---

🟢 *HU1 Completada – API funcional con CRUD + Swagger.*



