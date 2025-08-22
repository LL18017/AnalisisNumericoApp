package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReportesService {

    private final DataSource dataSource; // Inyecta tu DataSource de Spring Boot

    public ReportesService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JasperPrint generarReporte(String nombreReporte, Map<String, Object> parametros) throws JRException, SQLException {
        // Cargar archivo .jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                getClass().getResourceAsStream("/reportes/" + nombreReporte + ".jasper")
        );

        try (Connection con = dataSource.getConnection()) {
            // Llenar el reporte usando la conexión JDBC
            return JasperFillManager.fillReport(jasperReport, parametros, con);
        }
    }

    public byte[] exportarPdf(JasperPrint jasperPrint) throws JRException {
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

