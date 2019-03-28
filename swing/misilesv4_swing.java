package misiles_v4;


import java.awt.BorderLayout;

import javax.swing.JFrame;

public class misilesv4_swing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame ("Misiles v4");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(450, 800);
		
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); en teoria deberia maximizar ventana
		
		frame.setLayout(new BorderLayout());
		
		lamina_menu label_menu = new lamina_menu();
		frame.add(label_menu, BorderLayout.CENTER);
		//frame.pack();
		frame.setVisible(true);
	}

}
