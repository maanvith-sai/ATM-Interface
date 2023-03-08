package atmpackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class withdraw {
    JFrame bookFrame;
    JLabel titleLabel, amountText,nameText, bookingFormText, topname, topaccno,available;
    JLabel amount, name, email, locationinfo, datentime,trainName, trainNumber;
    JTextField amountInput, nameInput; 
    JButton submitButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    String serial,tamount;
    int count= 0;
    String cu;

    public withdraw() throws ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            Statement stmt = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        
        try{
            String c = "Select * from current where no = ? ";
            PreparedStatement ps1 = conn.prepareStatement(c);
            ps1.setString(1,"1");
            ResultSet rs1 = ps1.executeQuery();
            
            if(rs1.next()){
                cu = rs1.getString(2);
                
            }   
        }catch(Exception c){
            JOptionPane.showMessageDialog(null,"Current Table Error");
        }
        
        
        
        bookFrame = new JFrame("Withdraw Interface by Maanvith"); 
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);

        titleLabel = new JLabel("ATM Interface"); 
        titleLabel.setBounds (600, 5, 500, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 40);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);
        
        bookingFormText = new JLabel("WITHDRAW SECTION"); 
        bookingFormText.setBounds(580, 100, 400, 200); 
        bookingFormText.setForeground (Color.BLACK); 
        Font font2 = new Font("Serif", Font. BOLD, 30); 
        bookingFormText.setFont(font2);
        bookFrame.add(bookingFormText);
        
        
        topname = new JLabel("Name: "); 
        topname.setBounds(1200, 60, 300, 30); 
        topname.setForeground (Color.BLACK); 
        Font font7 = new Font("Serif", Font. PLAIN, 25); 
        topname.setFont(font7);
        bookFrame.add(topname);
        
        
        topaccno = new JLabel("Acc No: "); 
        topaccno.setBounds(1200, 100, 300, 30); 
        topaccno.setForeground (Color.BLACK); 
        topaccno.setFont(font7);
        bookFrame.add(topaccno);
        
        
        amountText = new JLabel("Enter Amount (₹) :");
        amountText.setBounds(400,400,300,40);
        amountText.setForeground(Color.BLACK);
        Font font3 = new Font("Sierf", Font.BOLD,25);
        amountText.setFont(font3);
        bookFrame.add(amountText);
        
        amountInput = new JTextField();
        amountInput.setBounds(650,400,300,40);
        amountInput.requestFocus();
        bookFrame.add(amountInput);
        
        /*nameText = new JLabel("Enter Username :");
        nameText.setBounds(400,300,300,40);
        nameText.setForeground(Color.BLACK);
        nameText.setFont(font3);
        bookFrame.add(nameText);

        nameInput = new JTextField();
        nameInput.setBounds(650,300,300,40);
        nameInput.requestFocus();
        bookFrame.add(nameInput);*/

        submitButton = new JButton("Submit"); 
        submitButton.setBounds (450, 600, 160, 40);
        submitButton.setFont(font7);
        bookFrame.add(submitButton);

        backButton = new JButton("Back to Main Menu"); 
        backButton.setBounds (700, 600, 250, 40);
        backButton.setFont(font7);
        bookFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    //String name = nameInput.getText();
                    String q = "Select * from userinfo where username = ?";
                    
                    PreparedStatement pst1 = conn.prepareStatement(q);
                    
                    pst1.setString(1,cu);
                   
                    ResultSet rSet = pst1.executeQuery();
                    if(rSet.next()){
                        topname.setText("Name: " +rSet.getString(1));
                        topaccno.setText("Acc No: " +rSet.getString(2));
                        String bamount = rSet.getString(3);
                        String damount = amountInput.getText().toString();
                        int i=Integer.parseInt(bamount);
                        int j=Integer.parseInt(damount);
                        if(i>=j){
                            int k = i-j;
                            tamount = String.valueOf(k);
                            
                            available = new JLabel("Available Amount : ");
                            available.setBounds(400,500,500,40);
                            available.setForeground(Color.BLACK);
                            available.setFont(font3);
                            bookFrame.add(available);

                            available.setText("Available Amount : "+tamount);
                            try{
                                String u = "UPDATE `userinfo` SET `amount` = ? WHERE `userinfo`.`username` = ?";
                                PreparedStatement pst3 = conn.prepareStatement(u);
                                pst3.setString(1,tamount);
                                pst3.setString(2,cu);
                                pst3.execute();
                            }catch(Exception e4){
                                JOptionPane.showMessageDialog(null,"Total Amount updation failed");
                            }
                            try{
                                String h = "insert into history (username,method,amount) values(?,?,?)";
                                PreparedStatement pst4 = conn.prepareStatement(h);
                                pst4.setString(1,cu);
                                pst4.setString(2,"Withdraw");
                                pst4.setString(3,damount);
                                pst4.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Withdraw Success Check your Account Balance");

                            }catch(Exception e6){
                                JOptionPane.showMessageDialog(null,"Updation of History table error");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Insufficient Balance");
                        }
                        
                        
                        
                        
                        
                        
                        /*try{
                            String u = "UPDATE `userinfo` SET `amount` = ? WHERE `userinfo`.`name` = ?";
                            PreparedStatement pst3 = conn.prepareStatement(u);
                            pst3.setString(1,tamount);
                            pst3.setString(2,name);
                            pst3.execute();
                            
                            String h = "insert into history (username,method,amount) values(?,?,?)";
                            PreparedStatement pst4 = conn.prepareStatement(h);
                            try{
                                String s = "select count (username) from history";
                                PreparedStatement pst6 = conn.prepareStatement(s);
                                ResultSet rSet7 =  pst6.executeQuery();
                                String sd = rSet7.getString(1);
                                
                            }catch(Exception s){
                                JOptionPane.showMessageDialog(null,"Serial Number Error");
                            }
                            pst4.setString(1,sd);
                            pst4.setString(2,name);
                            pst4.setString(3,"Deposit");
                            pst4.setString(4,damount);
                            pst4.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null,"Deposit of Amount Success");
                            
                        }catch(Exception e6){
                            JOptionPane.showMessageDialog(null,"Updation of total Amount Failed");
                        }*/
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username Does not exist");
                        amountInput.setText("");
                        amountInput.requestFocus();
                        //firstNameInput.requestFocus();
                    }
                }catch (Exception e2){
                        System.out.println(e2);
                        JOptionPane.showMessageDialog(null, "Assignment Failed");
                    }
                }
            
        });
        
        

        
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                bookFrame.dispose();
            }
        });
        
        
        bookFrame.setVisible(true);
        bookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}