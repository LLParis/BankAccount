/**
 * CheckingAccount: This class extends BankAccount and has an overdraft
 * limit of $200.00, allowing balance to go as low as -200.
 *
 * @author London Paris
 * @version 1.0
 * @since 14 Feb 2025
 */
public class CheckingAccount extends BankAccount {

    // ================== Fields/Constants ==================
    private static final double OVERDRAFT_LIMIT = 200.0;

    // ====================== Constructors ==================

    /**
     * CheckingAccount: No-argument constructor that initializes
     * a default CheckingAccount object.
     *
     * @author London Paris
     * @version 1.0
     * @param void
     * @return void
     * @since 14 Feb 2025
     */
    public CheckingAccount() {
        super();
    }

    /**
     * CheckingAccount: This constructor initializes a CheckingAccount
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
    public CheckingAccount(String customerName, int accountID, double initialBalance) {
        super(customerName, accountID, initialBalance);
    }

    // ====================== Methods ======================

    /**
     * withdraw: Overrides the generic BankAccount withdraw method to
     * allow overdrafting up to $200.
     *
     * @author London Paris
     * @version  1.0
     * @param amount The amount to withdraw
     * @param description A short description for this withdrawal
     * @return void
     * @since 14 Feb 2025
     */
    @Override
    public void withdraw(double amount, String description) {
        if (getBalance() - amount >= -OVERDRAFT_LIMIT) {
            setBalance(getBalance() - amount);
            Transaction t = new Transaction('W', amount, getBalance(), description);
            getTransactions().add(t);
        } else {
            System.out.println("Withdrawal declined. Overdraft limit exceeded.");
        }
    }
} // End of class CheckingAccount
