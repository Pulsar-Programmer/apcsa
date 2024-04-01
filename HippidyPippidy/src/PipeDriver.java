import java.io.File;
import java.util.Scanner;

public class PipeDriver {
    public static void main(String[] args) throws Exception {
        
        var scanner = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        final var filename = scanner.nextLine();
        scanner.close();
        var my_pipe = new Pipe(new File(filename));
        final var result = my_pipe.start();
        final var p_end = my_pipe.getEnd();
        System.out.println("Your solution:");
        var found = false;
        for(var i = 0; i < result.size(); i++){
            for(var j = 0; j < result.get(i).size(); j++){
                var c = result.get(i).get(j);
                if(c=='.'){
                    found = true;
                }
                if(p_end.y == i && p_end.x == j){
                    System.out.print('A');
                    continue;
                }
                System.out.print(c);
            }
            System.out.println();
        }
        if(!found){
            //this doesn't work if the As are next to each other
            System.out.println("There is no solution for this board.");
        }
    }
}
