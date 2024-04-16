package pl.Sniezgoda.ecomerce.katalog;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalog {
    private ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String description) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id , name, description);
        productStorage.add(newProduct);
        return newProduct.getID();
    }

    public Product getProductBy(String id) {
        return productStorage.getProductBy(id);
    }

    public void changePrice(String id, BigDecimal newPrice) {
        Product product = getProductBy(id);
        product.changePrice(newPrice);
    }
}
