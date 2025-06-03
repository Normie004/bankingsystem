
package projectcoe528;

/**
 *
 * @author v2mathur
 *
 * OVERVIEW:This class acts in a state pattern where the object
 * behavior depends on its state. It is 
 * mutable as it has mutable functions.
 * 
*    abstraction function:
*    AF(c) =  account, c, such that
*    c.setAmount = amount
*    c.getAmount = amount
*    c.deposit = amount + deposit
*    c.withdraw = amount - withdraw
*    c.setLevel = l
*    c.getLevel = l
*   The rep invariant is:
*   c.setAmount >= 0 &amp
*   c.getAmount >= 0 &amp
*   c.deposit >= 0 &amp
*   c.withdraw >= 0 &amp
*   (c.getAmount - withdraw) >= 0 
*   c.getLevel =! null  
 */
public class Account{
   private int amount;
   private Level level;

    /**
     *
     * @param amount
     */
    public Account(int amount){
   this.amount = amount;
   }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
    //REQUIRES: value of moeny (int)
    //MODIFIES: starting value of amount 
    //EFFECTS: returns the amount
            this.amount = amount;
    }

    /**
     *
     * @return
     */
    public int getAmount() {
    //EFFECTS: returns amount of money in account     
        return amount;
    }
   
    /**
     *
     * @param deposit
     */
    public void  deposit(int deposit){
    //REQUIRES: value of money (int)
    //MODIFIES: adds money to previous balance
    //EFFECTS: gives new balance    
        if(deposit < 1){
            throw new NullPointerException();
        }
        amount = amount + deposit;
    }
    
    /**
     *
     * @param withdraw
     */
    public void withdraw(int withdraw){
    //REQUIRES: value of money (int)
    //MODIFIES: substraction of money withdrawn
    //EFFECTS: gives new amount
        if(withdraw < 1 || ((amount - withdraw)<0)){
            throw new NullPointerException();
        }
        amount = amount - withdraw;
    

    }
    
    /**
     *
     * @param l
     */
    public void setLevel(Level l){
    //REQUIRES: level(l)(siver,gold,platinum)    
    //MODIFIES: modifies the level
    //EFFECTS: returns the new level 
        level = l;
    }

    /**
     *
     * @return
     */
    public Level getLevel(){
    //EFFECTS: gives the state of the account (also level)  
        if(amount< 10000){
            return level = new Silver();
        }
        if(amount >10000 && amount <20000){
        return level = new Gold();
        }
        return level = new Platinum();
    }
    
    /**
     *
     * @return
     */
    public boolean repOK(){
        // EFFECTS: gives true if rep invariant is valid
if(amount >= 0){
    return true;
}
  if (level != null){
      return true;
  }
    
    return false;

}

    @Override
    public String toString() {

        // EFFECTS: final amount returns
        return("Level: " + level + ", " + "Amount: " + amount);
        
    }

}