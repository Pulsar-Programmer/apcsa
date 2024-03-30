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
        var found = false;
        for(var i = 0; i < result.size(); i++){
            for(var j = 0; j < result.get(i).size(); j++){
                var c = result.get(i).get(j);
                if(c=='.'){
                    found = true;
                }
                System.out.print(c);
            }
            System.out.println();
        }
        if(!found){
            System.out.println("There is no solution for this board.");
        }
        System.out.println(result);

    }
}
