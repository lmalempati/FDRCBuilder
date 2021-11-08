package fdrc.Exceptions;

public class UnsupportedEnumValueException extends RuntimeException {

    public UnsupportedEnumValueException(String msg){
        super("Unsupported value: " + msg);
    }

}
