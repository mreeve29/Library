import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
public class BookViewerDialog extends GBDialog{

	private ArrayList<Book> books;
	
	private JLabel label = addLabel("",1,1,1,1);
	private JList<String> bookList = addList(2,1,1,1);
	private JTextArea bookDetails = addTextArea("",2,2,1,1);
	
	public BookViewerDialog(JFrame parent, ArrayList<Book> list, String dialogTitle, String labelText) {
		super(parent);
		
		books = list;
		
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		label.setText(labelText);
		
		populateList();
		
		this.setTitle(dialogTitle);
		this.setSize(400,400);
		this.setVisible(true);
	}

	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
//			if(b.isCheckedOut()) {
//				addItemToList("<html>" + b.getTitle() + " - " + "<font color='red'>Checked Out</font></html>");
//			}else {
//				addItemToList("<html>" + b.getTitle() + " - " + "<html><font color='green'>Available</font></html>");
//			}
			addItemToList(b.getTitle());
		}
	}
	
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList list) {
		bookDetails.setText(books.get(list.getSelectedIndex()).toString());
	}
	
	public void listDoubleClicked(JList list, String itemClicked) {
		bookDetails.setText(books.get(list.getSelectedIndex()).toString());
	}

}