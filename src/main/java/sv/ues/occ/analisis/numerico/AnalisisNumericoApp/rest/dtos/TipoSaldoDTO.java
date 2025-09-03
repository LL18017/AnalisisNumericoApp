package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoSaldo;

public record TipoSaldoDTO(
        Integer idTipoSaldo,
        @NotBlank(message = "El nombre es obligatorio")
        String nombre) {
    public  TipoSaldoDTO(TipoSaldo entity){
        this(entity.getIdTipoSaldo(), entity.getNombre());
    }
}
