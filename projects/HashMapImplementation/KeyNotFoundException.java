package Projects.HashMapImplementation;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException() {
        super("This key is not in this HashMap");
    }
}
