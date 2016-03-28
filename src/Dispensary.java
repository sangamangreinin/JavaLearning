import com.inin.dispensary.logger.Log;
import com.inin.dispensary.model.Appointment;
import com.inin.dispensary.model.Doctor;
import com.inin.dispensary.model.Medicine;
import com.inin.dispensary.model.Patient;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sangam on 28/3/16.
 */
public class Dispensary {
    private ServerSocket serverSocket;
    private int port = 9001;
    private static Doctor doctor = null;
    private static Scanner scanner = null;
    public static void main(String[] args)
    {
        doctor = Doctor.create(args[0], args[1], args[2]);
        scanner = new Scanner(System.in);
        Dispensary dispensary = new Dispensary();
        try {
            dispensary.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start dispensary server.
     * On this server patients can make a call to view Appointments as well initialize chat
     */
    public void start() throws IOException {
        while(true) {
            String id;
            String name;
            String address;

            Log.x("Please select your choice " +
                    "\n 1. View All Appointments" +
                    "\n 2. Book New Appointment" +
                    "\n 3. Create New patient" +
                    "\n 4. Add medicines to patient");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: // view all appointments
                    doctor.displayAppointments();
                    break;

                case 2: // Book appointment
                    Log.x("Enter id of patient");
                    id = scanner.nextLine();
                    Log.x("Enter name of patient");
                    name = scanner.nextLine();
                    Log.x("Enter address of patient");
                    address = scanner.nextLine();
                    Patient patient = Patient.create(id, name, address);
                    Log.x("Enter appointment date");
                    String appointmentDate = scanner.nextLine();
                    Log.x("Enter appointment Start Time");
                    int starttime = scanner.nextInt();
                    scanner.nextLine();
                    Log.x("Enter appointment duration");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    Appointment appointment = doctor.bookAppointment(patient, appointmentDate, starttime, starttime+duration);
                    appointment.displayAppointment();

                    break;

                case 3: // create patient
                    Log.x("Enter id of patient");
                    id = scanner.nextLine();
                    Log.x("Enter name of patient");
                    name = scanner.nextLine();
                    Log.x("Enter address of patient");
                    address = scanner.nextLine();
                    Log.x("Enter summary of patient");
                    String summary = scanner.nextLine();
                    Log.x("Enter number of medicines");
                    int count = scanner.nextInt();
                    scanner.nextLine();
                    List<Medicine> medicines = new ArrayList<Medicine>();
                    for (int i = 0; i < count; i++) {
                        Log.x("Enter medicine name");
                        String medicineName = scanner.nextLine();
                        Log.x("Enter quantity");
                        int quantuity = scanner.nextInt();
                        scanner.nextLine();
                        Log.x("Enter 1 before food / 2 after food");
                        boolean beforeFood = false;
                        int beforeFoodValue = scanner.nextInt();
                        scanner.nextLine();
                        if (beforeFoodValue == 1)
                            beforeFood = true;
                        Log.x("Enter time diff before / after food");
                        int timeDiff = scanner.nextInt();
                        scanner.nextLine();
                        Medicine medicine = Medicine.create(medicineName, quantuity, beforeFood, timeDiff);
                        medicines.add(medicine);
                    }

                    doctor.createPatient(id, name, address, summary, medicines);
                    break;
            }
        }
    }
}
