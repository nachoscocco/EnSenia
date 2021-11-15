
package com.ensenia.Service;

import com.ensenia.Entity.Usuario;
import com.ensenia.Error.ErrorServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.context.request.ServletRequestAttributes;
import com.ensenia.Repository.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    
    ///////////// Metodo Registrar Usuario
    
    @Transactional
    public void registrar(String nombre, String apellido, String mail, String clave1, String clave2) throws ErrorServicio{
    
        validar(nombre, apellido, mail, clave1, clave2);
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        
        String encriptada = new BCryptPasswordEncoder().encode(clave1);
        
        usuario.setClave(encriptada);
        
//        System.out.println("clave = " + usuario.getClave());
//        usuario.setClave(clave1);
        
        usuarioRepositorio.save(usuario);
    }
    
    //////////// Metodo Modificar Usuario
    
    @Transactional
    public void modificar(String id, String nombre, String apellido, String mail, String clave1, String clave2) throws ErrorServicio{
    
        validar(nombre, apellido, mail, clave1, clave2);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Usuario usuario = respuesta.get();
        
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        
        String encriptada = new BCryptPasswordEncoder().encode(clave1);
        usuario.setClave(encriptada);
        
        usuario.setClave(clave1);
        
        usuarioRepositorio.save(usuario);     
        } else {
            throw new ErrorServicio("No Se Encontro El Usuario Solicitado");
        }
    }
    
    ///////////////// Metodo Eliminar Usuario
    
    @Transactional
    public void eliminar(String id) throws ErrorServicio {
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            
            Usuario usuario = respuesta.get();
            
            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No Se Encontro El Usuario Solicitado");
        }
    }
    
    ///////////// Metodo Validar Datos
    
    @Transactional
    private void validar(String nombre, String apellido, String mail, String clave1, String clave2)throws ErrorServicio {
       
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El Nombre De Usuario No Puede Ser Nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El Apellido Del Usuario No Puede Ser Nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El Mail No Puede Ser Nulo");
        }
        if (clave1 == null || clave1.isEmpty() || clave1.length() <= 6 ) {
            throw new ErrorServicio("La Clave Del Usuario No Puede Ser Nula y Debe Contener Mas De 6 Digitos");
        }
        if (!clave1.equals(clave2)) {
            throw new ErrorServicio("Las Claves Deben Ser Iguales");
        }
        
    }
   
    /////////////// Metodo Buscar Usuario Por Id
    
    @Transactional
    public Usuario buscarPorId(String id) throws ErrorServicio{
        
        Optional <Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ErrorServicio("El Usuario Solicitado No Existe");
        }
    }  
    
    ///////////////////// Metodo Seguridad
    
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
       
        Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
       
        System.out.println("Linea 138 = " + usuario);
        
        if (usuario != null) {
            List <GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority permiso1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(permiso1);
            
            ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            
            User user = new User(usuario.getMail(), usuario.getClave(), permisos);
//            System.out.println("User + " + user.toString());
            return user;
        } else {
            return null;
        }
    }   
    
    public void usuarioVeCurso(String id_usuario, String id_curso){
    
        System.out.println("");
        
    }
    
    
}
