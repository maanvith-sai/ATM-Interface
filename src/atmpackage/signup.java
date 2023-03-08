package atmpackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class signup extends javax.swing.JFrame{
    JFrame signupFrame;
    JLabel titlelabel, usernametext, pintext, signuptext;
    JTextField usernameinput, amountinput, nameinput, accnoinput;
    JPasswordField pininput;
    JButton backbutton, registerbutton;
    
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    
    public signup() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, user, password);
            //Statement stmt =conn.createStatement();
            /*String sql1 = "if not exists (select * from sys.databases where name = 'onlinereservationsystem')"
                    + "BEGIN create database onlinereservationsystem";
            stmt.executeUpdate(sql1);*/
            /*String sql2= "use onlinereservationsystem"
                    + "CREATE TABLE 'onlinereservationsystem'.'users' ('username' VARCHAR(100) NOT NULL , 'password' VARCHAR(100) NOT NULL ) ENGINE = InnoDB";
            stmt.executeUpdate(sql2);*/
            
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Something went wrong");
        }
        signupFrame = new JFrame("ATM SERVICES - Create Account");
        signupFrame.setSize(4000,4000);
        signupFrame.setLayout(null);
        
        titlelabel = new JLabel("SIGN UP - ATM SERVICES");
        titlelabel.setBounds(470,100,600,50);
        titlelabel.setForeground(Color.BLACK);
        Font font1 = new Font("Serif", Font.BOLD, 40);
        titlelabel.setFont(font1);
        signupFrame.add(titlelabel);
        
        signuptext = new JLabel("SIGNUP");
        signuptext.setBounds(680,200,300,50);
        signuptext.setForeground(Color.BLACK);
        Font font2 = new Font("Serif", Font.BOLD,30);
        signuptext.setFont(font2);
        signupFrame.add(signuptext);
        
       
        JLabel name = new JLabel("Full Name");
        name.setBounds(430,300,100,40);
        name.setForeground(Color.BLACK);
        Font font3 = new Font("Serif", Font.BOLD,20);
        name.setFont(font3);
        signupFrame.add(name);
        
        JLabel accno = new JLabel("Account Number");
        accno.setBounds(430,350,150,40);
        accno.setForeground(Color.BLACK);
        accno.setFont(font3);
        signupFrame.add(accno);
        
        
        JLabel amount = new JLabel("Intial Deposit");
        amount.setBounds(430,400,150,40);
        amount.setForeground(Color.BLACK);
        amount.setFont(font3);
        signupFrame.add(amount);
        
        usernametext = new JLabel("Username");
        usernametext.setBounds(430,450,100,40);
        usernametext.setForeground(Color.BLACK);
        usernametext.setFont(font3);
        signupFrame.add(usernametext);
        
        pintext = new JLabel("Pin (only in digits)");
        pintext.setBounds(430,500,200,60);
        pintext.setForeground(Color.BLACK);
        pintext.setFont(font3);
        signupFrame.add(pintext);
        
        
        
        usernameinput = new JTextField();
        usernameinput.setBounds(650,450,300,40);
        usernameinput.setFont(font3);
        usernameinput.requestFocus();
        signupFrame.add(usernameinput);
        
        nameinput = new JTextField();
        nameinput.setBounds(650,300,300,40);
        nameinput.setFont(font3);
        signupFrame.add(nameinput);
        
        
        accnoinput = new JTextField();
        accnoinput.setBounds(650,350,300,40);
        accnoinput.setFont(font3);
        signupFrame.add(accnoinput);
        
        
        amountinput = new JTextField();
        amountinput.setBounds(650,400,300,40);
        amountinput.setFont(font3);
        signupFrame.add(amountinput);

        
        pininput = new JPasswordField();
        pininput.setBounds(650,500,300,40);
        pininput.setFont(font3);
        signupFrame.add(pininput);
        
        registerbutton = new JButton("Register");
        registerbutton.setBounds(500,650,140,40);
        Font font4 = new Font("Serif", Font.BOLD,15);
        registerbutton.setFont(font4);
        signupFrame.add(registerbutton);
        
        registerbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e ){
                try {
                    insertintodatabase();
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        backbutton = new JButton("Back to Login");
        backbutton.setBounds(750,650,150,40);
        backbutton.setFont(font4);
        signupFrame.add(backbutton);
       
        backbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                back();
            }
        });
        signupFrame.setVisible(true);
        signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void insertintodatabase() throws IllegalArgumentException, SQLException{
        try{
            String q1, q2;
            String usernamed, pind, amountd, accnod, named ;
            usernamed = usernameinput.getText();
            pind = pininput.getText();
            amountd = amountinput.getText();
            accnod = accnoinput.getText();
            named = nameinput.getText();
            
            
            if(usernamed.isEmpty() || pind.isEmpty() || amountd.isEmpty() || accnod.isEmpty() || named.isEmpty()){
                throw new IllegalArgumentException();
            }
            
            //q1 = "create table if not exixts users (username varchar(100) unique not null, password int not null";
            //q1 = "if not exixts(select * from sys.objects where object_id = OBJECT_ID(n'[dbo].[users]') and type in(N'U'))"
                    //+ "create table[dbo].[users](username varchar(100) unique not null, password varchar(30) not null)";
            //PreparedStatement pstl = conn.prepareStatement(q1);
            //pstl.execute();
            q1 = "insert into users(username,pin) values(?,?)";
            PreparedStatement pst1 = conn.prepareStatement(q1);
            pst1.setString(1,usernamed);
            pst1.setString(2,pind);
            pst1.executeUpdate();
            
            q2 = "insert into userinfo(name,accno,amount,username) values(?,?,?,?)";
            PreparedStatement pst2 =conn.prepareStatement(q2);
            pst2.setString(1,named);
            pst2.setString(2,accnod);
            pst2.setString(3,amountd);
            pst2.setString(4,usernamed);
            try{
                pst2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Bank Account Created");
                pininput.setText("");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Username or Accnount Number already exists");
            }
            usernameinput.setText("");
            usernameinput.requestFocus();
            
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Fill all the Details");
            System.out.println(e);
        }
    }
    public void back(){
        signupFrame.dispose();
    }
 
}