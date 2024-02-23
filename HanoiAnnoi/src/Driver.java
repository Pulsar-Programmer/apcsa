import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        //make it sleep
        //make it clear the console
        while(true){
            var my_tower = new Towers();

            System.out.println("How would you like your tower? Enter a number greater than ");
            final var size = scanner.nextInt(); scanner.nextLine();
            if(size < 1){
                System.out.println("I strongly discourage this size... Please try again.");
                continue;
            }
            my_tower.setTower(size);

            System.out.println("Do you want me to show you the solution before you attempt it? (enter `true` or `false`)");
            final var for_ai = scanner.nextBoolean(); scanner.nextLine();
            
            var aimove = 0;
            while(!my_tower.isSolved()){
                if(for_ai){
                    switch (aimove) {
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
                    aimove += 1;
                    aimove %= 3;
                } else {
                    System.out.println("Which two values do you want to move? Enter the first followed by the second.");
                    final var one = scanner.nextInt(); scanner.nextLine();
                    final var two = scanner.nextInt(); scanner.nextLine();
                    if(!my_tower.makeMove(one, two)){
                        System.out.println("The move you attempted to make was invalid! Please try again!");
                        continue;
                    }
                }

                clear_console();
                System.out.println(my_tower);
                Thread.sleep(1000);
            }
            if(!for_ai){
                System.out.println("Great job! You've solved it!");
            }
            System.out.println("Care to try again? (enter true or false)");
            if(!scanner.nextBoolean()) break;
        }
        scanner.close();
    }

    public static void clear_console(){
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        for(var i = 0; i < 20; i+=1) System.out.println();
    }
}
