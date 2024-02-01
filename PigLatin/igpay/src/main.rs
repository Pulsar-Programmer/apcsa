fn main() {
    println!("Hello, world!");
    println!("Pig Latin - {} {}", to_pig_latin("Pig"), to_pig_latin("Latin"))
}

fn to_pig_latin(str: &str) -> String{
    if str.is_empty() {return String::new()};
    
    if "aieuo".contains(str.chars().next().unwrap()){
        pig_latin_vowel(str)
    }
    else{
        pig_latin_otherwise(str)
    }
}

fn pig_latin_vowel(str: &str) -> String{
    str.to_owned() + "way"
}

fn pig_latin_otherwise(string: &str) -> String{
    let mut string = string.to_owned();
    // ___ __ 
    let uppercase = (string.as_bytes()[0] as char).is_uppercase();
    string = string.to_lowercase();
    let mut portion = 0;
    for (u, i) in string.chars().enumerate(){
        if "aieuo".contains(i){
            portion = u;
            break;
        }
    }
    // println!("{portion}");
    let slice: String = string.drain(0..portion).collect();
    string.push_str(&slice);
    if uppercase{
        let uppercase = string.remove(0).to_uppercase().last().unwrap();
        string.insert(0, uppercase);
    }
    string.push_str("ay");
    string
}