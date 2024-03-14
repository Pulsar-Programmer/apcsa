import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortDriver {
    public static void main(String[] args) throws Exception {

        
        
        
        ///We create our frame and add settings.
        JFrame foundation = new JFrame();
        foundation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        foundation.setVisible(true);
        foundation.setSize(Table.x(100), Table.y(100));

        
        
        ///We propose a default piece len. If asking the user succeeds, then we overwrite it.
        var len = 10;
        try{len = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of elements to sort (5-30):"));} catch(Exception _e) {};
        if(len <= 0){
            len = 10;
        }

        ///We propose a default speed. If asking the user succeeds, then we overwrite it.
        double speed = 1000;
        try{speed = Double.parseDouble(JOptionPane.showInputDialog("Enter the speed of progression:"));} catch(Exception _e) {};
        if(speed <= 0){
            speed = 1000;
        }
        
        String[] choices = {"Random Distribution", "Reverse Distribution"};
        String option = (String) JOptionPane.showInputDialog(null, "Distribution Method", "How should they be ordered on the table?", 
        JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

        var my_table = new Table(len);
        if(option.equals("Reverse Distribution")){
            my_table.distribute_reverse();
        }
        foundation.add(my_table);

        ///This is the main game loop where we update the components.
        while(true){
            Thread.sleep((long)(1000/speed));
            foundation.repaint();
        }
    }
}
//https://www.google.com/logos/fnbx/polyhedral_dice/d20_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d12_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d10_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d8_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d6_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d4_blank.png