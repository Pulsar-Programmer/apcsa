import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {


    /**
     Test
    */
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        javax.swing.JFrame foundation = new javax.swing.JFrame();
        foundation.setSize(1000, 800);
        foundation.setVisible(true);
        foundation.setResizable(false);
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel j = new JPanel();
        foundation.add(j);
        for(var i = 0; true; i++){
            j.setBackground(Color.BLUE);
            
            foundation.repaint();
        }

        
        
    }
}


class Vehicle extends JFrame{
    
}