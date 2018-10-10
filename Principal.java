package misiles_final;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		int menu;
		boolean end = false;
		Equipo equipos[] = new Equipo [5];
         
		
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida(); //Creamos el objecto partida con el constructor por defecto para llamar al menu 
		
		
		
		while (end == false) { //Bucle principal del corazon del juego
			
			partida.MostrarMenu(); //Llamamos a la funcion de la partida para mostrar el menu
			menu = Comprobar_Menu(sc, 6, 1); // Funcion del main para comprobar el menu
			
			switch(menu) { //menu
				case 1: //jugar
					break;
				case 2://Crear equipos
					System.out.println("Ahora crearas los equipos");
					crearEquipos(equipos,sc);
					for(int i=0; i != 5; i++) {
						System.out.print("Nombre equipo: "+equipos[i].getNombre_equipo());
						System.out.println(" vidas: "+equipos[i].getVidas());	
					}
					break;
				case 3://Mostrar reglas
					partida.MostrarReglas(); //Llamamos a la funcion de la partida para mostrar las reglas
					break;
				case 4: //Mostar info
					partida.MostrarInfo(); //Llamamos a la funcion de la partida para mostrar la info
					break;
				case 5://????
					//parte secreta
					break;
				case 6://Final del juego
					end = true;
					break;
			}
		}
		sc.close();
	}
	
	
	
	public static int Comprobar_Menu (Scanner sc, int max, int min) {
		
		int menu=0; //variable para guardar el valor en integer de "pedir_menu"
		String pedir_menu; //variable para que el usuario entre el menu
		
		do { //Bucle para comprobar lo que entra por scanner
			try { //Zona protgida por si nos entra una caracter no numerico
				
				pedir_menu = sc.nextLine(); //pedimos por escaner un valor del menu
				menu = Integer.parseInt(pedir_menu); //Lo pasamos a integer, sí no es un numerico el error sera capturado por el try
				
				if(menu > max || menu < min) { //Comprobamos que estan en los limites
					System.out.println("Error 404");
				}
			}catch(Exception e) { //Si nos ha introduido un caracter no numerico capturaremos el error
				System.out.println("No es valido colocar a caracteres en este lugar");
				menu = 0; //colocamos el menu a 0 para que el bucle se repita 
			}

		}while(menu > max || menu < min);
		
		return menu; //devolvemos el valor del menu
	}
	public static void crearEquipos(Equipo equipos[], Scanner sc) {
		String nombre_equipo;
		for(int i=0;i<equipos.length;i++) {
			System.out.println("Dame el nombre del equipo "+i+" :");
			nombre_equipo = sc.nextLine();
			Equipo equipo = new Equipo(nombre_equipo);
			equipos[i] = equipo;
			equipo = null;
		}
		//Sólo funciona sin el constructor Equipo()
		
		
		
	}

	
	
	
	
	
}
