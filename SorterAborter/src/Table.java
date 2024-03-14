import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Table extends JPanel{
    private Piece[] pieces;
    private ArrayList<Pointer> ptrs;

    private static int total_accesses = 0;
    private static int total_mutations = 0;

    public Table(int len){
        pieces = new Piece[len];
        for(var i = 0; i < pieces.length; i += 1){
            pieces[i] = new Piece();
        }
        ptrs = new ArrayList<Pointer>();
    }

    ///This is the overridden paintComponent.
    @Override
    protected void paintComponent(Graphics g) {
        ///We do the relevant graphics things.
        super.paintComponent(g);
        var g2d = (Graphics2D) g;
        ///We draw the background.
        draw_backdrop(g2d);
        ///We draw each piece at the appropriate point.
        for(var i = 0; i < pieces.length; i += 1){
            pieces[i].setX(place_entity(i, pieces.length));
            pieces[i].draw(g2d, (int)entity_size(pieces.length));
        }
    }

    ///Here we draw the background.
    public static void draw_backdrop(Graphics2D g){
        g.setColor(new Color(0xCDA678));
        g.fillRect(0, 0, x(100), y(100));
    }

    public static int place_entity(int i, int total){
        final double size = entity_size(total);
        return (int)(size/2 + i * size * 1.5);
    }

    public static double entity_size(int total){
        return x(100)/(1.5 * total + 0.5);
    }

    public void distribute_reverse(){
        Arrays.sort(pieces, Comparator.reverseOrder());
    }



    //The sorts

    public void insertion_sort() {
        for (int i = 1; i < pieces.length; i++) {
            Piece temp = pieces[i];
            int j = i - 1;
            while (j >= 0 && pieces[j].getValue() > temp.getValue()) {
                pieces[j + 1] = pieces[j];
                j--;
            }
            pieces[j + 1] = temp;
        }
    }

    public void bubble_sort(){
        
    }
    
    public void selection_sort(){
        
    }


    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }
}





