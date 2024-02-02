public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    static <T> void shuffle(T[] arr){
		for (int i =0; i < arr.length; i++){
			int random = (int)(Math.random() * arr.length);
			T temp = arr[i];
			arr[i] = arr[random];
			arr[random] = temp;
			drop(temp);
            // drop();
		}
	}
	static <T> void drop(T t){
		t = null;
	}
    public int[] notAlone(int[] a, int val) {
        int[] nums = a.clone();
        for (var i =0; i < nums.length; i++){
            if(i == 1 || i == nums.length - 1){
                continue;
            }
            var max = Math.max(i - 1, i + 1);
            nums[i] = nums[max];
        }
        return nums;
    }
      
}
// class <T> Potato{
//     private T t;
// }