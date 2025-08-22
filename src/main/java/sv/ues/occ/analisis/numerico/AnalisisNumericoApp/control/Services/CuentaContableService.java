package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.CuentasContablesController;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaContableService {

    @Autowired
    private CuentasContablesController controller;

    public List<CuentaContable> getDescendientes(String codigo) {
        List<CuentaContable> descendientes = new ArrayList<>();
        List<CuentaContable> hijos = getHijos(codigo);

        for (CuentaContable hijo : hijos) {
            descendientes.add(hijo);
            descendientes.addAll(getDescendientes(hijo.getCodigo()));
        }

        return descendientes;
    }

    public List<CuentaContable> getHijos(String codigo) {
        CuentaContableDto cuenta = controller.findByCodigo(codigo).toDto();
        List<CuentaContable> hijos = controller.getDecendientes(cuenta.idCuentaContable());
        return hijos;

    }


}
