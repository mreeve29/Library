import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class BookViewerDialog extends GBDialog{

	private Date current;
	private ArrayList<Book> books;
	
	private JLabel label = addLabel("",1,1,1,1);
	private JList<String> bookList = addList(2,1,1,1);
	private JTextArea bookDetails = addTextArea("",2,2,1,1);
	
	private JButton closeButton = addButton("Close",3,2,1,1);
	
	
	public void buttonClicked(JButton button) {
		if(button == closeButton) {
			dispose();
		}
	}
	
	public BookViewerDialog(JFrame parent, ArrayList<Book> list, String dialogTitle, String labelText, Date d) {
		super(parent);
		
		books = list;
		
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		label.setText(labelText);
		
		populateList();
		
		current = new Date(d);
		
		this.setTitle(dialogTitle);
		this.setSize(400,400);
		this.setVisible(true);
	}

	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
			addItemToList(b.getTitle());
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel<String> model = (DefaultListModel<String>)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList<String> list) {
		if(books.get(list.getSelectedIndex()).isOverdue(current)) {
			bookDetails.setText(books.get(list.getSelectedIndex()).toString() + "\nOVERDUE");
		}else {
			bookDetails.setText(books.get(list.getSelectedIndex()).toString());
		}
		revalidate();
	}
	
	public void listDoubleClicked(JList<String> list, String itemClicked) {
		if(books.get(list.getSelectedIndex()).isOverdue(current)) {
			bookDetails.setText(books.get(list.getSelectedIndex()).toString() + "\nOVERDUE");
		}else {
			bookDetails.setText(books.get(list.getSelectedIndex()).toString());
		}
		revalidate();
	}

}