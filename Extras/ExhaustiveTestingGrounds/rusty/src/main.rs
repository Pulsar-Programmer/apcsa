fn main() {
    println!("helxxxxloxxx! : {}", no_x("helxxxxloxxx!".into()));
}

fn recursive_add(vec: Vec<i32>) -> i32{
    if vec.is_empty(){ return 0 };
    let mut vec = vec.clone();
    vec.pop().unwrap_or_default() + recursive_add(vec)
}

fn show_best_of_n(){

}







fn is_safe(board: &mut Vec<Vec<bool>>, i: usize, j: usize) -> bool{
    false
}



fn queens() -> Vec<Vec<bool>>{
    let mut board = vec![vec![false; 8]; 8];
    let column = 0;
    loop{
        if column == board.len(){
            break;
        }
        for i in 0..board.len(){
            // let mut none_safe = true;
            for j in 0..board[i].len(){
                if is_safe(&mut board, i, j){
                    
                }
            }
        }
    }
    board
}










struct SquareVec<T>{
    inner: Vec<Vec<T>>,
}
impl<T> SquareVec<T>{
    unsafe fn init(inner: Vec<Vec<T>>) -> Self{
        SquareVec{inner}
    }

    // fn column(&self, idx: usize) -> Vec<&T>{
    //     let mut collection = Vec::new();
    //     for i in 0..idx{
    //         for j in 0..idx{

    //         }
    //     }
    //     // self.inner.get(idx).unwrap_or(vec![])
    // }
//     fn row() -> Vec<T>{

//     }
}







fn no_x(str: String) -> String{
    let Some(first_char) = str.chars().next() else { return String::new() };
    let mut to_insert = if first_char == 'x'{
        String::new()
    } else {
        first_char.to_string()
    };
    to_insert.push_str(no_x(str[1..].to_string()).as_str());
    to_insert
}
