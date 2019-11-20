import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
public class ReturnBookDialog extends GBDialog{
	
	private ArrayList<Book> books;
	private ArrayList<Book> checkedOut = new ArrayList<Book>();
	
	private Book selectedBook;
	private int indexSelected;
	
	private JList bookList = addList(1,1,1,1);
	private JTextArea bookDetails = addTextArea("Book Selected: ",1,2,2,1);
	
	private JButton enterButton = addButton("Return Book",3,2,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			if(selectedBook == null) {
				messageBox("Please select a book to return");
				return;
			}
			Book updated = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), null, null, false);
			books.set(indexSelected, updated);
			dispose();
		}
	}
	
	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
			if(b.isCheckedOut())addItemToList(b.getTitle());
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList list) {
		bookDetails.setText("Book Selected:\n" + checkedOut.get(list.getSelectedIndex()).toString());
		selectedBook = checkedOut.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
	}
	
	public void listDoubleClicked(JList list) {
		bookDetails.setText("Book Selected:\n" + checkedOut.get(list.getSelectedIndex()).toString());
		selectedBook = checkedOut.get(list.getSelectedIndex());
		indexSelected = books.indexOf(selectedBook);
	}
	
	
	public ReturnBookDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));

		books = list;
		for(Book b : list) {
			if(b.isCheckedOut())checkedOut.add(b);
		}
		populateList();
		

		this.setTitle("Loan Out Book");
		this.setSize(400,400);
		this.setVisible(true);
	}
}
