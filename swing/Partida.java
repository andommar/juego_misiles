package misiles_v4;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Partida {
	
	//variables fijas que sirven para determinar el rango de los menus

	
	private String ganador;
	private String info="Este juego consiste en una batalla entre planetas (jugadores) hasta que solo quede uno en pie (o ninguno).\n "
			+ "El juego funciona por rondas. Cada ronda un planeta puede elegir a quien atacar con sus misiles o utilizarlos para defenderse";

	
	
	//metodos
	
	

	public void MostrarMenu ()	{
		System.out.println("------------- MENÚ ------------");
		System.out.println("---- 1 | Jugar ----------------");
		System.out.println("---- 2 | Crear equipos --------");
		System.out.println("---- 3 | Reglas del juego -----");
		System.out.println("---- 4 | Apartado abierto -----");
		System.out.println("---- 5 | Mostrar ranking -----");
		System.out.println("---- 6 | Salir ----------------");
	}
	
	public boolean Comprobarequiposvivos(int equipos) {//En el caso de que solo quede 1 o 0 equipos se devuelve true, sino false y se sigue jugando
		
		if(equipos!=1 && equipos!=0)
			return true;
		else
			return false;
	}
	
	
	public void MostrarGanador(int num_equipos) throws Exception {//Si no queda ningún equipo, se muestra el texto de que han muerto todos, sino se muestra el ganador
		
		if(num_equipos==0)
			System.out.println("¡TODOS HAN MUERTO!");
		
		else {
			System.out.println("¡El ganador es el equipo "+this.ganador+"!");
			
			File f = new File("Ranking_equipos.xml");
			XMLGenerator xml = new XMLGenerator();
			
			if(f.exists()) {
				
				//Si el equipo ya está registrado en el archivo XML se modifica su número de victorias
				if(xml.ComprobarExisteEquipoXML(this.ganador))
					xml.ModificarXML(this.ganador);
				//Si no lo está, se añade al archivo XML
				else {
					System.out.println("No existe");
					xml.AnadirXML(this.ganador);
				}
			}
			else {
				xml.GenerarXML(this.ganador);
			}
				
		}
	}
	
	public void Mostrartipoplaneta(){
		System.out.println("A continuación se mostrarán los tipos de planeta.");
		System.out.println("1.\t Planeta normal: daño y vida por defecto.");
		System.out.println("2.\t Planeta rojo: Hace el doble de daño a planeta verde. Hace la mitad de daño a planeta azul.");
		System.out.println("3.\t Planeta azul: Hace el doble de daño a planeta rojo. Hace la mitad de daño a planeta verde.");
		System.out.println("4.\t Planeta verde: Hace el doble de daño a planeta azul. Hace la mitad de daño a planeta rojo.");
		System.out.println("5.\t Gigante gaseoso: Doble de vida. Empieza con 10 misiles, aumenta 10 misiles por cada ronda.");
		System.out.println("6.\t Planeta enano: Mitad de vida. Probabilidad esquivar ataques 50%.");
		System.out.println("7.\t Planeta cabrón: 1 de vida. 300 misiles por ronda.");
		System.out.println("8.\t Planeta dejadme en paz: Cuenta con 50 misiles extra de defensa.");
		System.out.println("9.\t Planeta copión. En cada ronda elige a qué planeta copiarle (solo vida y nombre)");
		System.out.println("10.\t Planeta de los simios: Al inicio de cada ronda puede x2 su vida y misiles o reducir a la mitad vida y misiles");	
		
	}
	
	public void MostrarInfo() {
		System.out.println("_______________________________");
		System.out.println("Juego misiles v2.");
		System.out.println("Código picado, sudado y llorado por Andrés Domínguez.");
		System.out.println("DAW 2. Ilerna Lleida.");
		System.out.println("_______________________________");
	}
	
	public void NormasJuego() {
		System.out.println("_______________________________");
		System.out.println("Este juego consiste en una batalla entre planetas (jugadores) hasta que solo quede uno en pie (o ninguno)");
		System.out.println("El juego funciona por rondas. Cada ronda un planeta puede elegir a quien atacar con sus misiles o utilizarlos para defenderse");
		System.out.println("Por defecto cada planeta tiene 50 misiles cada ronda. Si decide utilizarlos como defensa, su coste es el doble.");
		System.out.println("Es decir si se tiene 50 misiles y se quiere destinar a la defensa. Se quedará con 25 misiles (50/2");
		System.out.println("Eso es por defecto, existen modificadores según el tipo de planeta");
		System.out.println("También es posible añadir insultos/frases que se pueden enviar al atacar");
		System.out.println("_______________________________");
	}
	
	public void finalizarPartida() {//metodo que finaliza el programa y lo cierra
		
		System.out.println("La partida ha finalizado. ¡Enhorabuena al ganador!");
		System.out.println("Finalizando el programa...");
		System.exit(0);
	}

	

	public int ControlErroresNum (Scanner sc, int min_opcion, int max_opcion) {
		
		String datos;
		int numero=0;
		boolean error= false;
		//Try catch que controla que solo se introduzcan números. También controla que estos números estén dentro de un rango
		do {
			error = false;
			try { 
				datos = sc.nextLine(); 
				numero = Integer.parseInt(datos); 
				 
				
				if(numero > max_opcion || numero < min_opcion) { //si los números no están dentro del rango, da error							
					
					System.out.println("Error: No has introducido un valor entre "+min_opcion+" y "+max_opcion+". Escribe un número correspondiente al tipo de planeta.");
					error = true;
				}
			}catch(Exception e) { //si se introducen caracteres no númericos da error										
				
				System.out.println("Error: Introduce un número por favor entre "+min_opcion+" y "+max_opcion+". Escribe un número correspondiente al tipo de planeta.");
				error = true;
			}
		}while(error);
		
		return numero;
	}
	
	public int ControlErroresNumMenu (Scanner sc, int min_opcion, int max_opcion) {
		
		String datos="";
		int numero=0;
		boolean error= false;
		//Try catch que controla que solo se introduzcan números. También controla que estos números estén dentro de un rango
		do {
			error = false;
			try {
				datos = sc.nextLine(); 
				numero = Integer.parseInt(datos); 
				 
				
				if(numero > max_opcion || numero < min_opcion) { //si los números no están dentro del rango, da error							
					
					System.out.println("Error: No has introducido un valor entre "+min_opcion+" y "+max_opcion+".");
					error = true;
				}
			}catch(Exception e) { //si se introducen caracteres no númericos da error										
				
				System.out.println("Error: Introduce un número por favor entre "+min_opcion+" y "+max_opcion+".");
				error = true;
			}
		}while(error);
		
		return numero;
	}
	
	
	public String CaracteresRaros_palabras(Scanner sc, int cont) {
		
		String nombre_planeta="yep";
		
		//Try catch que controla caracteres como $, &, no se introduzcan en el string. También controla que no sea un string vacío
		
		boolean error = false;																 
		do {
			error = false;
			nombre_planeta = sc.nextLine();
			
			if(!nombre_planeta.matches("^[a-zA-Z]*$")){ //mientras contenga números o caracteres especiales dará error						
				
				error = true;	
				System.out.println("Error: No se pueden introducir carácteres especiales ni números. Escribe nombre de equipo:");
			}
			else if(nombre_planeta == null || nombre_planeta.equals("")) { //también da error si es un string vacio						
				error = true;
				System.out.println("Error: El nombre del planeta debe tener un nombre. Escribe nombre de equipo:");
			}
			else if(nombre_planeta.equals("salir") && cont<3) {
				error=true;
				System.out.println("Error: ¡No puedes salir sin introducir un mínimo de 3 equipos! Escribe nombre de equipo:");
			}
			
		}while(error);
		
		return nombre_planeta;
	}
	
	

	
	//getters setters

	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	
	
}
