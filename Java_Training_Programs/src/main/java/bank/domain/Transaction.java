package bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 *
 */
public class Transaction implements Serializable{
    private static final  long serialVersionUID = 1;
    private long accountNumber;
    private String transactionType; // withdraw / deposit
    private String transactionMode; // cheque / cash
    private String panNumber; // if required
    private LocalDateTime created;

    /**
     * creating a new tracnsacion object
     * @param accountNumber account number  of the customer
     * @param transactionType transaction type  of the customer
     * @param transactionMode transaction mode  of the customer
     */
    public Transaction(long accountNumber, String transactionType, String transactionMode) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.transactionMode = transactionMode;
        created = LocalDateTime.now();
    }

    public Transaction() {
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber=" + accountNumber +
                ", transactionType='" + transactionType + '\'' +
                ", transactionMode='" + transactionMode + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", created=" + created +
                '}';
    }
}
