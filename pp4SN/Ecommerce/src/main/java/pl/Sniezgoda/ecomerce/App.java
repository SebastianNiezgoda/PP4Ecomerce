package pl.Sniezgoda.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.Sniezgoda.ecomerce.katalog.ArrayListProductStorage;
import pl.Sniezgoda.ecomerce.katalog.ProductCatalog;
import pl.Sniezgoda.ecomerce.Sales.SalesFacade;

import java.math.BigDecimal;

@SpringBootApplication

public class App {
    public static void main(String[] args){
        System.out.println("Witam");
        SpringApplication.run(App.class,args);
    }
    @Bean
    SalesFacade createSalesFacade(){
        return new SalesFacade();
    }

    @Bean
    ProductCatalog createMyCatalog(){
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("100 Smoczych monet","kox");
        catalog.addProduct("200 Smoczych monet","giga kox");
        catalog.addProduct("300 Smoczych monet","giga kox");
        catalog.addProduct("400 Smoczych monet","giga kox");
        return catalog;
    }
}
