package atmpackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class login {
    JFrame loginFrame;
    JLabel titlelabel, usernametext, passwordtext, logintext;
    JTextField usernameinput;
    JPasswordField passwordinput;
    JButton loginButton, registerButton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    
    public login() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(DB_URL, user, password);
            Statement stmt = conn.createStatement();
            //String sql = "create database if not exists onlinereservationsystem";
            //stmt.executeUpdate(sql);
            
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error in Connection Establishment");
        }
        loginFrame = new JFrame("ATM INTERFACE - Login Page");
        loginFrame.setSize(4000,4000);
        loginFrame.setLayout(null);
        
        titlelabel = new JLabel("Welcome to ATM Services");
        titlelabel.setBounds(500,5,500,200);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Serif", Font.BOLD, 35);
        titlelabel.setFont(font1);
        loginFrame.add(titlelabel);
        
        logintext = new JLabel("LOGIN");
        logintext.setBounds(650,150,400,200);
        logintext.setForeground(Color.BLACK);
        Font font2 = new Font("Serif", Font.BOLD, 32);
        logintext.setFont(font2);
        loginFrame.add(logintext);
        
        usernametext = new JLabel("Username");
        usernametext.setBounds(400,300,100,40);
        usernametext.setForeground(Color.BLACK);
        Font font3 = new Font("Serif", Font.BOLD, 20);
        usernametext.setFont(font3);
        loginFrame.add(usernametext);
        
        passwordtext = new JLabel("Pin");
        passwordtext.setBounds(400,350,100,40);
        passwordtext.setForeground(Color.BLACK);
        Font font4 = new Font("Serif", Font.BOLD, 20);
        passwordtext.setFont(font4);
        loginFrame.add(passwordtext);
        
        usernameinput = new JTextField();
        usernameinput.setBounds(550,300,300,40);
        usernameinput.requestFocus();
        loginFrame.add(usernameinput);
        
        passwordinput = new JPasswordField();
        passwordinput.setBounds(550,350,300,40);
        passwordinput.requestFocus();
        loginFrame.add(passwordinput);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(500,500,150,40);
        loginButton.setFont(font4);
        loginFrame.add(loginButton);
        
        registerButton = new JButton("Register");
        registerButton.setBounds(700,500,150,40);
        registerButton.setFont(font4);
        loginFrame.add(registerButton);
        
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(login()){
                    loginFrame.dispose();
                    try {
                        menu Menu =new menu();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e ){
                try {
                    signup Signup = new signup();
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
        
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public boolean login(){
        try{
            String useri = usernameinput.getText();
            String pwd = passwordinput.getText();
            String q  = "select * from users where username = ? and pin = ?";
            PreparedStatement pst1 = conn.prepareStatement(q);
            pst1.setString(1,useri);
            pst1.setString(2,pwd);
            ResultSet rSet = pst1.executeQuery();
            
            String u = "insert into current (no,username,pin) values(?,?,?)";
            PreparedStatement pst4 = conn.prepareStatement(u);
            pst4.setString(1,"1");
            pst4.setString(2,useri);
            pst4.setString(3,pwd);
            pst4.execute();
            
            
            if(rSet.next()){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid username or password!!");
                usernameinput.setText("");
                passwordinput.setText("");
                usernameinput.requestFocus();
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error in Login Process");
            return false;
        }
        
        
    }
    
}
