import BreezySwing.*;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;
@SuppressWarnings("serial")
public class ReturnSingleBookDialog extends GBDialog{

	private ArrayList<Book> books;
	private Book book;
	private int index;
	
	@SuppressWarnings("unused")
	private JLabel returnLabel = addLabel("Are you sure you want to return?",1,1,2,1);
	
	private JTextArea bookDetails = addTextArea("",2,1,2,1);
	
	private JButton enterButton = addButton("Return book",3,2,1,1);
	private JButton cancelButton = addButton("Cancel",3,1,1,1);
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			Book updated = new Book(book.getTitle(), book.getAuthor(), null, null, false);
			books.set(index, updated);
			dispose();
		}else if(button == cancelButton) {
			dispose();
		}
	}
	
	public ReturnSingleBookDialog(JFrame parent, ArrayList<Book> list, Book b, int i) {
		super(parent);
		
		books = list;
		book = b;
		index = i;
		
		bookDetails.setEditable(false);
		bookDetails.setFont(new Font("SansSerif", Font.PLAIN, 14));
		bookDetails.setText(b.toString());
		
		setVisible(true);
		setSize(400,400);
		setTitle("Return book");
		}

}
