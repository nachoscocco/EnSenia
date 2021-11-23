
package com.ensenia.Entity;

import java.sql.Blob;
import java.sql.Clob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Texto extends Apartado {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
     

    private String contenido;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores

    
    
    public Texto() {
    }

  public Texto(String id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

   


   
    
    

    /////////////////////////////////////////////////////////////////////

    //////////////// Get y Set
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

   
  

    
  

   

    
    
    
}
