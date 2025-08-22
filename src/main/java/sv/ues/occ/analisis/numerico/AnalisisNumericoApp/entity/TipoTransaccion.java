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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.TipoTransacionDto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "tipo_transaccion")
@NamedQueries({
    @NamedQuery(name = "TipoTransaccion.findAll", query = "SELECT t FROM TipoTransaccion t"),
    @NamedQuery(name = "TipoTransaccion.findByIdTipoTransaccion", query = "SELECT t FROM TipoTransaccion t WHERE t.idTipoTransaccion = :idTipoTransaccion"),
    @NamedQuery(name = "TipoTransaccion.findByNombre", query = "SELECT t FROM TipoTransaccion t WHERE t.nombre = :nombre")})
public class TipoTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_transaccion")
    private Integer idTipoTransaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTransaccion")
    private List<Transaccion> transaccionList;

    public TipoTransaccion() {
    }
    public TipoTransaccion(TipoTransacionDto DTO){
        this.idTipoTransaccion = DTO.idTipoTransacion();
        this.nombre = DTO.nombre();
    }

    public TipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public TipoTransaccion(Integer idTipoTransaccion, String nombre) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombre = nombre;
    }

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTransaccion != null ? idTipoTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTransaccion)) {
            return false;
        }
        TipoTransaccion other = (TipoTransaccion) object;
        if ((this.idTipoTransaccion == null && other.idTipoTransaccion != null) || (this.idTipoTransaccion != null && !this.idTipoTransaccion.equals(other.idTipoTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoTransaccion[ idTipoTransaccion=" + idTipoTransaccion + " ]";
    }
    
}
