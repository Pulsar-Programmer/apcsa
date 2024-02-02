fn main() {
    println!("Hello, world!");
    let arr: Vec<i32> = (1..=10).map(|x|x.pow(2)).collect();
}

struct PenHouse{
    posts: u32,

}


struct FarmAnimal{
    weight: f64,
    gender: bool,
    pos: (i32, i32),
    species: String,
}
impl FarmAnimal{
    fn eat(&mut self, inner: f64) -> f64{
        todo!() 
    }

    fn mate(&mut self, rhs: Self) -> Option<Self>{
        todo!();
    }

    fn slaughter(self) -> (f64,){
        todo!()
    }

    fn move_pos(&mut self, (x, y) : (i32, i32)){
        todo!()
    }
}