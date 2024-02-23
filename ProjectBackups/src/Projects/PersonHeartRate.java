package Projects;
import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.of;

public class PersonHeartRate {
    private String firstName;
    private String lastName;
    private DateOfBirth dateOfBirth; //

    public PersonHeartRate(String firstName, String lastName, DateOfBirth dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public DateOfBirth getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int calculateAge(){
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = of(dateOfBirth.getYearDOB(), dateOfBirth.getMonthDOB(), dateOfBirth.getDayDOB());
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
        //Remember to check the of method - use ctrl & left-click to see requirements
    }
    /*
    This method will calculate the user inputted age into years based of off the current date using the
    imported java.time.LocalDate class
    */

    public int maxBPM() {
        //maxBPM = 200 - age(years)
        return 220 - calculateAge();
    }

    public String targetHeartRate(){
        int maxBPM = maxBPM();
        int fiftyPercent = (int) (maxBPM * .5);
        int eightyFivePercent = (int) (maxBPM * .85);
        return fiftyPercent + " - " + eightyFivePercent;
    }

    public void printData(){
        System.out.println(getLastName() + ", " + getFirstName());
        System.out.println("Max heart rate: " + maxBPM());
        System.out.println("Target heart rate: " + targetHeartRate());
    }
}
