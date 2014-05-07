package com.javi.ejerciciocalculadora;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Calculadora {

	public double operando1=0;
	public String operador="";
	public String display="";
	public double resultado=0;
	public Calculadora() {
		
	}

	private void numeroPulsado(String digito) {
		// Para no acumular los ceros
		 if(display.equals("0"))  display=digito;
		 else display=display+digito;// concatenacion de string
    }
 
	private void operacionPulsado(String operadorNuevo) {
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
        else if(operador.equals("*")) resultado=operando1*operando2;
        else if(operador.equals("/"))resultado=operando1/operando2;
         
        System.out.println("el resultado es:::"+resultado);
        display=String.valueOf(resultado);
        operando2=0;
            return String.valueOf(resultado);
    }
    
	public int suma(int n1, int n2){//metodo para sumar, recibe dos numeros
        int op; //variable que almacena la respuesta
        op = n1 + n2; //operacion
        return op; //devuelve el valor de la operacion
    } //fin metodo suma
 
    public int resta (int n1, int n2){ //metodo para restar, recibe dos numeros
        int op; //variable que almacena la respuesta
        op = n1 - n2; //operacion
        return op; //devuelve el valor de la operacion
    }// fin metodo resta
 
    public int multiplicacion(int n1, int n2){ //metodo para multiplicar, recibe dos numeros
        int op; //variable que almacena la respuesta
        op = n1 * n2; //operacion
        return op; //devuelve el valor de la operacion
    } //fin metodo multiplicacion 
 
    public int division(int n1, int n2){ //metodo para division, recibe dos numeros
        int op; //variable que almacena la respuesta
        op = n1 / n2; //operacion
        return op; //devuelve el valor de la operacion
    }//fin metodo division
 
	 private void calcularResultado(View v) {
	     
	    }
	
}
