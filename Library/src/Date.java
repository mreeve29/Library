
public class Date {

	private int month;
	private int day;
	private int year;
	
	public Date(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
	}
	
	public Date() {
		month = 1;
		day = 1;
		year = 2000;
	}
	
	public Date(Date d) {
		month = d.getMonth();
		day = d.getDay();
		year = d.getYear();
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setDay(int d) {
		day = d;
	}
	
	public void setMonth(int m) {
		month = m;
	}
	
	public void setYear(int y) {
		year = y;
	}
	
	public String toString() {
		return month + "/" + day + "/" + year;
	}
	
	public static int getDaysInMonth(int month, int year) {
		switch(month) {
		case 1:
			return 31;
		case 2:
			if(year % 4 == 0 || (year % 100 == 0 && year % 400 == 0)) {
				return 29;
			}else {
				return 28;
			}
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return -1;
		}
	}
	
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
