package ru.gb.main.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList;

    @PostConstruct
    private void primarySet() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1, "bread"),
                new Product(2, "milk"),
                new Product(3, "cheese"),
                new Product(4, "meat"),
                new Product(5, "chicken")));
    }

    public Product getProductForIndex(int i) {
        for (Product element: productList) {
            if (element.getId() == i){
                return element;
            }
        }
        return null;
    }
    public List<Product> getAllProducts() {
        return productList;
    }
}
