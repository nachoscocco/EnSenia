/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensenia.Controller;

import com.ensenia.Entity.Curso;
import com.ensenia.Entity.Seccion;
import com.ensenia.Entity.Texto;
import com.ensenia.Entity.Video;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Repository.CursoRepositorio;
import com.ensenia.Repository.GrupoRepositorio;
import com.ensenia.Repository.SeccionRepositorio;
import com.ensenia.Repository.TextoRepositorio;
import com.ensenia.Repository.UsuarioRepositorio;
import com.ensenia.Repository.VideoRepositorio;
import com.ensenia.Service.CursoServicio;
import com.ensenia.Service.GrupoServicio;
import com.ensenia.Service.SeccionServicio;
import com.ensenia.Service.TextoServicio;
import com.ensenia.Service.UsuarioServicio;
import com.ensenia.Service.VideoServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class cursoController {
    @Autowired
    private UsuarioServicio usuarioServicio; 
    @Autowired
    private CursoServicio cursoServicio;
    @Autowired
    private GrupoServicio grupoServicio;
    @Autowired
    private SeccionServicio seccionServicio;
    @Autowired
    private TextoServicio textoServicio;
    @Autowired
    private VideoServicio videoServicio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio; 
    @Autowired
    private CursoRepositorio cursoRepositorio;
    @Autowired
    private GrupoRepositorio grupoRepositorio;
    @Autowired
    private SeccionRepositorio seccionRepositorio;
    @Autowired
    private TextoRepositorio textoRepositorio;
    @Autowired
    private VideoRepositorio videoRepositorio;
     
    
    @GetMapping("/contenido")   
    public String contenido(HttpSession session,ModelMap model,@RequestParam(value = "id")String id) {
      
        publicarInfoCurso(model,id);
        return "contenido";
     
      
    } 
    @GetMapping("/apartado")   
    public String apartado(HttpSession session,ModelMap model,@RequestParam(value = "id")String id,@RequestParam(value = "idCurso")String idCurso,@RequestParam(value = "clase")String clase)throws Exception{
       try{
        
        
        publicarInfoApartado(model, id,clase);
        publicarInfoCurso(model,idCurso);
        
           Video v = new Video();
           
        if(clase.equals(v.getClass().toString()) ){
            return "video";
        }else{
            return "texto";
        }
       }catch(Exception e){
           System.out.println("exception perro=="+e.getMessage());
          return "contenido";
       }
    }
    
    @GetMapping("/texto")   
    public String texto(HttpSession session,ModelMap model,@RequestParam(value = "id")String id,@RequestParam(value = "idCurso")String idCurso,@RequestParam(value = "clase")String clase) {
        publicarInfoCurso(model,idCurso);
        publicarInfoApartado(model, id,clase);
        return "texto";
     
      
    } 
    
    @GetMapping("/video")   
    public String video(HttpSession session,ModelMap model,@RequestParam(value = "id")String id,@RequestParam(value = "idCurso")String idCurso,@RequestParam(value = "clase")String clase) {
        publicarInfoCurso(model,idCurso);
        publicarInfoApartado( model,id,clase);

        return "video";
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   public void publicarInfoCurso(ModelMap model,String id){
       try {        
       Curso curso =cursoServicio.buscarCursoPorId(id);
       List<Seccion>secciones = cursoServicio.traerSeccionesXCurso(id);
       
         model.put("curso",curso);
         model.put("secciones",secciones);
           System.out.println("publicinfo curso="+curso.getTitulo());
         
      } catch (ErrorServicio e) {
          model.put("error", "ERROR DE CURSO ");
       
       } 
       
   }    
   
   public void publicarInfoApartado(ModelMap model,String id,String clase){
       try {
          Video v = new Video();
           
        if(clase.equals(v.getClass().toString()) ){
            v = videoRepositorio.getById(id);
            model.put("video",v);

        }else{
            Texto texto = textoRepositorio.getById(id);
            model.put("texto",texto);
        }
       } catch (Exception e) {
       }
   }
    
    
    
    
    
    
    
    
    
    
}
