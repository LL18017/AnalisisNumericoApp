package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoSaldo;

public record TipoSaldoDTO(Integer idTipoSaldo, String nombre) {
    public  TipoSaldoDTO(TipoSaldo entity){
        this(entity.getIdTipoSaldo(), entity.getNombre());
    }
}
