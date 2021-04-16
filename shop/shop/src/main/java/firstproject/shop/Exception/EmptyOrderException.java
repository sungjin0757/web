package firstproject.shop.Exception;

public class EmptyOrderException extends RuntimeException{
    public EmptyOrderException() {
        super();
    }

    public EmptyOrderException(String message) {
        super(message);
    }

    public EmptyOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyOrderException(Throwable cause) {
        super(cause);
    }
}
