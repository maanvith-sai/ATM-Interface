package atmpackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class history extends javax.swing.JFrame  {
    JFrame bookFrame;
    JLabel titleLabel, enterPnrText, bookingFormText, emailLabel, fromLabel, toLabel, dateLabel, timeLabel;
    JLabel pnr, name, email, locationinfo, datentime,trainName, trainNumber;
    JTextField firstNameInput, lastNameInput, emailInput, pnrInput, timeInput; JComboBox fromlistCities, tolistCities;
    JButton submitButton, backButton;
    Connection conn;
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    JTable historyTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    String cu;

    public history() throws ClassNotFoundException {

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
        
        
        bookFrame = new JFrame("Transaction History by Maanvith"); 
        bookFrame.setSize(4000, 4000);
        bookFrame.setLayout(null);

        titleLabel = new JLabel("Transaction History"); 
        titleLabel.setBounds (500, 5, 700, 200); 
        Font font1 = new Font("Serif", Font.BOLD, 60);
        titleLabel.setForeground (Color.BLACK);
        titleLabel.setFont(font1);
        bookFrame.add(titleLabel);
        
        bookingFormText = new JLabel("View History"); 
        bookingFormText.setBounds(650, 170, 400, 200); 
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
        

        submitButton = new JButton("View History"); 
        submitButton.setBounds (550, 400, 500, 80); 
        Font font4 = new Font("Sierf", Font.PLAIN,35);
        submitButton.setFont(font4);
        bookFrame.add(submitButton);

        backButton = new JButton("Back"); 
        backButton.setBounds (550, 550, 500, 80); 
        backButton.setFont(font4);
        bookFrame.add(backButton);
        
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    //String user = pnrInput.getText();
                    tableModel = new DefaultTableModel();
                    historyTable = new JTable(tableModel);
                    historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 25));
                    historyTable.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    String[] columnNames = {"username", "method", "amount"};
                    tableModel.setColumnIdentifiers(columnNames);
                    
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM history WHERE username = ?");
                    
                    stmt.setString(1, cu);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Object[] rowData = {rs.getString("username"), rs.getString("method"), rs.getString("amount")};
                        tableModel.addRow(rowData);
                    }
                    
                    
                    
                }catch(Exception e2){
                    JOptionPane.showMessageDialog(null,"History retrieval Error");
                }
                
                scrollPane = new JScrollPane(historyTable);
                JScrollBar bar = scrollPane.getVerticalScrollBar();
                bar.setPreferredSize(new Dimension(40, 0));
                add(scrollPane);
                pack();
                setVisible(true);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
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