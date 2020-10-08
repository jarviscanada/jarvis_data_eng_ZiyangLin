package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class Position {

    private int accountId;
    private String ticker;
    private int position;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return accountId == position1.accountId &&
                position == position1.position &&
                Objects.equals(ticker, position1.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, ticker, position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "accountId=" + accountId +
                ", ticker='" + ticker + '\'' +
                ", position=" + position +
                '}';
    }
}
