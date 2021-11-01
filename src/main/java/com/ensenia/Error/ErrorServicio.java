
package com.ensenia.Error;

import org.springframework.stereotype.Service;

@Service
public class ErrorServicio extends Exception{
 
    public ErrorServicio(String msn){
    super(msn);
    }
}
