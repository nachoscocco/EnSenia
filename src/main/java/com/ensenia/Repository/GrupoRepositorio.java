
package com.ensenia.Repository;

import com.ensenia.Entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepositorio extends JpaRepository<Grupo, String> {
    
}
