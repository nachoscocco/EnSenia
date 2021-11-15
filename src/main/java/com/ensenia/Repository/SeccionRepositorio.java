
package com.ensenia.Repository;

import com.ensenia.Entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SeccionRepositorio extends JpaRepository<Seccion,String>{
    
    @Query("SELECT c FROM Curso c WHERE c.id = :id AND c.alta = true") 
    public Seccion buscarSeccionPorId(@Param("id") String id);
}
