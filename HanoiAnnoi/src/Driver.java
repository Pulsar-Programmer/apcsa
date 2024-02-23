import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        //make it sleep
        //make it clear the console
        System.out.println("Do you want me to show you the solution before you attempt it? (enter `true` or `false`)");
        var for_ai = scanner.nextBoolean();

        
        var my_tower = new Towers();
        var move = 0;
        while(!my_tower.isSolved()){
            if(for_ai){
                switch (move) {
                    case 0:
                        my_tower.makeMove(0, 1);
                        break;
                    case 1:
                        my_tower.makeMove(0, 2);
                        break;
                    case 2:
                        my_tower.makeMove(1, 2);
                        break;
                    default:
                        break;
                }
                move += 1;
                move %= 3;
            } else {
                
            }

            clear_console();
        }





    }

    public static void clear_console(){
        System.out.print("\033[H\033[2J");
        // System.out.flush();
        for(var i = 0; i < 20; i+=1) System.out.println();
    }
}
