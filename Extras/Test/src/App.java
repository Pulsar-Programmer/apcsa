public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // bar(new Foo[] {});
        // .getClass();
        System.out.println(java.util.Arrays.deepToString(Rotate.rotate(new Number[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }

    public static void bar(Foo[] arrFoos){
        //do stuff
        return;
    }
}
class Foo{

}
class ExtendsFoo extends Foo {

}