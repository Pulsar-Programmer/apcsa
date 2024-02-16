import java.util.ArrayList;

public class Tower {
    private ArrayList<Integer> inner;

    public Tower() {
        inner = new ArrayList<Integer>();
    }
    
    public static Tower with_num(Integer num){
        var tower = new Tower();

        for(var i = 0; i < num; i += 1){
            tower.push(num - i);
        }

        return tower;
    }

    private void push(Integer val){
        inner.add(val);
    }

    private int size(){
        return inner.size();
    }

    private Integer peek(){
        return inner.get(size() - 1);
    }

    private Integer pop_or_zero(){
        var size = size();
        if(size == 0){
            return 0;
        }
        var get = inner.get(size - 1);
        inner.remove(size - 1);
        return get;
    }

    private Integer get_or_zero(int index){
        try {
            return inner.get(index);
        } catch (Exception e) {
            return 0;
        }
    }
}
