# Servicio web de registro e inicio de sesión

**Evidencia:** GA7-220501096-AA5-EV01 · Diseño y desarrollo de servicios web - caso
**Programa:** Análisis y Desarrollo de Software (SENA)
**Aprendiz:** Oscar Fabian Estrada Arango

API REST hecha con **Spring Boot + MySQL** que permite **registrar** un usuario e **iniciar sesión**
con usuario y contraseña. La contraseña se guarda protegida con **BCrypt** (nunca en texto plano).

## Diseño del servicio (endpoints)

URL base: `http://localhost:8080`

| Endpoint | Recibe (JSON) | Si todo sale bien | Si algo falla |
|---|---|---|---|
| `POST /api/auth/registro` | `usuario`, `contrasena` | **201** · Usuario registrado correctamente | **400** datos vacíos · **409** El usuario ya existe |
| `POST /api/auth/login` | `usuario`, `contrasena` | **200** · Autenticación satisfactoria | **400** datos vacíos · **401** Error en la autenticación |

Ejemplo de body:

```json
{ "usuario": "ana.perez", "contrasena": "Clave123*" }
```

## Arquitectura en capas

```
src/main/java/co/edu/sena/auth/
├── controller/  AuthController.java       (recibe peticiones y responde)
├── service/     AuthService.java          (lógica: valida, encripta, compara)
├── repository/  UsuarioRepository.java    (acceso a la base de datos)
├── model/       Usuario.java              (tabla usuarios)
├── dto/         CredencialesDTO.java      (datos de la petición)
├── exception/   ManejadorErrores.java     (respuestas 400)
└── AuthApplication.java
```

## Cómo ejecutarlo

1. Tener instalados **Java 17+**, **Maven** (o IntelliJ/VS Code) y **MySQL**.
2. Configurar el usuario y la contraseña de MySQL en `src/main/resources/application.properties`
   (por defecto usuario `root`; la contraseña se lee de la variable de entorno `DB_PASSWORD`).
3. Ejecutar la clase `AuthApplication` (botón Run) o `mvn spring-boot:run`.
4. Cuando la consola muestre `Started AuthApplication`, el servicio escucha en `http://localhost:8080`.
5. Probar con Postman (ver evidencia EV02).
