import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }



    public static ArrayList<Integer> range(int range){
        var vec = new ArrayList<Integer>();
        for(var i = 0; i < range; i += 1){
            vec.add(i);
        }
        return vec;
    }

    public static ArrayList<String> split(String str, String pat){
        var vec = new ArrayList<String>();
        var window_start = 0;
        var window_end = pat.length();
        while(window_end <= str.length()){
            final var slice = str.substring(window_start, window_end);
            if(slice.equals(pat)){
                vec.add(str.substring(0, window_start));
                str = str.substring(window_end);
            }
            window_start += 1;
            window_end += 1;
        }
        return vec;
    }







}
