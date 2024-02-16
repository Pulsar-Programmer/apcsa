import java.util.ArrayList;

public class Towers {
    private Tower a;
    private Tower b;
    private Tower c;


    //impl Tower:

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static char base_char(){
        return '¯';
    }

    private void assert_legality(int _from, int _to){
        int from = (int)clamp(_from, 1.0, 3.0);
        int to = (int)clamp(_to, 1.0, 3.0);
        //TODO

    }

    @Override
    public String toString() {
        return "Towers [a=" + a + ", b=" + b + ", c=" + c + "]";
        //TODO
    }



    
}