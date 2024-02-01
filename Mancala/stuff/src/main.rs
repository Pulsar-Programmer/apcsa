fn main() {
    println!("Hello, world!");
    let mut set = Mancala{board:[4;12], turn:false, box1:0, box2:0};
    set.make_move(2);
    // set.make_move();
    set.make_move(2);
    set.make_move(2);
    set.make_move(9);
    while set.make_move(2){
        
    }
    println!("{set}");
}
// enum GameResult{
//     Win(bool),
//     Tie,
//     Lose,
// }
struct Mancala{
    board: [i32; 12],
    turn: bool,
    box1: i32,
    box2: i32,
}
impl Mancala{
    // fn has_won(&self) -> GameResult{
    //     let no_seeds: bool = todo!("Detect for all seeds.");
    //     if no_seeds{
    //         if self.box1 > self.box2{
    //             GameResult::Win(false)
    //         }
    //         else if self.box2 > self.box1{
    //             GameResult::Win(true)
    //         }
    //         else{
    //             GameResult::Tie
    //         }
    //     } else{GameResult::Lose}
    // }
    fn make_move(&mut self, value: i32) -> bool{
        let within_p2 = self.turn && (6..12).contains(&value);
        let within_p1 = !self.turn && (0..6).contains(&value);
        if !(within_p1 || within_p2){
            return false;
        }
        if self.board[value as usize] == 0 { return false; }
        let mut is_zer0 = true;
        for i in &self.board[if self.turn{6..12}else{0..6}]{
            if i != &0{
                is_zer0 = false;
                break;
            }
        }
        if is_zer0 {
            self.turn = !self.turn;
            return true;
        }
        let mut counter = 0;
        while self.board[value as usize] > 0{
            self.board[value as usize] -= 1;
            let index = (value + counter) % 12;
            let is_last = self.board[value as usize] == 0;
            if self.board[index as usize] == 0 && is_last && (self.turn && (6..12).contains(&index) || !self.turn && (0..6).contains(&index)) {
                let extra_value = self.board[11 - index as usize] + 1;
                if self.turn{
                    self.box2 += extra_value;
                } else{self.box1 += extra_value;}
                self.board[index as usize] = 0;
                self.board[11 - index as usize] = 0;
                break;
            }
            self.board[index as usize] += 1;
            if index == 5 && !self.turn {
                if is_last{
                    break;
                }
                self.board[value as usize] -= 1;
                self.box1 += 1;
                if self.board[value as usize] == 0{
                    return true;
                }
            } else if index==11 && self.turn{
                if is_last{
                    break;
                }
                self.board[value as usize] -= 1;
                self.box2 += 1;
                if self.board[value as usize] == 0{
                    return true;
                }
            }
            counter += 1;
        }
        self.turn = !self.turn;
        true
    }


}

impl std::fmt::Display for Mancala{
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        let mut mancala = format!("{}\n\t", self.turn);
        for i in (6..12).rev() {
            mancala.push_str(&format!("{} ", self.board[i]));
        }
        mancala.push_str(&format!("\n Box2: {}   \t   {} :Box1\n\t", self.box2, self.box1));
        for i in 0..6 {
            mancala.push_str(&format!("{} ", self.board[i]));
        }
        mancala.push('\t');

        f.write_str(&mancala)
    }
}