import javax.swing.JButton;
import javax.swing.JLabel;

import BreezySwing.GBFrame;
public class LibraryGUI extends GBFrame{
	
	private Library lib;
	private JLabel dateLabel = addLabel("",1,1,1,1);
	private JButton newBookButton = addButton("New Book",2,1,1,1);
	private JButton searchButton = addButton("Search Books",3,1,1,1);
	private JButton newLoanButton = addButton("Loan Out Book",4,1,1,1);
	private JButton returnButton = addButton("Return Book",5,1,1,1);
	private JButton outputAllBooksButton = addButton("Output All Books",6,1,1,1);
	private JButton extraCreditButton = addButton("Extra Credit",7,1,1,1);
	private JButton showAllOverdueBooksButton = addButton("Show Overdue Books",8,1,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == newBookButton) {
			AddBookDialog bookDialog = new AddBookDialog(this,lib.getBooks());
			
		}else if(button == searchButton) {
			SearchDialog sd = new SearchDialog(this, lib.getBooks());
			
		}else if(button == newLoanButton) {
			LoanBookDialog lbd = new LoanBookDialog(this, lib.getBooks(), lib.getDate());
			
		}else if(button == returnButton) {
			ReturnBookDialog rbd = new ReturnBookDialog(this, lib.getBooks());
			
		}else if(button == outputAllBooksButton) {
			OutputDialog od = new OutputDialog(this, lib.getBooks(), "All books in library:", "All Books");
			
		}else if(button == extraCreditButton) {
			ViewAllBooksDialog vabd = new ViewAllBooksDialog(this, lib.getBooks());
			
		}else if(button == showAllOverdueBooksButton) {
			OutputDialog od = new OutputDialog(this, lib.getOverdueBooks(), "Overdue books in library:", "Overdue Books");
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
