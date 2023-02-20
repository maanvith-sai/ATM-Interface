/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmpackage;

/**
 *
 * @author Maanvith Sai
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class current  {
    Connection conn1;
    String DB_URL1 = "jdbc:mysql://localhost:ijy=3306/atm";
    String user1 = "root";
    String password1 = "";
    public static String pd;
    public static String no;
    public static String us;
    public current() throws ClassNotFoundException{
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn1= DriverManager.getConnection(DB_URL1, user1, password1);
            Statement stmt = conn1.createStatement();
            
            try{
                String cs= "select * from current where no = ?";
                PreparedStatement pst11 = conn1.prepareStatement(cs);
                pst11.setString(1,"1");
                ResultSet rs1 = pst11.executeQuery();
                if(rs1.next()){
                    no = rs1.getString(1);
                    us = rs1.getString(2);
                    pd = rs1.getString(3);
                }
            }catch(Exception o1){
                JOptionPane.showMessageDialog(null,"Current error");
            }
            
        }catch(Exception o){
            JOptionPane.showMessageDialog(null,"Connection Error");
        }
    }
}
