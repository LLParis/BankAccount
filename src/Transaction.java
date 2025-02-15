/**
 * Transaction: This class represents a transaction object in
 * a bank account. It includes the transaction date, type
 * (Deposit or Withdrawal), amount, balance, and
 * a description.
 * The transactionType is 'D' for deposit and 'W' for withdrawal.
 * NOTE: This class is used by BankAccount and its subclasses
 * to log deposit/withdraw actions.
 *
 * @author London Paris
 * @version 1.0
 * @since 14 Feb 2025
 */

import java.util.Date;

public class Transaction {
    // ========================= Fields =========================
    private Date transactionDate;
    private char transactionType;    // 'D' for deposit, 'W' for withdrawal
    private double amount;
    private double balanceAfter;     // balance after this transaction
    private String description;

    // ====================== Constructor =======================

    /**
     * Transaction: Constructs a Transaction object with
     * the specified parameters. The date is automatically
     * set to the current date/time.
     *
     * @author London Paris
     * @version 1.0
     * @param transactionType The transaction type ('D' or 'W')
     * @param amount The transaction amount
     * @param balanceAfter The balance after the transaction
     * @param description A short description of the transaction
     * @return void
     * @since 14 Feb 2025
     */
    public Transaction(char transactionType, double amount,
                       double balanceAfter, String description) {
        this.transactionDate = new Date();
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    // ======================== Getters =========================

    public Date getTransactionDate() {
        return transactionDate;
    }

    public char getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDescription() {
        return description;
    }
} // End of class Transaction
