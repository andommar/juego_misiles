package misiles_v4;

import java.util.ArrayList;
import java.util.Scanner;


public class Jugar {
	
	
	//variables fijas del num min de equipos, seleccion de menus, etc
	final static int NUM_MIN_EQUIPOS=3;
	
	final static int OPCION_MIN_TIPO=0;
	final static int OPCION_MAX_TIPO=10;
	
	final static int OPCION_MIN_MENU=1;
	final static int OPCION_MAX_MENU=6;
	

	public static ArrayList <Planeta> planetas = new ArrayList <Planeta>();
	public static ArrayList <String> frases = new ArrayList <String>();
	
	
		
	
public void	CrearEquipos(ArrayList<Planeta>planetas, Scanner sc, Partida partida) {
	
	int sel_tipo_planeta=0, cont=0;
	String nombre="";
	
	System.out.println("Introduce nombre mínimo 3 equipos");
	System.out.println("Introduce nombre equipo. Escribe 'salir' si has finalizado de introducir los equipos:");
	nombre=partida.CaracteresRaros_palabras(sc,cont);
	
	while(!nombre.equals("salir") || cont<3) {
		


		System.out.println("Selecciona el tipo de planeta que quieres ser. Pulsa 0 para ver la información:");
		//sel_tipo_planeta=sc.nextInt();
		
		sel_tipo_planeta=partida.ControlErroresNum(sc,OPCION_MIN_TIPO,OPCION_MAX_TIPO);
		
		while(sel_tipo_planeta==0) {
			partida.Mostrartipoplaneta();
			sel_tipo_planeta=sc.nextInt();
			sc.nextLine();
		}
		

		Planeta planeta = new Planeta (nombre, sel_tipo_planeta);
		planetas.add(planeta);
		
		cont++;
		System.out.println("Introduce nombre equipo. Escribe 'salir' si has finalizado de introducir los equipos:");
		nombre=partida.CaracteresRaros_palabras(sc,cont);

	}
	
	
	for(Planeta pasar_planeta:planetas) {
		System.out.println("Nombre del planeta:"+pasar_planeta.getNombre());
	}
	
}
	
}
