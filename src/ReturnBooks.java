import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
public class ReturnBooks implements ActionListener 
{
    JFrame jfReturnBook = new JFrame("Returning Book");
    JLabel lbMid = new JLabel("MID ");
    JLabel lbBName = new JLabel("Book Name ");
    JTextField tfMid = new JTextField();
    JTextField tfBname = new JTextField();
    JButton bSubmit = new JButton("Submit");
    JButton bCancel = new JButton("Cancel");
    Date returnDate,rdate;
    public ReturnBooks()
    {
        long millis=System.currentTimeMillis();  
        returnDate = new java.sql.Date(millis);
        lbMid.setBounds(100,100,200,50);
        lbBName.setBounds(100,160,200,50);
        tfMid.setBounds(250,100,200,50);
        tfBname.setBounds(250,160,200,50);
        bSubmit.setBounds(150,220,150,50);
        bCancel.setBounds(310,220,150,50);
        bSubmit.addActionListener(this);
        bCancel.addActionListener(this);
    }
    public JFrame getReturnFrame()
    {
        jfReturnBook.add(lbMid);
        jfReturnBook.add(lbBName);
        jfReturnBook.add(tfMid);
        jfReturnBook.add(tfBname);
        jfReturnBook.add(bSubmit);
        jfReturnBook.add(bCancel);
        jfReturnBook.setSize(500,500);
        jfReturnBook.setLayout(null);
        jfReturnBook.setVisible(true);
        return jfReturnBook;
    }
    public void actionPerformed(ActionEvent ae)
    {
        int avail,Avail;
        if(ae.getSource() == bSubmit)
        {
        String mid = tfMid.getText();
        String bname = tfBname.getText();
        try
        {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
            Statement stmt = con.createStatement();
            
            String q = "select MID,ReturnDate from IssuedBooks where BName = '"+bname+"'";
            ResultSet re = stmt.executeQuery(q);
            while(re.next())
            {
                String MID = re.getString(1);
                Date date = re.getDate(2);
                if(mid.equals(MID) && date == null)
                {
                stmt.executeUpdate("update IssuedBooks set ReturnDate = #"+returnDate+"# where BName = '"+bname+"' and MID = '"+mid+"'");
                JOptionPane.showMessageDialog(jfReturnBook,"Successfully Updated");
                String sq = "select AvailableCopies from Books where BName = '"+bname+"'";
                ResultSet rs = stmt.executeQuery(sq);
                while(rs.next())
                {
                avail = rs.getInt(1);
                Avail = avail+1;
                //Avail = Avail+1;
                stmt.executeUpdate("update Books set AvailableCopies = "+Avail+" where BName = '"+bname+"'");
                }
                jfReturnBook.dispose();
                AdminPage adminFrame = new AdminPage();
                adminFrame.getAdminFrame();
                }
                else
                {
                JOptionPane.showMessageDialog(jfReturnBook,"Values Entered Wrong","Alert",JOptionPane.WARNING_MESSAGE);
                
                }
            }
        }
        catch(Exception e)
        {
            
        }
        }
        if(ae.getSource() == bCancel)
        {
            jfReturnBook.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
    }
}
