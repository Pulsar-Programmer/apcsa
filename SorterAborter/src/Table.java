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
            pieces[i].setX(place_entity(i, len));
            pieces[i].setSize((int)entity_size(len));
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
            pieces[i].draw(g2d);
        }
        for(var i = 0; i < ptrs.size(); i += 1){
            ptrs.get(i).draw(g2d);
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
        ptrs.add(new Pointer(Color.blue, place_entity(0, pieces.length), y(62)));
        for (int i = 1; i < pieces.length; i++) {
            ptrs.get(0).move(place_entity(i, pieces.length));
            Piece temp = pieces[i];
            temp.pick();

            int j = i - 1;
            ptrs.add(new Pointer(Color.green, place_entity(j, pieces.length), y(74)));

            while (j >= 0 && pieces[j].getValue() > temp.getValue()) {
                pieces[j + 1] = pieces[j];
                pieces[j].move((int)pieces[j + 1].getX());

                j--;
                ptrs.get(1).move(place_entity(j, pieces.length));
            }
            temp.place();
            pieces[j + 1] = temp;

            ptrs.remove(1);
        }
        ptrs.remove(0);
    }

    public void bubble_sort() {
        ptrs.add(new Pointer(Color.blue, place_entity(0, pieces.length), y(62)));
        for (int i = 0; i < pieces.length; i++) {
            ptrs.get(0).move(place_entity(i, pieces.length));
            boolean swapped = false;

            for (int j = 1; j < pieces.length - i; j++) {
                if (pieces[j - 1].compareTo(pieces[j]) > 0) {
                    swapped = true;

                    Piece temp = pieces[j - 1];
                    pieces[j - 1] = pieces[j];
                    pieces[j] = temp;
                    
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
    
    public void selection_sort() {
        for (int i = 0; i < pieces.length - 1; i++) {
            int min = i;
            for (int j = 1 + i; j < pieces.length; j++) {
                
                if (pieces[min].compareTo(pieces[j]) > 0) {
                    min = j;
                }
            }
            Piece temp = pieces[i];
            pieces[i] = pieces[min];
            pieces[min] = temp;
        }
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





