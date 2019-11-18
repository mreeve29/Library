import java.util.ArrayList;
import java.util.Calendar;

public class Library {
	
	private ArrayList<Book> books = new ArrayList<Book>();
	
	private Date d;
	
	public void addBook(Book b) {
		books.add(b);
	}
	
	public Book getBook(int i) {
		return books.get(i);
	}
	
	public int getAmountOfBooks() {
		return books.size();
	}
	
	public ArrayList<Book> getBooks(){
		return books;
	}
	
	public Date getDate() {
		return d;
	}
	
	public Library() {
		int day = Calendar.getInstance().get(Calendar.DATE);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		d = new Date(month,day,year);
	}
	
}
