package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.TipoSaldoController;

@RestController
@RequestMapping("TipoSaldo")
public class TipoSaldoRestController {
    @Autowired
    TipoSaldoController tipoSaldoController;
}
