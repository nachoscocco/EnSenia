
package com.ensenia.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public abstract class Apartados {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
    
    private Integer numero;
    private String titulo;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores
    
    public Apartados() {
    }
    
    public Apartados(String id, Integer numero, String titulo) {
        this.id = id;
        this.numero = numero;
        this.titulo = titulo;
    }

    /////////////////////////////////////////////////////////////////////
    
    ////////////////// Get y Set
    
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
    
    
    
}
