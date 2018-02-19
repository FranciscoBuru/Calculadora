/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

/**
 *
 * @author Francsico Aramburu
 */
public class ExceptionColecVacia  extends RuntimeException{
    
    public ExceptionColecVacia(){
        super("Colección vacía");
    
    }
    
    public ExceptionColecVacia(String mensaje){
        super(mensaje);
    
    }
}