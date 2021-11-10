
package com.ensenia.Controller;

import com.ensenia.Entity.Usuario;
import com.ensenia.Error.ErrorServicio;
import com.ensenia.Service.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping ("/")
public class mainController {
    
    @Autowired
    private UsuarioServicio usuarioServicio; 
    
    @GetMapping("/")
    public String index() {
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
    public String inicio(HttpSession session) {
        
        Usuario login = (Usuario)session.getAttribute("usuariosession");
        
//        System.out.println("login.toString())" + login.getNombre());
        
        
        return "index.html";
    }
    
    @PostMapping("/loguearse")
    public String login(@RequestParam(required = false)String error,@RequestParam(required = false)String logout, ModelMap model) {
        if (error != null) {   
        model.put("error", "Nombre de Usuario O Clave Incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Ha Salido Correctamente De La Plataforma");
        }
        
        System.out.println("Error = " + error);
        
        return "redirect:/index.html";
    }
    
    
}

