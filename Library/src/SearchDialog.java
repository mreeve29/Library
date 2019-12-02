import BreezySwing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class SearchDialog extends GBDialog{

	//class objects
	private ArrayList<Book> allBooks;
	private ArrayList<Book> booksInList = new ArrayList<Book>();
	private Date current;
	
	//elements
	@SuppressWarnings("unused")
	private JLabel searchLabel = addLabel("Search for book by keyword:",1,1,2,1);
	private JTextField searchField = addTextField("",2,1,2,1);
	private JList<String> list = addList(3,1,1,1);
	private JTextArea bookDetails = addTextArea("",3,2,1,1);
	
	private JButton closeButton = addButton("Close",4,1,2,1);
	
	public void buttonClicked(JButton button) {
		if(button == closeButton) {
			dispose();
		}
	}
	
	//list event listeners
	public void listItemSelected(JList<String> list) {
		if(booksInList.get(list.getSelectedIndex()).isOverdue(current)) {
			bookDetails.setText(booksInList.get(list.getSelectedIndex()).toString() + "\nOVERDUE");
		}else {
			bookDetails.setText(booksInList.get(list.getSelectedIndex()).toString());
		}
		revalidate();
	}
	
	public void listDoubleClicked(JList<String> list, String itemClicked) {
		if(booksInList.get(list.getSelectedIndex()).isOverdue(current)) {
			bookDetails.setText(booksInList.get(list.getSelectedIndex()).toString() + "\nOVERDUE");
		}else {
			bookDetails.setText(booksInList.get(list.getSelectedIndex()).toString());
		}
		revalidate();
	}
	
	//KeyListener that handles when a key is pressed
	private KeyListener searchFieldKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {
			bookDetails.setText("");
			resetList();
			if(isBlank(searchField.getText())) return;
			updateList(searchField.getText());
		}
	};
	
	//updates the list when search field is updated
	private void updateList(String search) {
		booksInList.clear();
		for(Book b : allBooks) {
			boolean add = false;
			for(String s : b.getKeywords()) {
				String lowercase = s.toLowerCase();
				String searchFinal = search.toLowerCase().trim();
				if(lowercase.contains(searchFinal) || b.getTitle().toLowerCase().contains(searchFinal))add = true;
				break;
			}
			if(add) {
				booksInList.add(b);
				if(b.isCheckedOut()) {
					if(b.isOverdue(current)) {
						addItemToList("<html>" + b.getTitle() + " - " + "<font color='red'>OVERDUE</font></html>");
					}else {
						addItemToList("<html>" + b.getTitle() + " - " + "<font color='red'>Checked Out</font></html>");
					}
				}else {
					addItemToList("<html>" + b.getTitle() + " - " + "<html><font color='green'>Available</font></html>");
				}
			}
		}
		
	}
	
	//helper method that adds a single String to the list
	private void addItemToList(String add) {
		DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
        model.addElement(add);
	}
	
	private void resetList() {
		 DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
	     model.removeAllElements();
	}
	
	private boolean isBlank(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) return false;
		}
		return true;
	}
	
	//constructor
	public SearchDialog(JFrame parent, Library lib) {
		super(parent);
		
		allBooks = lib.getBooks();
		bookDetails.setEditable(false);
		searchField.addKeyListener(searchFieldKL);
		
		current = lib.getDate();
		
		this.setSize(676,434);
		this.setTitle("Book Finder");
		this.setVisible(true);
	}
	
}
