public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");







        int[] prison = new int[100];
        for (int i = 0; i < prison.length; i++) prison[i] = i;
        shuffle(prison);
        System.out.println(runAll(prison));
        var success_count = 0;
        var failure_count = 0;
        for(var i = 0; i < 1000000; i+=1){
            shuffle(prison);
            var calc = runAll(prison);
            if(calc) success_count += 1;
            else failure_count += 1;
        }
        System.out.println("Success: " + success_count + "\nFailure: " + failure_count);

    }
    public static boolean runAll(int[] prisoners){
        for(var counter = 0; counter < prisoners.length; counter++)
            if(!runOne(counter, prisoners))
                return false;
        return true;
    }



    public static boolean runOne(int prisoner, int[] prisoners){
        var prev_box = prisoner;
        for(var i = 0; i < prisoners.length/2; i++){
            // System.out.println(prev_box);
            if(prisoners[prev_box] == prisoner) {
                return true;
            }
            prev_box = prisoners[prev_box];
        }
        return false;
    }
    // public static boolean


    public static  void shuffle(int[] prison) {
        for (int i = 0; i < prison.length; i++){
            int r = (int) (Math.random() * prison.length);
            int temp = prison[i];
            prison[i] = prison[r];
            prison[r] = temp;
        }
    }
}

