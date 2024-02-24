import java.util.ArrayList;
import java.util.Scanner;

///Note: Triple comments are intended to be read.
//Note: Double comments give no meaningful information.

public class Driver {
    public static void main(String[] args) throws Exception {
        ///We ready our scanner.
        var scanner = new Scanner(System.in);
        ///We create our game loop.
        while(true){
            ///We create our tower.
            var my_tower = new Towers();

            ///We query for a tower size.
            System.out.println("How would you like your tower? Enter a number.");
            final var size = scanner.nextInt(); scanner.nextLine();
            if(size < 1){
                System.out.println("I strongly discourage this size... Please try again.");
                continue;
            }
            my_tower.setTower(size);

            ///We query whether we should involve the solution or not.
            System.out.println("Do you want me to show you the solution before you attempt it? (enter `true` or `false`)");
            final var for_ai = scanner.nextBoolean(); scanner.nextLine();

            ///We first keep track of the type of move next to do.
            var aimove = 0;
            ///While my_tower is not solved, we continue.
            while(!my_tower.isSolved()){
                ///We clear the console and display our tower.
                clear_console();
                System.out.println(my_tower);
                
                ///If we must solve it, lets do it, otherwise the user is free to.
                if(for_ai){
                    ///We sleep so the user has a chance to see the animation.
                    Thread.sleep(1000);
                    ///We measure the move. Based on which time, we roll the makeMove in the special wikipedia config.
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
                    ///Each time, we increment the move.
                    aimove += 1;
                    ///Then we cap it my circling it at 3 with only three states.
                    aimove %= 3;
                } else {
                    ///We query the two values, one after the other.
                    System.out.println("Which two values do you want to move? Enter the first followed by the second.");
                    final var one = scanner.nextInt(); scanner.nextLine();
                    final var two = scanner.nextInt(); scanner.nextLine();
                    ///If the move is invalid, we must address it.
                    if(!my_tower.makeMove(one, two)){
                        System.out.println("The move you attempted to make was invalid! Please try again!");
                        Thread.sleep(1000);
                        continue;
                    }
                }
            }
            ///We clear the console and print out the tower.
            clear_console();
            System.out.println(my_tower);
            ///If the user solved it, say good job!
            if(!for_ai){
                System.out.println("Great job! You've solved it!");
            }
            ///Request a re-run.
            System.out.println("Care to try again? (enter true or false)");
            if(!scanner.nextBoolean()) break;
        }
        scanner.close();
    }

    public static void clear_console(){
        // System.out.print("\033[H\033[2J");
        // System.out.flush();
        ///Print 20 lines of empty returns.
        for(var i = 0; i < 20; i+=1) System.out.println();
    }
}
