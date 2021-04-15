package firstproject.shop.Exception;

public class NotEnoughStockQuantityException extends RuntimeException{
    public NotEnoughStockQuantityException() {
        super();
    }

    public NotEnoughStockQuantityException(String message) {
        super(message);
    }

    public NotEnoughStockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockQuantityException(Throwable cause) {
        super(cause);
    }
}
