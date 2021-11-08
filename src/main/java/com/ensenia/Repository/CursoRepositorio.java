
package com.ensenia.Repository;

import com.ensenia.Entity.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepositorio extends JpaRepository<Curso,String> {
    
     @Query("SELECT c FROM Curso c WHERE c.titulo = :titulo and c.alta = true")
    public Curso buscarCursoPorNombre(@Param("titulo")String titulo);
    
    @Query("SELECT c FROM Curso c WHERE c.id = :id AND c.alta = true") 
    public Curso buscarCursoPorId(@Param("id") String id);
    
    @Query("SELECT c FROM Curso c WHERE c.alta=true")
    public List<Curso> traerCursosAlta();
    

    
    
}
