package misiles_v4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

//Cambios respecto v2 (entregada)
//--------------------------------------------------------------------------------
//-->Controlado en caso de que se quiera empezar a jugar sin introducir los equipos
//-->Arreglado en caso de que al seleccionar a que equipo atacar, si se pone +1 respecto a las opciones que existen aún entraba

import java.util.Scanner;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class main_juego_planetas{
	
	
	//https://jarroba.com/arraylist-en-java-ejemplos/
	
	public static ArrayList <String> frases = new ArrayList <String>();
	public static ArrayList <Planeta> planetas = new ArrayList <Planeta>();
	
	//variables fijas del num min de equipos, seleccion de menus, etc
	final static int NUM_MIN_EQUIPOS=3;
	
	final static int OPCION_MIN_TIPO=0;
	final static int OPCION_MAX_TIPO=10;
	
	final static int OPCION_MIN_MENU=1;
	final static int OPCION_MAX_MENU=6;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int menu=0,ronda=0;
		boolean end = false, fin_partida=false;

		
		Scanner sc = new Scanner (System.in);
		
		Partida partida = new Partida ();
		
		
		
		
		//-------------------------INTERFICIE SWING-------------------------
		
		
		

		
		//-------------------------MENU
		
		
		
		
		
		//--------------------------------------------------
		
		while (end != true) {
		
			partida.MostrarMenu();
			
			System.out.println("Selecciona una opción");
			menu=partida.ControlErroresNumMenu(sc,OPCION_MIN_MENU,OPCION_MAX_MENU);
			
			switch(menu) {
			
				case 1:
						if(!planetas.isEmpty()) {
							while (partida.Comprobarequiposvivos(Planeta.getNum_equipos())) {
								
								ronda++;
								
								System.out.println("\t		____________" );
								System.out.println("\t		| RONDA: "+ronda+" |" );
								
								Jugar(planetas,frases,sc);
								
								System.out.println("Numero planetas vivos: "+Planeta.getNum_equipos());
								
								for (Planeta planetas_partida:planetas) {
									System.out.println(planetas_partida.getNombre()+" "+planetas_partida.getVidas()+" vidas.");
									if(planetas_partida.getVidas()>0)
										partida.setGanador(planetas_partida.getNombre());
								}//metodo de partidas que nos devuelve el listado de los equipos y sus vidas
								
								
							}
							
							////metodo que en el caso de que haya 0 equipos restantes no devuelve ningun ganador y guarda en un XML el equipo ganador
							try {
								partida.MostrarGanador(Planeta.getNum_equipos());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							
							
							partida.finalizarPartida(); //metodo que finaliza el programa y lo cierra
						}
						else
							System.out.println("¡Primero tienes que crear los equipos!");
						
						break;
						
				case 2:
						CrearEquipos(planetas,sc, partida);
						break;
				case 3:
						partida.NormasJuego();
						break;
				case 4:
						System.out.println("No te cortes, son solo palabras.");
						CrearInsultos(planetas,sc);
						break;
				case 5:
						
						File f = new File("Ranking_equipos.xml");
						
						if(f.exists()) {
						  	System.out.println("________________________________________________");
							XMLGenerator xml = new XMLGenerator();
							try {
								xml.MostrarRankingXML();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("________________________________________________");
						}
						
						else
							System.out.println("Hay que jugar al menos una partida para ver el ranking!");
						break;
											
				case 6:
						System.out.println("Saliendo");
						end = true;
						break;
						
						
			}
			
			
		}
			
	}
	
	
	public static void CrearEquipos(ArrayList<Planeta>planetas, Scanner sc, Partida partida) {
		
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
	
	public static void Jugar(ArrayList<Planeta>planetas, ArrayList<String> frases, Scanner sc) {
		
		int opcion,seleccion, misiles_ataq, index=1,misiles_ataq_modificados=0, copiar=0;
		String insulto;
		
		
		
		for(Planeta pasar_planeta:planetas) 
		{
			seleccion=-1;
			pasar_planeta.setMisiles_disponibles(pasar_planeta.getMisiles_antes_ronda()); //al inicio de cada ronda establecemos la variable de misiles disponibles copiando la de misiles por ronda

			
			
			//Arrancamos el arraylist con el primer elemento
			System.out.println("----------------------------------------------------");
			System.out.println("| \t Turno de ataque del equipo: "+ pasar_planeta.getNombre()+"\t \t|");
			System.out.println("----------------------------------------------------");

			
			
			while(seleccion!=0 && pasar_planeta.getMisiles_disponibles()>0) { //mientras la opcion escogida sea diferente de 0 y aun tenga misiles disponibles realizamos lo del dentro del bucle
				
				index=1;
				for(Planeta planeta_atacado:planetas)//Arrancamos un segundo array para recorrer los demás planetas
				{
					
					if(!((planeta_atacado.getNombre()).equals(pasar_planeta.getNombre())))
						//si el elemento del segundo array(planeta_atacado) tiene nombre diferente al elemento 
						//que nos encontramos, entonces imprimimos el nombre e incrementamos el indice y 
					{
						System.out.print(index+". ");
						System.out.println(planeta_atacado.getNombre());
						index++;
					}

				}
				
				seleccion=-1;
				System.out.println("0. Misiles restantes a defensa");
				System.out.println("----------------------------------------------------");
				System.out.println("Misiles disponibles: "+ pasar_planeta.getMisiles_disponibles());
				while(seleccion<0 || seleccion>index-1) {
					System.out.println("¿A quién quieres atacar? Introduce el número correspondiente de la lista.");
					seleccion=sc.nextInt();
				}
				
				
				if(seleccion!=0) {
					
					//si selecionamos un valor diferente de 0. tenemos en cuenta la seleccion que hace el usuario
					//si observamos bien, en nuestro array de objetos de planetas las posiciones coinciden con el indice que se muestra en pantalla
					//pero una vez seleccionamos una opcion que es superior a la posicion del objeto en el arraylist, entonces no coincide
					//es debido a que cuando nos saltamos al jugador al que le toca atacar (no puede atacarse a si mismo) los indices se desplazan
					//en resumen: restamos 1 a la opcion que ha escogido el usuario para que nos coincida con el objeto del arraylist
					if(seleccion<=planetas.indexOf(pasar_planeta))
						opcion=seleccion-1;
					//si la seleccion del usuario no es mas alta que la posicion del indice en el arraylist, no hacemos nada
					else
						opcion=seleccion;
					
					System.out.println("Introduce el número de misiles con los que quieres atacar");
					misiles_ataq=sc.nextInt();
					
					
						
					//comprobamos que el usuario no mande más misiles de los que tiene disponibles y le avisamos que no puede hacerlo
					while(misiles_ataq>pasar_planeta.getMisiles_disponibles() || misiles_ataq<0 ){
						if(misiles_ataq>pasar_planeta.getMisiles_disponibles() || misiles_ataq<0)
							System.out.println("No puedes introducir más misiles de los que tienes disponibles o menos de cero!");
						
						System.out.println("Introduce el número de misiles con los que quieres atacar");
						misiles_ataq=sc.nextInt();
						
					}
					

					
					//En el caso de que el planeta sea del tipo copión, se le pregunta a quién quiere copiar para la próxima ronda
					if((pasar_planeta.getTipo_planeta()==9)) {
						System.out.println("¿A qué equipo quieres copiarle el nombre y la vida?");
						
						//lo mismo que antes, tenemos que tener en cuenta que la seleccion del usuario no coincide con el indice del objeto
						//en el arraylist
						copiar=sc.nextInt();
						if(copiar<=planetas.indexOf(pasar_planeta))
							copiar=copiar-1;
					}
					
					pasar_planeta.setMisiles_disponibles(pasar_planeta.getMisiles_disponibles()-misiles_ataq);
					
					//Modificadores de combate
					//-- Con el metodo modificadores recalculamos el valor de daño de los misiles que realiza el equipo atacante. Metemos como variable en el metodo los misiles, el tipo de planeta atacante y el atacado.
					//se devuelve la cantidad de misiles modificada según los modificadores
					misiles_ataq_modificados=pasar_planeta.Modificadores(misiles_ataq, pasar_planeta.getTipo_planeta(), planetas.get(opcion).getTipo_planeta());
					
					//En los misiles recibidos sumamos los misiles enviados por el equipo atacante al equipo atacado
					planetas.get(opcion).setMisiles_recibidos((planetas.get(opcion).getMisiles_recibidos()+misiles_ataq_modificados));
					
					//En el arraylist de frases añadimos la frase de ataque
					frases.add("El equipo "+pasar_planeta.getNombre()+" ("+pasar_planeta.getTipo()+") ataca a "+planetas.get(opcion).getNombre()+" ("+planetas.get(opcion).getTipo()+") con "+misiles_ataq+ " misiles.");
					
					//Si el jugador tiene guardada una frase de insulto se le pregunta si quiere enviarsela
					if(pasar_planeta.EnviarFrase(sc))
						frases.add("Además, le dice: "+pasar_planeta.getInsulto());
					
					//En el caso del planeta enano, según si ha conseguido o no esquivar los ataques añadimos la frase correspondiente al arraylist de frases
					if(planetas.get(opcion).getTipo_planeta()==6) {
						if(misiles_ataq_modificados==0)
							frases.add("¡El planeta enano esquivó el ataque!");
						else
							frases.add("¡El planeta enano NO esquivó el ataque!");
					}
					
					//segun si es efectivo o no el ataque (si después del modificador los misiles son más o menos que el ataque por defecto
					//añadimos al arraylist la frases de efectividad
					else if(misiles_ataq_modificados>misiles_ataq)
						frases.add("Es muy efectivo hace "+misiles_ataq_modificados+" de daño.");
					else if(misiles_ataq_modificados<misiles_ataq)
						frases.add("Es poco efectivo hace "+misiles_ataq_modificados+" de daño.");
					
				}
				
				//En el caso de que el atacante decida guardar los misiles para defensa. Realizamos el calculo y lo guardamos en misiles defensivos, además de añadir la frase de defensa
				else {
					pasar_planeta.setMisiles_defensivos((pasar_planeta.getMisiles_defensivos()+pasar_planeta.getMisiles_disponibles()/2));//Hacer caso cuando sea mandar misiles a defensa y añadir frase defensa al arraylist frases
					frases.add("El equipo "+pasar_planeta.getNombre()+" ("+pasar_planeta.getTipo()+") se defiende con "+pasar_planeta.getMisiles_defensivos()+" misiles.");
				}
			}
		}
		
		//Al final de la ronda realizamos el metodo Combate. Usamos los misiles recibidos por los rivales y los misiles recibidos
		//Después del calculo se reinician ambos atributos para la siguiente ronda.
		//También según la modificación que tenga cada tipo de planeta por ronda llamamos al metodo modificador para que prepare los cambios para la proxima ronda
		for(Planeta pasar_planeta:planetas)
		{
			pasar_planeta.Combate(pasar_planeta.getMisiles_recibidos(), pasar_planeta.getMisiles_defensivos());
			pasar_planeta.setMisiles_defensivos(0);
			pasar_planeta.setMisiles_recibidos(0);
			pasar_planeta.Modificador_ronda(pasar_planeta.getTipo_planeta());
			
		}
		
		//Planetas copion copian los atributos del planeta seleccionado
		for(Planeta pasar_planeta:planetas) {
			if(pasar_planeta.getTipo_planeta()==9) {
				pasar_planeta.PlanetaCopion(planetas.get(copiar).getNombre(),planetas.get(copiar).getVidas());
			}
		}
		
		//analizamos si existe algun equipo con 0 vidas y si existe, lo eliminamos y reducimos el numero de equipos
		ListIterator<Planeta> iter = planetas.listIterator();
		while(iter.hasNext()){
		    if(iter.next().getVidas()==0){
		        iter.remove();
		        Planeta.setNum_equipos(Planeta.getNum_equipos()-1);
		    }
		}
		
		//En este iterador, se recorre el arraylist de las frases de ataque, defensa, modificadores, etc
		Iterator<String> iterador = frases.iterator();
		System.out.println("_______________________________");
		while(iterador.hasNext()) {
			String frase = iterador.next();
			System.out.println(frase);
		}
		System.out.println("_______________________________");
		
		//Limpiamos el arraylist para la siguiente ronda
		frases.clear();

		
		
	}
	
	public static void CrearInsultos(ArrayList <Planeta> planetas, Scanner sc) {//en esta funcion pedimos a todos los usuarios si quieres ponerse una frase que luego podrá ser enviada
		
		String frase;
		
		for( Planeta iterador_planeta:planetas) {
			System.out.println("Equipo"+iterador_planeta.getNombre());
			System.out.println("Escribe tu insulto/frase por defecto. Si no quieres tener un insulto, no escribas nada");
			frase=sc.nextLine();
			iterador_planeta.CrearFraseInsulto(frase);
		}
		
	}
	


}

