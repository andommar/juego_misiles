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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.xml.soap.Text;

public class Lamina_Info extends JPanel implements ActionListener{

	   private final static String newline = "\n";
	    
	    private String ranking;
	    
	    protected JTextField textField;
	    protected JTextField textField2;
	    protected JTextArea textArea;
	    
	    
	    JButton boton1 = new JButton ("Volver");
	    JButton boton2 = new JButton ("Avanzar");
	    
	    Image img;
	    
	    int num_equipos=0;
		
		Lamina_Info(){
			
			Partida partida = new Partida ();
			
			// Read the image and place it in the variable img so it can be used in paintComponent
	        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
			
	        
	        //Add Components to this panel.
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
		    
		    textArea = new JTextArea(10,20);	    
		    textArea.setEditable(false);    
			textArea.append(partida.getInfo());
		      
		    JScrollPane scrollPane = new JScrollPane(textArea); //se añade un scroll panel al textArea
		    scrollPane.setBorder(null);
	        
		    //
					

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

			
			        
	        //boton 1
	        
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 4;
			add(boton1,c);
	        

	        //Scroll Panel
	        
	        c.insets = new Insets(20,0,20,0);
	        
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.gridwidth = GridBagConstraints.REMAINDER;
			c.gridx = 0;
			c.gridy = 3;
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
