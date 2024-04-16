package pl.Sniezgoda.creditcard;

import java.math.BigDecimal;

public class CreditCard {


    private BigDecimal creditLimit;
    private BigDecimal balance;

    public void assignCreditLimit(BigDecimal creditLimitMoney) {
        if(isCreditAlreadyAssigned()){
            throw new CreditCantBeReassignException();
        }
        if(CreditBelowThreshold(creditLimitMoney)) {
            throw new CreditBelowThresholdException();
        }

        this.creditLimit = creditLimitMoney;
        this.balance = creditLimitMoney;



    }

    private static boolean CreditBelowThreshold(BigDecimal creditLimit) {
        return BigDecimal.valueOf(100).compareTo(creditLimit) > 0;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.creditLimit != null;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void pay(BigDecimal money) {
        if(cantAfford(money)){
            throw new InsufficientFoundsException();
        }
        this.balance = this.balance.subtract(money);

    }

    private boolean cantAfford(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) > 0;
    }
}
