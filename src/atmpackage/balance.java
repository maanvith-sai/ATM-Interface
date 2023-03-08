package atmpackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class balance {
    JFrame bookFrame;
    JLabel titleLabel, enterPnrText, bookingFormText, emailLabel, fromLabel, toLabel, dateLabel, timeLabel;
    JLabel pnr, name, email, locationinfo, datentime,trainName, trainNumber;
    JTextField firstNameInput, lastNameInput, emailInput, pnrInput, timeInput; JComboBox fromlistCities, tolistCities;
    JButton submitButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    String cu;
    


    public balance() throws ClassNotFoundException {

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
        bookFrame = new JFrame("Balance Enquiry by Maanvith"); 
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);

        titleLabel = new JLabel("Balance Enquiry"); 
        titleLabel.setBounds (500, 5, 500, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 60);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);
        
        bookingFormText = new JLabel("View Balance"); 
        bookingFormText.setBounds(580, 100, 400, 200); 
        bookingFormText.setForeground (Color.BLACK); 
        Font font2 = new Font("Serif", Font. BOLD, 50); 
        bookingFormText.setFont(font2);
        bookFrame.add(bookingFormText);
        
        
        /*enterPnrText = new JLabel("Enter UserName : ");
        enterPnrText.setBounds(400,300,500,40);
        enterPnrText.setForeground(Color.BLACK);
        Font font3 = new Font("Sierf", Font.BOLD,30);
        enterPnrText.setFont(font3);
        bookFrame.add(enterPnrText);
        
        pnrInput = new JTextField();
        pnrInput.setBounds(700,300,300,60);
        pnrInput.setFont(font3);
        pnrInput.requestFocus();
        bookFrame.add(pnrInput);*/
        

        submitButton = new JButton("View Balance"); 
        submitButton.setBounds (450, 450, 500, 100); 
        Font font4 = new Font("Sierf", Font.PLAIN,35);
        submitButton.setFont(font4);
        bookFrame.add(submitButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (450, 650, 500, 100); 
        backButton.setFont(font4);
        bookFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    
                            pnr = new JLabel("Amount : ");
                            pnr.setBounds(400,300,800,60);
                            pnr.setForeground(Color.BLACK);
                            Font font4 = new Font("Serif", Font.BOLD, 50);
                            pnr.setFont(font4);
                            bookFrame.add(pnr);
                    
                    //String pnrNumber = pnrInput.getText().toString();
                    String q = "Select amount from userinfo where username = ?";
                    
                    PreparedStatement pst1 = conn.prepareStatement(q);
                    
                    pst1.setString(1,cu);
                   
                    ResultSet rSet = pst1.executeQuery();
                    if(rSet.next()){
                        pnr.setText("Available Amount : " +rSet.getString(1));
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username Doesnot exits");
                        pnrInput.setText("");
                        pnrInput.requestFocus();
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