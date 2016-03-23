package bank.domain;

import java.io.Serializable;

/**
 * Created by root on 23/3/16.
 * Customer class contains the customer information like name, address, contact details & kys documents.
 */
public class Customer implements Serializable {
    private static final  long serialVersionUID = 1;
    private int id;
    private String name;
    private String address;
    private String contact;
    private String kycType;
    private String KycDocPath;

    /**
     * creating a new customer object
     * @param id  unique id for the customer
     * @param name name of the customer
     * @param address address  of the customer
     * @param contact contact number  of the customer
     * @param kycType tyc type  of the customer
     * @param kycDocPath kyc document of the  of the customer
     */
    public Customer(int id, String name, String address, String contact, String kycType, String kycDocPath) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.kycType = kycType;
        this.KycDocPath = kycDocPath;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", kycType='" + kycType + '\'' +
                ", KycDocPath='" + KycDocPath + '\'' +
                '}';
    }
}
