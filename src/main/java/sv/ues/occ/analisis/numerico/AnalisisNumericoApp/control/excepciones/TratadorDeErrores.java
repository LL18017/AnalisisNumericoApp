package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.excepciones;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores
{
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }


//    @ExceptionHandler(TokenExpiredException.class)
//    public ResponseEntity<Map<String, String>> tratarErrorTokenExpirado() {
//        Map<String, String> error = new HashMap<>();
//        error.put("error", "Token expirado o inválido");
//
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN) // o HttpStatus.UNAUTHORIZED si aplica
//                .body(error);
//    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> otros(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage() != null ? ex.getMessage() : "Error inesperado");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }



}
