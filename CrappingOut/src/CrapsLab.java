public class CrapsLab {
    public static void main(String[] args) throws Exception {
        System.out.println("Do not play.");
        System.out.println("How do you want to play?");
        System.out.println("1. Play until loss or leave.");
        System.out.println("2. Run a simulation of multiple games and return the results. (Don't worry it won't count!)");
        ///Initialize the console.
        final var console = new java.util.Scanner(System.in);
        ///Manage the game type.
        final var game = console.nextInt();
        ///Match on the game type.
        switch (game){
            ///If game type is 1, we play the game with money.
            case 1: {
                ///Interpret the money.
                System.out.println("How much money do you want to donate to the poor casino?");
                var money_total = console.nextDouble();
                ///Checks whether the money is valid or not.
                if (money_total <= 0){
                    System.out.println("You are banned from the casino for this outrageous, ravage behavior!");
                    console.close(); return;
                }
                ///Initialize amount of money that will be bet each round.
                double money_burned;
                ///Enter the first loop of all rounds.
                do {
                    ///Check if the user wants to play.
                    System.out.println("Do you still want to play? (true or false)");
                    if (!console.nextBoolean()){
                        System.out.println("You have a total of " + money_total + "!");
                        System.out.println("No burning money for you! Goodbye!");
                        console.close(); return;
                    }
                    else{
                        System.out.println("Yay!");
                    }
                    ///Find the money wanted to bet this round.
                    System.out.println("How much money do you want to bet this round?");
                    money_burned = console.nextDouble();
                    ///These two if statements are used to ensure that the money bet is less than the total money and greater than zero.
                    if (money_burned > money_total){
                        System.out.println("You silly guy! Don't gamble more than you can afford! Try gambling again!");
                        continue;
                    }
                    if (money_burned <= 0){
                        System.out.println("No ravage behavior! Try gambling again!");
                        continue;
                    }
                    ///Is the bet determined. As in, should I quit the loop?
                    var bet_determined = false;
                    ///Is this the initial round? This changes some of the game mechanics if so.
                    var initial_round = true;
                    ///What is the established point between loops?
                    var established_point = 0;
                    ///Enter the loop of the round. The round loops each time an established point is made.
                    do {
                        ///Roll all the dice for this round.
                        final var dice1 = (int)(Math.random() * 6 + 1);
                        final var dice2 = (int)(Math.random() * 6 + 1);
                        final var dicesum = dice1 + dice2;
                        ///If it is the initial round, we change some game mechanics/
                        if (initial_round){
                            ///Match on the sum of the die.
                            switch (dicesum) {
                                ///If a 7 or 11 initially, you win your bet.
                                case (7): case (11): {
                                    System.out.println("You rolled a(n) " + dicesum + " and won!");
                                    money_total += money_burned;
                                    System.out.println("You now have $" + money_total);
                                    bet_determined = true;
                                    break;
                                }
                                ///Rolling a 2, 3, or 12, means you lose your bet and `crap out`.
                                case (2): case (3): case (12): {
                                    System.out.println("You crapped out on a " + dicesum + " !");
                                    money_total -= money_burned;
                                    System.out.println("You now have $" + money_total);
                                    bet_determined = true;
                                    break;
                                }
                                ///Otherwise we go on.
                                default: {
                                    System.out.println("You rolled a(n) " + dicesum);
                                    established_point = dicesum;
                                    initial_round = false;
                                    break;
                                }
                            }
                        }
                        ///If the round is not in its first portion, we do the regular, looping rules.
                        else{
                            ///You lose your bet when 7.
                            if (dicesum == 7){
                                System.out.println("You rolled a(n) " + dicesum + " and lost!");
                                money_total -= money_burned;
                                System.out.println("You now have $" + money_total);
                                bet_determined = true;
                                break;
                            }
                            ///You must roll your previous sum to win.
                            else if (dicesum == established_point){
                                System.out.println("You rolled a(n) " + dicesum + " and won!");
                                money_total += money_burned;
                                System.out.println("You now have $" + money_total);
                                bet_determined = true;
                                break;
                            }
                            ///Establish your new point otherwise.
                            else{
                                System.out.println("You rolled a(n) " + dicesum);
                                established_point = dicesum;
                            }
                        }
                        ///Stop the round if the bet is determined.
                    } while (!bet_determined);
                    ///You must stop if you run out of money.
                } while(money_total > 0);
                System.out.println("You ran out of money and were forced to stop playing!");
                console.close(); return;
            }
            ///If the game type is 2, we play the game as a sim.
            case 2: {
                ///Find the amount of games to simulate.
                System.out.println("How many games would you like to simulate?");
                final var games_simulating = console.nextInt();
                ///You cannot simulate less than or equal to zero games.
                if (games_simulating <= 0){
                    System.out.println("No, that is far too challenging.");
                    console.close(); return; 
                }
                ///Establish statistics we must keep track of.
                int num_wins = 0;
                ///Loop over each game.
                for(var i = 0; i < games_simulating; i++){
                    ///Is the bet determined. As in, should I quit the loop?
                    var bet_determined = false;
                    ///Is this the initial round? This changes some of the game mechanics if so.
                    var initial_round = true;
                    ///What is the established point between loops?
                    var established_point = 0;
                    ///Enter the loop of the round. The round loops each time an established point is made.
                    do {
                        ///Roll all the dice for this round.
                        final var dice1 = (int)(Math.random() * 6 + 1);
                        final var dice2 = (int)(Math.random() * 6 + 1);
                        final var dicesum = dice1 + dice2;
                        ///If it is the initial round, we change some game mechanics/
                        if (initial_round){
                            ///Match on the sum of the die.
                            switch (dicesum) {
                                ///If a 7 or 11 initially, you win your bet.
                                case (7): case (11): {
                                    num_wins++;
                                    bet_determined = true;
                                    break;
                                }
                                ///Rolling a 2, 3, or 12, means you lose your bet and `crap out`.
                                case (2): case (3): case (12): {
                                    bet_determined = true;
                                    break;
                                }
                                ///Otherwise we go on.
                                default: {
                                    established_point = dicesum;
                                    initial_round = false;
                                    break;
                                }
                            }
                        }
                        ///If the round is not in its first portion, we do the regular, looping rules.
                        else{
                            ///You lose your bet when 7.
                            if (dicesum == 7){
                                bet_determined = true;
                                break;
                            }
                            ///You must roll your previous sum to win.
                            else if (dicesum == established_point){
                                num_wins++;
                                bet_determined = true;
                                break;
                            }
                            ///Establish your new point otherwise.
                            else{
                                established_point = dicesum;
                            }
                        }
                        ///Stop the round if the bet is determined.
                    } while (!bet_determined);
                }
                ///Report the stats of the game when done.
                int num_losses = games_simulating - num_wins;
                System.out.println(num_wins / games_simulating + " (" + num_wins + ") Wins");
                System.out.println(num_losses / games_simulating + " (" + num_losses + ") Losses");
            }
        }
        console.close();
    }
}
