import javax.swing.JButton;
import javax.swing.JLabel;

import BreezySwing.GBFrame;
@SuppressWarnings("serial")
public class LibraryGUI extends GBFrame{
	
	private Library lib;
	private JLabel dateLabel = addLabel("",1,1,1,1);
	private JButton newBookButton = addButton("New Book",2,1,1,1);
	private JButton searchButton = addButton("Search Books",3,1,1,1);
	private JButton newLoanButton = addButton("Loan Out Book",4,1,1,1);
	private JButton returnButton = addButton("Return Book",5,1,1,1);
	private JButton outputAllBooksButton = addButton("Show All Books",2,2,1,1);
	private JButton extraCreditButton = addButton("Extra Credit",6,1,2,1);
	private JButton showAllOverdueBooksButton = addButton("Show Overdue Books",5,2,1,1);
	private JButton showAllLoanedBooksButton = addButton("Show Loaned Books",4,2,1,1);
	private JButton showAllAvailableBooksButton = addButton("Show Available Books",3,2,1,1);
	
	@SuppressWarnings("unused")
	public void buttonClicked(JButton button) {
		if(button == newBookButton) {
			AddBookDialog bookDialog = new AddBookDialog(this,lib.getBooks());
			
		}else if(button == searchButton) {
			SearchDialog sd = new SearchDialog(this, lib);
			
		}else if(button == newLoanButton) {
			LoanBookDialog lbd = new LoanBookDialog(this, lib);
			
		}else if(button == returnButton) {
			ReturnBookDialog rbd = new ReturnBookDialog(this, lib.getBooks());
			
		}else if(button == outputAllBooksButton) {
			if(lib.getBooks().size() == 0) {
				messageBox("There are no books");
				return;
			}
			OutputDialog od = new OutputDialog(this, lib.getBooks(), "All books:", "All Books");
			
		}else if(button == extraCreditButton) {
			ViewAllBooksDialog vabd = new ViewAllBooksDialog(this, lib.getBooks(), lib.getDate());
		}else if(button == showAllOverdueBooksButton) {
			if(lib.getOverdueBooks().size() == 0) {
				messageBox("There are no avaliable books");
				return;
			}
			BookViewerDialog bvd = new BookViewerDialog(this, lib.getOverdueBooks(), "Overdue books:", "Overdue books", lib.getDate());
		}else if(button == showAllLoanedBooksButton) {
			if(lib.getCheckedOutBooks().size() == 0) {
				messageBox("There are no loaned books");
				return;
			}
			BookViewerDialog bvd = new BookViewerDialog(this, lib.getCheckedOutBooks(), "Loaned out books:", "Loaned out books", lib.getDate());
		}else if(button == showAllAvailableBooksButton) {
			if(lib.getAvailableBooks().size() == 0) {
				messageBox("There are no avaliable books");
				return;
			}
			OutputDialog od = new OutputDialog(this, lib.getAvailableBooks(), "Books in Library:", "Available Books");
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
