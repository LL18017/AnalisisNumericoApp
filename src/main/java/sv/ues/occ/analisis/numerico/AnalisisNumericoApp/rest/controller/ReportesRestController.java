package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services.ReportesService;

import java.util.*;

@RestController
@RequestMapping("reportes")
public class ReportesRestController {
    private  ReportesService reportesService;

    public ReportesRestController(ReportesService reportService) {
        this.reportesService = reportService;
    }


    @GetMapping("/mayo")
    public ResponseEntity<byte[]> descargarReporte() throws Exception {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("paramPrueba", "Este es un parámetro");

        JasperPrint jasperPrint = reportesService.generarReporte("mayo", parametros);
        byte[] pdf = reportesService.exportarPdf(jasperPrint);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=partidas.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}
