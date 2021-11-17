
package com.ensenia.Repository;

import com.ensenia.Entity.Seccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SeccionRepositorio extends JpaRepository<Seccion,String>{
    
    @Query("SELECT c FROM Seccion c WHERE c.id = :id AND c.alta = true") 
    public Seccion buscarSeccionPorId(@Param("id") String id);
    
    @Query("SELECT c FROM Seccion c WHERE c.alta=true")
    public List<Seccion> traerSeccionesAlta();
}
