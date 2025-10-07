package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.RegistroRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.EstadoResultadoDto;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstadosFinancierosService {
    private static final Logger log = LoggerFactory.getLogger(EstadosFinancierosService.class);
    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    CuentaContableService cuentaContableService;

    final List<String> CODIGOS_CUENTAS_ESTADOS_RESULTADO=List.of("1.105","5.10101","5.10102","5.10103","4.103","4.104","4.105",
            "4.106","4.202","4.201","4.203","4.206","5.202","");

    public List<Integer> geCodigosCuentasDeBalance() {
        List<Integer> descendientesActivosCorriente = cuentaContableService.getDescendientes("1.1")
                .stream().map(c -> c.getIdCuentaContable()).toList();
        List<Integer> descendientesActivosNoCorriente = cuentaContableService.getDescendientes("1.2")
                .stream().map(c -> c.getIdCuentaContable()).toList();
        List<Integer> descendientesPasivoCorriente = cuentaContableService.getDescendientes("2.1")
                .stream().map(c -> c.getIdCuentaContable()).toList();
        List<Integer> descendientesPasivoNoCorriente = cuentaContableService.getDescendientes("2.2")
                .stream().map(c -> c.getIdCuentaContable()).toList();
        List<Integer> descendientesPatrimonio = cuentaContableService.getDescendientes("3.1")
                .stream().map(c -> c.getIdCuentaContable()).toList();
        List<Integer> cuentasDeBalance = new ArrayList<>(descendientesActivosCorriente);
        cuentasDeBalance.addAll(descendientesActivosNoCorriente);
        cuentasDeBalance.addAll(descendientesPasivoCorriente);
        cuentasDeBalance.addAll(descendientesPasivoNoCorriente);
        cuentasDeBalance.addAll(descendientesPatrimonio);
        return cuentasDeBalance;
    }

    public List<Registro> getRegistrosParaBalance(LocalDate fechaFinal) {

        try {
            List<Registro> cuentas = registroRepository.findByIdCuentaContableIdCuentaContableInAndFechaBetween(this.geCodigosCuentasDeBalance(), fechaFinal);
            if (!cuentas.isEmpty()) {
                return cuentas;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return List.of();
    }
    public List<Registro> getRegistrosParaEStadoDeResultados(LocalDate fechaInicio, LocalDate fechaFinal) {

        try {
            List<Registro> cuentas = registroRepository.findByIdCuentaContableCodigoInAndFechaBetween(this.CODIGOS_CUENTAS_ESTADOS_RESULTADO,
                    fechaInicio, fechaFinal);
            if (!cuentas.isEmpty()) {
                return cuentas;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return List.of();
    }

    public EstadoResultadoDto mapRegistrosAEstado(List<Registro> registros) {
        EstadoResultadoDto estado = new EstadoResultadoDto();

        for (Registro reg : registros) {
            String nombreCuenta = reg.getIdCuentaContable().getNombre();
            Double saldo = reg.getSaldo() != null ? reg.getSaldo() : 0.0;

            // Normalizamos la cadena: quitamos acentos y pasamos a minúsculas
            String nombreCuentaNormalized = normalize(nombreCuenta);

            // Iteramos sobre las cuentas del DTO y buscamos coincidencia ignorando acentos y mayúsculas
            for (String cuentaDto : estado.getCuentasYSaldos().keySet()) {
                if (normalize(cuentaDto).equals(nombreCuentaNormalized)) {
                    estado.setSaldo(cuentaDto, saldo);
                    break;
                }
            }
        }

        estado.actualizarCalculadas();
        return estado;
    }

    // Método auxiliar para normalizar cadenas: quita acentos y convierte a minúsculas
    private String normalize(String str) {
        if (str == null) return "";
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") // elimina diacríticos
                .toLowerCase();
    }
}
