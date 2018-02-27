package Calculadora;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Francsico Aramburu, David Espinosa, Alan Ramirez
 */
public class Calculos extends PilaA{

    
    
    private String datos;
    private double resultado;
    private int k;
    
    
    /**
     @param datos
     * Constructor de la clase Calculos
     */
    
    public Calculos(String datos){
        if(datos != "")
            this.datos=datos;
        else 
            this.datos= "5";
    }
    
    /**
     * Getter del resultado después de examinar una expresión
     * @return resultado
     */
    
    public double getResultado(){
        return resultado;
    }
    
    /**
     * Getter de la expresión ingresada
     * @return datos
     */
    
    public String getDatos(){
        return datos;
    }
    
    /**
     @param leer 
     * @return boolean
     * Método que revisa si los paréntesis en la expresión estan colocados
     * adecuadamente
     */
    
    
    public boolean revisorParentesis(String leer){
        PilaA<Character> revisor = new PilaA();
        int n=0;
        
        while(n<leer.length()){

            if(leer.charAt(n)=='('){
	
                revisor.push('(');

            }
	
            else{
        
                if(leer.charAt(n)==')'&&!revisor.isEmpty()){
                
                    revisor.pop();
                }
            

                else{
                    if(leer.charAt(n)==')'&& revisor.isEmpty()){
                      return false;
                    }
                   
                }
            }
            n++; 
        }

        return (revisor.isEmpty());
   
    

    }

    /**
     @param signo
     * Método que regresa la prioridad de un signo ante el orden de operaciones, regresa -1
     * si la expreción evaluada no es un signo.
     * @return Integer
     */
    
    public static int signo(String signo){
        int res=-1;
        
        try{
        switch(signo.charAt(0)){
            
            case '(': 
                res=0;
                
                break;
                
            case '+':
                res=1;
                
            case '-':
                res=1;
               
                break;
                
            case '/':
                res=2;
                
                break;
                
            case '*':
                res=2;
                
                break;
                
        }
        
        return res;
        }catch(Exception e){
            throw new ExceptionColecVacia();
        }
                
    }
    
    /**
     
     * Método que regresa un arreglo con los datos separados
     * @return String[]
     */
    
    
    public String[] separarString(){
        String arre[] = new String [40];
        
        int i = 0;
        StringTokenizer st = new StringTokenizer(datos);
        
         while (st.hasMoreTokens()) {
             arre[i] = st.nextToken();
             i++;
             
         }
        
        k = i;
        return arre;
        
    }
    
    /**
     @param cadena[]
     * Método que regresa la expreción a evaluar en notación postfija
     * @return ArrayList
     */
            
    public ArrayList notacionPostfija(String cadena[]){
        ArrayList<String> lista= new ArrayList();
        PilaA<String> pila= new PilaA();
        int pos=0;
        double aux = 0;
        
        
        while(pos<k){
          
           if(cadena[pos].equals("(")){
            pila.push("(");
           }
          
          else{
            if(cadena[pos].equals(")")){
            
                while(!pila.peek().equals("(")){
                    lista.add(pila.pop());
                }
                pila.pop();
            }
            else{
                if(signo(cadena[pos])==-1){
                    
                    lista.add(cadena[pos]);
                }
                else{
                   
                    while(!pila.isEmpty() && signo(cadena[pos])<signo(pila.peek())){
                        lista.add(pila.pop());    
                    }
                    pila.push(cadena[pos]);
                }
                
            }
        }
          pos++;
       }
        
       while(!pila.isEmpty()){
           lista.add(pila.pop());
        }
        
       
        return lista; 
    }
    
    /**
     @param leer
     * Método que anañiza si los signos están colocados de manera 
     * adecuada en la expresión
     * @return boolean
     */
    
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
       
    
    /**
     @param listaa
     * Método que recibe un ArrayList en notación postfija y analiza la expresión
     * @return double
     */
    
    public static double reduce(ArrayList<String> listaa){
        PilaA<Double> pila= new PilaA();
        int pos=0;
        double res=0;
        double num1;
        double num2;
        
        while(pos<listaa.size() && listaa.get(pos)!=null){
            
            if(signo(listaa.get(pos))==-1){
                pila.push(Double.valueOf(listaa.get(pos)));                
                
            }
            
            
            else{
                num1=pila.pop();
                num2=pila.pop();
                
                switch(listaa.get(pos).charAt(0)){
                    
                    case '+':
                        res=num1+num2;
                        
                        break;
                        
                    case '-':
                        res=num2-num1;
                        
                        break;
                        
                    case '/':
                        
                        if(num1!=0){
                            res= num2/num1;
                            //Excepcion
                        }
                        else
                           throw new ExceptionColecVacia();
                        
                        break;
                        
                    case '*':
                        
                        res= num1*num2;
                        
                        break;
                }
                
            pila.push(res);
            }
            
            pos++;
        }
        return pila.pop();
        
    }
    
    /**
     
     * Método que resuelve la expresión dada usando los métodos anteriores
     * y guarda el resultado en la clase
     * 
     */
    
    public void resuelve(){
        boolean siTrabaja, aux5;
        String cadena[];
        ArrayList<String> lista;
        
        aux5=revisorOperadores(datos);
        siTrabaja=revisorParentesis(datos);
        
        if(siTrabaja && aux5){
            
             cadena= separarString();
             lista= notacionPostfija(cadena);
             resultado=reduce(lista);
        
        }
        else
           throw new ExceptionColecVacia();
    }
    
   
   
}
