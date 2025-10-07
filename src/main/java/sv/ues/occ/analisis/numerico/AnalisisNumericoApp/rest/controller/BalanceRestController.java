package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xhtmlrenderer.pdf.ITextRenderer;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.EstadosFinancierosService;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.RegistroDto;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("balance")
public class BalanceRestController {

    @Autowired
    EstadosFinancierosService estadosFinancierosService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegistroDto>> getCuentaContable(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        if (fechaInicio == null) fechaInicio = LocalDate.of(2025, 1, 1);
        if (fechaFin == null) fechaFin = LocalDate.of(2025, 12, 31);

        List<RegistroDto> registrosParaBalance = estadosFinancierosService
                .getRegistrosParaBalance(fechaFin)
                .stream()
                .map(RegistroDto::new)
                .toList();

        return ResponseEntity.ok(registrosParaBalance);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void exportBalancePdf(HttpServletResponse response,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                 @RequestParam(required = false, defaultValue = "anio") String tipoPeriodo,
                                 @RequestParam(required = false, defaultValue = "reporte") String tipoReporte,
                                 @RequestParam(required = false, defaultValue = "4") Integer cantidadPeriodos
    ) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=BalanceGeneral.pdf");

        // Valores por defecto
        System.out.println("el valor real es: "+tipoReporte);
        if (fechaInicio == null) fechaInicio = LocalDate.of(2025, 1, 1);
        tipoPeriodo = tipoPeriodo == null || tipoPeriodo.isBlank() ? "anio" : tipoPeriodo.toLowerCase();

        // Generar lista de periodos
        List<Periodo> periodos = generarPeriodos(fechaInicio, tipoPeriodo, cantidadPeriodos);

        // Obtener los registros por periodo
        List<PeriodoConRegistros> periodosConRegistros = new ArrayList<>();
        for (Periodo p : periodos) {
            List<RegistroDto> registros = estadosFinancierosService
                    .getRegistrosParaBalance(p.fechaFin())
                    .stream()
                    .map(RegistroDto::new)
                    .toList();
            periodosConRegistros.add(new PeriodoConRegistros(p, registros));
        }

        // Separar cuentas
        List<RegistroDto> activosCorrientes = obtenerCuentasPorCodigo(periodosConRegistros, "1.1");
        List<RegistroDto> activosNoCorrientes = obtenerCuentasPorCodigo(periodosConRegistros, "1.2");
        List<RegistroDto> pasivosCorrientes = obtenerCuentasPorCodigo(periodosConRegistros, "2.1");
        List<RegistroDto> pasivosNoCorrientes = obtenerCuentasPorCodigo(periodosConRegistros, "2.2");
        List<RegistroDto> capital = obtenerCuentasPorCodigo(periodosConRegistros, "3.");

        // Nombres de periodos
        List<String> nombresPeriodos = new ArrayList<>();
        for (Periodo p : periodos) nombresPeriodos.add(formatearNombrePeriodo(p, tipoPeriodo));

        // Estilos HTML
        String estilos = "<style>"
                + "h1,h2, span, th, td { color: #112240; text-align: center; }"
                + ".balance-cuerpo .tittle { padding: 20px; text-align: start; }"
                + ".balance-cuerpo { display:grid; grid-template-columns: repeat(2, 1fr); gap:4px; width:100%; margin:0 auto; }"
                + ".tabla-balance { border-collapse: separate; padding:20px; width:100%; table-layout:fixed; }"
                + ".tabla-balance th, .tabla-balance td { padding:8px; text-align:left; height:40px white-space: normal; word-wrap: break-word; overflow: visible; }"
                + "</style>";

        // HTML
        String html;
        if ("reporte".equalsIgnoreCase(tipoReporte)) {
            html = "<html><head>" + estilos + "</head><body>"
                    + "<h1>Balance General</h1>"
                    + "<h2>Empresa más chingona del mundo SA DE SV</h2>"
                    + "<h2>Tipo periodo: " + tipoPeriodo + "</h2>"
                    + "<div class='balance-cuerpo'>"
                    + generarTabla(activosCorrientes, "Activo Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTabla(activosNoCorrientes, "Activo No Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesActivos(periodosConRegistros, nombresPeriodos)
                    + generarTabla(pasivosCorrientes, "Pasivo Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTabla(pasivosNoCorrientes, "Pasivo No Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesPasivos(periodosConRegistros, nombresPeriodos)
                    + generarTabla(capital, "Capital", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesPasivoPatrimonio(periodosConRegistros, nombresPeriodos)
                    + "</div></body></html>";
        } else {
            // Dos columnas: Activos | Pasivos + Capital

             estilos = "<style>"
                    // Encabezados
                    + "h1,h2,h4 { color:#112240; text-align:center; font-family: Arial; font-size: 8pt; }"

                    // Tablas limpias sin bordes ni fondo
                    + "table { border: none !important; border-collapse: collapse; width: 100%; table-layout: fixed; background: none !important; }"
                    + "th, td { font-size: 8pt; border: none !important; padding: 4pt !important; background: none !important; color: inherit !important; text-align: left !important; font-family: inherit !important; word-wrap: break-word; vertical-align: middle; }"
                    + "th:first-child, td:first-child { text-align: left !important; }"  // nombres de cuentas alineados a la izquierda
                    + "td:nth-child(n+2), th:nth-child(n+2) { text-align: right !important; }" // saldos alineados a la derecha
                    // Columna para dividir Activos / Pasivos+Capital
                    + ".columna { vertical-align: top; width: 50%; }"
                     +"reporteContainer{ border: 1px solid black;}"

                    // Totales alineados vertical y horizontalmente
                    + ".total-td { vertical-align: middle; text-align: right; font-weight: bold; }"
                    + "</style>";


            // HTML usando tabla principal para dividir en dos columnas
             html = "<html><head>" + estilos + "</head><body>"
                    + "<h1>Balance General</h1>"
                    + "<h2>Empresa más chingona del mundo SA DE SV</h2>"
                    + "<h2>Tipo periodo: " + tipoPeriodo + "</h2>"
                     +"<div class='reporteContainer'>"
                    + "<table class='tb-container' style='width:100%; border-collapse: collapse;'><tr>"

                    // Columna izquierda: Activos
                    + "<td class='columna'>"
                    + generarTabla(activosCorrientes, "Activo Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTabla(activosNoCorrientes, "Activo No Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesActivos(periodosConRegistros, nombresPeriodos)
                    + "</td>"

                    // Columna derecha: Pasivos + Capital
                    + "<td class='columna'>"
                    + generarTabla(pasivosCorrientes, "Pasivo Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTabla(pasivosNoCorrientes, "Pasivo No Corriente", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesPasivos(periodosConRegistros, nombresPeriodos)
                    + generarTabla(capital, "Capital", periodosConRegistros, nombresPeriodos)
                    + generarTablaTotalesPasivoPatrimonio(periodosConRegistros, nombresPeriodos)
                    + "</td>"

                    + "</tr></table>" +
                     "</div>" +
                     "</body></html>";
        }

        System.out.println(html);
        // Render PDF
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(response.getOutputStream(), false);
        renderer.finishPDF();
    }

    private List<Periodo> generarPeriodos(LocalDate inicio, String tipo, int cantidad) {
        List<Periodo> periodos = new ArrayList<>();
        LocalDate fechaInicio = inicio;
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaFin;
            switch (tipo) {
                case "anio" -> fechaFin = fechaInicio.with(TemporalAdjusters.lastDayOfYear());
                case "mes" -> fechaFin = fechaInicio.with(TemporalAdjusters.lastDayOfMonth());
                case "trimestre" -> fechaFin = fechaInicio.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
                case "semestre" -> fechaFin = fechaInicio.plusMonths(5).with(TemporalAdjusters.lastDayOfMonth());
                default -> fechaFin = fechaInicio.with(TemporalAdjusters.lastDayOfYear());
            }
            periodos.add(new Periodo(fechaInicio, fechaFin));
            fechaInicio = fechaFin.plusDays(1);
        }
        return periodos;
    }

    private List<RegistroDto> obtenerCuentasPorCodigo(List<PeriodoConRegistros> periodosConRegistros, String codigoPrefix) {
        List<RegistroDto> cuentas = new ArrayList<>();
        for (PeriodoConRegistros p : periodosConRegistros) {
            for (RegistroDto r : p.registros()) {
                if (r.idCuentaContable().codigo().startsWith(codigoPrefix)) {
                    cuentas.add(r);
                }
            }
        }
        return cuentas;
    }

    private String formatearNombrePeriodo(Periodo periodo, String tipoPeriodo) {
        return switch (tipoPeriodo) {
            case "anio" -> String.valueOf(periodo.fechaInicio().getYear());
            case "mes" -> periodo.fechaInicio().getMonth().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
            case "trimestre" -> {
                int trimestre = (periodo.fechaInicio().getMonthValue() - 1) / 3 + 1;
                yield "Trimestre " + trimestre + " " + periodo.fechaInicio().getYear();
            }
            case "semestre" -> {
                int semestre = (periodo.fechaInicio().getMonthValue() - 1) / 6 + 1;
                yield "Semestre " + semestre + " " + periodo.fechaInicio().getYear();
            }
            default -> String.valueOf(periodo.fechaInicio().getYear());
        };
    }

    private String generarTabla(List<RegistroDto> cuentas, String titulo, List<PeriodoConRegistros> periodosConRegistros, List<String> nombresPeriodos) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4 class='tittle'>").append(titulo).append("</h4>");
        sb.append("<table class='tabla-balance'><thead><tr><th>Cuenta</th>");
        for (String periodo : nombresPeriodos) sb.append("<th>").append(periodo).append("</th>");
        sb.append("</tr></thead><tbody>");

        double[] totales = new double[nombresPeriodos.size()];

        for (RegistroDto cuenta : cuentas) {
            sb.append("<tr><td>").append(cuenta.idCuentaContable().nombre()).append("</td>");
            for (int i = 0; i < periodosConRegistros.size(); i++) {
                PeriodoConRegistros p = periodosConRegistros.get(i);
                RegistroDto registroDelPeriodo = p.registros().stream()
                        .filter(r -> r.idCuentaContable().codigo().equals(cuenta.idCuentaContable().codigo()))
                        .findFirst().orElse(null);
                double saldo = registroDelPeriodo != null ? registroDelPeriodo.saldo() : 0;
                sb.append("<td>$ ").append(String.format("%,.2f", saldo)).append("</td>");
                totales[i] += saldo;
            }
            sb.append("</tr>");
        }

        sb.append("<tr class='total'><td><strong>Total ").append(titulo).append("</strong></td>");
        for (double total : totales) sb.append("<td><strong>$ ").append(String.format("%,.2f", total)).append("</strong></td>");
        sb.append("</tr></tbody></table>");
        return sb.toString();
    }

    private String generarTablaTotalesActivos(List<PeriodoConRegistros> periodosConRegistros, List<String> nombresPeriodos) {
        double[] totalesActivos = new double[nombresPeriodos.size()];
        for (int i = 0; i < periodosConRegistros.size(); i++) {
            PeriodoConRegistros p = periodosConRegistros.get(i);
            for (RegistroDto r : p.registros()) {
                if (r.idCuentaContable().codigo().startsWith("1")) totalesActivos[i] += r.saldo();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<h4 class='tittle'>Total Activos</h4><table class='tabla-balance'><tbody><tr>");
        sb.append("<td class='total-td'><strong>Total Activos</strong></td>");
        for (double total : totalesActivos) {
            sb.append("<td class='total-td'><strong>$ ").append(String.format("%,.2f", total)).append("</strong></td>");
        }
        sb.append("</tr></tbody></table>");
        return sb.toString();
    }


    private String generarTablaTotalesPasivos(List<PeriodoConRegistros> periodosConRegistros, List<String> nombresPeriodos) {
        double[] totalesPasivos = new double[nombresPeriodos.size()];
        for (int i = 0; i < periodosConRegistros.size(); i++) {
            PeriodoConRegistros p = periodosConRegistros.get(i);
            for (RegistroDto r : p.registros()) {
                if (r.idCuentaContable().codigo().startsWith("2")) totalesPasivos[i] += r.saldo();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<h4 class='tittle'>Total Pasivos</h4><table class='tabla-balance'><tbody><tr><td><strong>Total Pasivos</strong></td>");
        for (double total : totalesPasivos) sb.append("<td><strong>$ ").append(String.format("%,.2f", total)).append("</strong></td>");
        sb.append("</tr></tbody></table>");
        return sb.toString();
    }

    private String generarTablaTotalesPasivoPatrimonio(List<PeriodoConRegistros> periodosConRegistros, List<String> nombresPeriodos) {
        double[] totales = new double[nombresPeriodos.size()];
        for (int i = 0; i < periodosConRegistros.size(); i++) {
            PeriodoConRegistros p = periodosConRegistros.get(i);
            for (RegistroDto r : p.registros()) {
                String codigo = r.idCuentaContable().codigo();
                if (codigo.startsWith("2") || codigo.startsWith("3")) totales[i] += r.saldo();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<h4 class='tittle'>Total Pasivos + Patrimonio</h4><table class='tabla-balance'><tbody><tr>");
        sb.append("<td class='total-td'><strong>Total Pasivos + Patrimonio</strong></td>");
        for (double total : totales) {
            sb.append("<td class='total-td'><strong>$ ").append(String.format("%,.2f", total)).append("</strong></td>");
        }
        sb.append("</tr></tbody></table>");
        return sb.toString();
    }


    // Clases auxiliares
    record Periodo(LocalDate fechaInicio, LocalDate fechaFin) {}
    record PeriodoConRegistros(Periodo periodo, List<RegistroDto> registros) {}
}
