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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;


public class lamina_menu extends JLabel implements ActionListener {
	
	Image img;
	
	
	private int opcion;
	
	
	//https://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	//http://www.tutorialesprogramacionya.com/javaya/detalleconcepto.php?codigo=107&punto=&inicio=
	
	JButton boton1 = new JButton ("Jugar");
	JButton boton2 = new JButton ("Crear equipos");
	JButton boton3 = new JButton ("Información");
	JButton boton4 = new JButton ("Frases");
	JButton boton5 = new JButton ("Ranking");
	
	lamina_menu(){
		
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//-------------------------BOTONES-------------------------
		
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton2.setBackground(Color.lightGray);
		boton2.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton3.setBackground(Color.lightGray);
		boton3.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton4.setBackground(Color.lightGray);
		boton4.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton5.setBackground(Color.lightGray);
		boton5.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		
		
		

		
		c.ipady = 20;
		c.ipadx = 50;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30,0,0,0);
		
		c.gridx = 0;
		c.gridy = 1;
		add(boton1,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(boton2,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(boton3,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		add(boton4,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		add(boton5,c);
		
	    boton1.addActionListener(this); //debemos poner los botones a la escucha
	    boton2.addActionListener(this); 
	    boton3.addActionListener(this); 
	    boton4.addActionListener(this); 
	    boton5.addActionListener(this); 


	}
	
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null); // draw the image
    }
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JFrame frame =(JFrame)SwingUtilities.getWindowAncestor(this);
		System.out.println((JFrame)SwingUtilities.getWindowAncestor(this));
		
		if(e.getSource()==boton1) {
			
			
			frame.remove(this);
			//Lamina_Juego lamina_juegos  = new Lamina_Juego();
			//Lamina_Juego lamina_juego = new Lamina_Juego();
			//frame.add(lamina_juego);
			frame.setVisible(true);
			
			//frame.remove(this);
		}
		
		if(e.getSource()==boton2) {
			
			
			frame.remove(this);
			//Lamina_Juego lamina_juegos  = new Lamina_Juego();
			Lamina_Equipos lamina_equipos = new Lamina_Equipos();
			frame.add(lamina_equipos);

			frame.setVisible(true);
			
				
			//frame.remove(this);
		}
		
		if(e.getSource()==boton3) {
			
			
			frame.remove(this);
			Lamina_Info lamina_info = new Lamina_Info();
			frame.add(lamina_info);

			frame.setVisible(true);
			
				
			//frame.remove(this);
		}
		
		if(e.getSource()==boton5) {
			frame.remove(this);
			//Lamina_Juego lamina_juegos  = new Lamina_Juego();
			Lamina_Ranking lamina_ranking = new Lamina_Ranking();
			frame.add(lamina_ranking);
			frame.setSize(650, 400);
			frame.setVisible(true);
		}
		
		
	}
	
}


