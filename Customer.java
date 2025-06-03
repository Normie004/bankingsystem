
package projectcoe528;

/**
 *
 * @author v2mathur
 */
public class Customer extends Profile{

    Account account;

    public Customer(String name, String password, Account account, int b) {
        super.setUsername(name);
        super.setPass(password);
        setRole();
        this.account = new Account(b);
        
    }
    
    public Account getBankAccount(){
        return account;
    }
    

    
    @Override
    public void setRole() {
        super.role = "Customer";
    }
    
}