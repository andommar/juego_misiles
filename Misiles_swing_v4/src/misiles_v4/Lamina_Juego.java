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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class Lamina_Juego extends JPanel implements ActionListener {

	Image img;
	
	JButton boton1 = new JButton ("Juego");
	JButton boton2 = new JButton ("Jugar2");
	JButton boton3 = new JButton ("Jugar3");
	
    protected JTextField textField;
    protected JTextField textField2;
    protected JTextArea textArea;
    protected JLabel JLabel_nombre;
    protected JLabel jLabel_texto2;

    
    int pos_array=0;
	
	
	Lamina_Juego(ArrayList <Planeta> planetas){

		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        
        //Text Field
  		textField = new JTextField(15);
  		textField.addActionListener(this);
  		
	    //Label
	   JLabel jLabel_texto1 = new JLabel ("Nombre equipo");
	   JLabel jLabel_texto2 = new JLabel ();
	   jLabel_texto2.setText("hola");
	   
		//Botón 1
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton1.addActionListener(this);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		add(boton1,c);
		
	    //add label
        jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 1;
	    add(jLabel_texto1, c);
	    
	    //add label
        jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 2;
	    add(jLabel_texto2, c);
        
        
		/*
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(boton2,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(boton3,c);
		
		*/
	
		
	}
	
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null); // draw the image
    }
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JFrame frame =(JFrame)SwingUtilities.getWindowAncestor(this);
		
		
		if (e.getSource()==boton1) {
			jLabel_texto2.setText("burro");
        }
		
	}
	
	
}
