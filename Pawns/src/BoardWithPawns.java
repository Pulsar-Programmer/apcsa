import java.util.Arrays;

public class BoardWithPawns {
    private char[][] board; ///board[y][x] - y value increases with depth while x value increases with rightwards distance
    private boolean turn; ///True => X; False => O;

    public char[][] getBoard() {
        return board;
    }
    public void setBoard(char[][] inner) {
        this.board = inner;
    }
    public boolean isTurn() {
        return turn;
    }
    public void setTurn(boolean x_turn) {
        this.turn = x_turn;
    }
    public BoardWithPawns(char[][] inner, boolean x_turn) {
        this.board = inner;
        this.turn = x_turn;
    }
    public BoardWithPawns(int len) {
        turn = true;
        board = board_with_len(len);
    }
    public BoardWithPawns() {
        turn = true;
        board = board_with_len(8);
    }
    ///Resets the board to its default state.
    public void reset() {
        turn = true;
        board = board_with_len(8);
    }
    ///Creates and returns a board with a given length.
    private static char[][] board_with_len(int len){
        var array = new char[len][len];
        for(var i = 1; i < len - 1; i++){
            Arrays.fill(array[i], '.');
        }
        Arrays.fill(array[len - 1], 'X');
        Arrays.fill(array[0], 'O');
        return array;
    }
    ///Stringifies.
    @Override
    public String toString() {
        ///We create a string with two spaces.
        var string = "  ";
        for(var num = 0; num < board.length; num++) {
            ///We loop over the length to produce the first pattern.
            string += " " + num;
        }
        for(var i = 0; i < board.length; i++) {
            ///We loop over the length to add the number of each row.
            string += "\n" + i + " ";
            ///We add each entry.
            for(var j = 0; j < board[i].length; j++) {
                string += " " + board[i][j];
            }
        }
        ///We add the name of the turn.
        string += "\n" + (turn ? "X's Turn" : "O's Turn");
        return string;
    }



    ///Randomizes the board a bit.
    public void randomize(){
        ///We first reset the board.
        board = new char[board.length][board.length];
        ///We fill every row with the '.' character.
        for(var i = 0; i < board.length; i++){
            Arrays.fill(board[i], '.');
        }
        ///We now loop over each column and generate two random numbers.
        for(var column = 0; column < board.length; column++){
            ///For each random number, we place the X or the O in the new generated spot.
            final var r1 = (int) (Math.random() * 2);
            board[board.length - 1 - r1][column] = 'X';
            final var r2 = (int) (Math.random() * 2);
            board[r2][column] = 'O';
        }
    }
    ///This moves the pawn in the row.
    public boolean movePawn(int col){
        ///We obtain the location of the next valid move in the column.
        var y = is_valid_move(col);
        ///If there is an error we must communicate this.
        if(y == -1) return false;
        ///We get the move type from the pawn checker.
        var move = is_valid_pawn(col, y);
        ///We match on the variants of `move`.
        switch(move){
            case 1:{
                ///In this case, we simply move them up once.
                move_pawn(col, y, col);
                return true;
            }
            case 2:{
                ///In this case, we move them to the global left.
                move_pawn(col, y, col - 1);
                return true;
            }
            case 3:{
                ///In this case, we move them to the global right.
                move_pawn(col, y, col + 1);
                return true;
            }
            default:{
                ///Otherwise, nothing can be done.
                return false;
            }
        }
    }
    ///This generates a random value and attempts to play it.
    public void randomPlay(){
        int rand = (int) (Math.random() * board.length);
        while(!movePawn(rand)){
            rand = (int) (Math.random() * board.length);
        }
    }
    
    ///We attempt to access the specified indices. If no luck, we just count them as empty.
    private char try_access(int y, int x){
        try{
            return board[y][x];
        }
        catch(Exception e) {
            return '.';
        }
    }

    ///We access the requested column, returning a vector of `char`s. DO NOT MUTATE DIRECTLY -> CHANGES DO NOT CARRY OVER.
    private char[] access_column(int x){
        char[] array = new char[board.length];
        for (int i = 0; i < board.length; i++) {
            array[i] = board[i][x];
        }
        return array;
    }
    ///We access the requested row.
    private char[] access_row(int y){
        return board[y];
    }

