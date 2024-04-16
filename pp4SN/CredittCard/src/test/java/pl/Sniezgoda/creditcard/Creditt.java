package pl.Sniezgoda.creditcard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Creditt {
    @Test
    void helloTest(){
        //A / Arange
        var a = 2;
        var b =4;
        //A / Act
        var result = a+b;
        //A / Assert
        assert 6 == result;

    }


    @Test
    void helloTestError(){
        //A / Arange
        var a = 2;
        var b =4;
        //A / Act
        var result = a+b;
        //A / Assert
        assert 10 == result;

    }

    @Test
    void itGreetUsername() {
        String name = "Sebastian";

        String message = String.format("Hello %s", name);

        assertEquals("Hello Sebastian", message);
    }
}
