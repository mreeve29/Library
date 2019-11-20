import BreezySwing.*;

import java.util.ArrayList;

import javax.swing.*;
public class AddBookDialog extends GBDialog{

	private JLabel bookTitleLabel = addLabel("Book Title:",1,1,1,1);
	private JTextField bookTitleField = addTextField("",1,2,1,1);
	
	private JLabel bookAuthorLabel = addLabel("Book Author:",2,1,1,1);
	private JTextField bookAuthorField = addTextField("",2,2,1,1);
	
	private JButton enterButton = addButton("Add book to library",3,2,1,1);
	
	private ArrayList<Book> books;
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String title = bookTitleField.getText();
			if(isBlank(title)) {
				messageBox("Empty title");
				return;
			}
			String author = bookAuthorField.getText();
			if(isBlank(author)) {
				messageBox("Empty author");
				return;
			}
			
			books.add(new Book(title,author,null,null, false));
			dispose();
		}
	}
	
	private boolean isBlank(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!Character.isWhitespace(s.charAt(i))) return false;
		}
		return true;
	}
	
	public AddBookDialog(JFrame parent, ArrayList<Book> list) {
		super(parent);
		books = list;
		this.setVisible(true);
		this.setSize(400,400);
	}
	
}
