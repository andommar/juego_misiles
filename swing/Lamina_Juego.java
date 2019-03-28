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
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    protected JLabel jLabel_misiles;
    protected JLabel jLabel_misiles_disp;
    
    private final static String newline = "\n";
    
    private JLabel jLabel_num_misiles;
    
    JComboBox combobox_planetas;
    
    private ArrayList <Planeta> planetas_copia;
    private ArrayList <String> frases = new ArrayList <String>();
   
    int i=0;
    int pos_array=0;
    int num_misiles=0;
	
	
	Lamina_Juego(ArrayList <Planeta> planetas){
		
		planetas_copia = new ArrayList <Planeta> (planetas);
		
				
		String [] nombres_planetas = new String [planetas.size()];
		for(Planeta pasar_planeta:planetas) {
			nombres_planetas[i]=pasar_planeta.getNombre();
			i++;
		}
		
		planetas_copia.get(pos_array).setMisiles_disponibles(planetas_copia.get(pos_array).getMisiles_antes_ronda());

		
		
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		num_misiles=planetas_copia.get(pos_array).getMisiles_disponibles();
		
		//imagen icono
		ImageIcon image = new ImageIcon("src/misiles_v4/misil_r.png");
		jLabel_misiles = new JLabel(String.valueOf(num_misiles), image, JLabel.LEFT);
		
		
		
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
  		
  		//textArea
	    textArea = new JTextArea(50,20);
	    textArea.setEditable(false); //no deja editar el texto
  		JScrollPane scrollPane = new JScrollPane(textArea); //se añade un scroll panel al textArea
  		
	    //Label nombre y equipo
  		JLabel_nombre = new JLabel ("Turno del equipo: ");
	   jLabel_texto2 = new JLabel ();
	   jLabel_texto2.setText(planetas.get(pos_array).getNombre());
	   
	   jLabel_atacar = new JLabel ("Atacar a equipo: ");
	   
	   jLabel_num_misiles = new JLabel ("Número misiles a enviar: ");
	   
	   jLabel_misiles_disp = new JLabel ("Misiles disponibles:");
	   

	   
	   
	   //----------LAYOUT
	   
	   	c.ipady = 20;
		c.ipadx = 20;
		c.insets = new Insets(30,0,0,0); //external padding
		       
	    //add label
        c.ipady = 20;
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
		
	    //jLabel_misiles.disponibles
		jLabel_misiles_disp.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
	    add(jLabel_misiles_disp, c);
		
		
	    //jLabel_misiles.setForeground(Color.red);
		jLabel_misiles.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 3;
	    add(jLabel_misiles, c);
	    
	    //add label num misiles
       jLabel_num_misiles.setForeground(Color.red);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
	    add(jLabel_num_misiles, c);
	    	    
	    //add textField num_misiles
        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
        add(textField, c);
	       
        c.insets = new Insets(30,0,0,0);
        
		c.ipady = 80;
        c.fill = GridBagConstraints.HORIZONTAL; //rellenar hasta el final de la fila
        c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 5;
        add(scrollPane, c);

        
		//add label boton1
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1; //espacio de columnas que ocupa
		c.ipadx= 60; //Internal padding x
		c.ipady= 20;//Internal padding y
		c.gridx = 0;
		c.gridy = 6;
		add(boton1,c);
		
        c.insets = new Insets(30,0,0,0);
        
		//add label boton2
		c.fill = GridBagConstraints.NONE;
		c.ipadx= 40;
		c.ipady= 20;
		c.gridwidth = 1; //espacio de columnas que ocupa
		c.gridx = 1;
		c.gridy = 6;
		add(boton2,c);
				
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
		
		String misiles_ataq_string = textField.getText();
		int misiles_ataq = Integer.parseInt(misiles_ataq_string);	
		
		if (e.getSource()==boton1) {
			
			if(nombre_planeta.equals(planetas_copia.get(pos_array).getNombre())) //en el caso que decida atacarse a si mismo
			{
				JOptionPane.showMessageDialog(new JPanel(),
					    "No puedes atacarte a ti mismo",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if(misiles_ataq==0)
			{
				JOptionPane.showMessageDialog(new JPanel(),
					    "¡Ya no te quedan misiles!",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else if(misiles_ataq>0 && misiles_ataq<=planetas_copia.get(pos_array).getMisiles_disponibles())
			{
				
				
				planetas_copia.get(pos_array).setMisiles_disponibles(planetas_copia.get(pos_array).getMisiles_disponibles()-misiles_ataq); //restamos misiles disponibles
				System.out.println(planetas_copia.get(pos_array).getMisiles_disponibles());
				
				planetas_copia.get(combobox_planetas.getSelectedIndex()).setMisiles_recibidos(misiles_ataq); //misiles recibidos al planeta atacado
				System.out.println("Misiles recibido" +planetas_copia.get(combobox_planetas.getSelectedIndex()).getMisiles_recibidos());
				
				frases.add("El equipo "+planetas_copia.get(pos_array).getNombre()+" ataca a " +nombre_planeta+ " con "+ misiles_ataq+" misiles" );
				
				num_misiles=planetas_copia.get(pos_array).getMisiles_disponibles();
				

				jLabel_misiles.setText(String.valueOf(num_misiles));
				
				
				if(planetas_copia.get(pos_array).getMisiles_disponibles()<0) //si los misiles bajan a menos de 0 los ponemos a 0
					planetas_copia.get(pos_array).setMisiles_disponibles(0);
			}
			else
			{
				JOptionPane.showMessageDialog(new JPanel(),
					    "¡Introduce un valor entre 1 y los misiles disponibles que tengas!",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
			if(planetas_copia.get(pos_array).getMisiles_disponibles()==0) //si los misiles llegan a 0 pasamos de turno
			{

				
				
				
				Iterator<String> iterador = frases.iterator();
				String frase="";
				while(iterador.hasNext()) {
					frase =frase +iterador.next()+newline;
				}
				
				textArea.append(frase);
				
				pos_array++;
				jLabel_texto2.setText(planetas_copia.get(pos_array).getNombre());
				planetas_copia.get(pos_array).setMisiles_disponibles(planetas_copia.get(pos_array).getMisiles_antes_ronda());
				
				num_misiles=planetas_copia.get(pos_array).getMisiles_disponibles();
				

				jLabel_misiles.setText(String.valueOf(num_misiles));
				
				
			}
			
			
			

        }
		
		else if(e.getSource()==boton2) {
			
			planetas_copia.get(pos_array).setMisiles_defensivos((planetas_copia.get(pos_array).getMisiles_defensivos()+planetas_copia.get(pos_array).getMisiles_disponibles()/2));//Hacer caso cuando sea mandar misiles a defensa y añadir frase defensa al arraylist frases
			frases.add("El equipo "+planetas_copia.get(pos_array).getNombre()+" ("+planetas_copia.get(pos_array).getTipo()+") se defiende con "+planetas_copia.get(pos_array).getMisiles_defensivos()+" misiles.");
			
			
			pos_array++;
			jLabel_texto2.setText(planetas_copia.get(pos_array).getNombre());
			planetas_copia.get(pos_array).setMisiles_disponibles(planetas_copia.get(pos_array).getMisiles_antes_ronda());
			
			
			
			
			Iterator<String> iterador = frases.iterator();
			String frase="";
			while(iterador.hasNext()) {
				frase =frase +iterador.next()+newline;
			}
			
			textArea.append(frase);
			num_misiles=planetas_copia.get(pos_array).getMisiles_disponibles();
			

			jLabel_misiles.setText(String.valueOf(num_misiles));
		}
		
	}
	
	
	
	
}
