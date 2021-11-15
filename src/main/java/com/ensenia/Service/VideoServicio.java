package com.ensenia.Service;


import com.ensenia.Entity.Video;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.VideoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServicio {

    @Autowired
    private VideoRepositorio videoRepositorio;
    
    @Autowired
    private SeccionServicio seccionServicio;
            
    @Transactional
    public void alta(String link, Integer duracion, String id_seccion) throws ErrorServicio {

            validar(link, duracion);
            
            Video video = new Video();
            
            video.setLink(link);
            video.setDuracion(duracion);
            
            seccionServicio.agregarApartado(id_seccion, video);
            
            videoRepositorio.save(video);
            
    }

    @Transactional
    public void baja(String id) throws ErrorServicio {
        
        Optional<Video> respuesta = videoRepositorio.findById(id);  
        if(respuesta.isPresent()){
       
        Video video = respuesta.get();
        
        videoRepositorio.save(video);
     
        }else {
            throw new ErrorServicio("Error: no se encontro el video solicitado");
        }   
    }

    @Transactional
    public void modificar(String id, String link, Integer duracion, String id_seccion) throws ErrorServicio {
        
        validar(link, duracion);
        Optional<Video> respuesta = videoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Video video = respuesta.get();
            video.setLink(link);
            video.setDuracion(duracion);
            
            seccionServicio.agregarApartado(id_seccion, video);
            
            videoRepositorio.save(video);
        }else {
            throw new ErrorServicio("Error: no se encontro el video solicitado.!");
        }
    }

    @Transactional
    public void validar(String link, Integer duracion) throws ErrorServicio {
        if (link == null || link.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo.");
        }
        if (duracion == null || duracion >= 1) {
            throw new ErrorServicio("La duracion tiene que ser mayor a '0'. ");
        }
    }
}
