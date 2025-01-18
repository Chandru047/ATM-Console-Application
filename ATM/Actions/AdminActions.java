package ATM.Actions;

public interface AdminActions extends Actions
{

    void addUser();// declare the addUser method

    void deleteUser();// declare the deleteUser method

    void viewUser();// declare the viewUser method

    void viewTransaction();// declare the viewTransaction method

    void addTransactionToAdmin(double amount, String id);// declare the addTransactionToAdmin method
}