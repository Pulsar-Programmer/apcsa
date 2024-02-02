public class Rotate {
    public static <T> T[][] rotate(final T[][] first){
        var clone = first;
        var square_length  = first.length;
        for(int i = 0; i<square_length; i++){
            for(int j = 0; j<square_length; j++){
                clone[j][i] = first[i][j];
            }
        }
        return clone;
    }
}

