package misiles_v4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class panelFondo extends JPanel  {
	
	Image img;
    public panelFondo()
    {
        // Read the image and place it in the variable img so it can be used in paintComponent
        img = Toolkit.getDefaultToolkit().createImage("src/misiles_v4/space_fondo.jpg");
        	

    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null); // draw the image
    }
    
    
    
}
	

