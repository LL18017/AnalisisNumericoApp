package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.PartidaDiariaDetalle;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoSaldo;

import java.math.BigDecimal;

public record PartidaDetalleDto(Integer idPartidaDetalle, BigDecimal monto,CuentaContableDto cuentaContableDto,
                                TipoSaldoDTO tipoSaldoDTO,TransacionDto transacionDto) {
    public PartidaDetalleDto(PartidaDiariaDetalle entity){
        this(
                entity.getIdPartidaDetalle(),
                entity.getMonto(),
                new CuentaContableDto(entity.getIdCuentaContable()),
                new TipoSaldoDTO(entity.getIdTipoSaldo()),
                new TransacionDto(entity.getIdTransaccion()));
    }
}
