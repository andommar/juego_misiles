package misiles_v4;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//https://www.geeksforgeeks.org/java-swing-jtextarea/
//http://chuwiki.chuidiang.org/index.php?title=Ejemplos_con_JTextField
	
public class Lamina_Equipos extends JPanel implements ActionListener{
	
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    Image img;

	
	public Lamina_Equipos(){
		
		// Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
		
        //Add Components to this panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		textField = new JTextField(20);
		textField.addActionListener(this);
		
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false); //no deja editar el texto
        
        JScrollPane scrollPane = new JScrollPane(textArea); //se a�ade un scroll panel al textArea
        
       

        //c.gridwidth = GridBagConstraints.REMAINDER; //gridwidth ancho del grid
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 1.0;
        add(textField, c);
        
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

  
		
	}
	
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null); // draw the image
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        String text = textField.getText();
        textArea.append(text + newline); //append envia el texto al final
        textField.selectAll();
		
	}
}
