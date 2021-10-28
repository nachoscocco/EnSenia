<<<<<<< HEAD

package com.ensenia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class mainController {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
    
    
    
}
=======

package com.ensenia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class mainController {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
    
    
    
}
>>>>>>> master
