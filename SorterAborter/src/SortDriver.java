import javax.swing.JFrame;
import javax.swing.JOptionPane;
///Triple comments are intended to be read.
//Double comments are for dev.

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

        //We propose a default speed. If asking the user succeeds, then we overwrite it.
        // double speed = 1000;
        // try{speed = Double.parseDouble(JOptionPane.showInputDialog("Enter the speed of progression:"));} catch(Exception _e) {};
        // if(speed <= 0){
        //     speed = 1000;
        // }
        
        ///We create our table.
        var my_table = new Table(len);
        {
            ///We decide how to distribute.
            String[] choices = {"Random Distribution", "Reverse Distribution"};
            String option = (String) JOptionPane.showInputDialog(null, "Distribution Method", "How should they be ordered on the table?", 
            JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

            ///If we distribute reverse, we must update the creation and sort it properly.
            if(option.equals("Reverse Distribution")){
                // System.out.println();
                my_table.distribute_reverse();
                my_table.reupdate();
            }
        }
        ///We must add our components.
        foundation.add(my_table);
        foundation.setVisible(true);

        ///We msut select our preferred sort type.
        String[] choices = {"Bubble Sort", "Selection Sort", "Insertion Sort"};
        String option = (String) JOptionPane.showInputDialog(null, "Sort Method", "What method should be used to sort the elements?", 
        JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

        ///Spawn a second thread to periodically call repaint.
        new Thread(() -> {
            while(true){
                foundation.repaint();
            }
        }).start();

        ///We choose our type of sort.
        if(option.equals("Bubble Sort")){
            my_table.bubble_sort();
        } else if(option.equals("Selection Sort")){
            my_table.selection_sort();
        } else {
            my_table.insertion_sort();
        }
    }

    ///A method to sleep safely.
    public static void sleep_safe(long amt_ms){
        try {
            Thread.sleep(amt_ms);
        } catch (Exception e) {

        }
    }
}
//https://www.google.com/logos/fnbx/polyhedral_dice/d20_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d12_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d10_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d8_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d6_blank.png
//https://www.google.com/logos/fnbx/polyhedral_dice/d4_blank.png