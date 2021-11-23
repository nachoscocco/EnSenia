
package com.ensenia.Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Curso {
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
            
    private String titulo;
    private Integer duracion;
    
    private String descripcion;
    
    private Boolean alta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_alta;
    
    @ManyToOne
    @JoinColumn(name = "id_grupo") 
    private Grupo grupo; 
 

    @OneToMany //(mappedBy = "curso", cascade = CascadeType.ALL) 
    private List <Seccion> secciones;

    
    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores
    
    public Curso() {
    }
    
    public Curso(String id, String titulo, Integer duracion, String descripcion, Boolean alta, Date fecha_alta, Grupo grupo, List<Seccion> secciones) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.alta = alta;
        this.fecha_alta = fecha_alta;
        this.grupo = grupo;
        this.secciones = secciones;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    ////////////////// Get y Set
    
    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
}
