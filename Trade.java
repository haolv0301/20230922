import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    private LocalDateTime tradeDateTime;
    private String code;
    private TradeType type;
    private long quantity;

    public Trade() {
    }

    public LocalDateTime getTradeDateTime() {
        return tradeDateTime;
    }

    public void setTradeDateTime(LocalDateTime tradeDateTime) {
        this.tradeDateTime = tradeDateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Trade(LocalDateTime tradeDateTime, String code, TradeType type, long quantity, BigDecimal price) {
        this.tradeDateTime = tradeDateTime;
        this.code = code;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    private BigDecimal price;


}
