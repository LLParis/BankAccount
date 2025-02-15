/**
 * BankAccount: This class represents a bank account with data fields:
 * account ID, customer name, balance, interest rate, date
 * created, and a list of transactions. It provides functionality to
 * deposit, withdraw, and calculate monthly interest.
 *
 * @author London Paris
 * @version 1.0
 * @since 14 Feb 2025
 */

import java.util.*;

public class BankAccount {
    // ========================== Fields ==========================
    private int accountID;                  // Unique account ID
    private String customerName;            // Name of the customer
    private double balance;                 // Current balance
    private double annualInterestRate;      // Annual interest rate in percent
    private Date dateCreated;               // Date the account was created
    private ArrayList<Transaction> transactions; // List of transactions

    // ===================== Default Values =======================
    private static final int DEFAULT_ID = 0;
    private static final double DEFAULT_BALANCE = 0.0;
    private static final double DEFAULT_INTEREST_RATE = 0.0;

    // ====================== Constructors ========================

    /**
     * BankAccount: This no argument constructor initializes
     * a default BankAccount object.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    public BankAccount() {
        this.customerName = "NoName";
        this.accountID = DEFAULT_ID;
        this.balance = DEFAULT_BALANCE;
        this.annualInterestRate = DEFAULT_INTEREST_RATE;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    /**
     * BankAccount: This constructor initializes an account with the
     * customer name, account ID, and initial balance.
     *
     * @author London Paris
     * @version
     *   1.0
     * @param customerName The name of the account holder
     * @param accountID The account ID
     * @param initialBalance The initial balance for the account
     * @return void
     * @since 14 Feb 2025
     */
    public BankAccount(String customerName, int accountID, double initialBalance) {
        this.customerName = customerName;
        this.accountID = accountID;
        this.balance = initialBalance;
        this.annualInterestRate = DEFAULT_INTEREST_RATE;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    // ========================= Getters ==========================

    /**
     * getAccountID: Returns the account ID.
     *
     * @author London Paris
     * @version 1.0
     * @return the int accountID
     * @since 14 Feb 2025
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * getBalance: Returns the current balance of this account.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return the double balance
     * @since 14 Feb 2025
     */
    public double getBalance() {
        return balance;
    }

    /**
     * getAnnualInterestRate: Returns the current annual interest rate
     * as a percentage.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return the double annual interest rate
     * @since 14 Feb 2025
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * getDateCreated: Returns the Date the account was created.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return a Date object representing the creation date
     * @since 14 Feb 2025
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * getCustomerName: Returns the customer's name.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return a String representing the customer's name
     * @since 14 Feb 2025
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * getTransactions: Returns the list of transactions associated
     * with this account.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return an ArrayList of Transaction objects
     * @since 14 Feb 2025
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    // ========================= Setters ==========================
    // If required, you can add doc blocks for these as well.

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // ====================== Other Methods =======================

    /**
     * getMonthlyInterestRate: Returns the monthly interest rate,
     * (annualInterestRate / 12) as a decimal, not a percentage.
     * Example: if annualInterestRate is 1.5%, this method returns
     * 0.015 / 12 = 0.00125.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return the monthly interest rate in decimal form
     * @since 14 Feb 2025
     */
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100.0) / 12.0;
    }

    /**
     * getMonthlyInterest: Returns the monthly interest in dollars,
     * calculated as balance * monthlyInterestRate.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return the monthly interest amount
     * @since 14 Feb 2025
     */
    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    /**
     * withdraw: Withdraws the specified amount from this account
     * if there are sufficient funds. This generic method is overridden
     * by subclasses for special rules.
     *
     * @author London Paris
     * @version 1.0
     * @param amount The amount to withdraw
     * @param description A short description for this withdrawal
     * @return void
     * @since 14 Feb 2025
     */
    public void withdraw(double amount, String description) {
        if (balance >= amount) {
            balance -= amount;
            Transaction t = new Transaction('W', amount, balance, description);
            transactions.add(t);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    /**
     * deposit: Deposits the amount into this account and
     * records a transaction.
     *
     * @author London Paris
     * @version 1.0
     * @param amount The amount to deposit
     * @param description A short description for this deposit
     * @return void
     * @since 14 Feb 2025
     */
    public void deposit(double amount, String description) {
        balance += amount;
        Transaction t = new Transaction('D', amount, balance, description);
        transactions.add(t);
    }
} // End of class BankAccount
