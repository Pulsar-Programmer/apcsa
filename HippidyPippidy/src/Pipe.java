import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Pipe {
    private Point start;
    private Point end;
    private ArrayList<ArrayList<Character>> board;

    public Pipe(File file) throws Exception{
        start = new Point(0, 0);
        end = new Point(0, 0);
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        final var scanner = new Scanner(file);
        var layer = 0;
        var got_start = false;
        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            ArrayList<Character> line_list = new ArrayList<>();
            final var array = line.toCharArray();
            for(var i = 0; i < array.length; i += 1){
                if('A' == array[i]){
                    if(!got_start) {
                        start = new Point(i, layer);
                        got_start = true;
                    }
                    else{
                        end = new Point(i, layer);
                    }
                }
                line_list.add(array[i]);
            }
            result.add(line_list);
            layer += 1;
        }
        scanner.close();
        board = result;
    }

    public boolean is_empty(){
        return board.isEmpty();
    }

    public ArrayList<ArrayList<Character>> start(){
        var points = new ArrayList<Point>();
        var solution = new ArrayList<ArrayList<Character>>();
        for(var i = 0; i < board.size(); i++){
            var row = new ArrayList<Character>();
            for(var j = 0; j < board.get(i).size(); j++){
                row.add(Character.valueOf(board.get(i).get(j)));
            }
            solution.add(row);
        }
        move(start, points);
        for(var i = 0; i < points.size(); i+=1){
            var p = points.get(i);
            solution.get(p.y).set(p.x, '.');
        }

        return solution;
    }
    public boolean move(Point position, ArrayList<Point> points){
        if(position.equals(end)){
            return true;
        }
        try_place(position);
        switch(try_get(position)){
            case 'x': case '.' : break;
            case '|' : {
                if(try_vertical(position, points)){return true;}
            } break;
            case '-' : {
                if(try_horizontal(position, points)){return true;}
            } break;
            case 'A': {
                if(try_vertical(position, points)){return true;}
                if(try_horizontal(position, points)){return true;}
            } break;
            default : break;
        }
        return false;
    }

    private boolean try_vertical(Point position, ArrayList<Point> points){
        final var above = flank_above(position);
        final var below = flank_below(position);
        if(move(above, points)) { points.add(above); return true; }
        if(move(below, points)) { points.add(below); return true; }
        return false;
    }
    private boolean try_horizontal(Point position, ArrayList<Point> points){
        final var left = flank_left(position);
        final var right = flank_right(position);
        if(move(left, points)) { points.add(left); return true; }
        if(move(right, points)) { points.add(right); return true; }
        return false;
    }

    private static Point flank_below(Point p){
        Point point = (Point)p.clone();
        point.y -= 1;
        return point;
    }
    private static Point flank_above(Point p){
        Point point = (Point)p.clone();
        point.y += 1;
        return point;
    }
    private static Point flank_left(Point p){
        Point point = (Point)p.clone();
        point.x -= 1;
        return point;
    }
    private static Point flank_right(Point p){
        Point point = (Point)p.clone();
        point.x += 1;
        return point;
    }



    private void try_place(Point p){
        try {
            board.get(p.y).set(p.x, '.');
        } catch (final Exception _e) {}
    }
    private Character try_get(Point p){
        try {
            return board.get(p.y).get(p.x);
        } catch (final Exception _e) {
            return 'x';
        }
    }

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
    

}
