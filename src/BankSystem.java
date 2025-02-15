/**
 * BankSystem: This is the main driver class for the Bank Account project.
 * It provides an interactive menu for creating Checking/Savings accounts,
 * making deposits/withdrawals, loading/saving accounts to a file,
 * and viewing summaries of all accounts.
 *
 * NOTE: This class depends on BankAccount, CheckingAccount,
 * SavingsAccount, and Transaction classes.
 *
 * @author London Paris
 * @version 1.0
 * @since 14 Feb 2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

    // ========================== Fields ==========================
    private static final String ACCOUNTS_FILE_NAME = "accounts.txt";
    private static ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    // ======================== Main Method =======================

    /**
     * main: The entry point of the program. Displays a menu
     * to the user, processes input commands, and orchestrates
     * the bank account operations.
     *
     * @author London Paris
     * @version 1.0
     * @param args The command-line arguments (not used)
     * @return void
     * @since 14 Feb 2025
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userChoice = 0;

        while (userChoice != 7) {
            printMenu();
            System.out.print("Enter your choice: ");
            try {
                userChoice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-7.");
                continue;
            }

            switch (userChoice) {
                case 1:
                    createSavingsAccount(input);
                    break;
                case 2:
                    createCheckingAccount(input);
                    break;
                case 3:
                    depositToAccount(input);
                    break;
                case 4:
                    withdrawFromAccount(input);
                    break;
                case 5:
                    loadAccountsFromFile();
                    break;
                case 6:
                    viewSummary();
                    break;
                case 7:
                    saveAccountsToFile();
                    System.out.println("Exiting. Accounts have been saved.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        input.close();
    } // End of method main

    // ===================== Menu and Actions =====================

    /**
     * printMenu: Prints the available menu options to the user.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    private static void printMenu() {
        System.out.println("\nBank Account System");
        System.out.println("1. Create Savings Account");
        System.out.println("2. Create Checking Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Get Accounts from File");
        System.out.println("6. View Summary");
        System.out.println("7. Exit");
    }

    /**
     * createSavingsAccount: Prompts the user for necessary data and
     * creates a new SavingsAccount. Adds the account to bankAccounts.
     *
     * @author London Paris
     * @version 1.0
     * @param input A Scanner object for reading user input
     * @return void
     * @since 14 Feb 2025
     */
    private static void createSavingsAccount(Scanner input) {
        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        System.out.print("Enter account ID (int): ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Enter initial balance: ");
        double balance = Double.parseDouble(input.nextLine());

        SavingsAccount account = new SavingsAccount(name, id, balance);

        System.out.print("Enter annual interest rate (e.g. 1.5 for 1.5%): ");
        double rate = Double.parseDouble(input.nextLine());
        account.setAnnualInterestRate(rate);

        bankAccounts.add(account);
        System.out.println("Savings account created successfully!");
    }

    /**
     * createCheckingAccount: Prompts the user for necessary data and
     * creates a new CheckingAccount. Adds the account to bankAccounts.
     *
     * @author London Paris
     * @version 1.0
     * @param input A Scanner object for reading user input
     * @return void
     * @since 14 Feb 2025
     */
    private static void createCheckingAccount(Scanner input) {
        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        System.out.print("Enter account ID (int): ");
        int id = Integer.parseInt(input.nextLine());

        System.out.print("Enter initial balance: ");
        double balance = Double.parseDouble(input.nextLine());

        CheckingAccount account = new CheckingAccount(name, id, balance);

        System.out.print("Enter annual interest rate (e.g. 1.5 for 1.5%): ");
        double rate = Double.parseDouble(input.nextLine());
        account.setAnnualInterestRate(rate);

        bankAccounts.add(account);
        System.out.println("Checking account created successfully!");
    }

    /**
     * depositToAccount: Prompts the user for an account ID and
     * deposit amount, then performs the deposit on the matching
     * BankAccount object, if found.
     *
     * @author London Paris
     * @version 1.0
     * @param input A Scanner object for reading user input
     * @return void
     * @since 14 Feb 2025
     */
    private static void depositToAccount(Scanner input) {
        if (bankAccounts.isEmpty()) {
            System.out.println("No accounts available. Please create an account first.");
            return;
        }

        System.out.print("Enter account ID to deposit into: ");
        int id = Integer.parseInt(input.nextLine());
        BankAccount account = findAccountByID(id);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(input.nextLine());

        System.out.print("Enter deposit description: ");
        String desc = input.nextLine();

        account.deposit(amount, desc);
        System.out.println("Deposit successful.");
    }

    /**
     * withdrawFromAccount: Prompts the user for an account ID and
     * withdrawal amount, then performs the withdraw on the matching
     * BankAccount object, if found.
     *
     * @author London Paris
     * @version 1.0
     * @param input A Scanner object for reading user input
     * @return void
     * @since 14 Feb 2025
     */
    private static void withdrawFromAccount(Scanner input) {
        if (bankAccounts.isEmpty()) {
            System.out.println("No accounts available. Please create an account first.");
            return;
        }

        System.out.print("Enter account ID to withdraw from: ");
        int id = Integer.parseInt(input.nextLine());
        BankAccount account = findAccountByID(id);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = Double.parseDouble(input.nextLine());

        System.out.print("Enter withdrawal description: ");
        String desc = input.nextLine();

        account.withdraw(amount, desc);
    }

    /**
     * viewSummary: Prints a summary of every account (customer name,
     * account ID, interest rate, monthly interest, balance) and a table
     * of transactions.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    private static void viewSummary() {
        if (bankAccounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }
        System.out.println("\n---------- SUMMARY ----------");
        for (BankAccount acc : bankAccounts) {
            System.out.println("Customer Name: " + acc.getCustomerName());
            System.out.println("Account ID: " + acc.getAccountID());
            System.out.println("Interest Rate: " + acc.getAnnualInterestRate() + "%");
            System.out.printf("Monthly Interest: $%.2f%n", acc.getMonthlyInterest());
            System.out.printf("Current Balance: $%.2f%n", acc.getBalance());

            System.out.println("Transactions:");
            System.out.println("Type   Amount     Balance After       Date                Description");
            for (Transaction t : acc.getTransactions()) {
                System.out.printf("%-6c $%-10.2f $%-15.2f %-20s %s%n",
                        t.getTransactionType(),
                        t.getAmount(),
                        t.getBalanceAfter(),
                        t.getTransactionDate().toString(),
                        t.getDescription());
            }
            System.out.println("------------------------------------");
        }
    }

    /**
     * findAccountByID: Searches the bankAccounts list for an account
     * with the matching ID.
     *
     * @author London Paris
     * @version 1.0
     * @param id The ID of the account to find
     * @return A BankAccount object if found, or null otherwise
     * @since 14 Feb 2025
     */
    private static BankAccount findAccountByID(int id) {
        for (BankAccount acc : bankAccounts) {
            if (acc.getAccountID() == id) {
                return acc;
            }
        }
        return null;
    }

    // =============== Save/Load from File Methods ===============

    /**
     * saveAccountsToFile: Saves all current accounts to a text file
     * in CSV format:
     * type,accountID,name,balance,interestRate
     * The name has commas replaced with semicolons to avoid parsing issues.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    private static void saveAccountsToFile() {
        try (PrintWriter pw = new PrintWriter(ACCOUNTS_FILE_NAME)) {
            for (BankAccount acc : bankAccounts) {
                String accType;
                if (acc instanceof CheckingAccount) {
                    accType = "Checking";
                } else if (acc instanceof SavingsAccount) {
                    accType = "Savings";
                } else {
                    accType = "BankAccount";
                }
                // Replace commas in name
                String safeName = acc.getCustomerName().replace(",", ";");
                pw.printf("%s,%d,%s,%.2f,%.2f%n",
                        accType,
                        acc.getAccountID(),
                        safeName,
                        acc.getBalance(),
                        acc.getAnnualInterestRate());
            }
            System.out.println("Accounts saved to file: " + ACCOUNTS_FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    /**
     * loadAccountsFromFile: Loads accounts from a text file following
     * the same CSV format used by saveAccountsToFile(). Replaces the
     * bankAccounts list with the loaded data.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    private static void loadAccountsFromFile() {
        File file = new File(ACCOUNTS_FILE_NAME);
        if (!file.exists()) {
            System.out.println("No file found to load accounts.");
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            ArrayList<BankAccount> loadedAccounts = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                // Format: type,id,name,balance,rate
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String name = parts[2].replace(";", ","); // revert semicolon replacement
                double bal = Double.parseDouble(parts[3]);
                double rate = Double.parseDouble(parts[4]);

                BankAccount acc;
                if ("Checking".equalsIgnoreCase(type)) {
                    acc = new CheckingAccount(name, id, bal);
                } else if ("Savings".equalsIgnoreCase(type)) {
                    acc = new SavingsAccount(name, id, bal);
                } else {
                    acc = new BankAccount(name, id, bal);
                }
                acc.setAnnualInterestRate(rate);
                loadedAccounts.add(acc);
            }
            bankAccounts = loadedAccounts;
            System.out.println("Accounts loaded from file: " + ACCOUNTS_FILE_NAME);
        } catch (Exception e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
} // End of class BankSystem
