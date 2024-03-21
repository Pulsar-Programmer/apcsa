import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(alphaSort("hello monkie "));
        System.out.println("Hello, World!");
    }







    public static String alphaSort(String str) {
        // {

        //     IntFunction<Integer> t = Character::charCount;
        //     int a = t.apply(2);
            
        // }
        // Function<Void, Double> g = _a -> 1.0 * 5;
        // Supplier<Integer> supplier = () -> 5;
        //str.charAt(0)
        char[] array = str.toCharArray();
        Character[] charObjectArray = str.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        Arrays.sort(charObjectArray);
        String growable = "";
        for(var i = 0; i < array.length; i++){
            growable += charObjectArray[i];
        }
        return growable;
    }	


    public static int recursive_add(ArrayList<Integer> array){
        return array.remove(0) + recursive_add(array);
    }
}
