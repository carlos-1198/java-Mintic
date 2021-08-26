
package javachallenge;
import javachallenge.secondChallenge.SecondChallenge;
import javachallenge.thirdChallenge.ThirdChallenge;
/**
 *
 * @author carlo
 */
public class JavaChallenge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //clase para pruebas de los retos, la gui esta en el paquete de gui
        SecondChallenge r2 = new SecondChallenge();
        r2.run();
        ThirdChallenge r3 = new ThirdChallenge();
        r3.run();
    }
    
}
