package bank.domain;

/**
 * Created by root on 23/3/16.
 *
 */
public class Kyc {
    private String kycType;
    private String path;

    public Kyc(String kycType, String path) {
        this.kycType = kycType;
        this.path = path;
    }

    public void addKycDocuments(){

    }

    @Override
    public String toString() {
        return "Kyc{" +
                "kycType='" + kycType + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
