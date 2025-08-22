package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.CuentasContablesController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.CuentaContableService;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cuenta")
public class CuentaContableRestController {
    @Autowired
    CuentasContablesController controller;
    @Autowired
    CuentaContableService service;

    @GetMapping
    public ResponseEntity<List<CuentaContableDto>> getCuentaContable() {
        List<CuentaContableDto> list = controller.findAll().stream()
                .map(c -> new CuentaContableDto(c))
                .toList();
        if (list.isEmpty()) return ResponseEntity.notFound().build(); // Devuelve 404
        long total = controller.count();
        return ResponseEntity
                .ok()
                .header(HeadersAnalisisNumerico.TOTA_RECORS, String.valueOf(total))
                .body(list);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CuentaContableDto> getCuentaContable(@PathVariable String codigo) {
        CuentaContableDto cuenta = controller.findByCodigo(codigo).toDto();

        if (cuenta == null) return ResponseEntity.notFound().build(); // Devuelve 404

        return ResponseEntity.ok().body(cuenta);
    }

    @GetMapping("/codigo")
    public ResponseEntity<List<CuentaContableDto>> getCuentaContableByNombre(@RequestBody Map<String, String> request) {
        List<CuentaContableDto> cuenta = controller.findByNombreContainsIgnoreCase(request.get("nombre"));
        System.out.println("la cuentacon el nombre de "+ request.get("nombre")+" es: " + cuenta);
        return ResponseEntity.ok().body(cuenta);
    }

    @GetMapping("/padre/{codigo}")
    public ResponseEntity<List<CuentaContableDto>> getCuentaContableByCogigoPadre(@PathVariable String codigo) {
        List<CuentaContableDto> list = service.getDescendientes(codigo).stream().map(c -> c.toDto()).toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CuentaContableDto> createCuentaContable(@RequestBody CuentaContableDto cuenta) {
        controller.save(new CuentaContable(cuenta));
        return ResponseEntity.status(HttpStatus.CREATED).body(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaContableDto> updateCuentaContable(@PathVariable Integer id, @RequestBody CuentaContable cuenta) {
        cuenta.setIdCuentaContable(id);
        CuentaContable saved = controller.save(cuenta);
        return ResponseEntity.ok().body(new CuentaContableDto(saved));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CuentaContableDto> deleteCuentaContable(@PathVariable Integer id) {
        controller.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
