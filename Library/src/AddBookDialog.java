import BreezySwing.*;
import javax.swing.*;
public class AddBookDialog extends GBDialog{

	private JLabel bookTitleLabel = addLabel("Book Title:",1,1,1,1);
	private JTextField bookTitleField = addTextField("",1,2,1,1);
	
	private JLabel bookAuthorLabel = addLabel("Book Author:",2,1,1,1);
	private JTextField bookAuthorField = addTextField("",2,2,1,1);
	
	private JButton enterButton = addButton("Add book to library",3,2,1,1);
	
	private Book b;
	
	public void buttonClicked(JButton button) {
		if(button == enterButton) {
			String title = bookTitleField.getText();
			String author = bookAuthorField.getText();
			b = new Book(title,author,null,null, false);
			dispose();
		}
	}
	
	public AddBookDialog(JFrame parent) {
		super(parent);
		this.setVisible(true);
		this.setSize(400,400);
	}
	
	public Book getNewBook() {
		return b;
	}
	
}
