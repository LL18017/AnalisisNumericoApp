package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro,Integer> {

    @Query("SELECT r FROM Registro r " +
            "WHERE r.idCuentaContable.idCuentaContable IN :idsCuentas " +
            "AND FUNCTION('DATE', r.fecha) = :fechaFin")

    public List<Registro> findByIdCuentaContableIdCuentaContableInAndFechaBetween(
            @Param("idsCuentas") List<Integer> idsCuentas,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT r FROM Registro r " +
            "WHERE r.idCuentaContable.codigo IN :codigos " +
            "AND FUNCTION('DATE', r.fecha) = :fechaFin")

    public List<Registro> findByIdCuentaContableCodigoInAndFechaBetween(
            @Param("codigos") List<String> codigos,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT r FROM Registro r WHERE r.idCuentaContable.idCuentaContable = :idCuentaContable AND r.fecha = :fecha")
    public Registro findRegistroByIdCuentaContableAndFecha(
            @Param("idCuentaContable") Integer idCuentaContable,
            @Param("fecha") Date fecha
    );
}
