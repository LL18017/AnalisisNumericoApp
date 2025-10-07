package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos;
import java.util.LinkedHashMap;
import java.util.Map;


public class EstadoResultadoDto {

    private Map<String, Double> cuentasYSaldos;

    public EstadoResultadoDto() {
        cuentasYSaldos = new LinkedHashMap<>();
        cuentasYSaldos.put("Ventas al contado", null);
        cuentasYSaldos.put("Ventas al credito", null);
        cuentasYSaldos.put("Ventas totales", null); // calculada
        cuentasYSaldos.put("Devoluciones y rebajas en venta", null);
        cuentasYSaldos.put("Ventas netas", null); // calculada
        cuentasYSaldos.put("Compras al contado", null);
        cuentasYSaldos.put("Compras al credito", null);
        cuentasYSaldos.put("Gastos en compra", null);
        cuentasYSaldos.put("Compras totales", null); // calculada
        cuentasYSaldos.put("Devoluciones y rebajas en compras", null);
        cuentasYSaldos.put("Compras netas", null); // calculada
        cuentasYSaldos.put("Inventario inicial", null); // calculada
        cuentasYSaldos.put("Disponiblidad de productos para el periodo", null); // calculada
        cuentasYSaldos.put("Inventario", null); // calculada
        cuentasYSaldos.put("Costo de lo vendido", null);
        cuentasYSaldos.put("Utilidad Bruta", null); // calculada
        cuentasYSaldos.put("Gastos de operación", null);
        cuentasYSaldos.put("Gastos de venta", null);
        cuentasYSaldos.put("Gastos de administracion", null);
        cuentasYSaldos.put("Gastos financieros", null);
        cuentasYSaldos.put("Utilidad operativa", null); // calculada
        cuentasYSaldos.put("OTROS INGRESOS NO OPERACIONALES", null);
        cuentasYSaldos.put("Otros gastos", null);
        cuentasYSaldos.put("Utilidad antes de impuesto e intereses", null); // calculada
        cuentasYSaldos.put("Reserva legal", null);
        cuentasYSaldos.put("ISR", null);
        cuentasYSaldos.put("Utilidad del periodo", null); // calculada
    }

    public Map<String, Double> getCuentasYSaldos() {
        return cuentasYSaldos;
    }

    public void setSaldo(String cuenta, Double saldo) {
        if (cuentasYSaldos.containsKey(cuenta)) {
            cuentasYSaldos.put(cuenta, saldo);
        }
    }

    public void actualizarCalculadas() {
        // Ventas totales = Ventas al contado + Ventas al crédito
        Double ventasContado = cuentasYSaldos.get("Ventas al contado") != null ? cuentasYSaldos.get("Ventas al contado") : 0.0;
        Double ventasCredito = cuentasYSaldos.get("Ventas al credito") != null ? cuentasYSaldos.get("Ventas al credito") : 0.0;
        Double devolucionesEnVenta = cuentasYSaldos.get("Devoluciones y rebajas en venta") != null ? cuentasYSaldos.get("Devoluciones y rebajas en venta") : 0.0;
        Double comprasContado = cuentasYSaldos.get("Compras al contado") != null ? cuentasYSaldos.get("Compras al contado") : 0.0;
        Double compraCredito = cuentasYSaldos.get("Compras al credito") != null ? cuentasYSaldos.get("Compras al credito") : 0.0;
        Double gastosEnCompra = cuentasYSaldos.get("Gastos en compra") != null ? cuentasYSaldos.get("Gastos en compra") : 0.0;
        Double inventarioInical = cuentasYSaldos.get("Inventario inicial") != null ? cuentasYSaldos.get("Inventario inicial") : 0.0;
        Double inventarioFinal = cuentasYSaldos.get("Inventario") != null ? cuentasYSaldos.get("Inventario") : 0.0;

        Double devolucionesEncompra = cuentasYSaldos.get("Devoluciones y rebajas en compras") != null ? cuentasYSaldos.get("Devoluciones y rebajas en compras") : 0.0;
        Double gastoVenta=cuentasYSaldos.get("Gastos de venta") != null ? cuentasYSaldos.get("Gastos de venta") : 0.0;
        Double gastoAdmon=cuentasYSaldos.get("Gastos de administracion") != null ? cuentasYSaldos.get("Gastos de administracion") : 0.0;
        Double gastoFinancieros=cuentasYSaldos.get("Gastos financieros") != null ? cuentasYSaldos.get("Gastos financieros") : 0.0;
        Double otrosGastos=cuentasYSaldos.get("Otros gastos") != null ? cuentasYSaldos.get("Otros gastos") : 0.0;
        Double otrosIngresos=cuentasYSaldos.get("OTROS INGRESOS NO OPERACIONALES") != null ? cuentasYSaldos.get("OTROS INGRESOS NO OPERACIONALES") : 0.0;


        cuentasYSaldos.put("Ventas totales", ventasContado + ventasCredito);

        // Ventas netas = Ventas totales - Devoluciones y rebajas en venta
        cuentasYSaldos.put("Ventas netas", cuentasYSaldos.get("Ventas totales") - devolucionesEnVenta);

        // compras totales = Ventas al contado + Ventas al crédito
        cuentasYSaldos.put("Compras totales", comprasContado + compraCredito+gastosEnCompra);

        // compras netas = compras totales - Devoluciones y rebajas en compras
        cuentasYSaldos.put("Compras netas", cuentasYSaldos.get("Compras totales")- devolucionesEncompra);

        //disponiblidad de productos =compras netas + inventario inicial

        cuentasYSaldos.put("Disponiblidad de productos para el periodo", cuentasYSaldos.get("Compras netas") +inventarioInical);
        cuentasYSaldos.put("Costo de lo vendido", cuentasYSaldos.get("Disponiblidad de productos para el periodo") -inventarioFinal);
        // Utilidad Bruta = Ventas netas - Costo de lo vendido
        cuentasYSaldos.put("Utilidad Bruta", cuentasYSaldos.get("Ventas netas") - cuentasYSaldos.get("Costo de lo vendido"));


        cuentasYSaldos.put("Gastos de operación", gastoVenta+gastoAdmon+gastoFinancieros);
        cuentasYSaldos.put("Utilidad operativa", cuentasYSaldos.get("Utilidad Bruta")- cuentasYSaldos.get("Gastos de operación"));
        cuentasYSaldos.put("Utilidad antes de impuesto e intereses", cuentasYSaldos.get("Utilidad operativa") +otrosIngresos-otrosGastos);

        cuentasYSaldos.put("Reserva legal", cuentasYSaldos.get("Utilidad antes de impuesto e intereses")*0.07);
        cuentasYSaldos.put("ISR",
                cuentasYSaldos.get("Utilidad antes de impuesto e intereses")>=150000?
                        cuentasYSaldos.get("Utilidad antes de impuesto e intereses") *0.3:
                        cuentasYSaldos.get("Utilidad antes de impuesto e intereses") *0.25);
        cuentasYSaldos.put("Utilidad del periodo",
                cuentasYSaldos.get("Utilidad antes de impuesto e intereses")-
                cuentasYSaldos.get("Reserva legal")-
                cuentasYSaldos.get("ISR")
                );

        // Puedes seguir agregando las demás calculadas
    }
}
