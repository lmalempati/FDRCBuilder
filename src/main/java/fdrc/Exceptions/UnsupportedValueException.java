package fdrc.Exceptions;

public class UnsupportedValueException extends RuntimeException {

    public UnsupportedValueException(String msg){
        super("Unsupported value: " + msg);
    }

}
