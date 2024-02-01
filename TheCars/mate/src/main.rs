use rand::Rng;
///Call this main class BragDriver
fn main() {
    let (hand1, hand2) = (Hand::deal_by_rand(), Hand::deal_by_rand());
    let result = hand1.compare_to(&hand2);
    
}



const HEARTS: char = '♥';
const SPADES: char = '♠'; 
const CLUBS: char = '♣';
const DIAMONDS: char = '♦';
#[derive(Debug, PartialEq, Eq, Clone)]
struct Card{
    rank: i32,
    suite: char,
}
impl Card{

    fn from_rank(rank: i32) -> Self{
        Self { rank, ..Card::new_random() }
    }
    fn from_suite(suite: char) -> Self{
        Self { suite, ..Card::new_random() }
    }
    fn from_rank_and_suite(rank: i32, suite: char) -> Self{
        Self{rank, suite}
    }
    fn new_random() -> Self{
        let suite = rand::thread_rng().gen_range(0..4);
        let suite = match suite{
            0 => HEARTS,
            1 => CLUBS,
            2 => DIAMONDS,
            3 => SPADES,
            _ => 'e', //ERROR char
        };
        Self { rank: rand::thread_rng().gen_range(2..=14), suite }
    } 


    fn to_string(&self) -> String{
        format!("The card is of suite {} and of number {}.", self.suite, self.get_rank_as_char())
    }


    fn get_rank_as_char(&self) -> char{
        match self.rank{
            // 1 => 'A',
            10 => 'T',
            11 => 'J',
            12 => 'Q',
            13 => 'K',
            14 => 'A',
            c => c.to_string().pop().unwrap(),
        }
    }
    ///Returns the color where red is true and black is false.
    fn get_color(&self) -> bool{
        match self.suite{
            DIAMONDS => true,
            CLUBS => false,
            HEARTS => true,
            SPADES => false,
            _ => false // error in conversion
        }
    }
    fn get_rank_as_i32(&self) -> i32{
        self.rank
    }
    fn get_suite_as_char(&self) -> char{
        self.suite
    }
    fn set_suite(&mut self, suite: char){
        self.suite = suite;
    }
    fn set_rank(&mut self, rank: i32){
        self.rank = rank;
    }


}
impl Default for Card{
    fn default() -> Self {
        Self::new_random()
    }
}
#[derive(Default, Debug)]
struct Hand{
    cards: [Card;3],
}
impl Hand{
    fn get_card_1(&self) -> &Card{
        &self.cards[0]
    }
    fn get_card_2(&self) -> &Card{
        &self.cards[1]
    }
    fn get_card_3(&self) -> &Card{
        &self.cards[2]
    }
    fn from_cards(cards: [Card;3]) -> Self{
        Self{cards}
    }

    fn fold(self){
        drop(self);
    }
    fn equals(&self, rhs: &Self) -> bool{
        self.value() == rhs.value()
    }
    fn value(&self) -> String{
        let mut string = String::from("High Card");
        todo!()
    }
    fn value_quantify(&self) -> i32{
        let t = self.value();
        todo!()
    }
    fn compare_to(&self, rhs: &Self) -> i32{
        self.value_quantify() - rhs.value_quantify()
    }
    //make this private in java
    fn all_unique(&self) -> bool{
        self.cards[0] == self.cards[1] || self.cards[0] == self.cards[2] || self.cards[2] == self.cards[1]
    }


    ///Change this to deal() with overriding in java
    fn deal_by_rand() -> Self{
        let dealing = Self::default();
        Self::deal(dealing.cards)
    }
    fn deal(cards: [Card; 3]) -> Self{
        let construction = Self::from_cards(cards);
        if construction.all_unique(){
            return Self::deal_by_rand();
        }
        construction
    }
    
}


// enum Value{
//     HighCard,

// }

/*


- value – refer to Wikipedia for the different RANKS of combos this should return a String
(e.g. Pair;K or Straight Flush;J) use a semicolon for a delimeter.
- compareTo. Compares to another Hand and see who has a better hand. How far away
one hand is from the other (e.g. three of a kind compared to flush should return 3,
return 0 if they’re the same and return 10 if you won by a tie and -10 if you lost by a tie)
If the colors of the cards are ever the same then that will beat ties (THAT’S A CUSTOM
MOURADOV RULE)








*/