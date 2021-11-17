
package com.ensenia.Controller;

import com.ensenia.Entity.Curso;
import com.ensenia.Entity.Grupo;
import com.ensenia.Entity.Seccion;
import com.ensenia.Entity.Texto;
import com.ensenia.Entity.Usuario;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping ("/")
public class mainController {
    
                 /* SERVICIOS*/
    
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
            
            
    
    @GetMapping("/")
    public String inicio() {
        return "inicio.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String mail,@RequestParam String contrasenia,@RequestParam String contrasenia2){
        
        try {
            
            usuarioServicio.registrar(nombre, apellido, mail, contrasenia, contrasenia2);
            
            modelo.put("registrar", "Bienvenido a En-Seña");
            return "inicio.html";
        
        } catch (ErrorServicio e) {
            
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("contrasenia", contrasenia);
            modelo.put("contrasenia2", contrasenia2);
            
            return "inicio.html";
        }
//            modelo.put("descripcion", "Tu Usuario fue Registrado de Manera Satisfactoria");
    }

    @GetMapping("/index")
    public String index(HttpSession session,ModelMap modelo) {
      try{  
          Usuario login = loguearseSession(session);
         modelo.put("isAdmin",isAdmin(login));
        
          System.out.println("rol usuario"+login.getRol().getDescripcion() );
        System.out.println("login==="+session.getId());
      }catch(Exception e){
            
            modelo.put("error", e.getMessage());
             return "inicio.html";
      }
        
        
        return "index.html";
    }
    
    @PostMapping("/loguearse")
    public String login(@RequestParam(required = false)String error,@RequestParam(required = false)String logout, ModelMap model,@RequestParam String mail,@RequestParam String contrasenia) {
       

        if (error != null) {   
        model.put("error", "Nombre de Usuario O Clave Incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha Salido Correctamente De La Plataforma");
        }
        
        return "redirect:/index.html";
    }
    
    @GetMapping("/contenido")
    public String contenido(HttpSession session) {
       
        return "contenido.html";
    }
    
    
    
 
    
    public Usuario loguearseSession(HttpSession session){
        Usuario login = (Usuario)session.getAttribute("usuariosession");
        return login;
    }
    
   public Boolean isAdmin(Usuario user){
      Boolean b = false;
       System.out.println("user.getRol().getId()"+user.getRol().getId());
      if(user.getRol().getId().equals("3") ){
          b=true;
          System.out.println("rol = "+b);
           return b;
      }else{
         System.out.println("rol = "+b);
      return b;
      }
    
   }
    
    
    
}