    ///States are denoted below. We check whether a pawn can move or not.
    ///0 -> Pawn can do none.
    ///1 -> Pawn can go forward.
    ///2 -> Pawn can move to the objective left.
    ///3 -> Pawn can move to the objective right.
    private int is_valid_pawn(final int x, final int y){
        ///We find where a supposed barrier should be.
        final var barrier = y + nudge();
        ///We find the opposite player.
        var opp = turn ? 'O' : 'X';
        ///We obtain the global left and global right conditions.
        ///Remember try_access returns nothing if it is out of bounds, allowing us to make the inference that it will work on edges.
        var objective_left = try_access(barrier, x - 1) == opp;
        var objective_right = try_access(barrier, x + 1) == opp;
        ///We return the respective codes.
        if(objective_left){
            return 2;
        }
        if(objective_right){
            return 3;
        }

        ///If the place where the barrier is supposed to be is clear, then the move must be valid!
        ///This is, of course, unless the barrier forward is the wall, which would mean the player won.
        if(try_access(barrier, x) == '.'){
            return 1;
        }

        ///If, through all our efforts, we cannot prove it valid, then it must be invalid.
        return 0;
    }
    
    ///Checks whether a move is valid with the current board for the current turn.
    private int is_valid_move(final int x){
        ///We access the specified column for read privelages only.
        var column = access_column(x);
        if (turn) {
            ///We are looking for the smallest y-value for furthest pawn.
            for(var i = 0; i < column.length; i++){
                if(column[i] == 'X') {
                    ///We ensure the pawn is valid and return its index.
                    if(is_valid_pawn(x, i) >= 1) {
                        return i;
                    }
                }
            }
        } else{
            ///We are looking for the largest y-value for furthest pawn.
            for(var i = column.length - 1; i >= 0; i--){
                if(column[i] == 'O') {
                    ///We ensure the pawn is valid and return its index.
                    if(is_valid_pawn(x, i) >= 1) {
                        return i;
                    }
                }
            }
        }
        ///If, through all our efforts, we cannot prove it valid, then it must be invalid.
        return -1;
    }

    ///Checks for a stalemate on the board for the current turn.
    private boolean is_stalemate(){
        ///We loop through ...
        for(var i = 0; i < board.length; i++){
            ///... each possible move shown here.
            if(is_valid_move(i) != -1) {
                ///If, at all, any move here is valid, we can deduce that a stalemate cannot occur.
                return false;
            }
        }
        ///If, through all our efforts, we cannot prove any move valid, then there must be a stalemate.
        return true;
    }


    ///Make sure not to call this function on invalid values.
    private void move_pawn(int x, int y, int target_x){
        ///We move the pawn to the new value.
        board[y + nudge()][target_x] = board[y][x];
        ///And replace its previous value with the none.
        board[y][x] = '.';
    }

    ///This serves as a `nudge` depending on whose turn it is.
    private int nudge(){
        return turn ? -1 : 1 ;
    }

    ///States are indicated below. Checks for end events within the game.
    ///0 -> None.
    ///1 -> Stalemate.
    ///2 -> `X` wins.
    ///3 -> `O` wins.
    public int has_won(){

        ///If X is at the end.
        if(contains(access_row(0), 'X')){
            return 2;
        }
        ///If O is at the end.
        if(contains(access_row(board.length - 1), 'O')){
            return 3;
        }

        ///If there are no pieces for X.
        if(!double_contains(board, 'X')){
            ///O wins.
            return 3;
        }

        ///if there are no pieces for O.
        if(!double_contains(board, 'O')){
            ///X wins.
            return 2;
        }

        if(is_stalemate()) return 1;

        return 0;
    }

    ///We use this as a quick contains method.
    public static boolean contains(char[] arr, char want){
        ///If what we want is in here, we indicate so.
        for(var i = 0; i < arr.length; i++){
            if(arr[i] == want) return true;
        }
        return false;
    }

    ///We use this as a quick "deep" contains method.
    public static boolean double_contains(char[][] arr, char want){
        ///If what we want is in here, we indicate so.
        for(var i = 0; i < arr.length; i++){
            for(var j = 0; j < arr.length; j++){
                if(arr[i][j] == want) return true;
            }
        }
        return false;
    }
    
}