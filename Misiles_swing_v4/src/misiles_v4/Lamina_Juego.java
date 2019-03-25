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
	
	JButton boton1 = new JButton ("Atacar");
	JButton boton2 = new JButton ("Jugar2");
	JButton boton3 = new JButton ("Jugar3");
	
    protected JTextField textField;
    protected JTextField textField2;
    protected JTextArea textArea;
    protected JLabel JLabel_nombre;
    protected JLabel jLabel_texto2;
    
    private JLabel jLabel_num_misiles;
    
    private ArrayList <Planeta> planetas_copia;
   

    int pos_array=0;
	
	
	Lamina_Juego(ArrayList <Planeta> planetas){
		
		planetas_copia = new ArrayList <Planeta> (planetas);
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Botón 1
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton1.addActionListener(this);
        
        //Text Field
  		textField = new JTextField(15);
  		textField.addActionListener(this);
  		
	    //Label
	   JLabel jLabel_texto1 = new JLabel ("Nombre equipo: ");
	   JLabel jLabel_num_misiles = new JLabel ("jLabel_num_misiles");
	   jLabel_texto2 = new JLabel ();
	   jLabel_texto2.setText(planetas.get(pos_array).getNombre());
	   
	   jLabel_num_misiles = new JLabel();
	   
	   
	   //----------LAYOUT
	    //add label
       jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 1;
	    add(jLabel_texto1, c);
	    
	    //add label NOMBRE EQUIPO
       jLabel_texto2.setForeground(Color.red);
		c.gridx = 1;
		c.gridy = 1;
	    add(jLabel_texto2, c);
	    
	    //add label num misiles
       jLabel_num_misiles.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 2;
	    add(jLabel_num_misiles, c);
	    
	    //add textField
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 10;
		c.gridx = 1;
		c.gridy = 2;
        add(textField, c);
	    
	    
		//add label boton1
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
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
		
		
		if (e.getSource()==boton1) {
			pos_array++;
			jLabel_texto2.setText(planetas_copia.get(pos_array).getNombre());
        }
		
	}
	
	
}
