package pl.Sniezgoda.ecomerce.katalog;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.Sniezgoda.ecomerce.katalog.Product;
import pl.Sniezgoda.ecomerce.katalog.ProductCatalog;

import java.math.BigDecimal;
import java.util.List;


public class ProductKatalogTest {
    @Test
    void itListsProducts(){
        ProductCatalog catalog = getProductCatalog();

        List<Product> productList = catalog.allProducts();

        assert productList.isEmpty();

    }

    private static ProductCatalog getProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(new ArrayListProductStorage());
        return catalog;
    }

    @Test
    void itAllowsToAddProducts(){
        ProductCatalog catalog = getProductCatalog();

        catalog.addProduct("Kod na 1000 smoczych monet","nice one");
        List<Product>  productList = catalog.allProducts();
        assertThat(productList).hasSize(1);
    }

    @Test
    void itLoadsProductDetails(){
        ProductCatalog catalog = getProductCatalog();
        String id = catalog.addProduct("Kod na 1000 smoczych monet","Nice one");

        Product loadedProduct = catalog.getProductBy(id);
        assertThat(id).isEqualTo(loadedProduct.getID());
    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Kod na 1000 smoczych monet","Nice one");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);
        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());

    }
    private static ProductCatalog thereIsProductCatalog() {
        return getProductCatalog();
    }
}