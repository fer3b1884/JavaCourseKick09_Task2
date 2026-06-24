package by.shved.texttask.exception;

public class TextTaskException extends Exception{
    public TextTaskException() {
        super();
    }

    public TextTaskException(String message) {
        super(message);
    }

    public TextTaskException(Throwable cause) {
        super(cause);
    }

    public TextTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
