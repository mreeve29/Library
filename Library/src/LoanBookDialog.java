import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
public class LoanBookDialog extends GBDialog{
	
	private ArrayList<Book> books;
	
	private Book selectedBook;
	private int indexSelected;
	
	private JList bookList = addList(1,1,1,1);
	private JTextArea bookDetails = addTextArea("Book Selected: ",1,2,2,1);
	private JTextField borrowerNameField = addTextField("",2,2,1,1);
	private IntegerField monthField = addIntegerField(1,3,2,1,1);
	private IntegerField dayField = addIntegerField(1,4,2,1,1);
	private IntegerField yearField = addIntegerField(2000,5,2,1,1);
	
	private JLabel borrowLabel = addLabel("Borrower:",2,1,1,1);
	private JLabel monthLabel = addLabel("Month:",3,1,1,1);
	private JLabel dayLabel = addLabel("Day:",4,1,1,1);
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
		if(books.size() == 0)return;
		for(Book b : books) {
			if(!b.isCheckedOut())addItemToList(b.getTitle());
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList list) {
		bookDetails.setText("Book Selected:\n" + books.get(list.getSelectedIndex()).toString());
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
	}
	
	public void listDoubleClicked(JList list, String itemSelected) {
		bookDetails.setText("Book Selected:\n" + books.get(list.getSelectedIndex()).toString());
		selectedBook = books.get(list.getSelectedIndex());
		System.out.println(selectedBook);
		indexSelected = list.getSelectedIndex();
	}
	
	
	public LoanBookDialog(JFrame parent, ArrayList<Book> list, Date d) {
		super(parent);
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));

		books = list;
		populateList();
		
		monthField.setNumber(d.getMonth());
		dayField.setNumber(d.getDay());
		yearField.setNumber(d.getYear());

		this.setTitle("Loan Out Book");
		this.setSize(400,400);
		this.setVisible(true);
	}
}
