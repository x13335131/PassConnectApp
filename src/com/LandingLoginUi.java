package com;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

//
//class MenuPanel extends JPanel {
//
//  public MenuPanel() {
//      setBackground(Color.GREEN);
//      add(new JLabel("Menu"));
//  }
//
//  @Override
//  public Dimension getPreferredSize() {
//      return new Dimension(300, 300);
//  }
//}
//
public class LandingLoginUi extends JPanel{

	protected Shell shell;
	//private Text tfUsername;
	private JPasswordField tfMasterPW;
	private TextField tfUsername;
	private String username;
	private String masterPass;
	public static JDBC jdbc = new JDBC();
	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			MainWindow window = new MainWindow();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

  public LandingLoginUi(TemplateFrame mf) {
// open();
	  
	  createContents(mf);
	 
		
}
  

protected void login() {
		 
		username = tfUsername.getText();
		masterPass = tfMasterPW.getText();

       if (username == null || username.isEmpty() || masterPass == null || masterPass.isEmpty()) {
           String errorMsg = null;
           MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);

           messageBox.setText("Alert");
           if (username == null || username.isEmpty()) {
               errorMsg = "Please enter username";
           } else if (masterPass == null || masterPass.isEmpty()) {
               errorMsg = "Please enter password";
           }
           if (errorMsg != null) {
               messageBox.setMessage(errorMsg);
               messageBox.open();
           }
       } 
       else if(!jdbc.checkCredentials(username, masterPass)) {
    	   JOptionPane.showMessageDialog(null, "invalid");
       }
       else {
    	   JOptionPane.showMessageDialog(null, "Valid");
    	   
//    	   MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
//           messageBox.setText("Info");
//           messageBox.setMessage("Valid");
//           messageBox.open();
       }
   }
	



//	lblPleaseEnterCode.setVisible(true);
//	lbl_countDown.setVisible(true);
//
//	txt_code.setVisible(true);
//	
//	SwingUtilities.invokeLater(new Runnable()
//    {
//    	int i = 60;
//
//      public void run() {
//         while (true) {
//            try { Thread.sleep(1000); } catch (Exception e) { }
//          //  Display.getDefault().asyncExec(new Runnable() {
//            	SwingUtilities.invokeLater(new Runnable()
//                {
//               public void run() {
//                 // ... do any work that updates the screen ...
//            	   if (i >= 0) {
//	    	        	updateLabel(i);	
//	     	   	System.out.println(i--);
//	    	       }
//               }
//            });
//         }
//
//	
//    }});}
//public void updateLabel(int i) {
//
//	lbl_countDown.setText("You have got "+i+" seconds remaining");
//
//}

