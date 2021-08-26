/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachallenge.thirdChallenge;

import java.util.Scanner;
import javachallenge.secondChallenge.product.Product;
import javachallenge.secondChallenge.product.ProductStorage;

/**
 *
 * @author carlo
 */
public class ThirdChallenge {
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
        String rta = bd.generateReportTwo(crudMethod,product);
        System.out.println(rta);
    }
}
