package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.control.CuentasContablesRepository;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.CuentaContable;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaContableService {

    @Autowired
    private CuentasContablesRepository repository;

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
        CuentaContableDto cuenta = repository.findByCodigo(codigo).toDto();
        List<CuentaContable> hijos = repository.getDecendientes(cuenta.idCuentaContable());
        return hijos;

    }


}
