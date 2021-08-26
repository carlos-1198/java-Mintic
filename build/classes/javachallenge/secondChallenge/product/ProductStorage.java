/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachallenge.secondChallenge.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author carlo
 */
public class ProductStorage {
    private int lastId = 10;
    private final Product product1 = new Product(1,"Manzanas", 6000, 25);
    private final Product product2 = new Product(2,"Limones", 2900, 555);
    private final Product product3 = new Product(3,"Peras", 2700, 33);
    private final Product product4 = new Product(4,"Arandanos", 9300, 34);
    private final Product product5 = new Product(5,"Tomates", 2100, 42);
    private final Product product6 = new Product(6,"Fresas", 4100, 10);
    private final Product product7 = new Product(7,"Helado", 4500, 41);
    private final Product product8 = new Product(8,"Galletas", 500, 8);
    private final Product product9 = new Product(9,"Chocolates", 4600, 999);
    private final Product product10 = new Product(10,"Jamon", 15000, 10);

    private final Product[] products = {product1, product2, product3, product4, product5,
            product6, product7, product8, product9, product10};

    private HashMap<Integer, Product> productList = new HashMap<>();
    private boolean error = false;

    public ProductStorage() {
        this.createDatabase();
    }

    private void createDatabase() {
        for (Product product : this.products) {
            productList.put(product.getCode(), product);
        }
    }

    public HashMap<Integer, Product> getDatabase() {
         return this.productList;
    }
    
    private void errorTrue() {
        this.error = true;
    }
    
    private void restartError(){
        if(this.error){
            this.error = false;
        }
    }
    private boolean productExist(int id) {
        return this.productList.containsKey(id) ;
    }
    
    private Product productExistByName(String name) {
        Product repeated = null;
        for (Product value : productList.values()) {
            if (value.getName().equalsIgnoreCase(name))
                repeated = value;
        }
        return repeated;
    }
    
    private void deleteProduct(int id) {
        if (this.productExist(id)) {
            this.productList.remove(id);
        } else {
            this.errorTrue();
        }
    }

    private void addProduct(Product product) {
        int id = product.getCode();
        if (this.productExist(id)) {
            this.errorTrue();
        } else {
            this.productList.put(id, product);
            this.lastId++;
        }
    }

    private void updateProduct(Product product) {
        int id = product.getCode();
        if (this.productExist(id)) {
            this.productList.replace(id, product);
        } else {
            this.errorTrue();
        }
    }

    private void updateDataBase(String option, Product product){
        switch (option) {
            case "AGREGAR":
                addProduct(product);
                break;
            case "BORRAR":
                deleteProduct(product.getCode());
                break;
            case "ACTUALIZAR":
                updateProduct(product);
                break;
        }
    }

    private String messageReportOne(){
        String rta ="";
        String [] summary = this.storeSummary();
        if (!this.error) {
            for (int i = 0; i < summary.length; i++) {
                rta += summary[i]+" ";
            }
           return rta;
        }else{
            rta = "ERROR";
        }
        return rta;
    }
    
    private String[] storeSummary(){
        String [] rta = new String[4];
        Product cheapestProduct = new Product();
        Product moreExpensiveProduct = new Product();
        double totalStockPrice = 0.0;
        double averagePrice;
        double sumPrices = 0.0;
        int counter = 0;
        for (Map.Entry<Integer, Product> entry : productList.entrySet()) {
            if(cheapestProduct.getName() == null){
                cheapestProduct = entry.getValue();
            }
            if(moreExpensiveProduct.getName() == null){
                moreExpensiveProduct = entry.getValue();
            }
            if(entry.getValue().getPrice() < cheapestProduct.getPrice()){
                cheapestProduct = entry.getValue();
            }
            if(entry.getValue().getPrice() > moreExpensiveProduct.getPrice()){
                moreExpensiveProduct = entry.getValue();
            }
            counter++;
            sumPrices += entry.getValue().getPrice();
            totalStockPrice += entry.getValue().getQuantity() * entry.getValue().getPrice();
        }
        averagePrice = (double)Math.round((sumPrices/counter) * 10) / 10;
        rta[0] = moreExpensiveProduct.getName();
        rta[1] = cheapestProduct.getName();
        rta[2] = String.valueOf(averagePrice);
        rta[3] = String.valueOf(totalStockPrice);
        return rta;
    }
    
    private List<Product> listOrderedFromByPrice(){
        List<Product> productsByPrice = new ArrayList<>(productList.values());
        Collections.sort(productsByPrice);
        return productsByPrice;
    }
    
    private String messageReportTwo(){
        String message="";
        List<Product> sortedList = this.listOrderedFromByPrice();
        if(!this.error) {
            for (int i = 0; i < 3; i++) {
                message += sortedList.get(i).getName() + " ";
            }
        }else{
            message = "ERROR";
        }
        return message;
    }

    public String generateReportOne(String crudMethod, Product product) {
        this.updateDataBase(crudMethod, product);
        return this.messageReportOne();
    }
    
    public String generateReportTwo(String crudMethod, Product product) {
        this.updateDataBase(crudMethod, product);
        return messageReportTwo();
    }
    
    public String generateReportFromGUI(){
        String [] summary = this.storeSummary(); 
        return "Producto precio mayor: "+summary[0]+ "\n"+
               "Producto precio menor: "+summary[1]+ "\n"+
               "Promedio precios: "+summary[2]+ "\n"+
               "Valor del inventario: "+summary[3];
    }
    
    public void deleteFromGUI(Object id){
        int i = Integer.valueOf((String) id);
        this.deleteProduct(i);
        this.restartError();
    }
    
    public Boolean addFromGUI(String name, String price, String quantity){
        boolean success = false;
        Product existingProduct = this.productExistByName(name);
        Product newProduct;
        if(existingProduct == null){
            double value = Double.parseDouble(price);
            int stock = Integer.parseInt(quantity);
            newProduct = new Product(this.lastId + 1 ,name, value, stock);
            this.addProduct(newProduct);
            success = true;
        }
        this.restartError();
        return success;
    }
    
    public boolean updateFromGui(String name, String price, String quantity){
        boolean success = false;
        Product oldProductData = this.productExistByName(name);
        Product newProductData;
        if(oldProductData != null){
            double value = Double.parseDouble(price);
            int stock = Integer.parseInt(quantity);
            newProductData = new Product(oldProductData.getCode(),name, value, stock);
            this.updateProduct(newProductData);
            success = true;
        }
        this.restartError();
        return success;
    }
}
