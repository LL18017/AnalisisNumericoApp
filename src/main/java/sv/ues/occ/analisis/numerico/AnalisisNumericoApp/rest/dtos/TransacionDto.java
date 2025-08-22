package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Transaccion;

import java.util.Date;

public record TransacionDto(Integer idTransacion, Date fecha, String descripcion, TipoTransacionDto tipoTransacionDtos) {
    public TransacionDto(Transaccion entity){
        this(entity.getIdTransaccion(), entity.getFecha(), entity.getDescripcion(), new TipoTransacionDto(entity.getIdTipoTransaccion()));
    }
}
