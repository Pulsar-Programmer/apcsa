import java.io.File;
import java.util.Scanner;

public class PipeDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        final var scanner = new Scanner(System.in);
        System.out.println("What is the name of the file?");
        final var filename = scanner.nextLine();
        var my_pipe = new Pipe(new File(filename));

    }
}
