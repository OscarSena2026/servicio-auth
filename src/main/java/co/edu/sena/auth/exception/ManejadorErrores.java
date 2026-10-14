package co.edu.sena.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Maneja los errores de validacion para responder con un mensaje claro
 * y el codigo 400 (Bad Request) cuando faltan datos o el JSON es invalido.
 */
@RestControllerAdvice
public class ManejadorErrores {

    // Se ejecuta cuando usuario o contrasena llegan vacios (falla @NotBlank)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> datosInvalidos(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Datos incompletos: usuario y contrasena son obligatorios");
    }

    // Se ejecuta cuando no llega body o el JSON esta mal escrito
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> cuerpoInvalido(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Cuerpo de la peticion invalido: envie un JSON con usuario y contrasena");
    }
}
