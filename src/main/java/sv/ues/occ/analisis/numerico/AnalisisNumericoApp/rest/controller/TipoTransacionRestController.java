package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.TipoTransacionController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoTransaccion;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.TipoTransacionDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tipoTransaccion")
public class TipoTransacionRestController {
    @Autowired
    TipoTransacionController tipoTransacionController;
    @GetMapping
    public ResponseEntity<List<TipoTransacionDto>> getTipoTransacion() {
        List<TipoTransacionDto> list = tipoTransacionController.findAll()
                .stream()
                .map(TipoTransacionDto::new)
                .toList();

        long count = tipoTransacionController.count();

        return ResponseEntity.ok()
                .header(HeadersAnalisisNumerico.TOTA_RECORS, String.valueOf(count))
                .body(list);
    }

    @PostMapping
    public ResponseEntity<TipoTransacionDto> create( @RequestBody TipoTransacionDto dto) {
        System.out.println(dto.toString());
        TipoTransaccion entity = new TipoTransaccion(dto);

        TipoTransaccion saved = tipoTransacionController.save(entity);
        return ResponseEntity.ok(new TipoTransacionDto(saved));
    }

    // PUT: actualizar nombre por id
    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacionDto> update(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        return tipoTransacionController.findById(id)
                .map(entity -> {
                    entity.setNombre(request.get("nombre"));
                    TipoTransaccion updated = tipoTransacionController.save(entity);
                    return ResponseEntity.ok(new TipoTransacionDto(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (tipoTransacionController.existsById(id)) {
            tipoTransacionController.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 sin body
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

}
