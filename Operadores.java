/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora;

import java.util.StringTokenizer;

/**
 *
 * @author Francisco Aramburu
 */
public class Operadores {
    
     public static boolean  revisorOperadores(String leer){
        
        StringTokenizer st = new StringTokenizer(leer);
        double aux;
        String aux1;
        PilaA tokens = new PilaA();
        int n=0;
        
        tokens.push(st.nextToken());
        n++;
        
        if(tokens.peek().equals("-")){
            tokens.pop();
            n--;
        }
        
      
        while (st.hasMoreTokens()) {
            
            aux1 = st.nextToken();
            
            
            
           
            
                if( aux1.equals("+") || aux1.equals("-")  || aux1.equals("*")  || aux1.equals("/") ){
                    if(n==0){
                        return false;
                    }
                    tokens.pop();
                    n--;
                }
            
                else if (aux1.equals(")") ){
                    if(n==0){
                        
                    }
                    else
                        tokens.pop();
                        n--;
                }
                
                else if(aux1.equals("(")){
                    tokens.push(aux1);
                    n++;
                }
            
                else{
                    tokens.push(aux1);
                    n++;
                }
                
                if(!st.hasMoreTokens()){
                    if( aux1.equals("+") || aux1.equals("-")  || aux1.equals("*")  || aux1.equals("/") ){
                        return false;
                    }
            
            }
         
        
            
        }
        
        return true;
     }
     
     
     public static void main(String[] args) {
        
         System.out.println(revisorOperadores("- 5 + ( 56 + 6 )   - 23 * 5 / 5 "));
         
         
    }
    
    
}
