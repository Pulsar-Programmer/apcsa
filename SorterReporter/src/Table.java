import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Table extends JPanel{
    private Piece[] pieces;
    private ArrayList<Pointer> ptrs;

    private static int total_accesses = 0;
    private static int total_mutations = 0;

    public Table(int len){
        ///We assign pieces and iterate over them and assign them the correct places corresponding to the indices.
        pieces = new Piece[len];
        for(var i = 0; i < pieces.length; i += 1){
            pieces[i] = new Piece();
            pieces[i].setX(place_entity(i, pieces.length));
            pieces[i].setSize((int)entity_size(pieces.length));
        }
        ptrs = new ArrayList<Pointer>();

    }
    
    ///This is used to recorrespond the indicies and movements.
    public void reupdate(){
        for(var i = 0; i < pieces.length; i += 1){
            pieces[i].setX(place_entity(i, pieces.length));
            pieces[i].setSize((int)entity_size(pieces.length));
        }
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
        ///We draw all pointers.
        for(var i = 0; i < ptrs.size(); i += 1){
            ptrs.get(i).draw(g2d);
        }
        ///We draw the variables.
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Total Accesses: " + total_accesses, 100, 100);
        g2d.drawString("Total Mutations: " + total_mutations, 100, 200);
    }

    ///Here we draw the background.
    public static void draw_backdrop(Graphics2D g){
        g.setColor(new Color(0xCDA678));
        g.fillRect(0, 0, x(100), y(100));
    }

    ///We get the placement of each entity.
    public static int place_entity(int i, int total){
        final double size = entity_size(total);
        return (int)(size/2 + i * size * 1.5);
    }

    ///The size of each element on the screen.
    public static double entity_size(int total){
        return x(100)/(1.5 * total + 0.5);
    }

    ///To distribute the array in reverse order.
    public void distribute_reverse(){
        Arrays.sort(pieces, Comparator.reverseOrder());
    }



    ///The sorts:

    ///This is an insertion sort with infused animation.
    public void insertion_sort() {
        ///We create a pointer.
        ptrs.add(new Pointer(Color.blue, place_entity(0, pieces.length), y(51) + entity_size(pieces.length), entity_size(pieces.length) / 2.));
        for (int i = 1; i < pieces.length; i++) {
            ///We call the pointer.
            ptrs.get(0).move(place_entity(i, pieces.length));

            ///We select the item.
            Piece temp = pieces[i]; total_accesses += 1;
            temp.pick();
            ///We continue with pointers.
            int j = i - 1;
            ptrs.add(new Pointer(Color.green, place_entity(j, pieces.length), y(51) + 2 * entity_size(pieces.length), entity_size(pieces.length) / 2.));

            while (j >= 0 && pieces[j].getValue() > temp.getValue()) {
                total_accesses += 1;
                pieces[j].move(place_entity(j+1, pieces.length));

                pieces[j + 1] = pieces[j]; total_accesses += 1; total_mutations += 1;
                pieces[j] = temp; ///Doesn't count - > only used for rendering
                ///We ensure to make movements for each operation.
                j--;
                ptrs.get(1).move(place_entity(j, pieces.length));
            }
            ///We must add some accesses if it is true that the condition failed.
            if(j >= 0 && pieces[j].getValue() <= temp.getValue()) {total_accesses+=2; }
            
            ptrs.get(1).move(place_entity(j + 1, pieces.length));
            temp.move(place_entity(j+1, pieces.length));

            temp.place();

            pieces[j + 1] = temp; total_mutations += 1;

            ptrs.remove(1);
        }
    }

    ///Bubble sort with animation.
    public void bubble_sort() {
        ///We track pointers and make movements based on the actual operations in the array.
        ptrs.add(new Pointer(Color.blue, place_entity(0, pieces.length), y(51) + entity_size(pieces.length), entity_size(pieces.length) / 2.));
        for (int i = 0; i < pieces.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < pieces.length - i; j++) {
                ptrs.get(0).move(place_entity(j, pieces.length));
                if (pieces[j - 1].compareTo(pieces[j]) > 0) {
                    swapped = true;
                    
                    Piece temp = pieces[j - 1]; total_accesses += 1;
                    var x1 = pieces[j].getX();

                    pieces[j].pick();
                    pieces[j-1].pick();

                    pieces[j].move((int)pieces[j-1].getX());
                    pieces[j-1].move((int)x1);

                    pieces[j].place();
                    pieces[j-1].place();

                    pieces[j - 1] = pieces[j]; total_accesses += 1; total_mutations += 1;

                    pieces[j] = temp; total_mutations += 1;

                }
                total_accesses += 2;
            }
            if (!swapped) {
                break;
            }
        }
    }
    
    ///Selection sort with animation.
    public void selection_sort() {
        ptrs.add(new Pointer(Color.blue, place_entity(0, pieces.length), y(51) + entity_size(pieces.length), entity_size(pieces.length) / 2.));
        for (int i = 0; i < pieces.length - 1; i++) {
            ptrs.get(0).move(place_entity(i, pieces.length));
            int min = i;
            ///We pick the minimum.
            pieces[min].pick();

            ptrs.add(new Pointer(Color.green, place_entity(0, pieces.length), y(51) + 2 * entity_size(pieces.length), entity_size(pieces.length) /2.));
            for (int j = 1 + i; j < pieces.length; j++) {
                ptrs.get(1).move(place_entity(j, pieces.length));

                if (pieces[min].compareTo(pieces[j]) > 0) {
                    pieces[min].place();
                    min = j;
                    ///Replace this picking.
                    pieces[min].pick();
                }
                total_accesses += 2;
            }
            ptrs.remove(1);
            ///And we eventually do some swapping.
            Piece temp = pieces[i]; total_accesses += 1;
            pieces[i].pick();
            var x1 = pieces[min].getX();

            pieces[min].move((int)pieces[i].getX());
            pieces[i].move((int)x1);

            pieces[min].place();
            pieces[i].place();

            pieces[i] = pieces[min]; total_accesses += 1; total_mutations += 1;
            pieces[min] = temp; total_mutations += 1;
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


    public Piece[] getPieces() {
        return pieces;
    }


    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }


    public ArrayList<Pointer> getPtrs() {
        return ptrs;
    }


    public void setPtrs(ArrayList<Pointer> ptrs) {
        this.ptrs = ptrs;
    }


    public static int getTotal_accesses() {
        return total_accesses;
    }


    public static void setTotal_accesses(int total_accesses) {
        Table.total_accesses = total_accesses;
    }


    public static int getTotal_mutations() {
        return total_mutations;
    }


    public static void setTotal_mutations(int total_mutations) {
        Table.total_mutations = total_mutations;
    }
}





