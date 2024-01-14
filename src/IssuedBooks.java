import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
public class IssuedBooks implements ActionListener
{
    JFrame jfIssueBook = new JFrame("Issuing Book");
    JLabel lbMID = new JLabel("MID  ");
    JLabel lbBName = new JLabel("Book Name ");
    JLabel lbMName = new JLabel("Member Name ");
    JLabel lbMsg = new JLabel("Book is Not Available");
    JLabel lbMsg1 = new JLabel("Would you like to Reserve the Book");
    JTextField tfMID = new JTextField();
    JTextField tfBName = new JTextField();
    JTextField tfMName = new JTextField();
    JButton bCheckandIssue = new JButton("Check and Issue");
    JButton bCancel = new JButton("Cancel");
    JButton bReserve = new JButton("Reserve");
    Date Issue,Due;
    public IssuedBooks()
    {
        long millis=System.currentTimeMillis();  
        Issue = new java.sql.Date(millis);
        Calendar c = Calendar.getInstance(); 
        c.setTime(Issue); 
        c.add(Calendar.DATE, 10);
        Due = new java.sql.Date(c.getTimeInMillis());
        lbMID.setBounds(75,150,200,40);
        lbBName.setBounds(75,200,200,40);
        lbMName.setBounds(75,250,200,40);
        tfMID.setBounds(250,150,200,40);
        tfMName.setBounds(250,250,200,40);
        tfBName.setBounds(250,200,200,40);
        bCheckandIssue.setBounds(50,300,200,40);
        bCancel.setBounds(260,300,150,40);
        bCheckandIssue.addActionListener(this);
        bCancel.addActionListener(this);
        lbMsg.setBounds(75,400,200,40);
        lbMsg1.setBounds(280,400,200,40);
        bReserve.setBounds(75,450,200,40);
    }
    public JFrame getIssueBook()
    {
        jfIssueBook.add(lbMID);
        jfIssueBook.add(lbBName);
        jfIssueBook.add(lbMName);
        jfIssueBook.add(tfMID);
        jfIssueBook.add(tfBName);
        jfIssueBook.add(tfMName);
        jfIssueBook.add(bCheckandIssue);
        jfIssueBook.add(bCancel);
        jfIssueBook.setSize(5000,500);
        jfIssueBook.setLayout(null);
        jfIssueBook.setVisible(true); 
        return jfIssueBook;
    }
    public void frame()
    {
        jfIssueBook.add(lbMsg);
        jfIssueBook.add(lbMsg1);
        jfIssueBook.add(bReserve);
        bReserve.addActionListener(this);
        jfIssueBook.setLayout(null);
        jfIssueBook.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Date dues = null;
        String jIssue,jDue;
            try
            {
            String Mid = tfMID.getText();
            String Bname = tfBName.getText();
            String MName=tfMName.getText();
            String bid ="";
            int avail;
            Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
            Statement stmt = con.createStatement();  
            if(e.getSource() == bCheckandIssue)
            {
                String q = "select BID,AvailableCopies from Books where BName = '"+Bname+"'";
                ResultSet rs = stmt.executeQuery(q);
                //rs.beforeFirst();
                while(rs.next())
                {
                    String BID = rs.getString(1);
                    int AvailableCopies = rs.getInt(2);
                    bid = BID;
                    avail = AvailableCopies;
                
                    if(avail==0)
                    {
                        frame();
                    }
                    else
                    {
                        String sql = "insert into IssuedBooks (BID,BName,MID,MName,IssuedDate,DueDate) values ('"+bid+"','"+Bname+"','"+Mid+"','"+MName+"',#"+Issue+"#,#"+Due+"#)";
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(jfIssueBook,"Successfully Issued");
                        avail =  avail-1;
                        stmt.executeUpdate("update Books set AvailableCopies = "+avail+" where BName = '"+Bname+"'");
                        jfIssueBook.dispose();
                        AdminPage adminFrame = new AdminPage();
                        adminFrame.getAdminFrame();
                    }
                }
                
            }
           
            if(e.getSource() == bReserve)
            {
                stmt.executeUpdate("insert into Reserve (BID,MID,MName,BName) values ('"+bid+"','"+Mid+"','"+MName+"','"+Bname+"')");
                JOptionPane.showMessageDialog(jfIssueBook,"Successfully Reserved");
                String sq1 = "select DueDate from IssuedBooks where BName = '"+Bname+"' and ReturnDate IS NULL";
                ResultSet r = stmt.executeQuery(sq1);
                while(r.next())
                {
                    Date due = r.getDate(1);
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(Due); 
                    c.add(Calendar.DATE, -1);
                    dues = new java.sql.Date(c.getTimeInMillis());
                }
                stmt.executeUpdate("update IssuedBooks set DueDate = #"+dues+"# where BName = '"+Bname+"' and ReturnDate IS NULL");
                jfIssueBook.dispose();
                AdminPage adminFrame = new AdminPage();
                adminFrame.getAdminFrame();
            }
            if(e.getSource() == bCancel)
            {
                jfIssueBook.dispose();
                AdminPage adminFrame = new AdminPage();
                adminFrame.getAdminFrame();
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}