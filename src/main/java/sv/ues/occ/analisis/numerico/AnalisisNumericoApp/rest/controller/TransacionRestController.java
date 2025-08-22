package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.TransaccionController;

@RestController
@RequestMapping("transaccion")
public class TransacionRestController {
    @Autowired
    TransaccionController transaccionController;
}
