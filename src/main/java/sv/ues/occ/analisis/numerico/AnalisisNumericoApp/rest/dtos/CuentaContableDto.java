package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;

import java.util.List;

public record CuentaContableDto(Integer idCuentaContable, String codigo, String nombre) {

    public CuentaContableDto(CuentaContable entity){
        this(entity.getIdCuentaContable(), entity.getCodigo(), entity.getNombre());
    }
}
