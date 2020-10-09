package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

/**
 * https://iexcloud.io/docs/api/#quote
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "calculationPrice",
        "open",
        "openTime",
        "close",
        "closeTime",
        "high",
        "low",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "volume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "marketCap",
        "week52High",
        "week52Low",
        "ytdChange",
        "peRatio",
        "lastTradeTime",
        "isUSMarketOpen"
})
public class IexQuote {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private long open;
    @JsonProperty("openTime")
    private long openTime;
    @JsonProperty("close")
    private double close;
    @JsonProperty("closeTime")
    private long closeTime;
    @JsonProperty("high")
    private double high;
    @JsonProperty("low")
    private double low;
    @JsonProperty("latestPrice")
    private double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private long latestUpdate;
    @JsonProperty("latestVolume")
    private long latestVolume;
    @JsonProperty("volume")
    private long volume;
    @JsonProperty("iexRealtimePrice")
    private double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private long iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private long delayedPriceTime;
    @JsonProperty("oddLotDelayedPrice")
    private double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private long oddLotDelayedPriceTime;
    @JsonProperty("extendedPrice")
    private double extendedPrice;
    @JsonProperty("extendedChange")
    private double extendedChange;
    @JsonProperty("extendedChangePercent")
    private double extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private long extendedPriceTime;
    @JsonProperty("previousClose")
    private double previousClose;
    @JsonProperty("previousVolume")
    private long previousVolume;
    @JsonProperty("change")
    private double change;
    @JsonProperty("changePercent")
    private double changePercent;
    @JsonProperty("iexMarketPercent")
    private double iexMarketPercent;
    @JsonProperty("iexVolume")
    private long iexVolume;
    @JsonProperty("avgTotalVolume")
    private long avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private double iexBidPrice;
    @JsonProperty("iexBidSize")
    private long iexBidSize;
    @JsonProperty("iexAskPrice")
    private double iexAskPrice;
    @JsonProperty("iexAskSize")
    private long iexAskSize;
    @JsonProperty("marketCap")
    private long marketCap;
    @JsonProperty("week52High")
    private double week52High;
    @JsonProperty("week52Low")
    private double week52Low;
    @JsonProperty("ytdChange")
    private double ytdChange;
    @JsonProperty("peRatio")
    private double peRatio;
    @JsonProperty("lastTradeTime")
    private long lastTradeTime;
    @JsonProperty("isUSMarketOpen")
    private boolean isUSMarketOpen;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public long getOpen() {
        return open;
    }

    public void setOpen(long open) {
        this.open = open;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public long getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(long latestVolume) {
        this.latestVolume = latestVolume;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public long getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(long iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public double getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    public void setOddLotDelayedPrice(double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    public long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    public void setOddLotDelayedPriceTime(long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public double getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public long getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(long previousVolume) {
        this.previousVolume = previousVolume;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public double getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public long getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(long iexVolume) {
        this.iexVolume = iexVolume;
    }

    public long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public double getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(double iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public long getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(long iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public double getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(double iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public long getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(long iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public boolean isUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setUSMarketOpen(boolean USMarketOpen) {
        isUSMarketOpen = USMarketOpen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IexQuote iexQuote = (IexQuote) o;
        return open == iexQuote.open &&
                openTime == iexQuote.openTime &&
                Double.compare(iexQuote.close, close) == 0 &&
                closeTime == iexQuote.closeTime &&
                Double.compare(iexQuote.high, high) == 0 &&
                Double.compare(iexQuote.low, low) == 0 &&
                symbol.equals(iexQuote.symbol) &&
                companyName.equals(iexQuote.companyName) &&
                calculationPrice.equals(iexQuote.calculationPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, companyName, calculationPrice, open, openTime, close, closeTime, high, low);
    }

    @Override
    public String toString() {
        return "IexQuote{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", calculationPrice='" + calculationPrice + '\'' +
                ", open=" + open +
                ", openTime=" + openTime +
                ", close=" + close +
                ", closeTime=" + closeTime +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
