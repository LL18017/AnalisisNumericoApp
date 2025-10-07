package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro;

import java.util.Date;

public record RegistroDto(
        Integer idRegistro,
        CuentaContableDto idCuentaContable,
        TipoSaldoDTO idTipoSaldo,
        Date fecha,
        Double saldo) {
    public  RegistroDto(Registro entity){
        this(
                entity.getIdRegistro(),
                entity.getIdCuentaContable().toDto(),
                entity.getIdTipoSaldo().toDto(),
                entity.getFecha(),
                entity.getSaldo());
    }
}
