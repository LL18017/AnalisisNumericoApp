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
import sv.ues.occ.analisis.numerico.AnalisisNumericoApp.rest.dtos.TipoSaldoDTO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "tipo_saldo")
@NamedQueries({
    @NamedQuery(name = "TipoSaldo.findAll", query = "SELECT t FROM TipoSaldo t"),
    @NamedQuery(name = "TipoSaldo.findByIdTipoSaldo", query = "SELECT t FROM TipoSaldo t WHERE t.idTipoSaldo = :idTipoSaldo"),
    @NamedQuery(name = "TipoSaldo.findByNombre", query = "SELECT t FROM TipoSaldo t WHERE t.nombre = :nombre")})
public class TipoSaldo implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoSaldo")
    private List<Registro> registroList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_saldo")
    private Integer idTipoSaldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoSaldo")
    private List<PartidaDiariaDetalle> partidaDiariaDetalleList;

    public TipoSaldo() {
    }
public TipoSaldo(TipoSaldoDTO DTO) {
        this.idTipoSaldo= DTO.idTipoSaldo();
        this.nombre = DTO.nombre();

}
    public TipoSaldoDTO toDto() {
        return new TipoSaldoDTO(this);
    }

    public TipoSaldo(Integer idTipoSaldo) {
        this.idTipoSaldo = idTipoSaldo;
    }

    public TipoSaldo(Integer idTipoSaldo, String nombre) {
        this.idTipoSaldo = idTipoSaldo;
        this.nombre = nombre;
    }

    public Integer getIdTipoSaldo() {
        return idTipoSaldo;
    }

    public void setIdTipoSaldo(Integer idTipoSaldo) {
        this.idTipoSaldo = idTipoSaldo;
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
        hash += (idTipoSaldo != null ? idTipoSaldo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSaldo)) {
            return false;
        }
        TipoSaldo other = (TipoSaldo) object;
        if ((this.idTipoSaldo == null && other.idTipoSaldo != null) || (this.idTipoSaldo != null && !this.idTipoSaldo.equals(other.idTipoSaldo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.ues.occ.analisis.numerico.AnalisisNumericoApp.entity.TipoSaldo[ idTipoSaldo=" + idTipoSaldo + " ]";
    }


    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
