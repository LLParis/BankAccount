/**
 * SavingsAccount: This class extends BankAccount and prevents overdraw.
 * You cannot withdraw more than the current balance.
 *
 * @author London Paris
 * @version 1.0
 * @since 14 Feb 2025
 */
public class SavingsAccount extends BankAccount {

    // ====================== Constructors ======================

    /**
     * SavingsAccount: No-argument constructor that initializes a default
     * SavingsAccount object.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    public SavingsAccount() {
        super();
    }

    /**
     * SavingsAccount: This constructor initializes a SavingsAccount
     * with the specified customer name, account ID, and initial balance.
     *
     * @author London Paris
     * @version 1.0
     * @param customerName The name of the account holder
     * @param accountID The account ID
     * @param initialBalance The initial balance for the account
     * @return void
     * @since 14 Feb 2025
     */
    public SavingsAccount(String customerName, int accountID, double initialBalance) {
        super(customerName, accountID, initialBalance);
    }

    // ====================== Methods ======================

    /**
     * withdraw: Overrides the generic BankAccount withdraw method to
     * disallow overdraft. You cannot withdraw if balance is insufficient.
     *
     * @author London Paris
     * @version 1.0
     * @param amount The amount to withdraw
     * @param description A short description for this withdrawal
     * @return void
     * @since 14 Feb 2025
     */
    @Override
    public void withdraw(double amount, String description) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            Transaction t = new Transaction('W', amount, getBalance(), description);
            getTransactions().add(t);
        } else {
            System.out.println("Withdrawal declined. Savings cannot be overdrawn.");
        }
    }
} // End of class SavingsAccount
