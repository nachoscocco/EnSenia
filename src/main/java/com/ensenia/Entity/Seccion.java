
package com.ensenia.Entity;

import java.util.List;
import javax.persistence.Entity;
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
    
    @OneToMany
    private List <Apartados> apartados;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores

    public Seccion() {
    }

    public Seccion(String id, Integer numero, String titulo, List<Apartados> apartados) {
        this.id = id;
        this.numero = numero;
        this.titulo = titulo;
        this.apartados = apartados;
    }
 
    /////////////////////////////////////////////////////////////////////

    //////////////// Get y Set
    
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

    public List<Apartados> getApartados() {
        return apartados;
    }

    public void setApartados(List<Apartados> apartados) {
        this.apartados = apartados;
    }

    
}
