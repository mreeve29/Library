import java.util.ArrayList;

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
	
	public ArrayList<Book> getOverdueBooks(){
		ArrayList<Book> overdue = new ArrayList<Book>();
		for(Book b : books) {
			if(b.isOverdue(d))overdue.add(b);
		}
		return overdue;
	}
	
	public ArrayList<Book> getCheckedOutBooks(){
		ArrayList<Book> checkedOut = new ArrayList<Book>();
		for(Book b : books) {
			if(b.isCheckedOut())checkedOut.add(b);
		}
		return checkedOut;
	}
	
	public Library() {
		d = new Date();
	}
}
