package es.florida.psp;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.text.DecimalFormat;

// En la clase probabilidad, utilizaremos una funcion llamada Prueba, 
// que nos retornara el resultado de la probabilidad, de que caiga un NEO 
// colisione con la tierra.

public class Probabilidad 
{
	public double Prueba(double posicionNEO, double velocidadNEO) 
	{
		double posicionTierra = 1;
		
		double velocidadTierra = 100;
		
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) 
		{
			posicionNEO = posicionNEO + velocidadNEO * i;
			
			posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow(((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		
		return resultado;
	}
	
// En la clase main, nos mostrara por consola y nos escribira el resultado de dicha probabilidad en un fichero, el resultado de la probabilidad
// de que colisione un NEO en la tierra, llamando a la funcion Prueba 
// y dependiendo de si la probabilidad es menor a un 10%, 
// nos saldra un mensaje u otro con el nombre de ese NEO 
// y el resultado de la probabilidad de colision, con 2 decimales.

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Probabilidad prob = new Probabilidad();
		
		String nom = args[0];
		
		double posneo = Double.parseDouble(args[1]);
		
		double velneo = Double.parseDouble(args[2]);
		
		double resultado = prob.Prueba(posneo, velneo);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String ficher = nom;
		
		File ficher1 = new File(ficher);
		
		try 
		{
			FileWriter fw = new FileWriter(ficher1);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(String.valueOf(resultado));
			
			bw.close();
			
			fw.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		if(resultado <= 10) 
		{
			System.out.println(nom + " Tranquilidad y las probabilidades de colision de cada neo es: " + df.format(resultado));
		}
		else 
		{
			System.err.println(nom + " Lanzar alerta mundial y las probabilidades de colision de cada neo es: " + df.format(resultado));
		}
	}
}