package misiles_v4;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


/*https://www.mkyong.com/java/how-to-modify-xml-file-in-java-dom-parser/
 * https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
 * https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 * 
 * https://jarroba.com/map-en-java-con-ejemplos/
 * https://stackoverflow.com/questions/14626229/how-to-sort-xml-code-in-ascending-order-in-java
 */


//https://stackoverflow.com/questions/6445828/how-do-i-append-a-node-to-an-existing-xml-file-in-java

//http://jmoral.es/blog/xml-dom
//https://geekytheory.com/crear-fichero-xml-desde-java
//http://www.vogella.com/tutorials/JavaXML/article.html




public class XMLGenerator {
	
	private final static String newline = "\n";

	File file = new File("Ranking_equipos.xml");
	
	private String ranking_frases="";
	
	



	public void GenerarXML(String nombre) {
		
		
		try {
				//clases que crean el archivo
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.newDocument();
			
			  
			  //elemento raíz del documento
			  
			  Element raiz = doc.createElement("ranking");
			  doc.appendChild(raiz);
			  
		  
			  
			  //definimos el nodo que contendrá los elementos
			  Element eEquipo = doc.createElement("equipo");
			  raiz.appendChild(eEquipo);
			  
			  
			  //definimos elementos y asignamos valor
			  Element eNombre=doc.createElement("nombre");
			  eNombre.appendChild(doc.createTextNode(nombre));
			  eEquipo.appendChild(eNombre);
			  
			  Element ePartidas_ganadas=doc.createElement("Partidas_ganadas");
			  ePartidas_ganadas.appendChild(doc.createTextNode("1"));
			  eEquipo.appendChild(ePartidas_ganadas);
			  
			  
			  //clases necesarias para finalizar creación archivo XML
			  
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("Ranking_equipos.xml"));
			  transformer.transform(source, result);
			//Podemos definir donde guardar el archivo por ejemplo "C:\\archivo.xml".Si no indicamos nada se guarda en la carpeta del proyecto for defecto
			  
	 	}catch(Exception e) {
	 		e.printStackTrace();
	 	}
	}
	
	
	public void LeerXML () {
		
		
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  Document doc = dBuilder.parse(file);
			  
			  doc.getDocumentElement().normalize();
			  
			  //guardamos cantidad de nodos para mostrar la cantidad de ellos con getLength
			  NodeList numNodos = doc.getElementsByTagName("equipo");
			  
			  System.out.println("Número de equipos: " + numNodos.getLength());
			  
			  System.out.println("__________________________________");
				for(int temp = 0; temp <numNodos.getLength(); temp++) {
				
					Node nNode= numNodos.item(temp);
					
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						
						//System.out.println("\nEquipo: "+ eElement.getAttribute("id"));
						
						System.out.println("Nombre equipo: "+ eElement.getElementsByTagName("nombre").item(0).getTextContent());
						
						System.out.println("\t Partidas ganadas: "+ eElement.getElementsByTagName("Partidas_ganadas").item(0).getTextContent());
						
						
					}
					
	
				}
				System.out.println("__________________________________");
			} catch(Exception e) {
			  e.printStackTrace();
			}
		
	}
	
	public boolean ComprobarExisteEquipoXML(String nombre_eq) {
		
		boolean bandera=false;
		
		try {
	
			String filepath = "Ranking_equipos.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			//obtenemos una lista de los elementos con el tag "nombre"
			NodeList nombre_equipos = doc.getElementsByTagName("nombre");
			
			//iteramos esta lista
			for(int i = 0; i<nombre_equipos.getLength(); i++) {
				Node nodo_equipos = nombre_equipos.item(i);
				
				//si el nombre coincide con el nombre de algunos de los equipos que está en el xml, indica que ya existe
				if(nombre_eq.equals(nodo_equipos.getTextContent())) {
					bandera=true;
				}
				
				
			}
			
		

		} catch(Exception e) {
			  e.printStackTrace();
			}
		
		return bandera;
	}
	
	
	public void ModificarXML (String nombre_eq) {
		
		String string_veces_ganadas;
		int num_veces_ganadas;
		
		 try {
				String filepath = "Ranking_equipos.xml";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				
				
				//obtenemos una lista de los elementos con el tag "nombre"
				NodeList nombre_equipos = doc.getElementsByTagName("nombre");
				NodeList puntuacion = doc.getElementsByTagName("Partidas_ganadas");
				
				//iteramos esta lista
				for(int i = 0; i<nombre_equipos.getLength(); i++) {
					Node nodo_equipos = nombre_equipos.item(i);
					Node nodo_puntuacion = puntuacion.item(i);

					if(nombre_eq.equals(nodo_equipos.getTextContent())) {
		    			   //guardamos el valor en un string
		    			string_veces_ganadas=nodo_puntuacion.getTextContent();
		    			//lo convertimos en int
		    			num_veces_ganadas=Integer.parseInt(string_veces_ganadas);
		    			num_veces_ganadas++;
		    			//lo convertimos a String de nuevo
		    			nodo_puntuacion.setTextContent(Integer.toString(num_veces_ganadas));
						
					}
					
				}

				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.transform(source, result);
		
		 	}catch(Exception e) {
		 		e.printStackTrace();
		 	}
	}
	
	public void AnadirXML(String nombre_eq) {
				
		
	try {
			String filepath = "Ranking_equipos.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			
			//obtenemos el nodo principal (ranking)
			Node ranking = doc.getFirstChild();

			
			//añadimos al nodo principal un nuevo equipo
			 Element eEquipo = doc.createElement("equipo");
			 ranking.appendChild(eEquipo);

			  
			  //definimos elementos y asignamos valor
			  Element eNombre=doc.createElement("nombre");
			  eNombre.appendChild(doc.createTextNode(nombre_eq));
			  eEquipo.appendChild(eNombre);
			  
			  Element ePartidas_ganadas=doc.createElement("Partidas_ganadas");
			  ePartidas_ganadas.appendChild(doc.createTextNode("1"));
			  eEquipo.appendChild(ePartidas_ganadas);
			
		
		
		
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		 	}catch(Exception e) {
		 		e.printStackTrace();
		 	}
		 	
		
	}
	
	public void MostrarRankingXML() throws Exception{
		
		String frase="";
		int num_veces_ganadas;
		String string_veces_ganadas;
		String nombre_equipo;
		
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.parse(file);
		  
		  
		  NodeList nombre_equipos = doc.getElementsByTagName("nombre");
		  NodeList puntuacion = doc.getElementsByTagName("Partidas_ganadas");
		  
		  //Creamos un TreeMap donde guardamos el nombre del equipo y las victorias (las pasamos de string a integer)
		  
		  Map<String, Integer> items = new TreeMap<String, Integer>();
		  
		  for(int i = 0; i<nombre_equipos.getLength(); i++) {
			  Node nodo_equipos = nombre_equipos.item(i);
			  nombre_equipo=nodo_equipos.getTextContent();
			  
			  Node nodo_puntuacion = puntuacion.item(i);
			  string_veces_ganadas=nodo_puntuacion.getTextContent();
			  
			  num_veces_ganadas=Integer.parseInt( string_veces_ganadas);
			  items.put(nombre_equipo, num_veces_ganadas);
		  }
		  
		  
		  //ordenamos en un arraylist las victorias realizadas por cada equipo
		  
		  List <Integer> valores = new ArrayList(items.values());
		  
		  //con collections organizamos de mayor a menor los valores
		  Collections.sort(valores, Collections.reverseOrder()); 
		  System.out.println("\t Ranking de campeones");
		  System.out.println("----------------------------------------------");
	        //recorremos el arraylist y comparamos con los valores del treemap. cuando coincidan los valores imprimimos el nombre del equipo 
	        for (Integer num_vic : valores) {
	        	
	        	for(Map.Entry<String,Integer> entrada : items.entrySet()) {
	        	
	        		String nombre = entrada.getKey();
	        		Integer value = entrada.getValue();
	        		
		        	if(num_vic == value )
		        	{
			        	System.out.println("Nombre equipo: "+nombre+"\t partidas ganadas: " + num_vic);
			        	frase ="Equipo: "+ nombre+ "\t victorias: "+ num_vic;
			        	ranking_frases=ranking_frases+frase+newline;
		        	}
	        	}
	        }
		
	}
	
	public String getRanking_frases() {
		return ranking_frases;
	}


	public void setRanking_frases(String ranking_frases) {
		this.ranking_frases = ranking_frases;
	}
	
	

}
	
	

	
	

	

