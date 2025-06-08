package exception;

public class OverdueBookException extends Exception{
    public OverdueBookException(String message){
        //after service layer
        super(message);
    }

}
