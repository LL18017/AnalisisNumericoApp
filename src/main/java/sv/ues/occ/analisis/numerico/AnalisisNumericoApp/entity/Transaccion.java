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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.TransacionDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "transaccion")
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findByIdTransaccion", query = "SELECT t FROM Transaccion t WHERE t.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "Transaccion.findByFecha", query = "SELECT t FROM Transaccion t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Transaccion.findByDescripcion", query = "SELECT t FROM Transaccion t WHERE t.descripcion = :descripcion")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_tipo_transaccion", referencedColumnName = "id_tipo_transaccion")
    @ManyToOne(optional = false)
    private TipoTransaccion idTipoTransaccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
    private List<PartidaDiariaDetalle> partidaDiariaDetalleList;

    public Transaccion() {
    }
    public Transaccion(TransacionDto DTO){
        this.idTransaccion = DTO.idTransacion();
        this.fecha = DTO.fecha();
        this.descripcion = DTO.descripcion();
        this.idTipoTransaccion =new TipoTransaccion( DTO.tipoTransacionDtos());
    }
    public Transaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transaccion(Integer idTransaccion, Date fecha) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoTransaccion getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(TipoTransaccion idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
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
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.Transaccion[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
