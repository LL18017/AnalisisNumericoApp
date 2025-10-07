package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.TipoSaldoRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.RegistroDto;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.TipoSaldoDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tipoSaldo")
public class TipoSaldoRestController {
    @Autowired
    TipoSaldoRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoSaldoDTO>> getCuentaContable(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {

        if (fechaInicio == null) {
            fechaInicio = LocalDate.of(2025, 1, 1);
        }
        if (fechaFin == null) {
            fechaFin = LocalDate.of(2026, 12, 31);
        }

        List<TipoSaldoDTO> registros = repository.findAll().stream().map(TipoSaldoDTO::new).toList();

        return ResponseEntity.ok(registros);
    }
}
