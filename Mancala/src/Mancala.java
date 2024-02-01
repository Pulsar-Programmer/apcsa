
import java.util.Arrays;

public class Mancala {
    private int box1;
    private int box2;

    private int[] board;

    private boolean turn;

    public Mancala(){
        this.box1 = 0;
        this.box2 = 0;
        this.board = new int[12];
        Arrays.fill(board, 4);
        this.turn = false;
    }
    
    public void resetBoard(){
        this.box1 = 0;
        this.box2 = 0;
        this.board = new int[12];
        Arrays.fill(board, 4);
        this.turn = false;
    }

    public Mancala(int box1, int box2, int[] board, boolean turn) {
        this.box1 = box1;
        this.box2 = box2;
        this.board = board;
        this.turn = turn;
    }

    ///Returns 0 for nothing, 1 for tie, 2 for false win, 3 for true win.
    public byte has_won(){
        ///We check whether there exist seeds left on the side of the player.
        final var lacks_seeds = lacks_seeds();
        if (lacks_seeds){
            ///If we lack seeds, we must shuffle all the remaining seeds into the corresponding box.
            if(turn){
                ///If it is player 2's turn, we checked 6..=12 and thus we can shuffle all the remaining pieces into the p1s. 
                box1 += piecesLeft();
            } else {
                ///Same logic vice versa.
                box2 += piecesLeft();
            }
            ///If box2 is greater, true won, otherwise, either box1 (false) can win or it can result in a tie.
            if (box2 > box1){
                return 3;
            } else if (box1 > box2){
                return 2;
            } else{
                return 1;
            }
        } else{
            ///Otherwise, nothing has happened.
            return 0;
        }
    }

    ///Returns the amount of pieces left on the board.
    public int piecesLeft(){
        ///We declare a mutable sum.
        int sum = 0;
        ///We run through the board and sum all the values.
        for (var e: board){
            sum += e;
        }
        ///We return the inner sum.
        return sum;
    }

    ///This updates the game by one given move.
    public boolean makeMove(int value){
        ///If this variable is within player 1 or player 2, it should be valid.
        final var within_p2 = this.turn && value >= 6 && value < 12;
        final var within_p1 = !this.turn && value >= 0 && value < 6;
        ///Otherwise, we return an Err(()).
        if(!(within_p1 || within_p2)){
            return false;
        }
        ///If the value you have given us corresponds to a zero value, we can't do anything with it!
        if(this.board[value] == 0) { return false; }
        ///We start a counter to run through.
        var counter = 1;
        ///We make a while loop until we are out of seeds on the value.
        first: while(this.board[value] > 0){
            ///We make an index, and check whether it is the last value that exists.
            final int index = (value + counter) % 12;
            final var was_last = this.board[value] == 0;
            ///If it is 6 or 0, we have to rewind and give the bowl a chance to collect.
            if(index == 6 && !this.turn) {
                ///If it was last, then we must break the loop how it is to disallow taking more than we can afford.
                if(was_last){
                    break first;
                }
                ///Here, we move the value into the box when it passes.
                this.board[value] -= 1;
                this.box1 += 1;
                ///Here, we check if the value is now zero after this. If so, we can just return true and give another turn.
                if(this.board[value] == 0){
                    return true;
                }
            } else if(index==0 && this.turn){
                ///Do the same as before, just with box2.
                if(was_last){
                    break first;
                }
                this.board[value] -= 1;
                this.box2 += 1;
                if(this.board[value] == 0){
                    return true;
                }
            }

            ///We move the seed at the value to the desired index.
            this.board[value] -= 1;
            this.board[index] += 1;
            
            ///If, after this, the value is last, we must invoke our `customRule`.
            final var is_last = this.board[value] == 0;
            if (is_last) {
                ///We call customRule and break out of the loop since it is indeed last.
                customRule(index);
                break first;
            }
            ///We count up until the while loop disagrees.
            counter += 1;
        }
        ///We swap the turn and verify the move.
        this.turn = !this.turn;
        return true;
    }

    ///This is the invocation of the custom rule in Mancala.
    private void customRule(int index){
        ///An index is given, and with that we calculate that the opposite must be full and the turn must be in the range.
        final var opposite_is_empty = this.board[11 - index] == 0;
        final var turns_are_in_range = (this.turn && index >= 6 && index < 12) || (!this.turn && index >= 0 && index < 6);
        ///If all these are true including the fact that this occured on an empty container, we move to the main process.
        if(!opposite_is_empty && this.board[index] == 1 && turns_are_in_range) {
            ///We calculate the extra value.
            final var extra_value = this.board[11 - index] + 1;
            ///We then move it into the appropraite box.
            if (this.turn){
                this.box2 += extra_value;
            } else{this.box1 += extra_value;}
            ///Complete the movement.
            this.board[index] = 0;
            this.board[11 - index] = 0;
        }
    }

    ///We detect if zero seeds appear on either side.
    public boolean lacks_seeds(){
        ///We iterate over the respective sub-array and find zeros.
        var is_zer0 = true;
        final var sub_array = this.turn ? Arrays.copyOfRange(this.board, 6, 12): Arrays.copyOfRange(this.board, 0, 6);
        for(var i : sub_array){
            if(i != 0){
                is_zer0 = false;
                break;
            }
        }
        return is_zer0;
    }

    @Override
    public String toString() {
        ///We start with some spacing.
        var mancala = "\n\t";
        ///We loop over each index for the top.
        for (var i = 11; i >= 6; i--){
            mancala += board[i] + " ";
        }
        ///We create the middle section with the two box numbers.
        mancala += "\n Box2: " + box2 + "   \t   " +  box1 + " :Box1\n\t";
        ///Then for the bottom.
        for (var i = 0; i <= 5; i++){
            mancala += board[i] + " ";
        }
        mancala += "\t";
        ///We return our constructed String.
        return mancala;
    }   

    public int getBox1() {
        return box1;
    }

    public void setBox1(int box1) {
        this.box1 = box1;
    }

    public int getBox2() {
        return box2;
    }

    public void setBox2(int box2) {
        this.box2 = box2;
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }  
}







