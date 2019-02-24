/*
 * Author: Suki Sahota
 * Description: Simple Loan OOP Practice
 */
public class Loan {
    //instance variables
    private double rate = 0;
    private int years = 0;
    private double amount = 0;
    
    //constructor
    public Loan(double rate, int years, double amount) {
        this.rate = rate;
        this.years = years;
        this.amount = amount;
    }

    //no-argument constructor
    public Loan() {
        rate = 0;
        years = 1;
        amount = 0;
    }
    
    //setter
    public void setLoan(double rate, int years, double amount) {
        if (rate > 0) {
            this.rate = rate;
        } else if (rate == 0) {
            this.rate = rate;
            System.out.println("Warning: This is a free loan!");
        } else {
            System.out.println("The rate you entered does not make sense. Please try a non-negative value.");
        }
        if (years > 0) {
            this.years = years;
        } else {
            System.out.println("Because of the phenomena we call \"The Arrow of Time\", we cannot go back in time. Please enter a positive number for the length of the loan.");
        }
        if (amount > 0) {
            this.amount = amount;
        } else {
            this.amount = amount;
            System.out.println("Warning: A positive value was not entered for the amount of the loan!");
        }
    }
    
    //getter
    public double getRate() {
        return rate;
    }

    public int getYears() {
        return years;
    }

    public double getAmount() {
        return amount;
    }

    public double getMonthlyPayment() {
        return amount * (rate / 1200) / (1 - (1 / Math.pow(1 + (rate / 1200), years * 12)));
    }

    public double getTotal() {
        return amount * (rate / 1200) / (1 - (1 / Math.pow(1 + (rate / 1200), years * 12))) * years * 12;
    }

    public double getMonthlyInterestExpense() {
        return amount * (rate / 1200) / (1 - (1 / Math.pow(1 + (rate / 1200), years * 12))) - (amount / (years * 12));
    }

    public double getTotalInterest() {
        return amount * ((rate / 1200) / (1 - (1 / Math.pow(1 + (rate / 1200), years * 12))) * years * 12) - amount;
    }
    
    //equals check
    public boolean equals(Loan otherLoan) {
        return this.rate == otherLoan.rate &&
               this.years == otherLoan.years &&
               this.amount == otherLoan.amount;
    }
    
    //to string
    public String toString() {
        String s = "";
        s = s + "Amount: $" + this.amount;
        s = s + "\nRate: " + this.rate + "%";
        s = s + "\nYears: " + this.years;
        s = s + "\nMonthly Payment: $" + this.getMonthlyPayment();
        s = s + "\nMonthly Interest Expense: $" + this.getMonthlyInterestExpense();
        s = s + "\nTotal Paid: $" + this.getTotal();
        s = s + "\nTotal Interest Paid: $" + this.getTotalInterest();
        return s;
    }
}