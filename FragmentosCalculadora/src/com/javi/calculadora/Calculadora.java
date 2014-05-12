package com.javi.calculadora;

import com.javi.calculadora.FragmentoTeclado.InterfazTeclado;

import android.util.Log;


public class Calculadora {

	public double operando1=0;
	public String operador="";
	public String display="";
	public double resultado=0;
	
	public Calculadora() {
		
	}

	public void numeroPulsado(String digito) {
		// Para no acumular los ceros
		 if(display.equals("0"))  display=digito;
		 else display=display+digito;// concatenacion de string
    }
 
	public void operacionPulsado(String operadorNuevo) {
		operador=operadorNuevo;
        // compruebo que no ste vacia
        if(!display.equals("")) operando1=Double.parseDouble(display);
        //Evitar dos operadores
        display="";
    }
	
	public String calcularOperacion()
    {
        double operando2=Double.parseDouble(display);
        if(operador.equals("+")) resultado=operando1+operando2;
        else if(operador.equals("-")) resultado=operando1-operando2;
        else if(operador.equals("x")) resultado=operando1*operando2;
        else if(operador.equals("/"))resultado=operando1/operando2;
       // else if(operador.equals("Borrar")) resultado=0;
         
        Log.d("RESULTADO","el resultado es:::"+resultado);
        display=String.valueOf(resultado);
        operando2=0;
            return String.valueOf(resultado);
    }
    
	
	
}