@Override
public Dimension getPreferredSize() {
  return new Dimension(500, 500);
}
protected void createContents(TemplateFrame mf) {
	 setBackground(Color.GREEN);
	  setLayout(null);
	  
	  //username TF
	  tfUsername = new TextField();
	  tfUsername.setBounds(68, 93, 145, 22);
	  add(tfUsername);
	  
	  //username label
	  JLabel lblUserName = new JLabel();
		lblUserName.setBackground(Color.darkGray);
		lblUserName.setFont(new Font("Serif", Font.BOLD, 14));
		lblUserName.setBounds(68, 64, 95, 23);
		lblUserName.setText("USERNAME");
	  add(lblUserName);
	  
	  tfMasterPW = new JPasswordField();
	  tfMasterPW.setBounds(68, 159, 135, 21);
	  add(tfMasterPW);
	  
	  //master password label
		JLabel lblMasterPassword = new JLabel();
		lblMasterPassword.setBackground(Color.darkGray);
		lblMasterPassword.setFont(new Font("Serif", Font.BOLD, 14));
		lblMasterPassword.setBounds(60, 132, 153, 21);
		lblMasterPassword.setText("MASTER PASSWORD");
		add(lblMasterPassword);
		
		//Login button
		JButton btnLogin = new JButton("log in");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(68, 205, 75, 25);
		btnLogin.setText("LOGIN");
		add(btnLogin);
		
	  //create new account button
	  JButton btnCreateAccount = new JButton("create account");
	  btnCreateAccount.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		
	  		mf.createUserAccount();
	  	}
	  });
	  btnCreateAccount.setBounds(183, 341, 154, 49);
	  add(btnCreateAccount);
	  
	  //forgot pw
	  JLabel linkForgotPass = new JLabel();
		linkForgotPass.setBounds(267, 210, 150, 15);
		linkForgotPass.setForeground(Color.blue.darker());
		linkForgotPass.setText("Forgot Password");
		linkForgotPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		linkForgotPass.addMouseListener(new MouseAdapter() {
				 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	mf.forgotPassPanel();
		    }
		    
		});
		add(linkForgotPass);
		
}

	/**
	 * Open the window.
	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shell.open();
//		shell.layout();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 */
//	protected void createContents() {
//		shell = new Shell();
//		shell.setSize(483, 400);
//		shell.setText("Pass Connect");
//		Canvas canvas = new Canvas(shell, SWT.NONE);
//		canvas.setBackground(SWTResourceManager.getColor(255, 204, 51));
//		canvas.setBounds(0, 0, 467, 361);
//		
//		tfUsername = new Text(canvas, SWT.BORDER);
//		tfUsername.setBounds(227, 128, 135, 21);
//		
//		Label lblUserName = new Label(canvas, SWT.NONE);
//		lblUserName.setBackground(SWTResourceManager.getColor(255, 204, 102));
//		lblUserName.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
//		lblUserName.setBounds(115, 127, 95, 23);
//		lblUserName.setText("USERNAME");
//		
//		tfMasterPW = new Text(canvas, SWT.BORDER | SWT.PASSWORD);
//		tfMasterPW.setBounds(227, 173, 135, 21);
//		
//		Label lblMasterPassword = new Label(canvas, SWT.NONE);
//		lblMasterPassword.setBackground(SWTResourceManager.getColor(255, 204, 102));
//		lblMasterPassword.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
//		lblMasterPassword.setBounds(57, 172, 153, 21);
//		lblMasterPassword.setText("MASTER PASSWORD");
//		
//		Button btnLogin = new Button(canvas, SWT.NONE);
//		btnLogin.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//			}
//		});
//		btnLogin.setBounds(382, 171, 75, 25);
//		btnLogin.setText("LOGIN");
//		
//		Button btnCreateAccount = new Button(canvas, SWT.NONE);
//		btnCreateAccount.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				CreateAccount createAccount = new CreateAccount();
//
//				shell.dispose();
//				createAccount.open();
//			}
//		});
//		btnCreateAccount.setBounds(338, 231, 119, 25);
//		btnCreateAccount.setText("CREATE ACCOUNT");
//		
//		Link linkForgotPass = new Link(canvas, SWT.NONE);
//		linkForgotPass.setBounds(267, 210, 95, 15);
//		linkForgotPass.setText("<a>Forgot Password</a>");
//		
//		lbl_countDown = new Label(canvas, SWT.NONE);
//		lbl_countDown.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
//		lbl_countDown.setBounds(158, 307, 86, 18);
//		lbl_countDown.setVisible(false);
//		
//		txt_code = new Text(canvas, SWT.BORDER);
//		txt_code.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
//		txt_code.setBounds(168, 270, 76, 21);
//		txt_code.setVisible(false);
//		Button btnOk = new Button(canvas, SWT.NONE);
//		btnOk.setBounds(357, 274, 75, 25);
//		btnOk.setText("OK"); 
//		
//		Button btnCancel = new Button(canvas, SWT.NONE);
//		btnCancel.setBounds(357, 304, 75, 25);
//		btnCancel.setText("Cancel");
//		
//		Label lblPleaseEnterCode = new Label(canvas, SWT.NONE);
//		lblPleaseEnterCode.setBounds(28, 248, 297, 15);
//		lblPleaseEnterCode.setText("Please enter code that was sent to your email address:");
//		lblPleaseEnterCode.setVisible(false);
//		linkForgotPass.addListener(SWT.Selection, new Listener() {
//
//			@Override
//			public void handleEvent(Event arg0) {
//				// TODO Auto-generated method stub
//			//	ForgotPassPopUp fp = new ForgotPassPopUp();
//				//fp.display();	
//				//canvas.dispose();
//				lblPleaseEnterCode.setEnabled(true);
//				lbl_countDown.setVisible(true);
//
//				txt_code.setVisible(true);
//			  new Thread(new Runnable() {
//	            	int i = 60;
//
//			      public void run() {
//			         while (true) {
//			            try { Thread.sleep(1000); } catch (Exception e) { }
//			            Display.getDefault().asyncExec(new Runnable() {
//			            
//			               public void run() {
//			                 // ... do any work that updates the screen ...
//			            	   if (i >= 0) {
//          	    	        	updateLabel(i);	
//           	     	   	System.out.println(i--);
//           	    	       }
//			               }
//			            });
//			         }
//			      }
//			   }).start();
//
//	//		@Override
////			public void handleEvent(Event arg0) {
////				
////				  Timer timer = new Timer();
////				  TimerTask task = new TimerTask() {
////	    	    				int i =60;
////	            	    	    
////	            	    	    public void run(){
////	            	    	        if (i >= 0) {
////	            	    	        	updateLabel(i--);	
////	            	     	   	System.out.println(i);
////	            	    	       }
////	            	    	    }
////	            	    	};
////	            	    	timer.scheduleAtFixedRate(task, 0, 1000);
////	    	    			
////				// TODO Auto-generated method stub
////				//ForgotPassPopUp fp = new ForgotPassPopUp();
////				//fp.display();
////			}
//			  
////	            public void handleEvent(Event event) {
////	                new Timer().schedule(new TimerTask() {
////	                    @Override
////	                    public void run() {
////	                        Display.getDefault().asyncExec(new Runnable() {
////	                        	int i =60;
////	                            public void run() {
////	                            	if (i >= 0) {
////	            	    	        	//updateLabel(i--);	
////	            	     	   	System.out.println(i--);
////            	    	       }
////	                            }
////	                       });
////	                        
////	                    }
////	                    
////	                },1000);
////	            }
//			
//			}	
//		});
//		btnLogin.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event event) {
//				 
//				username = tfUsername.getText();
//				masterPass = tfMasterPW.getText();
// 
//                if (username == null || username.isEmpty() || masterPass == null || masterPass.isEmpty()) {
//                    String errorMsg = null;
//                    MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
// 
//                    messageBox.setText("Alert");
//                    if (username == null || username.isEmpty()) {
//                        errorMsg = "Please enter username";
//                    } else if (masterPass == null || masterPass.isEmpty()) {
//                        errorMsg = "Please enter password";
//                    }
//                    if (errorMsg != null) {
//                        messageBox.setMessage(errorMsg);
//                        messageBox.open();
//                    }
//                } 
//                else if(!jdbc.checkCredentials(username, masterPass)) {
//                	MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
//                    messageBox.setText("Info");
//                    messageBox.setMessage("Credentials are incorrect");
//                    messageBox.open();
//                }
//                else {
//                    MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
//                    messageBox.setText("Info");
//                    messageBox.setMessage("Valid");
//                    messageBox.open();
//                }
//            }
//
//		
//		});
//	}
//	public void updateLabel(int i) {
//		
//	lbl_countDown.setText("You have got "+i+"seconds remaining");
//	}
}
