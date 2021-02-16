package com;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

//class GamePanel extends JPanel {
//
//  public GamePanel() {
//      setBackground(Color.BLUE);
//      add(new JLabel("Game"));
//  }
public class CreateAccount extends JPanel{

	protected Shell shell;
	private TextField tfUsername;
	private TextField tfEmail;
	private JPasswordField tfEnterPW;
	private JPasswordField tfReEnterPW;

	private String username;
	private String email;
	private String password;
	private String password2;
	
	public static JDBC jdbc = new JDBC();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	    public CreateAccount() {
	    	
	    	createContents();
	    	 
	    }
	    protected void createContents() {
	    	setBackground(Color.blue);
		   	  setLayout(null);
		   	  
		    	JLabel lblUsername = new JLabel();
		    	lblUsername.setBackground(Color.darkGray);
				lblUsername.setFont(new Font("Serif", Font.BOLD, 14));
				lblUsername.setBounds(58, 106, 85, 26);
				lblUsername.setText("USERNAME");
				add(lblUsername);
				
				JLabel lblEnterAPassword = new JLabel();
				lblEnterAPassword.setBackground(Color.darkGray);
				lblEnterAPassword.setFont(new Font("Serif", Font.BOLD, 14));
				lblEnterAPassword.setBounds(58, 171, 148, 26);
				lblEnterAPassword.setText("ENTER PASSWORD");
				add(lblEnterAPassword);
				
				JLabel lblEnterPasswordAgain = new JLabel();
				lblEnterPasswordAgain.setBackground(Color.DARK_GRAY);
				lblEnterPasswordAgain.setFont(new Font("Serif", Font.BOLD, 14));
				lblEnterPasswordAgain.setBounds(58, 211, 163, 26);
				lblEnterPasswordAgain.setText("RE-ENTER PASSWORD");
				add(lblEnterPasswordAgain);
				
				tfUsername = new TextField();
				tfUsername.setBounds(174, 101, 96, 26);
				add(tfUsername);
				
				JLabel lblEmail = new JLabel();
				lblEmail.setFont(new Font("Serif", Font.BOLD, 14));
				lblEmail.setBackground(Color.DARK_GRAY);
				lblEmail.setBounds(58, 138, 71, 27);
				lblEmail.setText("EMAIL");
				add(lblEmail);
				
				tfEmail = new TextField();
				tfEmail.setBounds(174, 144, 76, 21);
				add(tfEmail);
				
				tfEnterPW = new JPasswordField();
				tfEnterPW.setBounds(212, 171, 76, 21);
				add(tfEnterPW);
				
				tfReEnterPW = new JPasswordField();
				tfReEnterPW.setBounds(224, 211, 76, 21);
				add(tfReEnterPW);
				
				JButton btnCreate = new JButton();
				btnCreate.addActionListener(new ActionListener() {
				

					@Override
					public void actionPerformed(ActionEvent arg0) {
						createAccount();
					}
				});
				btnCreate.setBounds(152, 261, 100, 25);
				btnCreate.setText("CREATE");
				add(btnCreate);	
	    }
	protected void createAccount() {
		username =  tfUsername.getText();
		email = tfEmail.getText();
		password = tfEnterPW.getText();
		password2 = tfReEnterPW.getText();

       String errorMsg="";
       List<String> errorList = new ArrayList<>();
            
       if (!isValid(email,password, password2, errorList)) {
                System.out.println("The credentials entered here is invalid");
                for (String error : errorList) {
                    errorMsg=errorMsg +"\r\n"+error;
                }
            
            System.out.println("your password is: " + password);
        
            if (!errorMsg.isEmpty()) {
           	JOptionPane.showMessageDialog(this, errorMsg,
            		    "ERROR",
            		    JOptionPane.PLAIN_MESSAGE);
            }
        }
	
       else {
           
            String salt = PasswordUtils.getSalt(30);
            String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);
            
            // Print out protected password 
            System.out.println("My secure password = " + mySecurePassword);
            System.out.println("Salt value = " + salt);
            
            boolean success = jdbc.addUserToDb(username, email, salt, mySecurePassword);
            
            if(success) {
            	JOptionPane.showMessageDialog(this, "Account Created",
            		    "Success",
            		    JOptionPane.PLAIN_MESSAGE);
            	 
            }else {      
            	JOptionPane.showMessageDialog(this, "Problem Creating Account. A user with this username may already exist",
            		    "ERROR",
            		    JOptionPane.PLAIN_MESSAGE);
         
            }
        }

	}

  @Override
  public Dimension getPreferredSize() {
      return new Dimension(500, 500);
  }
	
	public static boolean isValid(String email, String passwordhere, String confirmhere, List<String> errorList) {

		Pattern emailPattern = Pattern.compile("^.+@.+\\..+$");
	    Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	    Pattern upperCasePatten = Pattern.compile("[A-Z ]");
	    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
	    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
	    
	    errorList.clear();

	    boolean flag=true;
	    if (!emailPattern.matcher(email).matches()) {
	    	errorList.add("Email not valid");
		    flag=false;
	    }
	    if (!passwordhere.equals(confirmhere)) { 
	        errorList.add("Password and confirm password do not match");
	        flag=false;
	    }
	    if (passwordhere.length() < 8) {
	        errorList.add("Password length must have at least 8 characters");
	        flag=false;
	    }
	    if (!specailCharPatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one special characters");
	        flag=false;
	    }
	    if (!upperCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one uppercase characters");
	        flag=false;
	    }
	    if (!lowerCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have at least one lowercase characters");
	        flag=false;
	    }
	    if (!digitCasePatten.matcher(passwordhere).find()) {
	        errorList.add("Password must have atleast one digit characters");
	        flag=false;
	    }

	    return flag;

	}
	
}
