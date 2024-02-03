import java.util.Scanner;

public class TaxCalculator {
    public static void main(String[] args) {

        //Initialize Scanner.
        final Scanner scanner = new Scanner(System.in);
        
        //Interpret variables for tax calculation.
        System.out.println("What was your gross income?");
        final double gross_income = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Are you filing jointly? True or False?");
        final boolean if_marry = scanner.nextBoolean();
        scanner.nextLine();
        //`mul` is used here to scale the ranges and the deductions in the case of a joint filing.
        final double mul = if_marry ? 2.0 : 1.0;

        System.out.println("How many dependents do you have?");
        final int dependents = scanner.nextInt();
        scanner.nextLine();

        System.out.println("How much money did the government receive already from you?");
        final double tax_withheld = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("What is your deduction from taxes?");
        final double ded = scanner.nextDouble();
        scanner.nextLine();

        //Finally, drop the scanner.
        scanner.close();

        //Initialize deduction and obtain the maximum.
        final double std_deduction = 13850.0;
        final double deduction = Math.max(std_deduction, ded) * mul;

        //Define the ranges and their corresponding rate. 
        //We subtract the previous range here to make it easier to calculate later.
        //This is structured such that we can iterate both the bar (tax bracket) and rate.
        //Notice the `mul`s. They are used to scale the bars in the case of joint filing.
        final double[][] ranges = { 
            { 11000.0 * mul, 0.1 }, 
            { (44725.0 - 11000.0) * mul, 0.12 },
            { (95375.0 - 44725.0) * mul, 0.22 } 
        };
        
        //Deduct the deductions. Notice the omission of `final` here.
        //We cannot use `final` as we need these variables to be mutable.
        double remaining_taxable_income = gross_income - deduction;
        double total_gained_income = 0.0;
        
        //Iterate over each bar and rate.
        for (final double[] range : ranges) {
            //Define which parts of the vector is the `bar` and which is the `rate`.
            final double bar = range[0];
            final double rate = range[1];
            //If the bar is greater than or equal to the remaining taxable income,
            //we then need to stop,
            //because we know that we cannot take any more from this person.
            if (bar >= remaining_taxable_income) {
                total_gained_income += remaining_taxable_income * rate;
                remaining_taxable_income = 0.0;
                break;
            } else { 
                //Otherwise we can simply subtract and apply the rate to our gained income.
                remaining_taxable_income -= bar;
                total_gained_income += bar * rate;
            }
        }
        //We must deduce wether there is tax to be collected after the bars and rates.
        //We now apply the standard 0.35 rate to the rest of our money.
        if (remaining_taxable_income > 0.0) {
            total_gained_income += remaining_taxable_income * 0.35;
            //remaining_taxable_income = 0.0; //This should be done only IF you used this variable later.
        }

        //Subtract the dependents' taxes.
        total_gained_income -= 2000.0 * dependents;
        
        //You cannot possibly have the gov. pay you. It wants money.
        total_gained_income = Math.max(0.0, total_gained_income);

        //We finally calculate the absolute difference between the supposed gained income and the withheld taxes.
        final double number = total_gained_income - tax_withheld;
        if (number < 0) {
            System.out.println("We owe you $" + -number + "!!");
        } else {
            System.out.println("You owe us $" + number + "!!");
        }

    }
}

