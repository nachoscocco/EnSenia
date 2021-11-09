
package com.ensenia.Repository;

import com.ensenia.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositorio extends JpaRepository<Video, String>{
    
}
