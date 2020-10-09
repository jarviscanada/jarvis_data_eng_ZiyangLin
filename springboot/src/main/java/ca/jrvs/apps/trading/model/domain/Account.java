package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Account implements Entity<Integer> {

    private int id;
    private int traderId;
    private double amount;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                traderId == account.traderId &&
                Double.compare(account.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, traderId, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", traderId=" + traderId +
                ", amount=" + amount +
                '}';
    }
}
