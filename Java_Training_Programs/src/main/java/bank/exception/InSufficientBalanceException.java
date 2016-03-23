package bank.exception;

/**
 * Created by root on 23/3/16.
 *
 */
public class InSufficientBalanceException extends Exception{
    public InSufficientBalanceException() {}
    public InSufficientBalanceException(String msg) {super(msg);}
}
