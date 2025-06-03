
package projectcoe528;

/**
 *
 * @author v2mathur
 */
public class Silver extends Level{

    
    @Override
    public void changeState(Account a){
        if (a.getAmount() > 10000 && a.getAmount() < 20000) {
            a.setLevel(new Gold());
        }
        if (a.getAmount() > 20000){
            a.setLevel(new Platinum());
        }
       
    }
    
    @Override
    public void onlinePurchase(Account a,int cost){
        if ((cost >= 50) && (cost < a.getAmount())){
            a.withdraw(cost);
        }
        
     
        
    }
}
