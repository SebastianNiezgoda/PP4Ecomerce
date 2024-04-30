package pl.Sniezgoda.ecomerce.playground;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.Sniezgoda.ecomerce.katalog.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class SqlTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDatabase(){

        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS");
        var sql = """
                CREATE TABLE `product_catalog__products`(
                    id VARCHAR(100) NOT NULL,
                    name VARCHAR(200) NOT NULL,
                    description VARCHAR(255),
                    price DECIMAL(12,2),
                    PRIMARY KEY(id)
                );
                """;
        jdbcTemplate.execute(sql);
    }

    @Test
    void itQueryCurrDateViaSql(){
        var sql= """
                Select now() curr_date;
                """;
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2024");
    }

    @Test
    void itCreatesTable() {

        var result = jdbcTemplate.queryForObject("SELECT count(*) from `product_catalog__products`",
                Integer.class);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void itInsertElement() {


        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('c52f02c2', 'example product' , 'nice one' , 10.10),
                    ('c52f0232c2', 'example product X' , 'nice one', 20.20);
                """;
        jdbcTemplate.execute(sql);

        var result = jdbcTemplate.queryForObject("SELECT count(*) from `product_catalog__products`",
                Integer.class);
        assertThat(result).isEqualTo(2);


    }

    @Test
    void itInsertDynamicElement() {
        var product = new Product(UUID.randomUUID(),"Example one","Nice one");
        product.changePrice(BigDecimal.valueOf(1));

        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    (?, ?, ?, ?,)
                    ;
                """;
        jdbcTemplate.update(sql, product.getID(), product.getName(), product.getDescription(), product.getPrice());

        var result = jdbcTemplate.queryForObject("SELECT count(*) from `product_catalog__products`",
                Integer.class);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void itSelectAll() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('c52f02c2', 'example product' , 'nice one' , 10.10),
                    ('c52f0232c2', 'example product X' , 'nice one', 20.20);
                """;
        jdbcTemplate.execute(sql);

        List<Map<String,Object>> maps = jdbcTemplate.queryForList( "Select * from `product_catalog__products`");
       assertThat(maps)
               .hasSize(2)
               .extracting("NAME")
               .contains("example product");

    }

    @Test
    void itSelectWithCondition() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description, price)
                VALUES
                    ('c52f02c2', 'example product' , 'nice one' , 10.10),
                    ('c52f0232c2', 'example product X' , 'nice one', 20.20);
                """;
        jdbcTemplate.execute(sql);

        Product product = jdbcTemplate.queryForObject(
                "Select * from `product_catalog__products` where id = ? and 1 = ?" ,
                new Object[]{"c52f02c2", 1},
                (rs, i) -> {
                    var loaded = new Product(
                            UUID.randomUUID(),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );
                    loaded.changePrice(rs.getBigDecimal("PRICE"));
                    return loaded;
                }
        );

    }
}
