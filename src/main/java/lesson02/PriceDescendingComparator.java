package lesson02;

import java.util.Comparator;

public class PriceDescendingComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getPrice() - o1.getPrice();
    }
}
