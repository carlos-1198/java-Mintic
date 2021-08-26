package javachallenge.secondChallenge;

import java.util.Scanner;
import javachallenge.secondChallenge.product.Product;
import javachallenge.secondChallenge.product.ProductStorage;

/**
 *
 * @author carlo
 */
public class SecondChallenge {
    private final Scanner scanner = new Scanner(System.in);

    public String read(){
        return this.scanner.nextLine();
    }

    public void run(){
        ProductStorage bd = new ProductStorage();
        String crudMethod = this.read();
        String [] data = this.read().split(" ");
        int id = Integer.parseInt(data[0]);
        String productName = data[1];
        double price = Double.parseDouble(data[2]);
        int productQuantity = Integer.parseInt(data[3]);
        Product product = new Product(id, productName, price, productQuantity);
        String rta = bd.generateReportOne(crudMethod,product);
        //String rta = bd.generateReportFromGUI();
        System.out.println(rta);
    }
}
