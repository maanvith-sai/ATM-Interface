package atmpackage;
import java.util.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
public class menu {
    JFrame menuFrame;
    JLabel title1Label, userlabel, balancelabel;
    JTextField userI;
    JButton backButton,depositButton, historyButton, transferButton, withdrawButton,balanceButton, getButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    
    public menu() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL,user,password);
            Statement stmt = conn.createStatement();   
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error in Connection menu");
        }
        menuFrame = new JFrame("ATM Services by Maanvith");
        menuFrame.setSize(4000,4000);
        menuFrame.setLayout(null);
        
        title1Label = new JLabel("WELCOME TO ATM SERVICES");
        title1Label.setBounds(200, 0, 1000, 200); 
        title1Label.setForeground (Color.BLACK);
        Font font1 = new Font("Cooper Black", Font. BOLD, 50);
        title1Label.setFont(font1);
        menuFrame.add(title1Label);
        
        title1Label = new JLabel("By MAANVITH");
        title1Label.setBounds(450, 120, 500, 200); 
        title1Label.setForeground (Color.BLACK);
        title1Label.setFont(font1);
        menuFrame.add(title1Label);
        
        depositButton = new JButton("Deposit");
        depositButton.setBounds(1050, 300, 250, 60);
        Font font3 = new Font("Serif", Font. BOLD, 20);
        depositButton.setFont(font3);
        depositButton.setForeground (Color.BLACK);
        menuFrame.add(depositButton);
        
        
        withdrawButton = new JButton("WithDraw");
        withdrawButton.setBounds (1050, 400, 250, 60); 
        Font font4 = new Font("Serif", Font. BOLD, 20);
        withdrawButton.setFont(font4);
        withdrawButton.setForeground (Color.BLACK);
        menuFrame.add(withdrawButton);
        
        String s = "Transaction History";
        historyButton = new JButton(s);
        historyButton.setBounds (1050, 500, 250, 60); 
        historyButton.setFont(font4);
        historyButton.setForeground (Color.BLACK);
        menuFrame.add(historyButton);
        
        transferButton = new JButton("Money Transfer");
        transferButton.setBounds (1050, 600, 250, 60); 
        transferButton.setFont(font4);
        transferButton.setForeground (Color.BLACK);
        menuFrame.add(transferButton);
        

        backButton = new JButton("Back to Login");
        backButton.setBounds(550, 500, 300, 80);
        Font font5 = new Font("Serif", Font.BOLD, 20);
        backButton.setFont(font5);
        backButton.setForeground (Color.BLACK); 
        menuFrame.add(backButton);
        
        
        balanceButton = new JButton("Balanace Enquiry");
        balanceButton.setBounds (200, 500, 300, 80); 
        balanceButton.setFont(font5);
        balanceButton.setForeground (Color.BLACK);
        menuFrame.add(balanceButton);
        
        
        depositButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    deposit d = new deposit();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        historyButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    history h = new history();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        transferButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    transfer t = new transfer();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        withdrawButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    withdraw t = new withdraw();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });
        
        
        backButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    login l =new login();
                    menuFrame.dispose();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        balanceButton.addActionListener(new ActionListener() { 
            public void actionPerformed (ActionEvent e) {
                try {
                    balance b = new balance();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });
        
        
        
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
    
}