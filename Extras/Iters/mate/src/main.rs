fn main() {
    println!("Hello, world!");
}


fn split(mut str: String, pat: String) -> Vec<String>{
    let mut vec = Vec::new();
    let mut window_start = 0;
    let mut window_end = pat.chars().count();
    while window_end <= str.chars().count() {
        let slice = str[window_start..window_end].to_string();
        if slice == pat {
            vec.push(str[0..window_start].to_string());
            str = str[window_end..].to_string();
        }
        window_start += 1;
        window_end += 1;
    }
    vec
}