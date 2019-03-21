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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class Lamina_Seleccion_equipos extends JPanel implements ActionListener {

	protected JTextField textField;
	Image img;
	JButton boton1 = new JButton ("Aceptar");
	
	//https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
	
	
	Lamina_Seleccion_equipos(){
		 img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
		
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		 //Label
		JLabel jLabel_texto1 = new JLabel ("Cantidad de equipos");
		
		textField = new JTextField(15);
		
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton1.addActionListener(this);
		
		//--------------DISTRIBUCIÓN LAYOUT 
		c.insets = new Insets(20,0,20,0);  //top padding
		
		//add label
        jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 2;
	    add(jLabel_texto1, c);
	    
        //Text Field
	    c.insets = new Insets(0,0,100,0);
        c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 3;
        add(textField, c);
		
		
        //boton 1
        c.insets = new Insets(0,0,0,0);
        
		c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.ipady = 40; //hace el componente alto
		c.gridwidth = 2; 
		add(boton1,c);
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
		
		if (e.getSource()==boton1) {
			frame.remove(this);
			//Lamina_Juego lamina_juegos  = new Lamina_Juego();
			Lamina_Equipos lamina_equipos = new Lamina_Equipos();
			frame.add(lamina_equipos);
			frame.setVisible(true);
        }
		
		
	}
}
