
package com.ensenia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class mainController {
    
    @GetMapping("/")
    public String index() {
        return "inicio.html";
    }
    

   // @GetMapping("/inicio")
  //  public String inicio() {
    //    return "inicio.html";
    //}
    
    
    
}

