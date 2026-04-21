Libro Retos

Aplicación de escritorio desarrollada en JavaFX que simula una biblioteca online con autenticación y un sistema básico de gamificación.

Funcionalidades
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

Resultado:

Acierto: +10 puntos adicionales
Fallo: no se obtienen puntos extra

Este mecanismo introduce una capa de interacción y refuerza el sistema de gamificación

Estructura del proyecto
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

Proyecto orientado al desarrollo de aplicaciones cliente-servidor con Java y la incorporación de elementos de gamificación.
