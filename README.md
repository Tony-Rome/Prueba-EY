# Prueba - EY

---

### Objetivo
- Microservicio encargado de crear usuario usando JWT & JPA

---

### Requisitos
- JDK 1.8
- Gradle
---

## Comandos para ejecutar proyecto
1. Ubicarse en raíz de proyecto y ejecutar comando:

   gradle build
2. Una vez completado el paso anterior, ubicarse en carpeta \build\libs y ejecutar el siguiente comando:

   java -jar user-management-1.0-SNAPSHOT.JAR

3. Se levantará la aplicación con la siguiente path:
http://localhost:8080

---

### Rutas
Microservicio consta de la siguiente ruta:
1. POST /v1/user/sign-up
    1. Se debe enviar JSON especificado en los requerimientos

---

### Extra
Está disponible la consola de la base de datos H2 en la siguiente ruta:
http://localhost:8080/h2-console

con las crendeciales:
- username: sa
- password: <sin password>