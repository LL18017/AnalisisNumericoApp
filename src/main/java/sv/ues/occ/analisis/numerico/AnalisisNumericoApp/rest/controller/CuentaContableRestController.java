package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.CuentasContablesRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.CuentaContableService;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.util.List;

@RestController
@RequestMapping("cuenta")
public class CuentaContableRestController {
    @Autowired
    CuentasContablesRepository repository;
    @Autowired
    CuentaContableService service;

    /**
     * Obtiene todas las cuentas contables en formato JSON.
     * Devuelve un header con el total de registros.
     * Retorna 404 si no hay cuentas.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CuentaContableDto>> getCuentaContable() {
        List<CuentaContableDto> list = repository.findAll().stream()
                .map(c -> new CuentaContableDto(c))
                .toList();
        if (list.isEmpty()) return ResponseEntity.notFound().build(); // Devuelve 404
        long total = repository.count();
        return ResponseEntity
                .ok()
                .header(HeadersAnalisisNumerico.TOTA_RECORS, String.valueOf(total))
                .body(list);
    }

    /**
     * Obtiene una cuenta contable específica según su código tomar en cuenta que codigo y id no es lo mismo.
     * Devuelve 404 si la cuenta no existe.
     */
    @GetMapping(value = "/{codigo}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaContableDto> getCuentaContable(@PathVariable String codigo) {
        CuentaContableDto cuenta = repository.findByCodigo(codigo).toDto();

        if (cuenta == null) return ResponseEntity.notFound().build(); // Devuelve 404

        return ResponseEntity.ok().body(cuenta);
    }

    @GetMapping(value = "/nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CuentaContableDto>> getCuentaContableByNombre(@PathVariable String nombre) {
        List<CuentaContableDto> cuenta = repository.findByNombreContainsIgnoreCase(nombre);
        return ResponseEntity.ok().body(cuenta);
    }

    /**
     * Obtiene todas las cuentas descendientes de una cuenta padre según su código.
     * Devuelve la lista de descendientes en formato JSON.
     */
    @GetMapping(value = "/padre/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CuentaContableDto>> getCuentaContableByCogigoPadre(@PathVariable String codigo) {
        List<CuentaContableDto> list = service.getDescendientes(codigo).stream().map(c -> c.toDto()).toList();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Crea una nueva cuenta contable a partir de los datos enviados en el body.
     * Retorna la cuenta creada con status 201 (CREATED).
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaContableDto> createCuentaContable(@RequestBody CuentaContableDto cuenta) {
        repository.save(new CuentaContable(cuenta));
        return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
    }

    /**
     * Actualiza una cuenta contable existente según su ID.
     * Devuelve la cuenta actualizada en formato JSON.
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaContableDto> updateCuentaContable(@PathVariable Integer id, @RequestBody CuentaContable cuenta) {
        cuenta.setIdCuentaContable(id);
        CuentaContable saved = repository.save(cuenta);
        return ResponseEntity.ok().body(new CuentaContableDto(saved));
    }

    /**
     * Elimina una cuenta contable según su ID.
     * Retorna 204 (No Content) si la eliminación fue exitosa.
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CuentaContableDto> deleteCuentaContable(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
