import java.util.ArrayList;

import javax.swing.*;
import BreezySwing.GBDialog;
@SuppressWarnings("serial")
public class ViewAllBooksDialog extends GBDialog{

	private ArrayList<Book> books;
	
	private Book selectedBook;
	private int indexSelected;
	
	@SuppressWarnings("unused")
	private JLabel label = addLabel("All books in library. Click on a book to check it out or return it",1,1,1,1);
	private JList<String> bookList = addList(2,1,1,1);
	
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
		DefaultListModel<String> model = (DefaultListModel<String>)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList<String> list) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
		openDialog();
	}
	
	public void listDoubleClicked(JList<String> list, String itemClicked) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
		openDialog();
	}
	
	@SuppressWarnings("unused")
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
		 DefaultListModel<String> model = (DefaultListModel<String>)bookList.getModel();
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
