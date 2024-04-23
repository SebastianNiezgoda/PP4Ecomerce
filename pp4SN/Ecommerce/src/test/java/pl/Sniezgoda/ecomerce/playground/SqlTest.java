package pl.Sniezgoda.ecomerce.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class SqlTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void itQueryCurrDateViaSql(){
        var sql= """
                Select now() curr_date;
                """;
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2024");
    }
}
