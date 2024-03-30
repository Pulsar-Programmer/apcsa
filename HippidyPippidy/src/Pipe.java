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

    
}
