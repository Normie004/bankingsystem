
package projectcoe528;

/**
 *
 * @author v2mathur
 */
public class Platinum extends Level{

    @Override
    public void changeState(Account a){
        if (a.getAmount() < 10000){
                a.setLevel(new Silver());
            }
        if (a.getAmount() < 20000 && a.getAmount() > 10000){
                a.setLevel(new Gold());
            }

        }
    @Override
    public void onlinePurchase(Account a,int cost){
        if ((cost >= 50) && (cost < a.getAmount())){
            a.withdraw(cost);
        }
       
        
    }       
    }
