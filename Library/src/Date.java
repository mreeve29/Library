import java.util.Calendar;
import java.util.TimeZone;

public class Date {

	//class object
	private Calendar c;
	
	//class variables
	private int month;
	private int day;
	private int year;
	private long timeInMillis;
	
	//used to determine overdue books
	public static final long FOURTEEN_DAYS = 1210000000;
	
	//constructors
	public Date(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
		updateTime();
	}
	
	public Date() {
		c = Calendar.getInstance();
		day = c.get(Calendar.DATE);
		month = c.get(Calendar.MONTH) + 1;
		year = c.get(Calendar.YEAR);
		c = removeTimeFromDate(c);
		updateTime();
	}
	
	//default constructor sets date to current date
	public Date(Date d) {
		month = d.getMonth();
		day = d.getDay();
		year = d.getYear();
		updateTime();
	}
	
	//getters
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public long getTime() {
		return timeInMillis;
	}

	public static int getDaysInMonth(int month, int year) {
		int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
		if(month == 2) {
			if(year % 4 == 0 || (year % 100 == 0 && year % 400 == 0)) {
				return 29;
			}else {
				return 28;
			}
		}
		return months[month-1];
	}

	public String toString() {
		return month + "/" + day + "/" + year;
	}

	//setters
	public void setDay(int d) {
		day = d;
		updateTime();
	}
	
	public void setMonth(int m) {
		month = m;
		updateTime();
	}
	
	public void setYear(int y) {
		year = y;
		updateTime();
	}
	
	//updates millisecond time
	private void updateTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		cal = removeTimeFromDate(cal);
		timeInMillis = cal.getTimeInMillis();
		
	}
	
	//helper method to remove hours minutes and seconds in milliseconds calculation
	public static Calendar removeTimeFromDate(Calendar c) {
		Calendar cal = c;
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	//error checks date
	public void validateDate() throws IllegalDateException{
		if(month > 12 || month < 1) {
			throw new IllegalDateException("Invalid month");
		}
		int max = getDaysInMonth(month, year);
		if(day < 1 || day > max) {
			throw new IllegalDateException("Invalid day");
		}
		if(year < 0) {
			throw new IllegalDateException("Invalid year");
		}
	}

}
