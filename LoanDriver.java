/*
 * Author: Suki Sahota
 * Description: Simple Loan OOP Practice Driver
 */
import java.util.*;
public class LoanDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println();

        //prompt user for their name
        System.out.print("Hello, what might your name be stranger? ");
        String userName = input.nextLine();
        System.out.println(userName + ", welcome to the simple loan program. This is where you can come to compare your current loan to loans of different structure. I understand you'd like to create some loans and shop around. How many loans would you like to create today?");
        int numberOfLoans;
        loop:
        while (true) {
            if (input.hasNextInt()) {
                numberOfLoans = input.nextInt();
                break loop;
            } else {
                System.out.println("I'm sorry, but the input you provided was not valid. Please enter a natural number (a positive integer).");
                //flush the buffer from the Scanner
                input.next();
            }
        }
        //create (loan object type) array of size numberOfLoans
        Loan[] loans = new Loan[Math.abs(numberOfLoans)];
        //declare user's current loan with no parameters
        Loan myLoan = new Loan();
        System.out.println();

        //prompt user for loan information and then use setter method to set current loan information
        System.out.println("Before we begin, please enter your current loan information so we can compare later.");
        System.out.println("How much money did you borrow on the loan you have now? ");
        double myAmount = input.nextDouble();
        System.out.println("What interest rate is your current loan? ");
        double myRate = input.nextDouble();
        System.out.println("And finally, for how many years did you finance? ");
        int myYears = input.nextInt();
        //use setter method to create user's current loan
        myLoan.setLoan(myRate, myYears, myAmount);
        System.out.println("Thank you for that. Let's begin creating our loans now.");
        System.out.println();
        
        //populate loans array with Loan objects
        population(loans, input);
        System.out.println();
        
        //check to see if any of the loans entered are the same
        isUnique(loans);
        //show alert if any of the loans entered match the user's current loan
        int j = 0;
        for(Loan loan : loans) {
            j++;
            if (myLoan.equals(loan)) {
                System.out.println("Alert: Loan " + j + " is the exact same as your current loan!");
            }
        }
        System.out.println();

        //summarize the loan information from the array loans
        double currentMonthlyPayment = myLoan.getMonthlyPayment();
        int loansLength = loans.length;
        int[] similar = quickSummary(loans, currentMonthlyPayment, loansLength);

        for (int i = 0; i < similar.length; i++) {
            if (similar[i] == 1) {
                System.out.println("It looks like Loan " + (i + 1) + " has a similar monthly payment as your current loan.");
            }
        }
        //prompt user to see if they would like more information on a particular loan
        System.out.print("If you would like a particular loan's full information, please type the integer value that corresponds to that loan now or press 0 to exit: ");
        int loanOfInterest = input.nextInt();
        if (loanOfInterest > 0 && loanOfInterest <= loansLength) {
            loanOfInterest--;
            System.out.println(loans[loanOfInterest].toString());
        }

        //exit message
        System.out.println();
        System.out.println("Thank you for stopping by " + userName + ", goodbye now.");
    }


    //method to populate loans based off of user input
    public static void population(Loan[] loans, Scanner input) {
        double a, r;
        int y;
        for (int i = 0; i < loans.length; i++) {
            System.out.print("Enter the amount for loan " + (i + 1) + ": ");
            a = input.nextDouble();
            System.out.print("Enter the rate for loan " + (i + 1) + ": ");
            r = input.nextDouble();
            System.out.print("Enter the number of years for loan " + (i + 1) + ": ");
            y = input.nextInt();
            //create a loan object
            loans[i] = new Loan(r, y, a);
            System.out.println();
            System.out.println();
        }
    }

    //method to display quick summary of monthly debt service
    public static int[] quickSummary(Loan[] loans, double currentMonthlyPayment, int loansLength) {
        int[] similar = new int[loansLength];
        double monthlyPayment, monthlyInterestExpense;
        for (int i = 0; i < loans.length; i++) {
            monthlyPayment = loans[i].getMonthlyPayment();
            monthlyInterestExpense = loans[i].getMonthlyInterestExpense();
            System.out.println("For loan " + (i + 1) + ": ");
            System.out.printf("Your loan's monthly payment is $" + "%.2f\n", monthlyPayment);
            System.out.printf("And your loan's monthly interest expense is $" + "%.2f\n", monthlyInterestExpense);
            System.out.println();
            if (30 + monthlyPayment > currentMonthlyPayment && currentMonthlyPayment > monthlyPayment - 30) {
                similar[i] = 1;
            }
        }
        return similar;
    }
    
    //method to check uniqueness of loans
    public static void isUnique(Loan[] loans) {
        boolean comp = false;
        for (int i = 0; i <= loans.length - 2; i++) {
            for (int j = 1; j < loans.length; j++) {
                if (loans[i].equals(loans[j + i])) {
                    comp = true;
                }
            }
        }
        if (comp) {
            System.out.println("One or more of the loans you entered are the exact same!");
        }
    }
}
