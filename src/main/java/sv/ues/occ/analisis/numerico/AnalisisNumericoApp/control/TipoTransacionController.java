package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoTransaccion;

import java.util.List;

public interface TipoTransacionController extends JpaRepository<TipoTransaccion,Integer> {
        public List<TipoTransaccion> findAll();

        public long count();
}