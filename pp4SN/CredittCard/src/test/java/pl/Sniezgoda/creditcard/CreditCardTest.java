package pl.Sniezgoda.creditcard;

import org.junit.jupiter.api.Test;
import pl.Sniezgoda.creditcard.CreditBelowThresholdException;
import pl.Sniezgoda.creditcard.CreditCantBeReassignException;
import pl.Sniezgoda.creditcard.CreditCard;
import pl.Sniezgoda.creditcard.InsufficientFoundsException;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCredit() {
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void itAllowsToAssignCredit2() {
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1200));

        assert BigDecimal.valueOf(1200).equals(card.getBalance());
    }

    @Test
    void itDenyCreditBelowThreshold() {
        CreditCard card = new CreditCard();

        try{
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch(CreditBelowThresholdException e){
            assertTrue(true);
        }

    }

    @Test
    void itDenyCreditBelowThresholdv2() {
        CreditCard card = new CreditCard();
        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(50))
        );
        try{
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch(CreditBelowThresholdException e){
            assertTrue(true);
        }

    }

    @Test
    void itDenyCreditReassignment(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditCantBeReassignException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(1200))

        );
    }

    @Test
    void payForSomething(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        card.pay(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(900),
                card.getBalance()
                );
    }

    @Test
    void ItDenyPaymentWhenNotEnoughMoney(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                InsufficientFoundsException.class,
                () -> card.pay(BigDecimal.valueOf(200))
        );


    }


}
