import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(alphaSort("hello monkie "));
        System.out.println("Hello, World!");
    }







    public static String alphaSort(String str) {
        char[] array = str.toCharArray();
        Character[] charObjectArray = str.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        Arrays.sort(charObjectArray);
        String growable = "";
        for(var i = 0; i < array.length; i++){
            growable += charObjectArray[i];
        }
        return growable;
    }	
}
