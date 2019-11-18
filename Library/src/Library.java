import java.util.ArrayList;

public class Library {
	
	private ArrayList<Book> books = new ArrayList<Book>();
	
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
	
}
