package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import java.util.Date;

public record RegistroDto(Integer idRegistro, CuentaContableDto idCuentaContable, TipoSaldoDTO idTipoSaldo, Date fecha) {
}
