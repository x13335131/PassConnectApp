package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ForgotPassword extends JPanel {

	private String code;
	private TextField txt_code;
	private JLabel lblPleaseEnterCode;
	private JTextField userEmail;
	private JTextField enteredCode;
	private JLabel lbl_countDown;

	public ForgotPassword() {

		createContents();

	}

	protected void createContents() {
		setBackground(Color.orange);
		setLayout(null);

		userEmail = new JTextField();
		userEmail.setBounds(38, 200, 150, 21);
		userEmail.setText("Email");
		add(userEmail);

		// countdown label
		lbl_countDown = new JLabel();
		lbl_countDown.setFont(new Font("Serif", Font.BOLD, 14));
		lbl_countDown.setBounds(38, 370, 200, 21);
		lbl_countDown.setText("countdown");
		add(lbl_countDown);

		// text code
		txt_code = new TextField();
		txt_code.setFont(new Font("Serif", Font.BOLD, 14));
		txt_code.setText("code");
		txt_code.setBounds(38, 267, 76, 21);
		add(txt_code);

		// okButton
		JButton sendCodeToEmailbtn = new JButton();
		sendCodeToEmailbtn.setBounds(320, 200, 150, 25);
		sendCodeToEmailbtn.setText("Send code to email");
		add(sendCodeToEmailbtn);

		JButton codeOkBtn = new JButton();
		codeOkBtn.setBounds(320, 304, 75, 25);
		codeOkBtn.setText("Ok");
		add(codeOkBtn);

		sendCodeToEmailbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendCodeToEmail();
			}
		});
		codeOkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				codeOk();
			}
		});

		lblPleaseEnterCode = new JLabel();
		lblPleaseEnterCode.setBounds(38, 248, 350, 15);
		lblPleaseEnterCode.setText("Please enter code that was sent to your email address:");
		add(lblPleaseEnterCode);

	}

	protected void codeOk() {

		System.out.println("code entered");
		System.out.println("code:" + code);
		System.out.println("entered code:" + enteredCode.getText());
		String codeEntered = enteredCode.getText();
		if (codeEntered.equals(code)) {
			System.out.println("Codes entered matched! Let them in");
		} else {
			System.out.println("Codes do not match! no access");
		}

	}

	protected void sendCodeToEmail() {

		System.out.println(" " + userEmail.getText());

		boolean emailExists = checkdb(userEmail.getText());
		if (!emailExists) {
			System.out.println("Email does not exist in db");
		} else {

			System.out.println("Email exists in db");
			Emailer e = new Emailer();
			code = e.sendEmail(userEmail.getText());
			enteredCode = new JTextField("");

			int delay = 1000; // milliseconds

			ActionListener taskPerformer = new ActionListener() {
				int i = 60;

				public void actionPerformed(ActionEvent evt) {

					// ...Perform a task...
					if (i >= 0) {

						lbl_countDown.setText("You have got " + i + " seconds remaining");
					
						System.out.println(i--);
						
					} 
				}
			};
			new Timer(delay, taskPerformer).start();
			
		}
	}


	private boolean checkdb(String email) {

		JDBC c = new JDBC();

		Connection con = null;
		con = c.connectionQuery(con);
		String statement;
		try {

			statement = "SELECT COUNT(*) AS totalEmails FROM users where email=?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(statement);
			preparedStmt.setString(1, email);

			preparedStmt.execute();
			System.out.println("DataBase table accessed");

			ResultSet rs = preparedStmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("totalEmails") > 0) {
					System.out.println("total > 0");
					con.close();
					return true;
				} else {
					System.out.println("email doesnt exist");
					con.close();
					return false;
				}

			} else {
				System.out.println("no emails in list that match the input");
				con.close();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
