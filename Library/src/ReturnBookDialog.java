import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class ReturnBookDialog extends GBDialog{
	
	//class objects
	private ArrayList<Book> books;
	private ArrayList<Book> checkedOut = new ArrayList<Book>();
	
	private Book selectedBook;
	private int indexSelected;
	
	//elements
	private JList<String> bookList = addList(1,1,1,1);
	private JTextArea bookDetails = addTextArea("Book Selected: ",1,2,2,1);
	
	private JButton enterButton = addButton("Return Book",3,2,1,1);
	private JButton cancelButton = addButton("Cancel",3,1,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			if(selectedBook == null) {
				messageBox("Please select a book to return");
				return;
			}
			Book updated = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), null, null, false);
			books.set(indexSelected, updated);
			dispose();
		}else if(button == cancelButton) {
			dispose();
		}
	}
	
	//populates list with books
	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
			if(b.isCheckedOut())addItemToList(b.getTitle());
		}
	}
	
	//helper method that adds one String to list
	private void addItemToList(String add) {
		DefaultListModel<String> model = (DefaultListModel<String>)bookList.getModel();
        model.addElement(add);
	}
	
	//list event listeners
	public void listItemSelected(JList<String> list, String itemSelected) {
		bookDetails.setText("Book Selected:\n" + checkedOut.get(list.getSelectedIndex()).toString());
		selectedBook = checkedOut.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
		revalidate();
	}
	
	public void listDoubleClicked(JList<String> list, String itemSelected) {
		bookDetails.setText("Book Selected:\n" + checkedOut.get(list.getSelectedIndex()).toString());
		selectedBook = checkedOut.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
		revalidate();
	}
	
	//constructor
	public ReturnBookDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));

		books = list;
		for(Book b : list) {
			if(b.isCheckedOut())checkedOut.add(b);
		}
		populateList();
		

		this.setTitle("Return Book");
		this.setSize(600,400);
		this.setVisible(true);
	}
}
