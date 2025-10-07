package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.RegistroRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.RegistroDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("registro")
public class RegistroRestController {

    @Autowired
    RegistroRepository repository;

    /**
     * Crea un nuevo registro.
     * Retorna 201 (CREATED) con el registro enviado.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroDto> createRegistro(@RequestBody RegistroDto registroDto) {
        try {
        Registro registro = new Registro(registroDto);
            System.out.println("la fecha es : " +registro.getFecha().toString());
            repository.save(registro);
            return ResponseEntity.status(HttpStatus.CREATED).body(registroDto);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error al guardar", e);
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(registroDto);
    }

    /**
     * Crea un nuevo registro.
     * Retorna 201 (CREATED) con el registro enviado.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroDto> findRegistroByIdAndDate(
            @RequestParam Integer idCuentaContable,
            @RequestParam String fecha // en formato yyyy-MM-dd
    ){
        System.out.println("la registro es  "+idCuentaContable);
     try {
         LocalDate localDate = LocalDate.parse(fecha); // yyyy-MM-dd
         java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        System.out.println("la fecha es "+sqlDate);
         RegistroDto registro = new RegistroDto(repository.findRegistroByIdCuentaContableAndFecha(idCuentaContable, sqlDate));

         return ResponseEntity.status(HttpStatus.OK).body(registro);
     } catch (Exception e) {
         System.out.println(e);
         return ResponseEntity.badRequest().build();
     }
    }

    /**
     * Actualiza un registro existente según su ID.
     * Devuelve el registro actualizado en formato JSON.
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroDto> updateRegistro(@PathVariable Integer id, @RequestBody RegistroDto registroDto) {
       try {
           Registro registro = new Registro(registroDto);
           registro.setIdRegistro(id); // Ajusta según tu entidad
           Registro saved = repository.save(registro);
           return ResponseEntity.ok().body(new RegistroDto(saved));
       } catch (Exception e) {
           System.out.println(e);
           return ResponseEntity.badRequest().build();
       }

    }

    /**
     * Elimina un registro según su ID.
     * Retorna 204 (No Content) si la eliminación fue exitosa.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRegistro(@PathVariable Integer id) {
        try {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
