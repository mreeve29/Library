import BreezySwing.*;
import javax.swing.*;
public class LibraryGUI extends GBFrame{
	
	private Library lib;
	private JLabel dateLabel = addLabel("",1,1,1,1);
	private JButton newBookButton = addButton("New Book",2,1,1,1);
	private JButton searchButton = addButton("Search Books",3,1,1,1);
	private JButton newLoanButton = addButton("Loan Out Book",4,1,1,1);
	private JButton returnButton = addButton("Return Book",5,1,1,1);
	private JButton outputAllBooksButton = addButton("Output All Books",6,1,1,1);
	private JButton extraCreditButton = addButton("Extra Credit(beta)",7,1,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == newBookButton) {
			AddBookDialog bookDialog = new AddBookDialog(this);
			lib.addBook(bookDialog.getNewBook());
		}else if(button == searchButton) {
			
		}else if(button == newLoanButton) {
			LoanBookDialog lbd = new LoanBookDialog(this, lib.getBooks());
			
		}else if(button == returnButton) {
			ReturnBookDialog rbd = new ReturnBookDialog(this, lib.getBooks());
			
		}else if(button == outputAllBooksButton) {
			for(Book b : lib.getBooks()) {
				System.out.println(b);
			}
		}else if(button == extraCreditButton) {
			ViewAllBooksDialog vabd = new ViewAllBooksDialog(this, lib.getBooks());
		}
	}
	
	public LibraryGUI() {
		lib = new Library();
		dateLabel.setText("Current Date: " + lib.getDate().toString());
	}
	
	public static void main(String[] args) {
		LibraryGUI frm = new LibraryGUI();
		frm.setTitle("Library Manager");
		frm.setVisible(true);
		frm.setSize(400,400);
	}

}
