import java.util.Scanner;

public class PawnDriver {
    public static void main(String[] args) throws Exception {

        ///We initialize the scanner.
        var scanner = new Scanner(System.in);

        ///We query the length of the board.
        System.out.println("How big is the board?");
        BoardWithPawns board = new BoardWithPawns(scanner.nextInt());
        scanner.nextLine();

        ///We check whether the board can be randomized and randomize it if the player agrees.
        if(board.getBoard().length >= 4){
            System.out.println("Do you want to randomize the board? (true/false)");
            if(scanner.nextBoolean()){
                board.randomize();
            }
            scanner.nextLine();
        }
        ///We make the game automated if requested.
        System.out.println("Do you want the game to be roboticized? (true/false)");
        boolean is_robot = scanner.nextBoolean(); scanner.nextLine();
        
        ///We ensure no events are occuring with `has_won`.
        while (board.has_won() == 0) {
            ///We print the game each round and split the cases.
            System.out.println(board.toString());
            if (!is_robot) {
                ///We obtain the requested column.
                int column = scanner.nextInt(); scanner.nextLine();
                ///We communicate if the move hasn't been played.
                if (!board.movePawn(column)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            } else {
                ///We sleep.
                try {
                    Thread.sleep(750); ///zzzZZZZZ
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ///We randomly play the board.
                board.randomPlay();
            }
            ///We toggle/invert the turn.
            board.setTurn(!board.isTurn());
        }

        ///We drop the scanner.
        scanner.close();

        ///We print the board one last time.
        System.out.println(board.toString());

        ///We match on the winning case.
        switch(board.has_won()){
            case 1:{
                System.out.println("Stalemate..!");
                break;
            }
            case 2: {
                System.out.println("X wins!");
                break;
            }
            case 3: {
                System.out.println("O wins!");
                break;
            }
            default: {
                break;
            }
        }
    }
    
}
