package com.ensenia.Service;

import com.ensenia.Entity.Texto;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.TextoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextoServicio {

    @Autowired
    private TextoRepositorio textoRepositorio;

    @Autowired
    private SeccionServicio seccionservicio;
    
    @Transactional
    public void alta(String contenido, String titulo, Integer numero, String id_seccion) throws ErrorServicio {

        validar(contenido, titulo, numero);

        Texto texto = new Texto();

        texto.setContenido(contenido);
        texto.setTitulo(titulo);
        texto.setNumero(numero);
        
        seccionservicio.agregarApartado(id_seccion, texto);
        
        textoRepositorio.save(texto);

    }

    @Transactional
    public void baja(String id) throws ErrorServicio {

        Optional<Texto> respuesta = textoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Texto texto = respuesta.get();

            textoRepositorio.save(texto);

        } else {
            throw new ErrorServicio("Error: no se encontro la seccion solicitada");
        }
    }

    @Transactional
    public void modificar(String id, String contenido, String titulo, Integer numero, String id_seccion) throws ErrorServicio {

        validar(contenido, titulo, numero);
        Optional<Texto> respuesta = textoRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Texto texto = respuesta.get();
            texto.setContenido(contenido);
            texto.setTitulo(titulo);
            texto.setNumero(numero);
            
            seccionservicio.agregarApartado(id_seccion, texto);
            textoRepositorio.save(texto);
        } else {
            throw new ErrorServicio("Error: no se encontro el texto solicitado.!");
        }
    }

    @Transactional
    public void validar(String contenido, String titulo, Integer numero) throws ErrorServicio {
        if (contenido == null || contenido.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo.");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo.");
        }
        if (numero == null || numero >= 1) {
            throw new ErrorServicio("El numero tiene que ser mayor a '0'. ");
        }
    }

}
