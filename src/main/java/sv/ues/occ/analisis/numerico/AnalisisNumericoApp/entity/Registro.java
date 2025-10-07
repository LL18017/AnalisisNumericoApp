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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.RegistroDto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "registro")
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findByIdRegistro", query = "SELECT r FROM Registro r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "Registro.findByFecha", query = "SELECT r FROM Registro r WHERE r.fecha = :fecha")})
public class Registro implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo")
    private Double saldo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro")
    private Integer idRegistro;
    @JoinColumn(name = "id_cuenta_contable", referencedColumnName = "id_cuenta_contable")
    @ManyToOne
    private CuentaContable idCuentaContable;
    @JoinColumn(name = "id_tipo_saldo", referencedColumnName = "id_tipo_saldo")
    @ManyToOne
    private TipoSaldo idTipoSaldo;

    public Registro() {
    }
 public Registro(RegistroDto dto) {
        this.idRegistro= dto.idRegistro();
        this.idCuentaContable=new CuentaContable(dto.idCuentaContable());
        this.idTipoSaldo=new TipoSaldo(dto.idTipoSaldo());
        this.saldo= dto.saldo();
        this.fecha=dto.fecha();
    }

    public Registro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registro(Integer idRegistro, Date fecha) {
        this.idRegistro = idRegistro;
        this.fecha = fecha;
    }


    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Registro[ idRegistro=" + idRegistro + " ]";
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
}
