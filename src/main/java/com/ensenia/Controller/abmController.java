
package com.ensenia.Controller;

import com.ensenia.Entity.Curso;
import com.ensenia.Entity.Grupo;
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
import java.sql.Clob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("/abm")
public class abmController {
    
    
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
    
  ////////////////////////////////////*** CURSOS  ***///////////////////////  
    @GetMapping("/abm_cursos")
    public String abm_cursos(ModelMap model){
        publicarInfoTemplate(model);
        
    return "abm/abm_cursos.html";
    }
    
    @PostMapping("/alta_curso")
    public String alta_curso(ModelMap model,@RequestParam String titulo,@RequestParam String descripcion,@RequestParam Integer duracion,@RequestParam String grupoId) throws Exception{
        try {
            Grupo grupo = grupoServicio.buscaGrupoPorId(grupoId);
            if (grupo.getId() != null) {
                
                cursoServicio.alta(titulo, duracion, descripcion,grupo );
                
            }
    
           
            
        } catch (ErrorServicio e) {
            System.out.println("Error de servicio 'altaCurso' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_cursos";
    }
    
    @PostMapping("/baja_curso")
    public String baja_curso(ModelMap model,@RequestParam String cursoId) throws Exception{
        try {
            cursoServicio.baja(cursoId);
            
        } catch (ErrorServicio e) {
             System.out.println("Error de servicio 'baja de curso' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_cursos";
    }
    
    @PostMapping("/modif_curso")
    public String modif_curso(ModelMap model,@RequestParam String cursoId,@RequestParam(required=false) String titulo,@RequestParam(required=false) String descripcion,@RequestParam(required=false) Integer duracion,@RequestParam(required=false) String grupoId) throws Exception{
        try {
             Grupo grupo = grupoServicio.buscaGrupoPorId(grupoId);
            if (grupo.getId() != null) {
                
                cursoServicio.modificar(cursoId, titulo, duracion, descripcion, grupo);
                
            }
            
            
            
        } catch (ErrorServicio e) {
            System.out.println("Error de servicio 'modifCurso' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_cursos";
    }
    
     ////////////////////////////////////*** SECCIONES  ***///////////////////////
        
        
    @GetMapping("/abm_secciones")
    public String abm_secciones(ModelMap model){
        publicarInfoTemplate(model);
        
    return "abm/abm_secciones.html";
    }
    
    
    @PostMapping("/alta_seccion")
    public String alta_seccion(ModelMap model,@RequestParam String cursoId,@RequestParam String titulo,@RequestParam Integer numero) throws Exception{
        try {

             seccionServicio.alta(cursoId, titulo, numero);
        
            
        } catch (ErrorServicio e) {
            System.out.println("Error de servicio 'altaSeccion' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_secciones";
    }
    
    
    @PostMapping("/baja_seccion")
    public String baja_seccion(ModelMap model,@RequestParam String seccionId) throws Exception{
        try {
            seccionServicio.baja(seccionId);
            
        } catch (ErrorServicio e) {
             System.out.println("Error de servicio 'bajaSeccion' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_secciones";
    }
    
    
    @PostMapping("/modif_seccion")
    public String modif_seccion(ModelMap model,@RequestParam String seccionId,@RequestParam String cursoId,@RequestParam String titulo,@RequestParam Integer numero) throws Exception{
        try {

             seccionServicio.modificar(seccionId, cursoId, titulo, numero);
                   
        } catch (ErrorServicio e) {
            System.out.println("Error de servicio 'modifSeccion' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_secciones";
    }
    
    
    
    
    
    
    @GetMapping("/abm_apartados")
    public String abm_apartados(ModelMap model){
        publicarInfoTemplate(model);
        
    return "abm/abm_apartados.html";
    }
    
    @PostMapping("/alta_texto")
    public String alta_texto(ModelMap model,@RequestParam String seccionId,@RequestParam String titulo,@RequestParam Integer numero,@RequestParam String contenido) throws Exception{
        try {
          
            textoServicio.alta(contenido, titulo, numero, seccionId);
            
        
            
        } catch (ErrorServicio e) {
            System.out.println("Error de servicio 'altaSeccion' ="+e.getMessage());
            model.put("error",e.getMessage());
        }
        publicarInfoTemplate(model);
        return "redirect:/abm_secciones";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void publicarInfoTemplate(ModelMap model) {
        List<Grupo> grupos = grupoRepositorio.findAll();
        model.put("grupos",grupos);
        List<Curso> cursos = cursoRepositorio.traerCursosAlta();
        model.put("cursos",cursos);
        List<Seccion> secciones = seccionRepositorio.traerSeccionesAlta();
        model.put("secciones",secciones);
        List<Texto> textos = textoRepositorio.findAll();
        model.put("textos",textos);
        List<Video> videos = videoRepositorio.findAll();
        model.put("videos",videos);
        
        
       
    }
}
