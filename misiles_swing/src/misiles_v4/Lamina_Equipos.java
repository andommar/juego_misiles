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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

//https://www.geeksforgeeks.org/java-swing-jtextarea/
//http://chuwiki.chuidiang.org/index.php?title=Ejemplos_con_JTextField
//https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
//http://www.sc.ehu.es/sbweb/fisica/cursoJava/applets/diseno/gridbag.htm


public class Lamina_Equipos extends JPanel implements ActionListener{
	
	
	final static int NUM_MIN_EQUIPOS=3;
	
	final static int OPCION_MIN_TIPO=0;
	final static int OPCION_MAX_TIPO=10;
	
	final static int OPCION_MIN_MENU=1;
	final static int OPCION_MAX_MENU=6;
	

	public static ArrayList <Planeta> planetas = new ArrayList <Planeta>();
	public static ArrayList <String> frases = new ArrayList <String>();
	
	
	
    protected JTextField textField;
    protected JTextField textField2;
    protected JTextArea textArea;
 
    private final static String newline = "\n";
    
    JButton boton1 = new JButton ("Crear");
    JButton boton2 = new JButton ("Avanzar");
    
    Image img;
    
    int num_equipos=0;
	
	public Lamina_Equipos(){
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
		
      //Text Field
		textField = new JTextField(15);
		textField.addActionListener(this);
		
		textField2 = new JTextField(15);
		textField2.addActionListener(this);
		
	    textArea = new JTextArea(10,20);
	    textArea.setEditable(false); //no deja editar el texto
	      
	    JScrollPane scrollPane = new JScrollPane(textArea); //se a�ade un scroll panel al textArea
        
	    //Label
	   JLabel jLabel_texto1 = new JLabel ("Nombre equipo");
	   JLabel jLabel_texto2 = new JLabel ("Tipo equipo");

        
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				

		//Padding de componentes
		c.ipady = 20;
		c.ipadx = 20;
		c.insets = new Insets(30,0,0,0); //external padding

		//--------------DISTRIBUCI�N LAYOUT 
		//Bot�n 1
		boton1.setBackground(Color.lightGray);
		boton1.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
		boton1.addActionListener(this);
		
		
		
		//Bot�n 2
		boton2.setBackground(Color.lightGray);
		boton2.setBorder(new MatteBorder(2,2,2,2, Color.WHITE)); //matteborder define el grosor de cada extremo del boton y el color
		boton2.addActionListener(this);

		
	    //add label
        jLabel_texto1.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 1;
	    add(jLabel_texto1, c);
	    
	    //add label
        jLabel_texto2.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 2;
	    add(jLabel_texto2, c);
		
        //Text Field
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 10;
		c.gridx = 1;
		c.gridy = 1;
        add(textField, c);
        
        //Text field 2
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 10;
		c.gridx = 1;
		c.gridy = 2;
        add(textField2, c);
        
        //boton 1
        
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		add(boton1,c);
        
        
        //boton 2
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		add(boton2,c);


        
        //Scroll Panel
        
        c.insets = new Insets(20,0,20,0);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 0;
        c.weighty = 0;
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
		System.out.println((JFrame)SwingUtilities.getWindowAncestor(this));
		
		if (e.getSource()==boton1) {
			num_equipos++;
			
	        String text = textField.getText();
	        String texto2 = textField2.getText();
	        
	        Planeta planeta = new Planeta (text, 1);
	        planetas.add(planeta);
	        
	        textArea.append(text + " de tipo "+texto2+" "+ newline); //append envia el texto al final
	        textField.setText("");
	        textField2.setText("");
        }
		
		if(e.getSource()==boton2) {

			if(num_equipos<3) {
				JOptionPane.showMessageDialog(new JPanel(),
					    "Debes introducir un m�nimo de 3 equipos.",
					    "Inane warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				frame.remove(this);
				Lamina_Juego lamina_juego = new Lamina_Juego(planetas);
				frame.add(lamina_juego);
				frame.setVisible(true);
			}

			
		}
		
		for(Planeta pasar_planeta:planetas) {
			System.out.println("Nombre del planeta:"+pasar_planeta.getNombre());
		}
		
		



	}
	

}
