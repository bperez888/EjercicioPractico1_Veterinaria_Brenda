# EjercicioPractico1_Veterinaria_TUNOMBRE

Caso Práctico 1 (Variante) – Clínica Veterinaria. Spring Boot 4 · JPA · Thymeleaf · Bootstrap · MySQL.

## Cómo ejecutarlo
1. Ejecutar `src/main/resources/db/veterinaria_citas.sql` en MySQL (crea el usuario, la base `veterinaria_citas` y los datos de prueba).
2. Ejecutar la aplicación (`mvn spring-boot:run` o desde NetBeans).
3. Abrir <http://localhost:8086>.

## Rutas
| Ruta | Descripción |
|------|-------------|
| `/` | Inicio promocional + formulario de consultas |
| `/tipos-servicio` | CRUD de tipos (con cantidad de servicios) |
| `/servicios` | CRUD de servicios (tipo en lista desplegable) |
| `/citas` | Consulta de citas (solo lectura) |

## Estructura
`domain` · `repository` · `service` (+ `service.impl`) · `controllers`.
Plantilla base: `templates/plantilla/base.html`; Header, Navbar, Footer, toast y modal de eliminación: `templates/plantilla/componentes.html`.
Los textos de Header/Navbar/Footer están en `messages.properties`.
