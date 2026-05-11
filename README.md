## Libro Retos

Aplicación de escritorio desarrollada en JavaFX que simula una biblioteca online con autenticación y un sistema básico de gamificación.

## Funcionalidades
Autenticación de usuarios mediante JWT
Registro y gestión de usuario
Consulta de catálogo de libros
Reserva y devolución de libros
Gestión de lista de espera
Sistema de puntos
Minijuego “Caza-palabras” asociado a los préstamos
Caza-palabras

Al realizar una reserva, el sistema genera un reto:

Se muestran tres palabras
Solo una está relacionada con el libro seleccionado
El usuario debe elegir la opción correcta

## Resultado:

Acierto: +10 puntos adicionales
Fallo: no se obtienen puntos extra y además se le restan 5 puntos.

Este mecanismo introduce una capa de interacción y refuerza el sistema de gamificación

## Estructura del proyecto
cliente     → comunicación HTTP con el servidor
controller  → lógica de interacción
service     → lógica de negocio
dto         → objetos de transferencia de datos
view        → interfaz gráfica (JavaFX)
Flujo de uso
Inicio de sesión
Consulta de libros disponibles
Reserva de un libro
Resolución del reto asociado
Actualización de puntos
Tecnologías utilizadas
Java 17
JavaFX
Spring Boot (backend)
JWT
HTTP Client
Estado actual
Funcional: autenticación, gestión de libros, sistema de puntos y minijuego
Pendiente: integración completa del ranking
Autor

------------------------------------------------------------------------------

## Últimas mejoras implementadas
## v2.0
### Mejoras de navegación
- Se ha añadido un botón de flecha para volver atrás en distintas pantallas, haciendo la navegación más natural e intuitiva.

### Panel de administración
- Se ha diseñado una nueva pantalla de administración.
- El administrador puede realizar las siguientes acciones:
  - Listar todos los usuarios.
  - Crear nuevos usuarios.
  - Eliminar usuarios.
  - Restablecer contraseñas de usuarios.
  - Listar todos los libros.
  - Insertar nuevos libros.
  - Eliminar libros.
  - Tabla ranking puntos usuarios.

### Mejoras en el listado de libros
- Se ha añadido un sistema de filtrado dinámico por:
  - Título.
  - Autor.
  - Editorial.
- La pantalla distingue entre usuarios normales y administradores:
  - Los usuarios pueden seleccionar libros para reservarlos o devolverlos.
  - Los administradores pueden consultar el catálogo, pero tienen deshabilitada la interacción de reserva sobre los libros.

