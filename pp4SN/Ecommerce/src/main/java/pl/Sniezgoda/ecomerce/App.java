package pl.Sniezgoda.ecomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.Sniezgoda.ecomerce.katalog.ArrayListProductStorage;
import pl.Sniezgoda.ecomerce.katalog.ProductCatalog;

@SpringBootApplication

public class App {
    public static void main(String[] args){
        System.out.println("Elo");
        SpringApplication.run(App.class,args);
    }
    @Bean
    ProductCatalog createMyCatalog(){
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("100 Smoczych monet","kox");
        catalog.addProduct("200 Smoczych monet","giga kox");
        return catalog;
    }
}
