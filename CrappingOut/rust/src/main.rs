use std::io;
use rand::Rng;

fn main() {
    println!("Do not play.");
    println!("What do you want to play?");

    let mut input = String::new();
    let scanner = io::stdin();
    scanner.read_line(&mut input).unwrap_or_default();

    if input.trim().parse::<bool>().unwrap_or(false) {
        println!("Goodbye!");
        return;
    }

    println!("How much money did you bring with you?");
    let total_money: f64 = input.trim().parse().expect("Invalid input");

    println!("How much money do you want to donate to the poor casino?");
    let money_bet: f64 = input.trim().parse().expect("Invalid input");

    if money_bet > total_money {
        println!("You silly guy! Don't gamble more than you can afford!");
    }

    let dice1 = rand::thread_rng().gen_range(1..=6);
    let dice2 = rand::thread_rng().gen_range(1..=6);
    let dicesum = dice1 + dice2;

    match dicesum {
        7 | 11 => {
            // Code for case 7 and 11
        }
        2 | 3 | 12 => {
            // Code for case 2, 3, and 12
        }
        _ => {
            // Code for default case
        }
    }
}
