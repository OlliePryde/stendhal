/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.client.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import games.stendhal.client.*;
import marauroa.client.*;

/**
 * Summary description for CreateAccountDialog
 *
 */
public class CreateAccountDialog extends JDialog {
    private static final long serialVersionUID = 4436228792112530975L;

    // Variables declaration
    private JLabel usernameLabel;
    private JLabel serverLabel;
    private JLabel serverPortLabel;
    private JLabel passwordLabel;
    private JLabel passwordretypeLabel;
    private JLabel emailLabel;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordretypeField;
    private JTextField emailField;
    private JComboBox serverField;
    private JTextField serverPortField;
    
    private JButton createAccountButton;
    
    private JPanel contentPane;
    
    // End of variables declaration
    private StendhalClient client;
    
    private Frame owner;
    
    public CreateAccountDialog(Frame owner, StendhalClient client) {
        super(owner, true);
        this.client = client;
        this.owner = owner;
        initializeComponent();
        
        this.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Windows Form Designer. Otherwise, retrieving design
     * might not work properly. Tip: If you must revise this method, please
     * backup this GUI file for JFrameBuilder to retrieve your design properly
     * in future, before revising this method.
     */
    private void initializeComponent() {
        serverLabel = new JLabel("Stendhal server for this account");
        serverField = new JComboBox();
        serverField.setEditable(true);
        serverPortLabel = new JLabel("Server port");
        serverPortField = new JTextField("32160");

        usernameLabel = new JLabel("Choose a username");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Choose a password");
        passwordField = new JPasswordField();

        passwordretypeLabel = new JLabel("Retype password");
        passwordretypeField = new JPasswordField();

        emailLabel = new JLabel("E-mail address");
        emailField = new JTextField();

        createAccountButton = new JButton();
        contentPane = (JPanel) this.getContentPane();
        
        //
        // serverField
        //
        for(String server: stendhal.SERVERS_LIST)
          {
          serverField.addItem(server);
          }
          
        //
        // serverPortField
        //
        //
        // createAccountButton
        //
        createAccountButton.setText("Create Account");
        createAccountButton.setMnemonic(KeyEvent.VK_C);
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccountButton_actionPerformed(e, false);
            }
        });
        //
        // contentPane
        //
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        GridBagConstraints c = new GridBagConstraints();
        
        //row 0
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(4, 4, 15, 4);
        c.gridx=0;//column
        c.gridy=0;//row
        contentPane.add(serverLabel,c);
        c.gridx=1;
        c.gridy=0;
        contentPane.add(serverField,c);
        //row 1
        c.insets = new Insets(4,4,4,4);
        c.gridx=0;
        c.gridy=1;
        contentPane.add(serverPortLabel,c);
        c.gridx=1;
        c.gridy=1;
        c.insets = new Insets(4, 4, 4, 150);
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(serverPortField,c);
        //row 2
        c.insets = new Insets(4,4,4,4);
        c.gridx=0;
        c.gridy=2;
        contentPane.add(usernameLabel,c);
        c.gridx=1;
        c.gridy=2;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(usernameField,c);
        //row 3
        c.gridx=0;
        c.gridy=3;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(passwordLabel,c);
        c.gridx=1;
        c.gridy=3;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(passwordField,c);
        //row 4
        c.gridx=0;
        c.gridy=4;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(passwordretypeLabel,c);
        c.gridx=1;
        c.gridy=4;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(passwordretypeField,c);
        //row 5
        c.gridx=0;
        c.gridy=5;
        c.fill = GridBagConstraints.NONE;
        contentPane.add(emailLabel,c);
        c.gridx=1;
        c.gridy=5;
        c.fill = GridBagConstraints.BOTH;
        contentPane.add(emailField,c);
        //row 6
        c.gridx=1;
        c.gridy=6;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 4, 4, 4);
        contentPane.add(createAccountButton,c);
        
        //
        // CreateAccountDialog
        //
        this.setTitle("Create New Account");
        this.setResizable(false);
        this.setSize(new Dimension(410, 275));
        this.setLocationRelativeTo(owner);
    }

