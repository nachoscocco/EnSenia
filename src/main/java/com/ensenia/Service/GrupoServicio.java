package com.ensenia.Service;

import com.ensenia.Entity.Grupo;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.GrupoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoServicio {

    @Autowired
    private GrupoRepositorio grupoRepositorio;

    @Transactional
    public void alta(String descripcion) throws ErrorServicio {

        validar(descripcion);

        Grupo grupo = new Grupo();

        grupo.setDescripcion(descripcion);

        grupoRepositorio.save(grupo);
    }

    @Transactional
    public void baja(String id) throws ErrorServicio {

        Optional<Grupo> respuesta = grupoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Grupo grupo = respuesta.get();

            grupoRepositorio.save(grupo);

        } else {
            throw new ErrorServicio("Error: no se encontro el grupo solicitado.");
        }
    }

    @Transactional
    public void modificar(String id, String descripcion) throws ErrorServicio {

        validar(descripcion);
        Optional<Grupo> respuesta = grupoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Grupo grupo = respuesta.get();

            grupo.setDescripcion(descripcion);

            grupoRepositorio.save(grupo);
        } else {
            throw new ErrorServicio("Error: no se encontro el grupo solicitado.!");
        }
    }

    @Transactional
    public void validar(String descripcion) throws ErrorServicio {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("La descripcion no puede ser nulo.");
        }

    }
    
    @Transactional
    public Grupo buscaGrupoPorId(String id) throws ErrorServicio{
        Optional<Grupo> respuesta = grupoRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            return respuesta.get();
        }
        else{
            return null;
        }
    }
}
