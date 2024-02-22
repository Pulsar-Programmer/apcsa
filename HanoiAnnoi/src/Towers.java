import java.util.ArrayList;

public class Towers {
    private Tower left;
    private Tower middle;
    private Tower right;


    //impl Tower:

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void assert_legality(int _from, int _to){
        int from = (int)clamp(_from, 1, 3);
        int to = (int)clamp(_to, 1, 3);
        //TODO

    }

    private int max_len(){
        return Math.max(Math.max(left.size(), middle.size()), right.size());
    }

    @Override
    public String toString() {

        String string = "¯¯¯\n";

        final var len = max_len();
        for(var i = 0; i < len; i += 1){
            final var leftc = left.get_as_str_or_space(i);
            final var midc = middle.get_as_str_or_space(i);
            final var rightc = right.get_as_str_or_space(i);
            string = (leftc + midc + rightc + "\n").concat(string);
        }
        return string;
        //TODO: Awaits testing
    }



    
}