package es.florida.psp;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// En la clase Lanzador, llamaremos a traves de lanzarProbabilidad, a la clase Probabilidad 
// y a traves del process builder, ejecutaremos todo lo que hace la clase probabilidad 
// (esta clase nos indica la probabilidad de una colision de NEO, en la tierra). 

public class Lanzador 
{
	public void lanzarProbabilidad(String neo, String posneo, String velneo) 
	{
		String clase = "es.florida.psp.Probabilidad";
		
		try 
		{
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			// System.out.println(classpath);
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(neo.toString());
			command.add(posneo.toString());
			command.add(velneo.toString());
			
			// System.out.println("Comando que se pasa a ProcessBuilder: " + command);
			// System.out.println("Comando a ejecutar en cmd.exe: " + command.toString().replace(",",""));
	
			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			process.waitFor();
			// Process process = builder.start();
//			process.waitFor();
//			System.out.println(process.exitValue());
			

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
// En la clase main, recorreremos y mostraremos en un array de strings separados con comas, 
// los resultado de las probabilidades de las colisiones de los NEO en la tierra
// y mostraremos aparte en consola, los resultado del tiempo medio y tiempo final de la ejecuccion de la aplicacion.

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String[] elementos;
		
		String ficher = args[0];
		
		File ficher1 = new File(ficher);
		
		int cores = Runtime.getRuntime().availableProcessors();
		
		try
		{
			FileReader fr = new FileReader(ficher1);
			
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			
			long tiempoInicio = System.nanoTime();
			
			for(int i = 0; i < cores; i++) 
			{
				elementos = linea.split(",");
					
				Lanzador lanza = new Lanzador();
						
				lanza.lanzarProbabilidad(elementos[0],elementos[1],elementos[2]);
				
				linea = br.readLine();
			}
			
			long tiempoFin = System.nanoTime();
			
			long tiempoMedio = (tiempoFin - tiempoInicio)/cores;
			
			long tiempoTotal = (tiempoFin - tiempoInicio)/1000000;
			
			System.out.println("Tiempo medio de ejecucion de la aplicacion: " + tiempoMedio + " ms");
			
			System.out.println("Tiempo de ejecucion total de la aplicacion: " + tiempoTotal +" ms");
			
			br.close();
			
			fr.close();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}