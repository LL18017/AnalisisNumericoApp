package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.RegistroRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoResultadoService {
    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    CuentaContableService cuentaContableService;
    final List<String> CODIGOS_CUENTAS_ESTADOS_RESULTADO=List.of("5.10101","5.10102","5.10103","4.202","4.201","4.203","5.202");


}
