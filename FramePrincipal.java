package misiles_v4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;


//SET Bounds
//https://techlandia.com/utilizar-setbounds-jbutton-como_231675/

//http://www.chuidiang.org/java/novatos/hola_mundo_swing.php

public class FramePrincipal extends JFrame{
	
	public FramePrincipal (String titulo) {
		super(titulo);
		

		
		//-------------------------LAYOUT SWING-------------------------
		
		setLayout(new BorderLayout());
		
		//-------------------------COMPONENTES SWING-------------------------
		
		
		//Crear clase label_fondo y desde allí crear variables y llamar botones según situación del programa

		
		panelFondo panelFondo = new panelFondo();
		
		add(panelFondo, BorderLayout.CENTER);
		lamina_menu label_menu = new lamina_menu();
		add(label_menu, BorderLayout.CENTER);
				
		
		//-------------------------COMPORTAMIENTO-------------------------
		

		
	}

}