// TODO: Never used? remove me
//    /** Add Component Without a Layout Manager (Absolute Positioning) */
//    private void addComponent(Container container, Component c, int x, int y,
//            int width, int height) {
//        c.setBounds(x, y, width, height);
//        container.add(c);
//    }
    
    private void createAccountButton_actionPerformed(ActionEvent e, boolean saveLoginBoxStatus) {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());
        final String passwordretype = new String(passwordField.getPassword());
        
        if(!password.equals(passwordretype))
          {
          JOptionPane.showMessageDialog(owner, "The passwords do not match. Please retype both.", "Password Mismatch", JOptionPane.WARNING_MESSAGE);
          return;
          }
        
        final String email = emailField.getText();
        final String server = (String) serverField.getSelectedItem();
        int port = 32160;
        
        final int finalPort;//port couldnt be accessed from inner class
        final ProgressBar progressBar = new ProgressBar(owner);
        
        try {
            port = Integer.parseInt(serverPortField.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(owner, "That is not a valid port number. Please try again.", "Invalid Port", JOptionPane.WARNING_MESSAGE);
            return;
        }
        finalPort = port;
        
        /*seprate thread for connection proccess added by TheGeneral*/
        //run the connection procces in separate thread
        Thread m_connectionThread = new Thread() {
            @Override
			public void run() {
                progressBar.start();//intialize progress bar
                setVisible(false);//hide this screen when attempting to connect
                                
                try {
                    client.connect(server, finalPort, true);
                    progressBar.step();//for each major connection milestone call step()
                } catch (Exception ex) {
                    progressBar.cancel();//if something goes horribly just cancel the progressbar
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Stendhal cannot connect to the Internet. Please check that your connection is set up and active, then try again.");
                    
                    ex.printStackTrace();
                    
                    return;
                }
                
                try {
                    if (client.createAccount(username, password, email) == false) {
                        String result = client.getEvent();
                        if (result == null) {
                            result = "The server is not responding. Please check that it is online, and that you supplied the correct details.";
                        }
                        progressBar.cancel();
                        setVisible(true);
                        JOptionPane.showMessageDialog(owner, result, "Error Creating Account", JOptionPane.ERROR_MESSAGE);

                        return;
                    } else {
                        progressBar.step();
                        progressBar.finish();
//                        try {//wait just long enough for progress bar to close
//                            progressBar.m_run.join();
//                        }catch (InterruptedException ie) {}
                        
//                        setVisible(false);
//                        frame.setVisible(false);
                    }
                } catch (ariannexpTimeoutException ex) {
                    progressBar.cancel();
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Unable to connect to server to create your account. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.", "Error Creating Account", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    if (client.login(username, password) == false) {
                        String result = client.getEvent();
                        if (result == null) {
                            result = "Unable to connect to server. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.";
                        }
                        progressBar.cancel();
                        setVisible(true);
                        JOptionPane.showMessageDialog(owner, result, "Error Logging In", JOptionPane.ERROR_MESSAGE);
                    } else {
                        progressBar.step();
                        progressBar.finish();
//                        try {//wait just long enough for progress bar to close
//                            progressBar.m_run.join();
//                        }catch (InterruptedException ie) {}
                        
                        setVisible(false);
                        owner.setVisible(false);
                        stendhal.doLogin = true;
                    }
                } catch (ariannexpTimeoutException ex) {
                    progressBar.cancel();
                    setVisible(true);
                    JOptionPane.showMessageDialog(owner, "Unable to connect to the server. The server may be down or, if you are using a custom server, you may have entered its name and port number incorrectly.", "Error Logging In", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        };
        m_connectionThread.start();
    }
    
    public static void main(String args[]) {
         new CreateAccountDialog(null,null).setVisible(true);
    }
}
