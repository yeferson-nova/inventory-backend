# Proyecto Spring Boot: Gestión de Inventario

Este proyecto es una aplicación web desarrollada con Spring Boot que permite gestionar un inventario de productos.

## Requisitos

* Java JDK 8 o superior
* Maven 3.6.0 o superior
* MySQL Server 5.7 o superior
* Un IDE como Eclipse, IntelliJ IDEA o Visual Studio Code

## Configuración

1. Crear una base de datos MySQL con el nombre `inventory`.
2. Ejecutar el script SQL `src/main/resources/data.sql` para crear las tablas y los datos iniciales.
3. Configurar el archivo `src/main/resources/application.properties` con los datos de conexión a la base de datos.

## Ejecución

1. Abrir el proyecto en un IDE.
2. Ejecutar el comando `mvn clean install` en la terminal para compilar el proyecto.
3. Ejecutar el comando `mvn spring-boot:run` en la terminal para iniciar el servidor.
4. Acceder a la aplicación a través del navegador web en la URL `http://localhost:8080`.

## Documentación

La documentación del proyecto se encuentra en este archivo README.md.

## Licencia

Este proyecto está licenciado bajo la licencia MIT. Para más información, consulte el archivo `LICENSE`.