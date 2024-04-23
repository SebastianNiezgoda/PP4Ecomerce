package pl.Sniezgoda.ecomerce.katalog;

import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    void add(Product newProduct);

    Product getProductBy(String id);
}
