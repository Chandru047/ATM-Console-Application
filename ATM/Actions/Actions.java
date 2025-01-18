package ATM.Actions;

import ATM.Account;

interface Actions
{
    Account login(String id) throws CloneNotSupportedException ; // declare the login method


    void deposit(Account current); // declare the deposit method

}