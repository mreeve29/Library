import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class LoanSingleBookDialog extends GBDialog{
	
	//class objects
	private ArrayList<Book> books;
	
	private Book selectedBook;
	private int indexSelected;
	
	private Date current;
	
	//elements
	@SuppressWarnings("unused")
	private JLabel loanLabel = addLabel("Are you sure you want to loan out...",1,1,1,1);
	private JTextArea bookDetails = addTextArea("",2,1,2,1);
	private JTextField borrowerNameField = addTextField("",3,2,1,1);
	private IntegerField monthField = addIntegerField(1,4,2,1,1);
	private IntegerField dayField = addIntegerField(1,5,2,1,1);
	private IntegerField yearField = addIntegerField(2000,6,2,1,1);
	
	@SuppressWarnings("unused")
	private JLabel borrowLabel = addLabel("Borrower:",3,1,1,1);
	@SuppressWarnings("unused")
	private JLabel monthLabel = addLabel("Month:",4,1,1,1);
	@SuppressWarnings("unused")
	private JLabel dayLabel = addLabel("Day:",5,1,1,1);
	@SuppressWarnings("unused")
	private JLabel yearLabel = addLabel("Year:",6,1,1,1);
	
	private JButton enterButton = addButton("Loan book",7,2,1,1);
	private JButton cancelButton = addButton("Cancel",7,1,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String borrower = borrowerNameField.getText();
			if(isBlank(borrower)) {
				messageBox("Empty borrower");
				return;
			}

			if(!yearField.isValidNumber()) {
				messageBox("Invalid year");
				return;
			}
			
			Date d = new Date(monthField.getNumber(), dayField.getNumber(), yearField.getNumber());
			
			try {
				d.validateDate();
			}catch(IllegalDateException e) {
				messageBox(e.getMessage());
				return;
			}

			
			Book updated = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), borrower, d, true);
			books.set(indexSelected, updated);
			dispose();
		}else if(button == cancelButton) {
			dispose();
		}
	}
	
	private boolean isBlank(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) return false;
		}
		return true;
	}
	
	//constructor
	public LoanSingleBookDialog(JFrame parent, ArrayList<Book> list, Book b, int i, Date d) {
		super(parent);
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		current = new Date(d);
		
		dayField.setNumber(current.getDay());
		monthField.setNumber(current.getMonth());
		yearField.setNumber(current.getYear());

		selectedBook = b;
		books = list;
		indexSelected = i;

		bookDetails.setText(selectedBook.toString());
		this.setTitle("Loan Out Book");
		this.setSize(400,400);
		this.setVisible(true);
	}
}
