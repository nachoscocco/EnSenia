package com.ensenia.Service;

import com.ensenia.Entity.Apartado;
import com.ensenia.Entity.Seccion;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.SeccionRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeccionServicio {

    @Autowired
    private SeccionRepositorio seccionRepositorio;

    @Autowired
    private CursoServicio cursoServicio;

    @Transactional
    public void alta(String id_curso, String titulo, Integer numero) throws ErrorServicio {
        
 
    
        validar(titulo, numero);
        Seccion seccion = new Seccion();

        seccion.setTitulo(titulo);
        seccion.setNumero(numero);
        seccion.setAlta(true);
        seccionRepositorio.save(seccion);
        cursoServicio.agregarSeccion(id_curso, seccion);

       
    }

    @Transactional
    public void modificar(String id, String id_curso, String titulo, Integer numero) throws ErrorServicio {

        validar(titulo, numero);
        Optional<Seccion> respuesta = seccionRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Seccion seccion = respuesta.get();
            seccion.setTitulo(titulo);
            seccion.setNumero(numero);
            seccionRepositorio.save(seccion);   
            cursoServicio.agregarSeccion(id_curso, seccion);

            

        } else {
            throw new ErrorServicio("Error: no se encontro la seccion solicitada.!");
        }
    }

    @Transactional
    public void baja(String id) throws ErrorServicio {

        Optional<Seccion> respuesta = seccionRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Seccion seccion = respuesta.get();
            seccion.setAlta(false);
            seccionRepositorio.save(seccion);

        } else {
            throw new ErrorServicio("Error: no se encontro la seccion solicitada");
        }
    }

    @Transactional
    public void validar(String titulo, Integer numero) throws ErrorServicio {
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo.");
        }
        if (numero == null || numero <= 0) {
            throw new ErrorServicio("El numero tiene que ser mayor a '0'. ");
        } 
    }

    @Transactional
    public void agregarApartado(String id_seccion, Apartado apartado) throws ErrorServicio {

        Optional<Seccion> respuesta = seccionRepositorio.findById(id_seccion);

        if (respuesta.isPresent()) {
            Seccion seccion = respuesta.get();
            try {
                seccion.getApartados().add(apartado);
                System.out.println("SECCION QUE TRAE???"+seccion.getTitulo());
                System.out.println("apartado a agregar perroo idd =="+apartado.getId());
                System.out.println("apartado calseee  =="+apartado.getClass().getCanonicalName());

                seccionRepositorio.save(seccion);
            } catch (Exception e) {
                throw new ErrorServicio("Error interno en agregar Apartados.");
            }
        } else {
            throw new ErrorServicio("La Seccion es incorrecta.");
        }
    }

}
