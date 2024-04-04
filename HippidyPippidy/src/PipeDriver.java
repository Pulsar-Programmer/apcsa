import java.io.File;
import java.util.Scanner;
///Triple comments are intended to be read.
//Double comments are not intended to be read.

public class PipeDriver {
    public static void main(String[] args) throws Exception {
        ///We create a scanner and ask some stuff.
        var scanner = new Scanner(System.in);
        while(true){
            System.out.println("What is the name of the file?");
            final var filename = scanner.nextLine();
            ///We create our pipe.
            var my_pipe = new Pipe(new File(filename));
            ///We start it and get the end for later.
            final var result = my_pipe.start();
            final var p_end = my_pipe.getEnd();
            System.out.println("Your solution:");
            ///Found keeps track of whether any '.' were found, denoting a positive or negative solution.
            var found = false;
            for(var i = 0; i < result.size(); i++){
                for(var j = 0; j < result.get(i).size(); j++){
                    var c = result.get(i).get(j);
                    ///If we find the dot, which is always present in a solved board (since the end marker (A) is dotted), we can say that it has a solution.
                    if(c=='.'){
                        found = true;
                    }
                    ///We then print the A character in place of the final '.'
                    if(p_end.y == i && p_end.x == j){
                        System.out.print('A');
                        continue;
                    }
                    ///We print c at that position for toString();
                    System.out.print(c);
                }
                ///We print a line to go to the next line.
                System.out.println();
            }
            ///If we didn't find any dots, we can safely deem no solution.
            if(!found){
                System.out.println("There is no solution for this board.");
            }
            ///We request another playthrough.
            System.out.println("Would you like to play again? Yes=`true` No=`false`");
            final var bool = scanner.nextBoolean(); scanner.nextLine();
            if(!bool){
                break;
            }
        }
        ///We end.
        scanner.close();
    }
}
