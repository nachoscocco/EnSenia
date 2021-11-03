
package com.ensenia.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Grupo {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
    
    private String descripcion;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores
    
    public Grupo() {
    }
    
    public Grupo(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    /////////////////////////////////////////////////////////////////////

    //////////////// Get y Set
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
