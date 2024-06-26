# Sistema Web de Gestión de Cursos - Universidad Amazónica de Pando

Este repositorio contiene el código fuente del prototipo de un sistema web desarrollado para la gestión de cursos en la Escuela Técnica de la Universidad Amazónica de Pando (UAP). El sistema está diseñado para optimizar la gestión de cursos y la generación de reportes, incluyendo datos estadísticos relevantes.

## Características principales

- Registro y gestión de cursos: Permite a los administradores y personal administrativo registrar, actualizar y eliminar información sobre los cursos ofrecidos por la institución.
- Inscripción de estudiantes: Facilita la inscripción de estudiantes en los cursos disponibles, así como el seguimiento de su participación y progreso.
- Generación de reportes: Proporciona la funcionalidad para generar informes detallados sobre la inscripción de cursos y otros datos estadísticos relevantes para la toma de decisiones institucionales.

## Tecnologías utilizadas

- Java - Spring Boot: Framework de desarrollo de aplicaciones web en Java que proporciona una estructura robusta y modular para el desarrollo rápido y eficiente.
- PostgreSQL: Sistema de gestión de bases de datos relacional utilizado para almacenar y gestionar la información relacionada con los cursos y los estudiantes.
- HTML, CSS y JavaScript: Tecnologías estándar para el desarrollo de interfaces de usuario web, utilizadas para crear una experiencia interactiva y amigable para el usuario.
- Lucidchart y Power Designer: Herramientas utilizadas para el diseño del modelo de base de datos y la representación gráfica de la arquitectura del sistema.

## Instrucciones de instalación y ejecución

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu entorno de desarrollo preferido (por ejemplo, IntelliJ IDEA, Eclipse).
3. Configura la base de datos PostgreSQL y asegúrate de que esté en funcionamiento.
4. Importa el proyecto y realiza las configuraciones necesarias para conectarte a la base de datos.
5. Ejecuta la aplicación y accede a través del navegador web utilizando la URL proporcionada por Spring Boot.


## Generación de Interfaces de Typescript-Frontend desde Clases de Java-Backend
Este proyecto utiliza Maven y el complemento TypeScript Generator para generar archivos TypeScript a partir de clases Java. Resúmen:<br> 
```Clase.java -> interface.ts```

Sigue los pasos a continuación para generar las interfaces TypeScript:

1) Abre la terminal desde la raiz del proyecto
2) Ejecuta el siguiente comando:
    ```bash
    mvn process-classes -P generar-archivos-typescript
    ```
### Explicación
* **mvn:** Es el comando principal que debes usar para interactuar con el proyecto desde la terminal.


* **process-classes:** Es una instruccion de Maven que indica cómo se deben procesar el proyecto según lo configurado en el archivo `pom.xml`
  **Aqui se generan las Interfaces Typescript!**


* **-P generar-archivos-typescript:** Es un perfil configurado en **pom.xml** que permite la generacion de archivos typescript a las clases que esten anotadas con `@GenerarTypescript`, el directorio de salida de los archivos se encuentra en `src/main/resources/frontend/types`
