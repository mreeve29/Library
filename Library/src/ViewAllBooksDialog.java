import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import BreezySwing.GBDialog;
public class ViewAllBooksDialog extends GBDialog{

	private ArrayList<Book> books;
	
	private Book selectedBook;
	private int indexSelected;
	
	private JList bookList = addList(1,1,1,1);
	
	private JFrame main;
	
	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
			if(b.isCheckedOut()) {
				addItemToList("<html>" + b.getTitle() + " - " + "<font color='red'>Checked Out</font></html>");
			}else {
				addItemToList("<html>" + b.getTitle() + " - " + "<html><font color='green'>Available</font></html>");
			}
			
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList list) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
		openDialog();
	}
	
	public void listDoubleClicked(JList list, String itemClicked) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
		openDialog();
	}
	
	private void openDialog() {
		if(selectedBook.isCheckedOut()) {
			//open return dialog
			ReturnSingleBookDialog rsbd = new ReturnSingleBookDialog(main, books, selectedBook, indexSelected);
			refreshList();
		}else {
			//open check out dialog
			LoanSingleBookDialog lsbd = new LoanSingleBookDialog(main, books, selectedBook, indexSelected, new Date());
			refreshList();
		}
	}
	
	private void refreshList() {
		 DefaultListModel model = (DefaultListModel)bookList.getModel();
	     model.removeAllElements();
	     populateList();
	}

	public ViewAllBooksDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		main = parent;
		books = list;
		populateList();
		this.setTitle("Extra Credit");
		this.setSize(400,400);
		this.setVisible(true);
	}

}
