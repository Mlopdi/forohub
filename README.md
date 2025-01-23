API REST Foro - Proyecto Challenge
Este proyecto fue desarrollado como parte del Challenge de Alura Latam, aplicando conocimientos en Java y Spring Boot para crear una API REST funcional para un foro. La API permite a los usuarios autenticados crear, listar, actualizar y eliminar tópicos de discusión.
Características
•	Autenticación JWT: Seguridad en las operaciones mediante tokens.
•	Gestión de Tópicos: Crear, listar, actualizar y eliminar tópicos.
•	Documentación: API documentada usando Swagger.
•	Pruebas: Endpoints probados con Insomnia.
Tecnologías Utilizadas
•	Java
•	Spring Boot
•	JWT (JSON Web Tokens)
•	MySQL
•	Swagger
•	Insomnia
Instalación y Configuración
1.	Clona este repositorio.
2.	Configura la base de datos en application.properties.
3.	Ejecuta el proyecto con tu IDE o usando el comando:
./mvnw spring-boot:run
4.	Usa Insomnia o Postman para probar los endpoints.
Endpoints Principales
•	GET /topicos: Lista todos los tópicos.
•	POST /topicos: Crea un nuevo tópico (requiere autenticación).
•	PUT /topicos/{id}: Actualiza un tópico existente (requiere autenticación).
•	DELETE /topicos/{id}: Elimina un tópico (requiere autenticación).
Notas
•	Este proyecto se enfoca en el backend. La implementación de un frontend es una extensión futura.

Autor Melissa Lopez Diaz
Proyecto desarrollado como parte del programa ONE (Oracle Next Education) de Alura Latam.
