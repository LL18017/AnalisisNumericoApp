package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.CuentasContablesRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.RegistroRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.EstadosFinancierosService;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.EstadoResultadoDto;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("estadoResultados")
public class EstadoDeResultadoRestController {

    @Autowired
    EstadosFinancierosService estadosFinancierosService;
    @Autowired
    RegistroRepository repositoryRegistro;    @Autowired
    CuentasContablesRepository repositoryCuenta;

    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoResultadoDto> getCuentaContable(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {

        if (fechaInicio == null) {
            fechaInicio = LocalDate.of(2025, 1, 1);
        }
        if (fechaFin == null) {
            fechaFin = LocalDate.of(2025, 1, 31);
        }

        List<Registro> cuentas = new ArrayList<>(estadosFinancierosService
                .getRegistrosParaEStadoDeResultados(fechaInicio, fechaFin));


        CuentaContable inventario = repositoryCuenta.findByNombreContainsIgnoreCase("inventario").stream().map(CuentaContable::new).toList().getFirst();
        Registro inventarioInicial = repositoryRegistro.findRegistroByIdCuentaContableAndFecha(inventario.getIdCuentaContable(),
                Date.from(
                        fechaInicio.minusDays(1)
                                .atStartOfDay(ZoneOffset.UTC) // lo llevamos a la zona horaria del sistema
                                .toInstant()));

        if (inventarioInicial != null) {
            inventario.setNombre("Inventario inicial");
            inventarioInicial.setIdCuentaContable(inventario);
            cuentas.add(inventarioInicial);
        } else {
            // Si no existe, creamos uno con saldo 0
            Registro inventarioInicialVacio = new Registro();
            inventario.setNombre("Inventario inicial");
            inventarioInicialVacio.setIdCuentaContable(inventario);
            inventarioInicialVacio.setSaldo(0.0);
            inventarioInicialVacio.setSaldo(0.0);
            inventarioInicialVacio.setFecha(Date.from(
                    fechaInicio.minusDays(1)
                            .atStartOfDay(ZoneOffset.UTC)
                            .toInstant()
            ));
            cuentas.add(inventarioInicialVacio);
        }

        EstadoResultadoDto estadoResultadoDto = estadosFinancierosService.mapRegistrosAEstado(cuentas);

        return ResponseEntity.ok(estadoResultadoDto);
    }
    public EstadoResultadoDto mapRegistrosAEstado(List<Registro> registros) {
        EstadoResultadoDto estado = new EstadoResultadoDto();

        for (Registro reg : registros) {
            String nombreCuenta = Normalizer.normalize(reg.getIdCuentaContable().getNombre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "") // eliminar acentos
                    .toLowerCase();           // pasar a minúsculas

            Double saldo = reg.getSaldo() != null ? reg.getSaldo() : 0.0;

            // Solo asignamos si la cuenta existe en nuestro DTO base
            estado.getCuentasYSaldos().forEach((cuenta, valor) -> {
                String cuentaNormalizada = Normalizer.normalize(cuenta, Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .toLowerCase();
                if (cuentaNormalizada.equals(nombreCuenta)) {
                    estado.setSaldo(cuenta, (valor != null ? valor : 0.0) + saldo); // suma al valor anterior
                }
            });

        }
        // Calcular cuentas dependientes, ej: ventas totales = contado + crédito
        Double ventasContado = estado.getCuentasYSaldos().getOrDefault("Ventas al contado", 0.0);
        Double ventasCredito = estado.getCuentasYSaldos().getOrDefault("Ventas al crédito", 0.0);
        estado.setSaldo("Ventas totales", ventasContado + ventasCredito);

        return estado;
    }

}
