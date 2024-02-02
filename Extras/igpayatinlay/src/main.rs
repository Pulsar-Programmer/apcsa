fn main() {
    println!("Hello, world!");
}

fn to_pig_latin(str: &str) -> String{
    if str.is_empty() {return String::new()};
    
    if "aieuo".contains(str.chars().nth(0).unwrap()){
        pig_latin_vowel(str)
    }
    else{
        pig_latin_otherwise(str)
    }
}

fn pig_latin_vowel(str: &str) -> String{
    str.to_owned() + "way"
}

fn pig_latin_otherwise(str: &str) -> String{
    let str = str.to_string();
    let mut string = String::new();
    // ___ __ 
    let mut portion = 0;
    for (u, i) in str.chars().enumerate(){
        if "aieuo".contains(i){
            portion = u;
            break;
        }
    }
    let portion = 0..portion;
    let pp = str.remove
    string.push_str("ay");
    string
}