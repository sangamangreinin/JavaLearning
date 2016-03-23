import com.ininbank.domain.IninBankMumbai;
import com.ininbank.util.UserInput;


/**
 * Created by Deepak on 23/3/16.
 */
public class RunBank {
    public static void main(String[] args) {
        boolean run = true;
        IninBankMumbai ininBankMumbai = new IninBankMumbai();
        while(run){
            showMenu();
            int option = UserInput.acceptNumberInput("Please enter your option:");

            switch (option){
                case 1:
                    ininBankMumbai.createAccount();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 0:
                    run = false;
                    break;
            }

        }
    }

    public static void showMenu(){
        System.out.println("1: Open new account");
        System.out.println("2: Deposit amount");
        System.out.println("3: Withdraw amount");
        System.out.println("4: View account details");
        System.out.println("5: View transactions");
        System.out.println("6: Add documents");
        System.out.println("0: QUIT");

    }

}
