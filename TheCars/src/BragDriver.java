
///Note: Triple comments are meant to be read.
//Note: Double comments are for me and not meant to be read.
public class BragDriver {
    public static void main(String[] args) throws Exception {

        Hand supermans_hand = new Hand(new Card(14, Card.DIAMONDS), new Card(14, Card.CLUBS), new Card(14, Card.HEARTS));
        Hand supermans_second_hand = new Hand(new Card(14, Card.DIAMONDS), new Card(14, Card.DIAMONDS), new Card(14, Card.DIAMONDS));

        Hand diamond_flush = new Hand(new Card(10, Card.DIAMONDS), new Card(3, Card.DIAMONDS), new Card(4, Card.DIAMONDS));
        Hand primal = new Hand(new Card(3, Card.CLUBS), new Card(3, Card.HEARTS), new Card(3, Card.SPADES));

        Hand monkie = new Hand(new Card(10, Card.DIAMONDS), new Card(11, Card.DIAMONDS), new Card(12, Card.DIAMONDS));
        Hand monkie_no_flush = new Hand(new Card(10, Card.DIAMONDS), new Card(11, Card.CLUBS), new Card(12, Card.DIAMONDS));

        Hand mason = new Hand(new Card(8, Card.DIAMONDS), new Card(6, Card.HEARTS), new Card(4, Card.CLUBS));
        Hand mason_duplicate = new Hand(new Card(10, Card.DIAMONDS), new Card(4, Card.HEARTS), new Card(12, Card.CLUBS));

        
        //Case 1:
        playScenario(supermans_hand, supermans_second_hand, 1);
        //Case 2:
        playScenario(diamond_flush, diamond_flush, 2);
        //Case 3:
        playScenario(diamond_flush, supermans_hand, 3);
        //Case 4:
        playScenario(primal, diamond_flush, 4);
        //Case 5:
        playScenario(monkie, monkie_no_flush, 5);
        //Case 6:
        playScenario(diamond_flush, monkie, 6);
        //Case 7:
        playScenario(supermans_hand, monkie_no_flush, 7);
        //Case 8:
        playScenario(primal, monkie, 8);
        //Case 9:
        playScenario(primal, mason_duplicate, 9);
        //Case 10:
        playScenario(mason, mason_duplicate, 10);








        ///Ignore this code, as it contributes no value to comprehension.
        // for (var i = 1; i <= 10; i++){
        //     playScenario(Hand.deal_by_rand(), Hand.deal_by_rand(), i);
        // }
    }
    ///This plays a scenario given two Hands.
    public static void playScenario(Hand hand1, Hand hand2, int round) {
        System.out.println("Playing Scenario #" + round + " ...\n - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("Hand 1" + " - " + hand1.value() + ": \n" + hand1);
        System.out.println("Hand 2" + " - " + hand2.value() + ": \n" + hand2);

        int result = hand1.compareTo(hand2);

        if (result > 0)
        System.out.println("Hand 1 wins by " + result + "!");
        else if (result < 0)
        System.out.println("Hand 2 wins by " + -result + "!");
        else
        System.out.println("It was a tie!");
    }
}





//You are responsible [for] creating 10 different scenarios and testing them.


