import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

public class Pipe {
    private Point start;
    private Point end;
    private ArrayList<ArrayList<Character>> board;
    
    ///We create our constructor that takes in a file and constructs the class.
    public Pipe(File file) throws Exception{
        ///We set our start and end to bad points out of bounds. Don't worry- this is not a problem.
        ///This is because of the way the get method is implemented --- you'll see.
        start = new Point(-1, -1);
        end = new Point(-1, -1);
        ///We create our new array to house the result of the board.
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        ///We scan and keep track of the amount of times scanned.
        final var scanner = new Scanner(file);
        var layer = 0;
        while (scanner.hasNextLine()) {
            ///We get the line.
            final var line = scanner.nextLine();
            ArrayList<Character> line_list = new ArrayList<>();
            final var array = line.toCharArray();
            ///We loop over each character and add it accordingly.
            for(var i = 0; i < array.length; i += 1){
                if('A' == array[i]){
                    ///Now if the start equals the end (meaning they are both the bad points from earlier), we can safely set start to a value. Start will never equal end otherwise.
                    if(start.equals(end)) {
                        ///We create start.
                        start = new Point(i, layer);
                    }
                    else{
                        ///We otherwise create end.
                        end = new Point(i, layer);
                    }
                }
                ///We add each character.
                line_list.add(array[i]);
            }
            ///We add each line.
            result.add(line_list);
            ///We increment layer.
            layer += 1;
        }
        ///We close the scanner and initialize the last var.
        scanner.close();
        board = result;
    }

    ///This checks if the board is empty.
    public boolean is_empty(){
        return board.isEmpty();
    }

    ///This starts the solving process.
    public ArrayList<ArrayList<Character>> start(){
        ///We create a set of points that we must keep.
        var points = new HashSet<Point>();
        ///We create our solution to display.
        var solution = new ArrayList<ArrayList<Character>>();
        ///We create a duplicate array for the displaying.
        for(var i = 0; i < board.size(); i++){
            var row = new ArrayList<Character>();
            for(var j = 0; j < board.get(i).size(); j++){
                row.add(Character.valueOf(board.get(i).get(j)));
            }
            solution.add(row);
        }
        ///We start the moving.
        move(start, points);
        ///We transfer over the relevant points.
        for(var p : points){
            solution.get(p.y).set(p.x, '.');
        }
        ///We return the solution.
        return solution;
    }
    public boolean move(Point position, HashSet<Point> points){
        // System.out.println("Considering point " + position);
        ///We check if we are at end yet. If we are, we return true, signifying the ability for the point to be recognized.
        if(position.equals(end)){
            return true;
        }
        // System.out.println(try_get(position));
        ///We match on the character of the position.
        switch(try_get(position)){
            ///Nothing happens unless it is a | or - or A whereby verticals, horizontals, and more are considered.
            case 'x': case '.' : break;
            case '|' : {
                try_place(position);
                if(try_vertical(position, points)){return true;}
            } break;
            case '-' : {
                try_place(position);
                if(try_horizontal(position, points)){return true;}
            } break;
            case 'A': {
                if(try_vertical(position, points)){return true;}
                if(try_horizontal(position, points)){return true;}
            } break;
            default : break;
        }
        ///We return false if no returns could be made.
        return false;
    }
    ///This tries to move above or below.
    private boolean try_vertical(Point position, HashSet<Point> points){
        final var above = flank_above(position);
        final var below = flank_below(position);
        ///We look above or below. If the move is successful, we backtrack and return.
        if(move(above, points)) { points.add(above); return true; }
        if(move(below, points)) { points.add(below); return true; }
        ///Otherwise we return false.
        return false;
    }
    ///Same thing as try_vertical but for horizontal.
    private boolean try_horizontal(Point position, HashSet<Point> points){
        final var left = flank_left(position);
        final var right = flank_right(position);
        if(move(left, points)) { points.add(left); return true; }
        if(move(right, points)) { points.add(right); return true; }
        return false;
    }
    ///Creates a point one below the input.
    private static Point flank_below(Point p){
        Point point = (Point)p.clone();
        point.y -= 1;
        return point;
    }
    ///Creates a point one above the input.
    private static Point flank_above(Point p){
        Point point = (Point)p.clone();
        point.y += 1;
        return point;
    }
    ///Creates a point one left of the input.
    private static Point flank_left(Point p){
        Point point = (Point)p.clone();
        point.x -= 1;
        return point;
    }
    ///Creates a point one right of the input.
    private static Point flank_right(Point p){
        Point point = (Point)p.clone();
        point.x += 1;
        return point;
    }


    ///Tries to place a dot a p. If it works, yay, but if it doesn't, no problem. Dot and X are considered the same anyway.
    private void try_place(Point p){
        try {
            board.get(p.y).set(p.x, '.');
        } catch (final Exception _e) {}
    }
    ///We try to get the character at a point p. If it works, then we can return the character otherwise we must return 'x'. This allows no out of bounds sussiness to happen.
    private Character try_get(Point p){
        try {
            return board.get(p.y).get(p.x);
        } catch (final Exception _e) {
            return 'x';
        }
    }
    ///How we display our board.
    @Override
    public String toString() {
        var string = new String();
        for(var i = 0; i < board.size(); i++){
            for(var j = 0; j < board.get(i).size(); j++){
                string += board.get(i).get(j);
            }
            string += '\n';
        }
        return string;
    }
    ///Standard getters and setters.
    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public ArrayList<ArrayList<Character>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Character>> board) {
        this.board = board;
    }
    

}
