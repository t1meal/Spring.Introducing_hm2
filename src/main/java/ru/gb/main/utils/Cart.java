package ru.gb.main.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {

    private final ProductRepository productRepository;
    private List<Product> cartProductList;

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostConstruct
    private void setCartProductList (){
        this.cartProductList = new ArrayList<>();
    }

    public void addProductForIndex (int i){
       cartProductList.add(productRepository.getProductForIndex(i));
    }
    public void removeProductForIndex(int i){
        for (Product element: cartProductList) {
            if (element.getId() == i){
                cartProductList.remove(element);
                return;
            }
        }
    }
    public List<Product> getCartProductList() {
        return cartProductList;
    }

}
