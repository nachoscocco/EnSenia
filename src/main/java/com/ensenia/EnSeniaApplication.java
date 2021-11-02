package com.ensenia;

import com.ensenia.Error.ErrorServicio;
import com.ensenia.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EnSeniaApplication {

    @Autowired
    private UsuarioServicio usuarioServicio;
    
	public static void main(String[] args) {
		SpringApplication.run(EnSeniaApplication.class, args);
	}
        
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
        }
        

}
