package Projects;

import java.util.ArrayList;
import java.util.Scanner;

public class HeartRateDemo {
    // Formula for Maximum heart rate in beats per minute: maxBPM = 220 - age(in years)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<PersonHeartRate> personHeartRatesList = new ArrayList<>();

        System.out.println("Please enter your first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = sc.nextLine();
        System.out.println("And lastly, enter your date of birth, please enter the day, month and year individually");
        int birthDay = Integer.parseInt(sc.nextLine());
        int birthMonth = Integer.parseInt(sc.nextLine());
        int birthYear = Integer.parseInt(sc.nextLine());


        DateOfBirth dateOfBirth = new DateOfBirth(birthDay, birthMonth, birthYear);
        PersonHeartRate personHeartRate = new PersonHeartRate(firstName, lastName, dateOfBirth);

        personHeartRatesList.add(personHeartRate);

        for (PersonHeartRate person : personHeartRatesList) {
            person.printData();
            System.out.println();
        }

    }
}
