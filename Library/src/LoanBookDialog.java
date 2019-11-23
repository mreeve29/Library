import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class LoanBookDialog extends GBDialog{
	
	private ArrayList<Book> books;
	private ArrayList<Book> available = new ArrayList<Book>();
	
	private Book selectedBook;
	private int indexSelected;
	
	private Date current;
	
	private JList<String> bookList = addList(1,1,1,1);
	private JTextArea bookDetails = addTextArea("Book Selected: ",1,2,2,1);
	private JTextField borrowerNameField = addTextField("",2,2,1,1);
	private IntegerField monthField = addIntegerField(1,3,2,1,1);
	private IntegerField dayField = addIntegerField(1,4,2,1,1);
	private IntegerField yearField = addIntegerField(2000,5,2,1,1);
	
	@SuppressWarnings("unused")
	private JLabel borrowLabel = addLabel("Borrower:",2,1,1,1);
	@SuppressWarnings("unused")
	private JLabel monthLabel = addLabel("Month:",3,1,1,1);
	@SuppressWarnings("unused")
	private JLabel dayLabel = addLabel("Day:",4,1,1,1);
	@SuppressWarnings("unused")
	private JLabel yearLabel = addLabel("Year:",5,1,1,1);
	
	private JButton enterButton = addButton("Loan book",6,2,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			
			if(selectedBook == null) {
				messageBox("Please select a book to loan out");
				return;
			}
			
			String borrower = borrowerNameField.getText();
			if(isBlank(borrower)) {
				messageBox("Empty borrower");
				return;
			}
			
			Date d = new Date(monthField.getNumber(), dayField.getNumber(), yearField.getNumber());
			
			try {
				d.validateDate();
			}catch(IllegalDateException e) {
				messageBox(e.getMessage());
				return;
			}
			
			if(d.getTime() > current.getTime()) {
				messageBox("Loan date cannot be before current date");
				return;
			}
			
			Book updated = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), borrowerNameField.getText(), d,true);
			books.set(indexSelected, updated);

			dispose();
		}
	}
	
	private boolean isBlank(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) return false;
		}
		return true;
	}
	

	private void populateList() {
		if(available.size() == 0)return;
		for(Book b : available) {
			addItemToList(b.getTitle());
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel<String> model = (DefaultListModel<String>)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList<String> list) {
		bookDetails.setText("Book Selected:\n" + available.get(list.getSelectedIndex()).toString());
		selectedBook = available.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
	}
	
	public void listDoubleClicked(JList<String> list, String itemSelected) {
		bookDetails.setText("Book Selected:\n" + available.get(list.getSelectedIndex()).toString());
		selectedBook = available.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
	}
	
	
	public LoanBookDialog(JFrame parent, Library lib) {
		super(parent);
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));

		current = new Date(lib.getDate());
		
		books = lib.getBooks();
		for(Book b : books) {
			if(!b.isCheckedOut())available.add(b);
		}
		populateList();
		
		current = lib.getDate();
		
		monthField.setNumber(current.getMonth());
		dayField.setNumber(current.getDay());
		yearField.setNumber(current.getYear());

		this.setTitle("Loan Out Book");
		this.setSize(400,400);
		this.setVisible(true);
	}
}
