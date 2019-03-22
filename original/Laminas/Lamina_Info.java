package misiles_v4.Laminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.soap.Text;

public class Lamina_Info extends JPanel implements ActionListener{

    // JButton 
    static JButton b; 
  
    // label to diaplay text 
    static JLabel l; 
  
    // text area 
    static JTextArea jt;
    
   
	
	public Lamina_Info(){
		
        // create a label to display text 
        l = new JLabel("nothing entered"); 
  
        // create a new button 
        b = new JButton("submit"); 
  
        // create a object of the text class 
        
        
        // addActionListener to button 
        //b.addActionListener(); 
  
        // create a text area, specifying the rows and columns 
        jt = new JTextArea(10, 10); 
        
        add(jt); 
        add(b); 
        add(l); 
  
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
