package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.ReportService;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping(value = "/{reporte}", produces = MediaType.APPLICATION_PDF_VALUE)
    private ResponseEntity<byte[]> getReporteByName(
            @PathVariable String reporte,
            @RequestParam Map<String, Object> params
    ) throws Exception {
        HashMap<String, Object> parametros = new HashMap<>();
        String path = null;
        HttpHeaders headers = new HttpHeaders();
        byte[] data;

        switch (reporte) {
            case "mayo":
                path = "/reportes/mayo.jasper";
                data = reportService.generarReporte(path, params);
                break;

            case "Balance":
                path = "/reportes/Balance_reporte.jasper";
                convertirFechas(params);
                parametros.put("SUBREPORT_DIR", getClass().getResource("/reportes/").getPath());
                configurarParametrosPorReporte("Balance_reporte",params);
                data = reportService.generarReporte("Balance_reporte", params);
                break;

            case "salacaracteristica":
                path = "/reportes/SalaCaracteristicas.jasper";
                data = reportService.generarReporte("Balance_reporte", params);
                break;

            default:
                return ResponseEntity.notFound()
                        .header(HeadersAnalisisNumerico.NOT_FOUND, reporte)
                        .build();
        }

        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + reporte + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }

    private void convertirFechas(Map<String, Object> params) {
        try {
            if (params.containsKey("DESDE")) {
                java.util.Date desdeUtil = sdf.parse(params.get("DESDE").toString());
                params.put("DESDE", new java.sql.Date(desdeUtil.getTime()));
            }
            if (params.containsKey("HASTA")) {
                java.util.Date hastaUtil = sdf.parse(params.get("HASTA").toString());
                params.put("HASTA", new java.sql.Date(hastaUtil.getTime()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al parsear fechas", e);
        }
    }

    private void configurarParametrosPorReporte(String nombre, Map<String, Object> params) throws IOException, JRException {
        // Mapa de reportes a sus subreportes y parámetros
        Map<String, Map<String, String>> configuraciones = new HashMap<>();

//        // Configuración para Balance_reporte
        Map<String, String> balanceSubreports = new HashMap<>();
        balanceSubreports.put("TIPOCUENTAS", "/reportes/BalanceSubReporte_subCuentas.jasper");
        configuraciones.put("Balance_reporte", balanceSubreports);

        // Obtener la configuración del reporte actual
        Map<String, String> subreports = configuraciones.get(nombre);
        if (subreports != null) {
            for (Map.Entry<String, String> entry : subreports.entrySet()) {
                String paramName = entry.getKey();
                String ruta = entry.getValue();
                params.put(paramName, cargarSubreporte(ruta)); // ahora devuelve JasperReport
            }
        } else {
            System.out.println("Reporte sin configuración especial: " + nombre);
        }
    }

    /**
     * Carga un subreporte desde el classpath como JasperReport
     */
    private JasperReport cargarSubreporte(String path) throws IOException, JRException {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Subreporte no encontrado: " + path);
            }
            return (JasperReport) JRLoader.loadObject(is);
        }
    }




}
