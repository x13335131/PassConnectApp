package com;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TemplateFrame extends JFrame {

    CardLayout cardLayout;
    public JPanel mainPanel;
    LandingLoginUi login;
    CreateAccount createAccount;
    ForgotPassword forgotPass;

    public TemplateFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        login = new LandingLoginUi(TemplateFrame.this);
        createAccount = new CreateAccount();
        forgotPass = new ForgotPassword();
        mainPanel.add(login, "login");
        mainPanel.add(createAccount, "createAccount");
        mainPanel.add(forgotPass, "forgotPass");

        add(mainPanel);
     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public void createUserAccount() {
        cardLayout.show(mainPanel, "createAccount");
    }

    public void forgotPassPanel() {
        cardLayout.show(mainPanel, "forgotPass");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                TemplateFrame templateFrame = new TemplateFrame();
            }
        });
    }
}
