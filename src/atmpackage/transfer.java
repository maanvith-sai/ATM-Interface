package atmpackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class transfer {
    JFrame bookFrame;
    JLabel titleLabel, amountText,nameText, bookingFormText, topname, topaccno,available,transfer;
    JLabel  accno1;
    JTextField amountInput, nameInput, accInput, accInput1; 
    JButton submitButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    String cu,tamount,name,name1, tamount1,uname;

    public transfer() throws ClassNotFoundException {

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
        
        
        bookFrame = new JFrame("Transfer interface by Maanvith"); 
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);

        titleLabel = new JLabel("ATM Interface"); 
        titleLabel.setBounds (600, 5, 500, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 50);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);
        
        bookingFormText = new JLabel("TRANSFER SECTION"); 
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
        amountInput.setBounds(850,400,300,40);
        amountInput.requestFocus();
        bookFrame.add(amountInput);
        
        
        /*accno = new JLabel("Enter Your Account Number :");
        accno.setBounds(400,300,400,40);
        accno.setForeground(Color.BLACK);
        accno.setFont(font3);
        bookFrame.add(accno);

        accInput = new JTextField();
        accInput.setBounds(850,300,300,40);
        accInput.requestFocus();
        bookFrame.add(accInput);*/
        
        accno1 = new JLabel("Enter To Account Number :");
        accno1.setBounds(400,300,400,40);
        accno1.setForeground(Color.BLACK);
        accno1.setFont(font3);
        bookFrame.add(accno1);

        accInput1 = new JTextField();
        accInput1.setBounds(850,300,300,40);
        accInput1.requestFocus();
        bookFrame.add(accInput1);

        submitButton = new JButton("Transfer"); 
        submitButton.setBounds (450, 700, 160, 40);
        submitButton.setFont(font7);
        bookFrame.add(submitButton);

        backButton = new JButton("Back to Main Menu"); 
        backButton.setBounds (700, 700, 250, 40);
        backButton.setFont(font7);
        bookFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    
                    //String ano = accInput.getText();
                    String q = "Select * from userinfo where username = ?";
                    
                    
                    PreparedStatement pst1 = conn.prepareStatement(q);
                    
                    pst1.setString(1,cu);
                    
                   
                    ResultSet rSet = pst1.executeQuery();
                    if(rSet.next()){
                        name = rSet.getString(1);
                        uname = rSet.getString(4);
                        topname.setText("Name: " +rSet.getString(1));
                        topaccno.setText("Acc No: " +rSet.getString(2));
                        String bamount = rSet.getString(3);
                        String damount = amountInput.getText().toString();
                        int i=Integer.parseInt(bamount);
                        int j=Integer.parseInt(damount);
                        int k = i-j;
                        tamount = String.valueOf(k);
                        
                        transfer = new JLabel("Transfered Amount : ");
                        transfer.setBounds(400,500,500,40);
                        transfer.setForeground(Color.BLACK);
                        transfer.setFont(font3);
                        bookFrame.add(transfer);
                        
                        available = new JLabel("Available Amount : ");
                        available.setBounds(400,600,500,40);
                        available.setForeground(Color.BLACK);
                        available.setFont(font3);
                        bookFrame.add(available);
                        
                        available.setText("Available Amount : "+tamount);
                        transfer.setText("Transfered Amount : "+damount);
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
                            pst4.setString(1,uname);
                            pst4.setString(2,"Debit");
                            pst4.setString(3,damount);
                            pst4.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Transfer Success Check your Account History");
                            
                        }catch(Exception e6){
                            JOptionPane.showMessageDialog(null,"Updation of History table error");
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Account Number Does not exist");
                        amountInput.setText("");
                        amountInput.requestFocus();
                        //firstNameInput.requestFocus();
                    }
                    String ano1 = accInput1.getText();
                    String r = "select * from userinfo where accno = ?";
                    PreparedStatement pst2 = conn.prepareStatement(r);
                    pst2.setString(1,ano1);
                    
                    ResultSet rSet1 = pst2.executeQuery();
                    if(rSet1.next()){
                        
                        name1 = rSet1.getString(4);
                        String bamount1 = rSet1.getString(3);
                        String damount1 = amountInput.getText().toString();
                        int i=Integer.parseInt(bamount1);
                        int j=Integer.parseInt(damount1);
                        int k = i+j;
                        tamount1 = String.valueOf(k);
                        
                        try{
                            String u = "UPDATE `userinfo` SET `amount` = ? WHERE `userinfo`.`accno` = ?";
                            PreparedStatement pst4 = conn.prepareStatement(u);
                            pst4.setString(1,tamount1);
                            pst4.setString(2,ano1);
                            pst4.execute();
                        }catch(Exception e1){
                            JOptionPane.showMessageDialog(null,"Credit Updation Error in userinfo");
                        }
                        try{
                            String h1 = "insert into history (username,method,amount) values(?,?,?)";
                            PreparedStatement pst4 = conn.prepareStatement(h1);
                            pst4.setString(1,name1);
                            pst4.setString(2,"Credit");
                            pst4.setString(3,damount1);
                            pst4.executeUpdate();
                            
                            
                        }catch(Exception e2){
                            JOptionPane.showMessageDialog(null,"Credit Updation Error in history");
                        }
                        
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
