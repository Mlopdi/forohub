# LiterAlura - Catálogo de Libros

Bienvenidos al proyecto **LiterAlura**, una aplicación desarrollada como parte del desafío Backend de Oracle Next Education (ONE) en Alura Latam. Este proyecto permite registrar, buscar y gestionar libros en una base de datos utilizando Java, Spring y PostgreSQL.

## Descripción del Proyecto

**LiterAlura** es un catálogo de libros que permite a la persona usuaria interactuar con una base de datos para registrar y consultar información sobre libros obtenidos desde la API **Gutendex**. 

La aplicación se ejecuta en consola, facilitando el aprendizaje práctico de tecnologías Backend y fortaleciendo habilidades en el manejo de bases de datos, consumo de APIs y programación en Java.

---

## Funcionalidades Principales

1. **Buscar libro por título**:  
   La persona usuaria puede buscar libros por su título. Si el libro existe en la API **Gutendex**, se registrará en la base de datos con información como título, autor, idioma y número de descargas.  

2. **Listar libros registrados**:  
   Muestra todos los libros almacenados en la base de datos.

3. **Listar autores registrados**:  
   Permite ver un listado único de autores cuyos libros están registrados.

4. **Listar autores vivos en un año específico**:  
   Filtra autores que estaban vivos en un año proporcionado por la persona usuaria.

5. **Listar libros por idioma**:  
   Busca libros almacenados en la base de datos según su idioma (ES, EN, FR, PT, etc.).

### Consideraciones:
- Si el libro no existe en la API o ya está registrado, se muestra un mensaje indicando que no es posible registrarlo.
- La aplicación maneja entradas incorrectas de la persona usuaria para evitar errores en el programa.

---

## Tecnologías Utilizadas

- **Java 17**: Lenguaje principal del proyecto.
- **Spring Boot 3.2.4**: Framework para construir la aplicación.
- **Spring Data JPA**: Para interactuar con la base de datos.
- **PostgreSQL**: Base de datos relacional.
- **Gutendex API**: Fuente de datos con información sobre libros.
- **Trello**: Herramienta para la gestión de tareas.

---

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de:
- Tener Java 17 instalado.
- Contar con un entorno de desarrollo (Eclipse, IntelliJ IDEA o similar).
- Configurar PostgreSQL en tu ordenador.
- Leer la documentación de la API [Gutendex](https://gutendex.com) para comprender su uso.

---

## Configuración del Proyecto

1. **Crear el Proyecto**:
   - Usa el sitio [Spring Initializr](https://start.spring.io/) para generar un proyecto Maven.
   - Configura las dependencias: `Spring Data JPA`, `PostgreSQL Driver`.

2. **Configurar PostgreSQL**:
   - Crea una base de datos para el proyecto.
   - Agrega las credenciales de conexión en el archivo `application.properties`.

3. **Clonar el Repositorio**:
   - Clona este proyecto desde GitHub:
     ```bash
     git clone <URL-del-repositorio>
     ```

4. **Ejecutar el Proyecto**:
   - Compila y ejecuta el proyecto desde tu IDE favorito o con:
     ```bash
     ./mvnw spring-boot:run
     ```

---

## Estructura de Menú en Consola

1. **Buscar libro por título**
2. **Listar libros registrados**
3. **Listar autores registrados**
4. **Listar autores vivos en un año**
5. **Listar libros por idioma**
6. **Salir**
---

## Desarrolladora
**Melissa Lopez Diaz**  
Este proyecto es parte del programa ONE, diseñado para formar a personas desarrolladoras con enfoque en proyectos prácticos.🚀
