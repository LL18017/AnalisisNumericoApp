/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.PartidaDetalleDto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "partida_diaria_detalle")
@NamedQueries({
    @NamedQuery(name = "PartidaDiariaDetalle.findAll", query = "SELECT p FROM PartidaDiariaDetalle p"),
    @NamedQuery(name = "PartidaDiariaDetalle.findByIdPartidaDetalle", query = "SELECT p FROM PartidaDiariaDetalle p WHERE p.idPartidaDetalle = :idPartidaDetalle"),
    @NamedQuery(name = "PartidaDiariaDetalle.findByMonto", query = "SELECT p FROM PartidaDiariaDetalle p WHERE p.monto = :monto")})
public class PartidaDiariaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_partida_detalle")
    private Integer idPartidaDetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;
    @JoinColumn(name = "id_cuenta_contable", referencedColumnName = "id_cuenta_contable")
    @ManyToOne(optional = false)
    private CuentaContable idCuentaContable;
    @JoinColumn(name = "id_tipo_saldo", referencedColumnName = "id_tipo_saldo")
    @ManyToOne(optional = false)
    private TipoSaldo idTipoSaldo;
    @JoinColumn(name = "id_transaccion", referencedColumnName = "id_transaccion")
    @ManyToOne(optional = false)
    private Transaccion idTransaccion;

    public PartidaDiariaDetalle() {
    }

    public PartidaDiariaDetalle(PartidaDetalleDto DTO){
        this.idPartidaDetalle=DTO.idPartidaDetalle();
        this.monto=DTO.monto();
        this.idCuentaContable=new CuentaContable(DTO.cuentaContableDto());
        this.idTipoSaldo=new TipoSaldo(DTO.tipoSaldoDTO());
        this.idTransaccion=new Transaccion(DTO.transacionDto());
    }

    public PartidaDiariaDetalle(Integer idPartidaDetalle) {
        this.idPartidaDetalle = idPartidaDetalle;
    }

    public PartidaDiariaDetalle(Integer idPartidaDetalle, BigDecimal monto) {
        this.idPartidaDetalle = idPartidaDetalle;
        this.monto = monto;
    }

    public Integer getIdPartidaDetalle() {
        return idPartidaDetalle;
    }

    public void setIdPartidaDetalle(Integer idPartidaDetalle) {
        this.idPartidaDetalle = idPartidaDetalle;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public CuentaContable getIdCuentaContable() {
        return idCuentaContable;
    }

    public void setIdCuentaContable(CuentaContable idCuentaContable) {
        this.idCuentaContable = idCuentaContable;
    }

    public TipoSaldo getIdTipoSaldo() {
        return idTipoSaldo;
    }

    public void setIdTipoSaldo(TipoSaldo idTipoSaldo) {
        this.idTipoSaldo = idTipoSaldo;
    }

    public Transaccion getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Transaccion idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartidaDetalle != null ? idPartidaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidaDiariaDetalle)) {
            return false;
        }
        PartidaDiariaDetalle other = (PartidaDiariaDetalle) object;
        if ((this.idPartidaDetalle == null && other.idPartidaDetalle != null) || (this.idPartidaDetalle != null && !this.idPartidaDetalle.equals(other.idPartidaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.PartidaDiariaDetalle[ idPartidaDetalle=" + idPartidaDetalle + " ]";
    }
    
}
