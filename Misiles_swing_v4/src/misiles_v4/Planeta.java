package misiles_v4;
import java.util.Random;
import java.util.Scanner;

public class Planeta {
	
	Random rnd = new Random();
	
	//Atributos
	private int vidas=200;
	private String nombre;
	private int misiles_enviados;
	private int misiles_recibidos;
	private int misiles_antes_ronda=50;
	private int misiles_disponibles;
	private int misiles_defensivos;
	private static int num_equipos = 0;
	private int tipo_planeta;
	private String tipo;
	private String insulto="";





	//constructor
	public Planeta (String nombre, int tipo_planeta) { //Constructor del planeta. Cogemos el nombre y el tipo de planeta que nos pasan
		
		switch(tipo_planeta) {			//En el switch según sea el tipo de planeta introducimos le asignamos su propiedad correspondiente
			
			//Planeta normal
			case 1:
				this.tipo = "Normal";
				break;
				
			//Planeta rojo	
			case 2:
				this.tipo = "Rojo";
				break;
			
			//Planeta azul
			case 3:
				this.tipo = "Azul";
				break;
			
			//Planeta verde
			case 4:
				this.tipo = "Verde";
				break;
		
			//Planeta gaseoso		
			case 5:
				
				this.vidas=vidas*2;
				this.misiles_antes_ronda=10;
				this.tipo = "Gaseoso";
				
				break;
			
			//Planeta enano	
			case 6:
				
				this.vidas=vidas/2;
				this.tipo = "Enano";
				break;
			
			//Planeta cabrón
			case 7:
				
				this.vidas=1;
				this.misiles_antes_ronda=300;
				this.tipo = "Cabron";
				break;
				
			case 8:
				this.misiles_defensivos=50;
				this.tipo="Dejadme en paz";
				break;
				
			case 9:
				this.tipo="Copión";
				break;
				
			case 10:
				this.tipo="Planeta de los simios";
		
		}
		
		
		this.tipo_planeta=tipo_planeta;
		this.nombre= nombre;
		num_equipos++;
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	//métodos
	
	
	public void Combate (int misiles_dano, int misiles_def) {//Método combate coge los misiles que recibe un planeta y los misiles de defensa que tiene
		
		
		if(misiles_dano>misiles_def) {										//Si los misile de ataque son superiores a los de la defensa
			this.vidas=this.vidas-(misiles_recibidos-misiles_defensivos);	//se le resta a vidas los misiles recibidos-los defensivos
		}
			
		
		if (this.vidas<0) //en el caso de que el equipo llegue a tener 0< vidas le ponemos 0 vidas
			this.vidas=0;
	}
	
	public int Modificadores(int misiles_ataq, int tipo_ataq, int tipo_def) {	//metodo que calcula los modificadores segun quien ataca a quien
		
		int prob;

		//Rojo==2 Azul==3 Verde==4
		
		//Planeta rojo ataca a verde O Planeta azul ataca a rojo O Planeta verde ataca a azul (Hacen doble de daño)
		
		if((tipo_ataq==2 && tipo_def==4) || (tipo_ataq==3 && tipo_def==2) || (tipo_ataq==4 && tipo_def==3)) 
				return misiles_ataq*2;
				
		//Planeta rojo ataca a azul O Planeta azul ataca a verde O Planeta verde ataca a rojo (Hace mitad de daño)
		else if((tipo_ataq==2 && tipo_def==3) || (tipo_ataq==3 && tipo_def==4) || (tipo_ataq==4 && tipo_def==2))
				return misiles_ataq/2;
		
		//Planeta enano
		else if(tipo_def==6) {
			
			//realizamos aleatorio entre 0 y 1. Si sale 1, no esquiva el ataque, de lo contrario sí y no recibe daño.
			prob=rnd.nextInt(2);
			if(prob==1)
				return misiles_ataq;
			else {
				misiles_ataq=0;
				return misiles_ataq;
			}
		}
		
		else						//en el caso de que no cumpla ninguna condición especial, el ataque es uno normal
			return misiles_ataq;

	}
	
	public void Modificador_ronda(int tipo_planeta) {//metodo modificador por ronda, según el tipo de planeta que sea
		int prob;
		
		//Planeta gaseoso
		if(tipo_planeta==5)
			this.misiles_antes_ronda=this.misiles_antes_ronda+10;
		
		//Planeta dejadme en paz
		else if(tipo_planeta==8)
			this.misiles_defensivos=50;
		
		//Planeta de los simios
		else if(tipo_planeta==10) {
			prob=rnd.nextInt(2); //en el caso de que salga 1 consideramos que multiplica x2 su vida actual y misiles
			if(prob==1) {
				this.vidas=vidas*2;
				this.misiles_antes_ronda=misiles_antes_ronda*2;
			}
			else {// en el caso de que salga 0 se divide por la mitado los misiles y vida disponible
				this.vidas=vidas/2;
				this.misiles_antes_ronda=misiles_antes_ronda/2;
			}
		}
	}
	
	
	public void PlanetaCopion(String nombre, int vidas) {//metodo para el planeta copion, copia la vida y nombre del objeto que le pasamos
		this.nombre=nombre;
		this.vidas=vidas;
	}
	
	
	public boolean EnviarFrase(Scanner sc) {
		//este metodo comprueba si el usuario tiene una frase para enviar, si la tiene le pregunta si quiere enviarla
		//si no tiene, no pregunta nada
		
		
		char c='z';
		
		if(this.insulto.equals(null) || this.insulto.equals("")) {
			return false;
		}
		else {
			
			while(c!='s' && c!='n') {
				System.out.println("Quieres enviar tu insulto? (S)í o (N)o");
				c=sc.next().charAt(0);
			}
			
			if(c=='s')
				 return true;
			else 
				return false;
				
		}
		
	}
	
	public void CrearFraseInsulto(String frase) {
		this.insulto=frase;
	}

	
	
	
	//---------------------------------------------------------------------------------------------------------
	
	//getters & setters
	


	public int getVidas() {
		return vidas;
	}



	public void setVidas(int vidas) {
		this.vidas = vidas;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getMisiles_enviados() {
		return misiles_enviados;
	}



	public void setMisiles_enviados(int misiles_enviados) {
		this.misiles_enviados = misiles_enviados;
	}



	public int getMisiles_recibidos() {
		return misiles_recibidos;
	}



	public void setMisiles_recibidos(int misiles_recibidos) {
		this.misiles_recibidos = misiles_recibidos;
	}



	public int getMisiles_antes_ronda() {
		return misiles_antes_ronda;
	}



	public void setMisiles_antes_ronda(int misiles_antes_ronda) {
		this.misiles_antes_ronda = misiles_antes_ronda;
	}



	public int getMisiles_defensivos() {
		return misiles_defensivos;
	}



	public void setMisiles_defensivos(int misiles_defensivos) {
		this.misiles_defensivos = misiles_defensivos;
	}
	
	public int getMisiles_disponibles() {
		return misiles_disponibles;
	}



	public void setMisiles_disponibles(int misiles_disponibles) {
		this.misiles_disponibles = misiles_disponibles;
	}
	
	public static int getNum_equipos() {
		return num_equipos;
	}



	public static void setNum_equipos(int num_equipos) {
		Planeta.num_equipos = num_equipos;
	}
	
	
	public int getTipo_planeta() {
		return tipo_planeta;
	}



	public void setTipo_planeta(int tipo_planeta) {
		this.tipo_planeta = tipo_planeta;
	}
	
	

	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getInsulto() {
		return insulto;
	}

	public void setInsulto(String insulto) {
		this.insulto = insulto;
	}



	

}
