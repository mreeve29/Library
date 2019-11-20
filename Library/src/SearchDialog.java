import BreezySwing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
public class SearchDialog extends GBDialog{

	private ArrayList<Book> books;
	
	private JLabel searchLabel = addLabel("Search for book by keyword:",1,1,2,1);
	private JTextField searchField = addTextField("",2,1,2,1);
	private JList list = addList(3,1,1,1);
	private JTextArea bookDetails = addTextArea("",3,2,1,1);
	
	public void listItemSelected(JList list) {
		bookDetails.setText(books.get(list.getSelectedIndex()).toString());
	}
	
	public void listDoubleClicked(JList list, String itemClicked) {
		bookDetails.setText(books.get(list.getSelectedIndex()).toString());
	}
	
	private KeyListener searchFieldKL = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {
			resetList();
			if(isBlank(searchField.getText())) return;
			updateList(searchField.getText());
		}
	};
	
	private void updateList(String search) {
		for(Book b : books) {
			boolean add = false;
			for(String s : b.getKeywords()) {
				String lowercase = s.toLowerCase();
				String searchFinal = search.toLowerCase().trim();
				if(lowercase.contains(searchFinal) || b.getTitle().toLowerCase().contains(searchFinal))add = true;
				break;
			}
			if(add) {
				if(b.isCheckedOut()) {
					addItemToList("<html>" + b.getTitle() + " - " + "<font color='red'>Checked Out</font></html>");
				}else {
					addItemToList("<html>" + b.getTitle() + " - " + "<html><font color='green'>Available</font></html>");
				}
			}
		}
		
	}
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)list.getModel();
        model.addElement(add);
	}
	
	private void resetList() {
		 DefaultListModel model = (DefaultListModel)list.getModel();
	     model.removeAllElements();
	}
	
	private boolean isBlank(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) return false;
		}
		return true;
	}
	
	public SearchDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		
		books = list;
		bookDetails.setEditable(false);
		searchField.addKeyListener(searchFieldKL);
		
		this.setSize(500,400);
		this.setTitle("Book Finder");
		this.setVisible(true);
	}
	
}
