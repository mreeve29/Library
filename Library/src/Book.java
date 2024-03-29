import java.util.Calendar;

public class Book {
	
	//instance class variables
	private String title;
	private String author;
	private String borrower;
	private Date borrowDate;
	private boolean checkedOut;
	private String[] keywords;
	
	//constructors
	public Book() {
		title = "";
		author = "";
		borrower = null;
		borrowDate = null;
		checkedOut = false;
	}
	
	public Book(String nm, String auth, String borrow, Date date, boolean check) {
		title = nm;
		author = auth;
		borrower = borrow;
		borrowDate = date;
		checkedOut = check;
		keywords = title.split(" ");
	}
	
	//getters
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getBorrower() {
		return borrower;
	}
	
	public Date getDate() {
		return borrowDate;
	}
	
	public String[] getKeywords() {
		return keywords;
	}

	public boolean isCheckedOut() {
		return checkedOut;
	}
	
	public String toString() {
		String out =  
				"Title: " + title + '\n' + 
				"Author: " + author + '\n';
		if(checkedOut) {
			out += "Loaned Out\n" + 
					"Borrower: " + borrower + '\n' +
					"Date Checked Out: " + borrowDate;
		}else {
			out += "Available\n";
		}	
		return out;
		
	}

	//setters
	public void setTitle(String nm) {
		title = nm;
	}
	
	public void setAuthor(String auth) {
		author = auth;
	}
	
	public void setBorrower(String borrow) {
		borrower = borrow;
	}
	
	public void borrowDate(Date d) {
		borrowDate = d;
	}
	
	public void setCheckedOut(boolean b) {
		checkedOut = b;
	}
	
	public boolean isOverdue(Date d) {
		Calendar newCal = Calendar.getInstance();
		newCal.set(d.getYear(), d.getMonth(), d.getDay());
		newCal = Date.removeTimeFromDate(newCal);
		if(!checkedOut)return false;
		if(newCal.getTimeInMillis() - borrowDate.getTime() > Date.FOURTEEN_DAYS) {
			return true;
		}else return false;
	}
	
	
}
