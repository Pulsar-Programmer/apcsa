import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class App { 
    public static void main(final String[] args) throws Exception {
        // System.out.println("Hello, World!");

        final var file = new File("blabla.txt");
        // var console = new java.util.Scanner(System.in);
        var file_reader = new Scanner(file);
        int sum = 0; int count = 0;
        var file_writer = new PrintWriter(new File("clean_data.txt"));
        while(file_reader.hasNext()){
            final var weight = file_reader.nextInt();
            if (weight!=0){
                sum += weight; count ++;
                file_writer.print(weight + " ");
            }
        }
        System.out.println("Average ball " + (double)sum/count);
        file_writer.close(); file_reader.close();
        file_reader = new Scanner(new File("monkie.txt"));
        file_writer = new PrintWriter(new File("monkie2.txt"));
        var str = "";
        while (file_reader.hasNext()) str += file_reader.next();
        System.out.println(str);
        file_writer.print(blabla(str));
        file_writer.close(); file_reader.close();






        
    }
    public static String blabla(String str){
        final var bla = "bla";
        final var len = str.length();
        str = "";
        for (int i = 0; i < len; i++){
            str += bla.charAt(i % 3);
        }
        return str;
    }
}
