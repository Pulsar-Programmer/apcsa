public class Hand{
    ///This class as 3 fields.
    Card card1, card2, card3;
    ///This returns the first card.
    public Card getCard1() {
        return card1;
    }
    ///This returns the second card.
    public Card getCard2() {
        return card2;
    }
    ///This returns the third card.
    public Card getCard3() {
        return card3;
    }
    ///This sets the first card to the provided value.
    public void setCard1(Card card1) {
        this.card1 = card1;
    }
    ///This sets the second card to the provided value.
    public void setCard2(Card card2) {
        this.card2 = card2;
    }
    ///This sets the third card to the provided value.
    public void setCard3(Card card3) {
        this.card3 = card3;
    }
    ///Constructs a hand given all of its cards.
    public Hand(Card card1, Card card2, Card card3) {
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
    }
    ///Constructs a Hand given only one Card.
    public Hand(Card card1) {
        this.card1 = card1;
    }
    ///Constructs a default, randomly-generated Hand.
    public Hand(){
        this.card1 = new Card();
        this.card2 = new Card();
        this.card3 = new Card();
    }
    ///Drops the Hand from memory via estranging the owners of the fields.
    public void fold(){
        this.card1 = null;
        this.card2 = null;
        this.card3 = null;
    }
    ///Detects whether the values of the hands are exactly equal.
    public boolean equals(Hand rhs){
        return this.value().equals(rhs.value());
    }
    ///Detects whether there exists at least one duplicate within the Hand.
    public boolean oneDuplicate(){
        return this.card1.equals(this.card2) || this.card2.equals(this.card3) || this.card3.equals(this.card1);
    }
    ///Determines the value of the card; see below.
    public String value(){
        ///We construct the three cards from the three Cards of the Hand.
        Card
        card1 = this.getCard1(),
        card2 = this.getCard2(),
        card3 = this.getCard3();
        ///We get the three ranks, and then calculate the max of these three.
        int
        rank1 = card1.getRank(),
        rank2 = card2.getRank(),
        rank3 = card3.getRank(),
        rank_max = Math.max(Math.max(rank1 , rank2), rank3);
        ///We get the three suites of each respective Card.
        char
        suite1 = card1.getSuite(),
        suite2 = card2.getSuite(),
        suite3 = card3.getSuite();
        ///We calculate two booleans: whether the Hand represents a flush, or whether it represents a straight.
        ///We calculate the flush by checking whether all variables are equivalent.
        ///By transitivity, we can conclude that if suite1 = suite3, and suite1 = suite2, suite2 definitively = suite3.
        ///We calculate the straight by tripling the middle number (max - 1) which should return the same as the sum of the ranks of the Cards.
        boolean 
        isFlush =  
            suite1 == suite2 && suite1 == suite3,
        isStraight = rank1 + rank2 + rank3 == 3 * rank_max - 3;
            // rank1 + 2 == rank2 + 1 && rank2 + 1 == rank3 ||
            // rank2 + 2 == rank3 + 1 && rank3 + 1 == rank1 ||
            // rank3 + 2 == rank1 + 1 && rank1 + 1 == rank2;
        ///If it is both a flush and a straight, we return this.
        if (isFlush && isStraight)
        return "Straight Flush;" + Card.rank_to_char(rank_max) + "" + suite1;
        ///If the ranks have formed a three of a kind, we express this via the return value.
        if (rank1 == rank2 && rank1 == rank3)
        return "Prial;" + card1.getRankAsChar();
        ///If it is just a flush, we return this.
        else if (isFlush)
        return "Flush;" + suite1;
        ///Same here.
        else if (isStraight)
        return "Straight;" + Card.rank_to_char(rank_max);

        ///Depending on which two are equal, we generate a pair for each rank.
        if (rank1 == rank2 || rank3 == rank1)
        return "Pair;" + card1.getRankAsChar();
        else if (rank2 == rank3)
        return "Pair;" + card2.getRankAsChar();
        ///We later return High Card if no other buckets are filled, giving the maximum rank.
        return "High Card;" + Card.rank_to_char(rank_max);
    }
    ///Quantifies the precedence of the value of the Hand.
    public int value_quantify(){
        var value = this.value();
        ///If the value is a High Card, it has a precedence of 0.
        if (value.startsWith("High Card"))
        return 0;
        ///If the value is a High Card, it has a precedence of 0.
        if (value.startsWith("Pair"))
        return 1;
        ///If the value is a High Card, it has a precedence of 0.
        if (value.startsWith("Flush"))
        return 2;
        ///Etc. etc. etc. 
        if (value.startsWith("Straight Flush"))
        return 4;
        ///Etc. etc. etc. 
        if (value.startsWith("Straight"))
        return 3;
        ///Etc. etc. etc. 
        if (value.startsWith("Prial"))
        return 5;
        ///This should never be called, as it means one of the buckets hasn't been checked, but all of them have been.
        return -999999; //error
    }
    ///Subquantifies the value. This was later deprecated after learning the difference between ties of precedence is 10 or -10.
    public int value_subquantify(){
        ///We generate the value. If it is a flush, subprecedence is not relevant.
        var value = this.value();
        if (value.startsWith("Flush"))
        return 0;
        ///We get the subdata from the given value, and translate it to it num equivalent and return.
        char subdata = value.charAt(value.indexOf(';') + 1);
        switch (subdata) {
            case 'A':{
                return 14;
            }
            case 'K':{
                return 13;
            }
            case 'Q':{
                return 12;
            }
            case 'J':{
                return 11;
            }
            case 'T':{
                return 10;
            }
            default:{
                return subdata - '0';
            }
        }
    }
    ///This compares to values of a Hand.
    public int compareTo(Hand rhs){
        ///We create three integers, one for the value of the left hand side, and one for the value of the right hand side.
        ///Additionally, we need one for the difference between these two values.
        int
        value_quantify_lhs = this.value_quantify(),
        value_quantify_rhs = rhs.value_quantify(),
        value_difference = value_quantify_lhs - value_quantify_rhs;
        ///Next, we find the regular values of each.
        String
        value_lhs = this.value(),
        value_rhs = rhs.value();
        ///If these values are exactly equivalent, we must apply a special color rule.
        if (value_lhs.equals(value_rhs)){
            ///We find whether they qualify or not.
            boolean
            lhsColor = this.specialColorRule(),
            rhsColor = rhs.specialColorRule();
            ///If one qualifies, but the other doesn't, we return that.
            if (lhsColor && !rhsColor){
                return 10;
            }
            if (rhsColor && !lhsColor){
                return -10;
            }
            ///Otherwise, upon equal precedence, we return 0.
            return 0;
        }
        ///In the result of an exact tie in the difference of values, we must consider the (later deprecated) subquantification.
        if (value_difference == 0){
            ///We calculate the two subquantifications.
            int 
            value_subquantify_lhs = this.value_subquantify(),
            value_subquantify_rhs = rhs.value_subquantify();
            
            ///We return the difference. However, this was deprecated.
            ///So, we use the sign() function to 
            return (int)Math.signum((float)(value_subquantify_lhs - value_subquantify_rhs)) * 10;
        }

        ///Otherwise, we return the difference here.
        return value_difference;
    }
    ///Detects whether it qualifies for the special color rule.
    ///This rule states that equal colors implies higher precedence, which is implemented above as shown.
    public boolean specialColorRule(){
        boolean
        color1 = this.getCard1().getColor(),
        color2 = this.getCard2().getColor(),
        color3 = this.getCard3().getColor();

        return color1 == color2 && color1 == color3;
    }
    ///We deal randomly the fields of the Hand class.
    public static Hand deal_by_rand() {
        Hand construction = new Hand();
        return construction.deal();
    }
    ///We deal the cards given a Hand, checking whether there exist duplicates and later replacing them with random values if so.
    public Hand deal() {
        if (this.oneDuplicate()){
            return Hand.deal_by_rand();
        }
        return this;
    }
    ///We return a String relating the value of the inner fields.
    public String toString() {
        return "\tCard1: " + card1.toString() + "\n\tCard2: " + card2.toString() + "\n\tCard3: " + card3.toString();
    }
    
}