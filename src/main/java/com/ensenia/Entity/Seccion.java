
package com.ensenia.Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Seccion {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
    
    private Integer numero;
    private String titulo;
    private Boolean alta;
    
    @OneToMany // (mappedBy = "seccion", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) 
    private List <Apartado> apartados;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores

    public Seccion() {
    }

    public Seccion(String id, Integer numero, String titulo, Boolean alta, List<Apartado> apartados) {
        this.id = id;
        this.numero = numero;
        this.titulo = titulo;
        this.alta = alta;
        this.apartados = apartados;
    }

  
 
    /////////////////////////////////////////////////////////////////////

    //////////////// Get y Set

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    
    
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Apartado> getApartados() {
        return apartados;
    }

    public void setApartados(List<Apartado> apartados) {
        this.apartados = apartados;
    }

    
}
