/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.CuentaContableDto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "cuenta_contable")
@NamedQueries({
    @NamedQuery(name = "CuentaContable.findAll", query = "SELECT c FROM CuentaContable c"),
    @NamedQuery(name = "CuentaContable.findByIdCuentaContable", query = "SELECT c FROM CuentaContable c WHERE c.idCuentaContable = :idCuentaContable"),
    @NamedQuery(name = "CuentaContable.findByCodigo", query = "SELECT c FROM CuentaContable c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CuentaContable.findByNombre", query = "SELECT c FROM CuentaContable c WHERE c.nombre = :nombre")})
public class CuentaContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta_contable")
    private Integer idCuentaContable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idPadre")
    private List<CuentaContable> cuentaContableList;
    @JoinColumn(name = "id_padre", referencedColumnName = "id_cuenta_contable")
    @ManyToOne
    private CuentaContable idPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContable")
    private List<PartidaDiariaDetalle> partidaDiariaDetalleList;

    public CuentaContable() {
    }
    public CuentaContable(CuentaContableDto DTO) {
        this.idCuentaContable = DTO.idCuentaContable();
        this.codigo = DTO.codigo();
        this.nombre = DTO.nombre();
    }

    public CuentaContable(Integer idCuentaContable) {
        this.idCuentaContable = idCuentaContable;
    }

    public CuentaContable(Integer idCuentaContable, String codigo, String nombre) {
        this.idCuentaContable = idCuentaContable;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdCuentaContable() {
        return idCuentaContable;
    }

    public void setIdCuentaContable(Integer idCuentaContable) {
        this.idCuentaContable = idCuentaContable;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CuentaContable> getCuentaContableList() {
        return cuentaContableList;
    }

    public void setCuentaContableList(List<CuentaContable> cuentaContableList) {
        this.cuentaContableList = cuentaContableList;
    }

    public CuentaContable getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(CuentaContable idPadre) {
        this.idPadre = idPadre;
    }

    public List<PartidaDiariaDetalle> getPartidaDiariaDetalleList() {
        return partidaDiariaDetalleList;
    }

    public void setPartidaDiariaDetalleList(List<PartidaDiariaDetalle> partidaDiariaDetalleList) {
        this.partidaDiariaDetalleList = partidaDiariaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaContable != null ? idCuentaContable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaContable)) {
            return false;
        }
        CuentaContable other = (CuentaContable) object;
        if ((this.idCuentaContable == null && other.idCuentaContable != null) || (this.idCuentaContable != null && !this.idCuentaContable.equals(other.idCuentaContable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "la cuenta se llama " +nombre+" y cuenta con el id: "+ idCuentaContable + " ,";
    }

    public CuentaContableDto toDto() {
        return new CuentaContableDto(this);
    }
    
}
