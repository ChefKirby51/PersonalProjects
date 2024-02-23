package Projects;
public class DateOfBirth {
    private int dayDOB;
    private int monthDOB;
    private int yearDOB;
    public DateOfBirth(){this(0,0,0);}

    public DateOfBirth(int dayDOB, int monthDOB, int yearDOB) {
        this.dayDOB = dayDOB;
        this.monthDOB = monthDOB;
        this.yearDOB = yearDOB;
    }

    public int getDayDOB() {
        return this.dayDOB;
    }

    public int getMonthDOB() {
        return this.monthDOB;
    }

    public int getYearDOB() {
        return this.yearDOB;
    }

    public void setDayDOB(int dayDOB) {
        this.dayDOB = dayDOB;
    }

    public void setMonthDOB(int monthDOB) {
        if (monthDOB < 1 || monthDOB > 12) monthDOB = 1;
        this.monthDOB = monthDOB;
    }

    public void setYearDOB(int yearDOB) {
        this.yearDOB = yearDOB;
    }
}
