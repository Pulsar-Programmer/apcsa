import java.util.Scanner;

//Note: These (double comments) are meant not to be read.
///Note: These (triple comments) are meant to be read.

public class DriverMancala {
    public static void main(String[] args) throws Exception {
        ///We create a scanner and print relevant data; we construct our structure and print the board.
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter in which box you want to move.");
        Mancala m = new Mancala();
        System.out.println(m.toString());
        ///We move to the main process and continue only if no wins or ties have been made.
        var has_won = 0;
        while (has_won == 0){
            ///We announce the next player.
            System.out.println(m.isTurn() ? "Next: Player 2's Turn" : "Next: Player 1's Turn");
            
            ///We detect our given value and make our move.
            final var given_value = scanner.nextInt(); scanner.nextLine();
            final var make_move = m.makeMove(given_value);
            ///If the value is invalid, we must invalidate it.
            if(!make_move) {
                System.out.println("That action does nothing! Try again.");
                continue;
            }
            ///We print the board.
            System.out.println(m.toString());
            ///We update the condition of the win.
            has_won = m.has_won();
        }
        ///We pattern match upon GameResult.
        switch (has_won){
            case 1:{
                System.out.println("It was a tie!");
            }break;
            case 2:{
                System.out.println("Player 1 wins!");
            }break;
            case 3:{
                System.out.println("Player 2 wins!");
            }break;
            default:{}break;
        }
        ///We drop the scanner.
        scanner.close();
    }
    // public static <T> void drop(T t){
    //     t = null;
    // }
}




