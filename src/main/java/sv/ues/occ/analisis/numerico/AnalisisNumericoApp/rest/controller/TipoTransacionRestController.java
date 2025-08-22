package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

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
    TipoTransacionController controler;
    @GetMapping
    public ResponseEntity<List<TipoTransacionDto>> getTipoTransacion() {
        List<TipoTransacionDto> list = controler.findAll()
                .stream()
                .map(TipoTransaccion::toDto)
                .toList();

        long count = controler.count();

        return ResponseEntity.ok()
                .header(HeadersAnalisisNumerico.TOTA_RECORS, String.valueOf(count))
                .body(list);
    }

    @PostMapping
    public ResponseEntity<TipoTransacionDto> create( @RequestBody TipoTransacionDto dto) {
        System.out.println(dto.toString());
        TipoTransaccion entity = new TipoTransaccion(dto);

        TipoTransaccion saved = controler.save(entity);
        return ResponseEntity.ok(saved.toDto());
    }

    // PUT: actualizar nombre por id
    @PutMapping("/{id}")
    public ResponseEntity<TipoTransacionDto> update(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        return controler.findById(id)
                .map(entity -> {
                    entity.setNombre(request.get("nombre"));
                    TipoTransaccion updated = controler.save(entity);
                    return ResponseEntity.ok(updated.toDto());
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (controler.existsById(id)) {
            controler.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 sin body
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

}
