package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.PartidaDiariaDetalle;

public interface PartidaDetalleController extends JpaRepository<PartidaDiariaDetalle,Integer> {
}
