package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoTransaccion;

public record TipoTransacionDto(Integer idTipoTransacion, String nombre) {
    public TipoTransacionDto(TipoTransaccion entity) {
        this(entity.getIdTipoTransaccion(), entity.getNombre());
    }
}
