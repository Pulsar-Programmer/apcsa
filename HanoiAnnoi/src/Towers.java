import java.util.ArrayList;

public class Towers {
    private ArrayList<Integer> left;
    private ArrayList<Integer> middle;
    private ArrayList<Integer> right;

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private int max_len(){
        return Math.max(Math.max(left.size(), middle.size()), right.size());
    }

    @Override
    public String toString() {
        String string = "- - -\n";
        string += "0 1 2\n";

        final var len = max_len();
        for(var i = 0; i < len; i += 1){
            final var leftc = get_as_str_or_space(left, i);
            final var midc = get_as_str_or_space(middle, i);
            final var rightc = get_as_str_or_space(right, i);
            string = (leftc + " " + midc + " " + rightc + "\n").concat(string);
        }
        return string;
    }
    
    public boolean makeMove(int one, int two){
        var a1 = corresponding_array(one);
        var a2 = corresponding_array(two);
        
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
        if(from.isEmpty()) return false;
        if(to.isEmpty()) return true;
        return peek(to) > peek(from);
    }

    private ArrayList<Integer> corresponding_array(int val){
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
        if(!left.isEmpty() || !middle.isEmpty()){
            return false;
        }
        for(var i = 0; i < right.size() - 1; i+=1){
            if(right.get(i) <= right.get(i + 1)){
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> with_num(int num){
        var tower = new ArrayList<Integer>();

        for(var i = 0; i < num; i += 1){
            tower.add(num - i);
        }

        return tower;
    }

    public void setTower(int num){
        left = with_num(num);
        middle.clear();
        right.clear();
    }

    public void reset(){
        setTower(getCount());
    }

    public int getCount(){
        return left.size() + middle.size() + right.size();
    }

    private static Integer peek(ArrayList<Integer> inner){
        if(inner.isEmpty()){
            return Integer.MAX_VALUE; //this should never be triggered
        }
        return inner.get(inner.size() - 1);
    }

    private static Integer pop_or_zero(ArrayList<Integer> inner){
        var size = inner.size();
        if(size == 0){
            return 0;
        }
        var get = inner.get(size - 1);
        inner.remove(size - 1);
        return get;
    }

    private static Integer get_or_zero(ArrayList<Integer> inner, int index){
        try {
            return inner.get(index);
        } catch (Exception e) {
            return 0;
        }
    }

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