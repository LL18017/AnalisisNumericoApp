package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import java.io.FileNotFoundException;
import java.io.InputStream;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void testConexion() {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Conexión exitosa a DB: " + conn.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            System.err.println("Error al conectar a la DB:");
            e.printStackTrace();
        }
    }

    public byte[] generarReporte(String nombreReporte, Map<String, Object> parametros) throws Exception {
        InputStream reporteStream = getClass().getResourceAsStream("/reportes/" + nombreReporte + ".jasper");

        if (reporteStream == null) {
            throw new FileNotFoundException("No se encontró el reporte: " + nombreReporte);
        }

        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteStream, parametros, conn);
            System.out.println("Reporte generado con " + jasperPrint.getPages().size() + " páginas");
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
