
package com.ensenia.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Video extends Apartado {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
    
    private Integer duracion;
    private String link;

    /////////////////////////////////////////////////////////////////////

    //////////////// Constructores
    
    public Video() {
    }

    
    public Video(String id, Integer duracion, String link) {
        this.id = id;
        this.duracion = duracion;
        this.link = link;
    }

    /////////////////////////////////////////////////////////////////////

    //////////////// Get y Set
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
    
}
