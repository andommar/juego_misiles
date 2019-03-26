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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class Lamina_Juego extends JPanel implements ActionListener {

	Image img;
	
	JButton boton1 = new JButton ("Atacar");
	JButton boton2 = new JButton ("Defender");
	JButton boton3 = new JButton ("Jugar3");
	
    protected JTextField textField;
    protected JTextField textField2;
    protected JTextArea textArea;
    protected JLabel JLabel_nombre;
    protected JLabel jLabel_atacar;
    protected JLabel jLabel_texto2;
    
    private JLabel jLabel_num_misiles;
    JComboBox combobox_planetas;
    
    private ArrayList <Planeta> planetas_copia;
   
    int i=0;
    int pos_array=0;
	
	
	Lamina_Juego(ArrayList <Planeta> planetas){
		
		planetas_copia = new ArrayList <Planeta> (planetas);
		
		String [] nombres_planetas = new String [planetas.size()];
		for(Planeta pasar_planeta:planetas) {
			nombres_planetas[i]=pasar_planeta.getNombre();
			i++;
		}
		

		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//metemos los planetas en combobox
		combobox_planetas = new JComboBox(nombres_planetas);
		
		//Botón 1
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton1.addActionListener(this);
        
		//Botón 2
		boton2.setBackground(Color.lightGray);
		boton2.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton2.addActionListener(this);
		
        //Text Field
  		textField = new JTextField(5);
  		textField.addActionListener(this);
  		
	    //Label nombre y equipo
  		JLabel_nombre = new JLabel ("Turno del equipo: ");
	   jLabel_texto2 = new JLabel ();
	   jLabel_texto2.setText(planetas.get(pos_array).getNombre());
	   
	   jLabel_atacar = new JLabel ("Atacar a equipo: ");
	   
	   jLabel_num_misiles = new JLabel ("Número misiles a enviar: ");
	   textField = new JTextField(15);

	   
	   
	   //----------LAYOUT
	   
	   	c.ipady = 20;
		c.ipadx = 20;
		c.insets = new Insets(30,0,0,0); //external padding

	    //add label
		JLabel_nombre.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
	    add(JLabel_nombre, c);
	    
	    //add label NOMBRE EQUIPO
       jLabel_texto2.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
	    add(jLabel_texto2, c);
	    
	    //add label Atacar Equipo
       jLabel_atacar.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
	    add(jLabel_atacar, c);
	    
	    //add combobox
	    c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 2;
		add(combobox_planetas,c);
	    
	    
	    //add label num misiles
       jLabel_num_misiles.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
	    add(jLabel_num_misiles, c);
	    	    
	    //add textField num_misiles
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
        add(textField, c);
	    
	    
		//add label boton1
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1; //espacio de columnas que ocupa
		c.gridx = 0;
		c.gridy = 4;
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
		
		String nombre_planeta = (String) combobox_planetas.getItemAt(combobox_planetas.getSelectedIndex()); //cogemos el nombre del equipo seleccionado a atacar
		
		String misiles_ataq_string = jLabel_num_misiles.getText();
		int misiles_ataq = Integer.parseInt(misiles_ataq_string);	
		
		if (e.getSource()==boton1) {
			
			if(nombre_planeta.equals(planetas_copia.get(pos_array).getNombre())) //en el caso que decida atacarse a si mismo
			{
				JOptionPane.showMessageDialog(new JPanel(),
					    "No puedes atacarte a ti mismo",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if(misiles_ataq>0 || misiles_ataq<planetas_copia.get(pos_array).getMisiles_disponibles())
			{
				
				
				planetas_copia.get(pos_array).setMisiles_disponibles(planetas_copia.get(pos_array).getMisiles_disponibles()-misiles_ataq); //restamos misiles disponibles
				System.out.println(planetas_copia.get(pos_array).getMisiles_disponibles());
			}
			
			
			pos_array++;
			jLabel_texto2.setText(planetas_copia.get(pos_array).getNombre());
        }
		
	}
	
	
}
