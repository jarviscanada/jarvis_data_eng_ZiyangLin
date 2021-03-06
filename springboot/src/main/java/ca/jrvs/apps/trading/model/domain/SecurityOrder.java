package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class SecurityOrder implements Entity<Integer> {

    private int id;
    private int accountId;
    private String status;
    private String ticker;
    private int size;
    private double price;
    private String notes;


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityOrder that = (SecurityOrder) o;
        return id == that.id &&
                accountId == that.accountId &&
                size == that.size &&
                Double.compare(that.price, price) == 0 &&
                Objects.equals(status, that.status) &&
                Objects.equals(ticker, that.ticker) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, status, ticker, size, price, notes);
    }

    @Override
    public String toString() {
        return "SecurityOrder{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", status='" + status + '\'' +
                ", ticker='" + ticker + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                '}';
    }
}
