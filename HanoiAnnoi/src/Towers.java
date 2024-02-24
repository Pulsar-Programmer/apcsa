import java.util.ArrayList;

public class Towers {
    private ArrayList<Integer> left;
    private ArrayList<Integer> middle;
    private ArrayList<Integer> right;

    //This is a utility function to clamp something between two values.
    // private static double clamp(double value, double min, double max) {
    //     return Math.max(min, Math.min(max, value));
    // }

    ///This finds the max length of the three towers.
    private int max_len(){
        return Math.max(Math.max(left.size(), middle.size()), right.size());
    }

    @Override
    public String toString() {
        ///We make our string.
        String string = "- - -\n";
        ///Append the indices.
        string += "0 1 2\n";

        ///We find the max length.
        final var len = max_len();
        ///We iterate over each and then prepend each part.
        for(var i = 0; i < len; i += 1){
            final var leftc = get_as_str_or_space(left, i);
            final var midc = get_as_str_or_space(middle, i);
            final var rightc = get_as_str_or_space(right, i);
            string = (leftc + " " + midc + " " + rightc + "\n").concat(string);
        }
        return string;
    }
    
    public boolean makeMove(int one, int two){
        ///If the move is invalid, we cannot preform it.
        if(one % 3 != one || two % 3 != two)
            return false;
        ///We get the reference to the corresponding array which we need.
        var a1 = corresponding_array(one);
        var a2 = corresponding_array(two);
        
        ///We find which move is legal. If neither are, we cannot preform the move.
        if(assert_legality(a1, a2)){
            a2.add(a1.remove(a1.size()-1));
        } else if(assert_legality(a2, a1)) {
            a1.add(a2.remove(a2.size()-1));
        } else {
            return false;
        }
        return true;
    }

    private boolean assert_legality(ArrayList<Integer> from, ArrayList<Integer> to){
        ///If you are drawing from an empty slot, you are immediately in the wrong.
        if(from.isEmpty()) return false;
        ///If you are placing an item in an empty slot, you are totally fine.
        if(to.isEmpty()) return true;
        ///If all this is fine, then we can safely peek at the values and determine the larger one to place.
        return peek(to) > peek(from);
    }

    private ArrayList<Integer> corresponding_array(int val){
        ///We find the corresponding array. If there is an issue with this, we can just loop back with the modded value.
        switch (val) {
            case 0:
                return left;
            case 1:
                return middle;
            case 2:
                return right;
            default:
                return corresponding_array(val % 3);
        }
    }

    public boolean isSolved(){
        //each element must be one less than the one before it;
        ///We first check to make sure that if the left or middle have any contents, we must return false for the config is not solved.
        if(!left.isEmpty() || !middle.isEmpty()){
            return false;
        }
        ///We create a loop ranging from 0..(the len of the right peg - 1)
        for(var i = 0; i < right.size() - 1; i+=1){
            ///We do this so that we can compare each element to make sure they are in order to avoid illegal mechanisms.
            ///Indeed, we check if the lower elem is bigger than the higher. If not, we return false.
            if(right.get(i) <= right.get(i + 1)){
                return false;
            }
        }
        ///Otherwise we safely return true here.
        return true;
    }

    public static ArrayList<Integer> with_num(int num){
        ///This is a very helpful factory function (I prefer them over constructors).
        ///It creates a tower and fills up the necessary pegs up to the relevant num.
        var tower = new ArrayList<Integer>();

        for(var i = 0; i < num; i += 1){
            tower.add(num - i);
        }

        return tower;
    }

    public void setTower(int num){
        ///This sets the left tower while clearing the others.
        left = with_num(num);
        middle.clear();
        right.clear();
    }

    public void reset(){
        ///This essentially sets the tower with the current amount of disks found.
        setTower(getCount());
    }

    public int getCount(){
        ///In order to get the count, we must return the sum of all the lens of the vectors.
        return left.size() + middle.size() + right.size();
    }

    private static Integer peek(ArrayList<Integer> inner){
        ///If it is empty, which should not happen, we just assing the largest number to our peek.
        if(inner.isEmpty()){
            return Integer.MAX_VALUE; //this should never be triggered
        }
        ///Otherwise, we genuinely check what is inside.
        return inner.get(inner.size() - 1);
    }

    // private static Integer pop_or_zero(ArrayList<Integer> inner){
    //     var size = inner.size();
    //     if(size == 0){
    //         return 0;
    //     }
    //     var get = inner.get(size - 1);
    //     inner.remove(size - 1);
    //     return get;
    // }

    // private static Integer get_or_zero(ArrayList<Integer> inner, int index){
    //     try {
    //         return inner.get(index);
    //     } catch (Exception e) {
    //         return 0;
    //     }
    // }
    
    ///This gets the value in the index as a str, but if it cannot be accessed we return a space str.
    public static String get_as_str_or_space(ArrayList<Integer> inner, int index){
        try {
            return inner.get(index).toString();
        } catch (Exception e) {
            return " ";
        }
    }



    //getters and setters
    public ArrayList<Integer> getLeft() {
        return left;
    }

    public void setLeft(ArrayList<Integer> left) {
        this.left = left;
    }

    public ArrayList<Integer> getMiddle() {
        return middle;
    }

    public void setMiddle(ArrayList<Integer> middle) {
        this.middle = middle;
    }

    public ArrayList<Integer> getRight() {
        return right;
    }

    public void setRight(ArrayList<Integer> right) {
        this.right = right;
    }

    //Constructors

    public Towers() {
        ///We create the left, middle, and right pegs.
        ///Funny hint: with_num(0) simply returns an empty peg. LOL.
        left = with_num(5);
        middle = with_num(0);
        right = with_num(0);
    }

    public Towers(ArrayList<Integer> left, ArrayList<Integer> middle, ArrayList<Integer> right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    
}