/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensenia.Service;

import com.ensenia.Entity.Curso;
import com.ensenia.Entity.Grupo;
import com.ensenia.Entity.Seccion;
import com.ensenia.Entity.Usuario;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.CursoRepositorio;
import com.ensenia.Repository.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class CursoServicio {
    
    @Autowired
    private CursoRepositorio cursoRepositorio;
    
    @Transactional
    public void alta(String titulo,Integer duracion, String descripcion,Grupo grupo) throws ErrorServicio{
    
        validar(titulo, duracion, descripcion, grupo);
        Curso curso = new Curso();
        curso.setAlta(true);
        curso.setTitulo(titulo);
        curso.setDescripcion(descripcion);
        curso.setDuracion(duracion);
        curso.setGrupo(grupo);
        curso.setFecha_alta(new Date());
    
    }
    
    @Transactional
    public void modificar(String id,String titulo,Integer duracion, String descripcion,Grupo grupo) throws ErrorServicio{
    
        validar(titulo, duracion, descripcion, grupo);
        Optional<Curso> respuesta = cursoRepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
       
        Curso curso = respuesta.get();
        curso.setTitulo(titulo);
        curso.setDescripcion(descripcion);
        curso.setDuracion(duracion);
        curso.setGrupo(grupo);
        cursoRepositorio.save(curso);
     
        }else {
            throw new ErrorServicio("Error: no se encontro el Curso solicitado");
        }
    }
    
    @Transactional
    public void baja(String id) throws ErrorServicio{

        Optional<Curso> respuesta = cursoRepositorio.findById(id);
        
        
        if(respuesta.isPresent()){
       
        Curso curso = respuesta.get();
        curso.setAlta(false);
        cursoRepositorio.save(curso);
     
        }else {
            throw new ErrorServicio("Error: no se encontro el Curso solicitado");
        }
    }
    
    @Transactional
    public void validar(String titulo,Integer duracion, String descripcion,Grupo grupo) throws ErrorServicio{
         if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo no puede ser nulo");
        }
         if (duracion == null || duracion<=0 ) {
            throw new ErrorServicio("La duracion tiene que ser mayor a 0");
        }
         if (descripcion == null || descripcion.isEmpty()) {
            throw new ErrorServicio("La descripcion no puede ser nula");
        }
         if (grupo == null ) {
            throw new ErrorServicio("La descripcion no puede ser nula");
        }
         
        
    }
    
    @Transactional
    public void agregarSeccion(String id_curso, Seccion seccion) throws ErrorServicio{
       Optional<Curso> respuesta= cursoRepositorio.findById(id_curso);
       
       if(respuesta.isPresent()){
        Curso curso = respuesta.get();
        try{   
            curso.getSecciones().add(seccion);
           cursoRepositorio.save(curso);
        }
        catch(Exception e){
            throw new ErrorServicio("Error interno en agregarSeccion");
        }
       }else{
           throw new ErrorServicio("El Curso es incorrecto");
       }
 
      
    
    }
    
    @Transactional(readOnly=true)
    public List<Seccion> obtenerSecciones(String id_curso)throws ErrorServicio {
    
    Optional<Curso> respuesta= cursoRepositorio.findById(id_curso);
       
       if(respuesta.isPresent()){
        Curso curso = respuesta.get();
        try{   
          
          List<Seccion>listaSeccion= curso.getSecciones();
          return listaSeccion;
           

        }
        catch(Exception e){
            throw new ErrorServicio("Error interno en agregarSeccion");
        }
       }else{
           throw new ErrorServicio("El Curso es incorrecto");
       }
    }
    
    

            
    
    
    
    
}
