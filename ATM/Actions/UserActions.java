package ATM.Actions;
import ATM.User;

public interface UserActions extends Actions
{
    void balance(User currentUser);// declare the balance method

    void withdrawCash(User currentUser) throws CloneNotSupportedException ;// declare the withdrawCash method

    void changePass(User currentUser);// declare the changePass method

    void viewTransactions(User currentUser);// declare the viewTransaction method

    void addTransactionToUser(String type, double amount, User currentUser);// declare the addTransactionToUser method
}