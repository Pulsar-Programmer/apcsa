public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // "Hello".toCharArray()
    }






    public static String caesarCypher(String stuff, int shift){
        String output = "";
        String veryBadString = null;
        for (int i = 0; i<stuff.length();i++){
            char c = stuff.charAt(i);
            c+=1;


        }
        return null;
    }

    public static char shiftChar(char c, int shift) {
        c += shift;
        if (c > 'z'){
            c = (char)(c-'z'-1+'a');
        }
        return c;
    }
}
