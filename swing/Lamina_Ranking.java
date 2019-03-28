package misiles_v4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import misiles_v4.Lamina_Juego;
import misiles_v4.Planeta;
import misiles_v4.XMLGenerator;

public class Lamina_Ranking extends JPanel implements ActionListener {
	
final static int NUM_MIN_EQUIPOS=3;
	
	final static int OPCION_MIN_TIPO=0;
	final static int OPCION_MAX_TIPO=10;
	
	final static int OPCION_MIN_MENU=1;
	final static int OPCION_MAX_MENU=6;
	

	public static ArrayList <Planeta> planetas = new ArrayList <Planeta>();
	public static ArrayList <String> frases = new ArrayList <String>();
	
	
	
    protected JTextField textField;
    protected JTextField textField2;
    protected JTextArea textArea;
 
    private final static String newline = "\n";
    
    private String ranking;
    
    JButton boton1 = new JButton ("Volver");
    JButton boton2 = new JButton ("Avanzar");
    
    Image img;
    
    int num_equipos=0;
	
	Lamina_Ranking(){
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
		
 
		
	    textArea = new JTextArea(10,20);	    
	    textArea.setEditable(false); //no deja editar el texto
	    
		File f = new File("Ranking_equipos.xml");
		
		if(f.exists()) {
			XMLGenerator xml = new XMLGenerator();
			try {
				xml.MostrarRankingXML();
				ranking = xml.getRanking_frases();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
			System.out.println("Hay que jugar al menos una partida para ver el ranking!");
		
		textArea.append(ranking);

	    
	    
	   // textArea.append(str);
	      
	    JScrollPane scrollPane = new JScrollPane(textArea); //se añade un scroll panel al textArea
        
	    //Label
	   JLabel jLabel_texto1 = new JLabel ("Ranking equipos");
	   JLabel jLabel_texto2 = new JLabel ("Tipo equipo");

        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				

		//Padding de componentes
		c.ipady = 20;
		c.ipadx = 20;
		c.insets = new Insets(30,0,0,0); //external padding

		//--------------DISTRIBUCIÓN LAYOUT 
		//Botón 1
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton1.addActionListener(this);
		
		
		//Botón 2
		boton2.setBackground(Color.lightGray);
		boton2.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton2.addActionListener(this);

		
	    //add label
        jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 0;
	    add(jLabel_texto1, c);
	    
		        
        //boton 1
        
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		add(boton1,c);
        

        //Scroll Panel
        
        //c.insets = new Insets(20,0,20,0);
        
        
		c.gridx = 0;
		c.gridy = 1;
        add(scrollPane, c);
		
	}
	
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null); // draw the image
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JFrame frame =(JFrame)SwingUtilities.getWindowAncestor(this);

		if(e.getSource()==boton1) {

			frame.remove(this);
			lamina_menu lamina_menu = new lamina_menu();
			frame.add(lamina_menu);
			frame.setVisible(true);

			
		}
		
		
	}

}
