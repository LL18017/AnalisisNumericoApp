package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.util.List;

public interface CuentasContablesController extends JpaRepository<CuentaContable,Integer> {
    public List<CuentaContable> findAll();

    public CuentaContable findByCodigo(String codigo);
    
    public List<CuentaContableDto> findByNombreContainsIgnoreCase(String nombre);

    public long count();

    @Query(value = "select c from CuentaContable c where c.idPadre.idCuentaContable=:idPadre")
    List<CuentaContable> getDecendientes(@Param("idPadre") Integer idPadre);

}
