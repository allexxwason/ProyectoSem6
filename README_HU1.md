# 🧩 ProyectoSem6 – CRUD de Eventos con Arquitectura Hexagonal

## Estado resumido (HU1 / HU2 / HU3)
- HU1 — Catálogo In‑Memory: implementado inicialmente; evolucionado a persistencia.git add .
git commit -m "feat(HU3): refactor hexagonal, adapters JPA, mappers, tests; add application.yml"
## Cómo ejecutar localmente
1. Compilar y tests:
```bash
mvn clean test
mvn clean package
```
2. Ejecutar:
```bash
mvn spring-boot:run
```
3. Acceder:
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb, user: sa)

## Qué está en este repo (alto nivel)
- domain/ — modelos y puertos (sin dependencias Spring/JPA)
- application/usecase — implementaciones de casos de uso
- infrastructure/adapters — controladores y adaptadores JPA
- repository/ + entity/ — repositorios Spring Data + entidades JPA
- dto/ — DTOs validados por Jakarta Validation
- tests: unitarios (usecases), mappers, integración JPA (H2), controladores (MockMvc)

## Endpoints principales (ejemplos)
- POST /events
- GET /events
- GET /events/{id}
- PUT /events/{id}
- DELETE /events/{id}
- Equivalentes en /venues

Ejemplo POST /events:
```json
{
  "name": "Concierto",
  "date": "2025-12-01",
  "venue": "Arena",
  "category": "Music",
  "city": "Bogotá",
  "startDate": "2025-12-01"
}
```

## Validaciones resumidas
- EventDTO: name (required), date (YYYY‑MM‑DD required), venue (required). Otros campos opcionales.
- VenueDTO: name (3–100 chars required), location (3–100 chars required).

## Tests incluidos
- UseCases: EventUseCaseImplTest, VenueUseCaseImplTest
- Mappers: EventMapperTest, VenueMapperTest
- JPA adapters (H2): JpaEventRepositoryAdapterTest, JpaVenueRepositoryAdapterTest
- Controllers (MockMvc): EventControllerTest, VenueControllerTest

## Comandos Git para preparar la rama y subir (ejecuta desde la raíz del repo)
```bash
# 1. Actualizar branch local
git checkout -b hu3/hexagonal-complete

# 2. Añadir cambios y commitear
git add .
git commit -m "feat(HU3): refactor a arquitectura hexagonal, adapters JPA, mappers y tests; add application.yml"

# 3. Subir a remote (origin)
git push -u origin hu3/hexagonal-complete
```

Si tu repo remoto no está configurado:
```bash
git remote add origin git@github.com:TU_USUARIO/ProyectoSem6.git
git push -u origin hu3/hexagonal-complete
```

## Plantilla de PR (copiar/pegar al crear PR)
Título:
```
feat(HU3): refactor hexagonal + JPA adapters, mappers, tests
```
Descripción:
- Resumen rápido de cambios.
- Qué probar: mvn clean test && mvn spring-boot:run → abrir Swagger y H2.
- Endpoints clave a demo.
- Consideraciones: eliminar duplicados, application.yml añadida.

## Checklist antes de la demo
- [ ] mvn clean package pasa sin errores
- [ ] mvn test pasa sin fallos
- [ ] Swagger UI y H2 accesibles
- [ ] Subida de rama y PR creado

---

## 👨‍💻 Autor
**Alex Vasquez – allexxwason**  
Repositorio: [https://github.com/allexxwason/ProyectoSem6](https://github.com/allexxwason/ProyectoSem6)  
Módulo: **Spring Avanzado – Semana 1**

---



