import BreezySwing.*;

import java.util.ArrayList;

import javax.swing.*;
public class ViewAllBooksDialog extends GBDialog{

	private ArrayList<Book> books;
	
	private Book selectedBook;
	private int indexSelected;
	
	private JList bookList = addList(1,1,1,1);
	
	
	
	private void populateList() {
		if(books.size() == 0)return;
		for(Book b : books) {
			addItemToList(b.getTitle() + '\n' + b.isCheckedOut());
		}
	}
	
	private void addItemToList(String add) {
		DefaultListModel model = (DefaultListModel)bookList.getModel();
        model.addElement(add);
	}
	
	public void listItemSelected(JList list) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
	}
	
	public void listDoubleClicked(JList list) {
		selectedBook = books.get(list.getSelectedIndex());
		indexSelected = list.getSelectedIndex();
	}
	
	private void openDialog() {
		if(selectedBook.isCheckedOut()) {
			//open return dialog
			//ReturnSingleBookDialog rsbd = new ReturnSingleBookDialog(this, books, selectedBook, indexSelected);
		}else {
			//open check out dialog
		}
	}
	
	public ViewAllBooksDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		books = list;
		populateList();
		this.setTitle("Extra Credit");
		this.setSize(400,400);
		this.setVisible(true);
	}

}
