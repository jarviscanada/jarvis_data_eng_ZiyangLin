package ca.jrvs.apps.trading.view;

import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;

import java.util.List;

public class PortfolioView {

    private List<SecurityView> securities;

    public PortfolioView(List<SecurityView> securities) {
        this.securities = securities;
    }

    public List<SecurityView> getSecurities() {
        return securities;
    }

    public void setSecurities(List<SecurityView> securities) {
        this.securities = securities;
    }

    public static class SecurityView {

        private final String ticker;
        private final Position position;
        private final Quote quote;

        public SecurityView(String ticker, Position position, Quote quote) {
            this.ticker = ticker;
            this.position = position;
            this.quote = quote;
        }

        public String getTicker() {
            return ticker;
        }

        public Position getPosition() {
            return position;
        }

        public Quote getQuote() {
            return quote;
        }
    }
}
