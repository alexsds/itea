package lesson02;

import java.util.LinkedList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Product1", 1);
        Product product2 = new Product("Product2", 2);
        Product product3 = new Product("Product3", 3);
        Product product4 = new Product("Product3", 3);

        List<Product> products = new LinkedList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        products.sort(new PriceDescendingComparator());
        Utils.removeDuplicates(products);

        System.out.println(products);
    }
}
