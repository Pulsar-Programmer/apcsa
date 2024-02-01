///This class represents a Card.
public class Card{
    ///This class has associated constants to assist with suite symbols. 
    final static char HEARTS = '♥';
    final static char SPADES = '♠';
    final static char CLUBS = '♣';
    final static char DIAMONDS = '♦'; 
    ///This class has the fields of rank and suite.
    int rank;
    char suite;

    ///Returns the suite of the Card.
    public char getSuite() {
        return suite;
    }
    ///Sets the suite to the provided value. However, some set values inputted will be invalid.
    public void setSuite(char suite) {
        this.suite = suite;
    }
    ///Returns the rank of the Card.
    public int getRank() {
        return rank;
    }
    ///Sets the rank of the Card to the provided value. However, some set values inputted will be invalid.
    public void setRank(int rank) {
        this.rank = rank;
    }


    ///This constructs a Card using the following values.
    public Card(int rank, char suite) {
        this.rank = rank;
        this.suite = suite;
    }
    ///This constructs a Card from the rank only.
    public Card(int rank){
        this.suite = (new Card()).suite;
        this.rank = rank;
    }
    ///This constructs a Card from the suite only.
    public Card(char suite){
        this.rank = (new Card()).rank;
        this.suite = suite;
    }
    ///Constructs a default, randomly-generated Card.
    public Card(){
        var suite = (int) (Math.random() * 4);
        switch (suite){
            case 0: {
                this.suite = DIAMONDS;
                break;
            }
            case 1: {
                this.suite = HEARTS;
                break;
            }
            case 2: {
                this.suite = SPADES;
                break;
            }
            case 3: {
                this.suite = CLUBS;
                break;
            }
        }
        this.rank = (int)(Math.random() * 12) + 2;
    }

    ///Returns the rank, converted as a char for viewing pleasure.
    public char getRankAsChar(){
        return rank_to_char(this.rank);
    }
    ///Converts a given integer to a char, given it is a rank.
    public static char rank_to_char(int rank){
        switch (rank){
            case 10:{
                return 'T';
            }
            case 11:{
                return 'J';
            }
            case 12:{
                return 'Q';
            }
            case 13:{
                return 'K';
            }
            case 14:{
                return 'A';
            }
            default:{
                return (char) ('0' + rank);
            }
        }
    }
    ///Returns a String representing the data of the Card.
    public String toString(){
        return "The card is of suite " + this.suite + " and of rank " + this.getRankAsChar() + ".";
    }

    ///Returns the color where red is true and black is false.
    public boolean getColor() {
        return this.suite == DIAMONDS || this.suite == HEARTS;
    }
}