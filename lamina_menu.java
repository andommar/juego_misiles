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

import misiles_v4.Laminas.Lamina_Equipos;

public class lamina_menu extends JLabel implements ActionListener {
	
	Image img;
	Partida partida = new Partida ();
	
	private int opcion;
	
	
	//https://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	//http://www.tutorialesprogramacionya.com/javaya/detalleconcepto.php?codigo=107&punto=&inicio=
	
	JButton boton1 = new JButton ("Jugar");
	JButton boton2 = new JButton ("Jugar2");
	JButton boton3 = new JButton ("Jugar3");
	JButton boton4 = new JButton ("Jugar4");
	JButton boton5 = new JButton ("Jugar5");
	
	lamina_menu(){
		
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//-------------------------BOTONES-------------------------
		
		boton1.setOpaque(false);
		boton1.setContentAreaFilled(false);
		boton1.setBorderPainted(false);
		boton1.setForeground(Color.RED);
		
		
		boton2.setBackground(Color.lightGray);
		boton2.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		
		
		
		
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
			System.out.println("yee");
			
			frame.remove(this);
			Lamina_Juego lamina_juegos  = new Lamina_Juego();
			frame.add(lamina_juegos);
			frame.setVisible(true);
			
			//frame.remove(this);
		}
		
		if(e.getSource()==boton2) {
			System.out.println("yee");
			
			frame.remove(this);
			Lamina_Equipos lamina_Equipos  = new Lamina_Equipos();
			frame.add(lamina_Equipos);
			frame.pack();
			frame.setVisible(true);
			
				
			//frame.remove(this);
		}
		
		if(e.getSource()==boton3) {
			System.out.println("yee");
			partida.NormasJuego();
				
			//frame.remove(this);
		}
		
		
	}
	
}


