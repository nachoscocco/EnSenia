
package com.ensenia.Repository;

import com.ensenia.Entity.Texto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextoRepositorio extends JpaRepository<Texto, String> {
    
}
