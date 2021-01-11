package com.baeldung.springsoap;

import com.baeldung.springsoap.gen.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductRepository {

    private static final Map<Long, Product> products = new HashMap<>();

    @PostConstruct
    public void initData() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product1");
        product1.setExtension("kg");
        product1.setPrice(100.0);
        product1.setSellNumber(1);
        products.put(product1.getId(), product1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product2");
        product2.setExtension("kg");
        product2.setPrice(120.0);
        product2.setSellNumber(2);
        products.put(product2.getId(), product2);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Product3");
        product3.setExtension("kg");
        product3.setPrice(140.0);
        product3.setSellNumber(3);
        products.put(product3.getId(), product3);

        Product product4 = new Product();
        product4.setId(4L);
        product4.setName("Product5");
        product4.setExtension("kg");
        product4.setPrice(160.0);
        product4.setSellNumber(4);
        products.put(product4.getId(), product4);
    }

    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public Product findProductById(Long id) {
        return products.get(id);
    }

    public Product save(Product product) {
        product.setId(products.size() + 1);

        products.put(product.getId(), product);

        return product;
    }

    public boolean delete(Long id) {
        products.remove(id);
        return true;
    }

    public Product update(Long id, Product product) {
        product.setId(id);
        products.replace(id, product);
        return product;
    }
}
