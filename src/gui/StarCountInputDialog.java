package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * An input dialog that prompts user for the number of stars to place in the
 * space.
 * 
 * @author Judy
 * 
 */
public class StarCountInputDialog extends JDialog implements ActionListener {

	private JLabel numStarsLabel = new JLabel("Enter number of stars to create:  ");
	private JTextField numStarsField = new JTextField(10);
	private JButton okButton = new JButton("OK");
	private int numStarsToCreate = -1;
	
	public StarCountInputDialog(JFrame parent) {
		super(parent, "", true);
		
		JPanel promptPane = new JPanel();
		setContentPane(promptPane);
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		promptPane.setLayout(gb);
		promptPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 11, 11));
		
		// place the number of stars to create label 
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.insets = new Insets(10, 10, 5, 0);
		gb.setConstraints(numStarsLabel, c);
		promptPane.add(numStarsLabel);
		
		// place the text field for the number of stars 
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 0, 5, 10);		
		gb.setConstraints(numStarsField, c);
		promptPane.add(numStarsField);

		// place the ok button
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.insets = new Insets(10, 10, 5, 10);
		c.anchor = GridBagConstraints.CENTER;
		gb.setConstraints(okButton, c);
		promptPane.add(okButton);
		
		okButton.addActionListener(this);
	    
		// anonymous inner class for closing the window
		addWindowListener(new WindowAdapter() 
		{
		    public void windowClosing(WindowEvent e) 
		    { 
			System.exit(0); 
		    }
		 });

		// initially, place the cursor in the numStarsField
		numStarsField.requestFocus();	  
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// check validity of user input
		String userInput = numStarsField.getText();
		try {
			numStarsToCreate = Integer.parseInt(userInput);
			if ( numStarsToCreate > 0 && numStarsToCreate < 101)
				dispose();
			else
				showErrorMessage();
		} catch (NumberFormatException e) {
			showErrorMessage();
		}
	}
	
	private void showErrorMessage() {
		JOptionPane.showMessageDialog(this, "Invalid input! Input must be a real integer between 1 to 100.");
	}
	
	public int getNumStarsToCreate() {
		return numStarsToCreate;
	}
}
