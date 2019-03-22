package misiles_v4;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Lamina_Juego extends JPanel implements ActionListener {

	
	JButton boton1 = new JButton ("Juego");
	JButton boton2 = new JButton ("Jugar2");
	JButton boton3 = new JButton ("Jugar3");
	
	
	Lamina_Juego(){
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
		
		
	
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
