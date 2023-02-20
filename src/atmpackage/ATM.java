package atmpackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;

public class ATM{
    String DB_URL = "jdbc:mysql://localhost:3306/atm";
    String user = "root";
    String password = "";
    Connection conn = null;
    public static void main (String [] args) throws Exception{
        String DB_URL = "jdbc:mysql://localhost:3306/atm";
        String user = "root";
        String password = "";
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection (DB_URL, user, password);
            
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        
        try{
            String r = "TRUNCATE table current";
            Statement stmt = conn.createStatement();
            stmt.execute(r);
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Main Error");
        }
        
        login Login = new login();
    }
    
}