
package projectcoe528;
/**
 *
 * @author v2mathur
 */
public class Manager extends Profile{
    public Manager() {
        super.setUsername("admin");
        super.setPass("admin");        
        setRole();
    }

    
    @Override
    public void setRole() {
    super.role="Manager";
    }
}