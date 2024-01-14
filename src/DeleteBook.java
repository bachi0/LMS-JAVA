
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
public class DeleteBook implements ActionListener
{
    JFrame jfDeleteBook = new JFrame("Deleting Book");
    JLabel lbBname = new JLabel("Name");
    JTextField tfName = new JTextField();
    JButton bSubmit = new JButton("Submit");
    JButton bCancel = new JButton("Cancel");
    public DeleteBook()
    {
        lbBname.setBounds(100,100,200,40);
        tfName.setBounds(300,100,200,40);
        bSubmit.setBounds(150,150,150,40);
        bCancel.setBounds(300,150,150,40);
        bSubmit.addActionListener(this);
        bCancel.addActionListener(this);
    }
    public JFrame getDeleteBook()
    {
        jfDeleteBook.add(lbBname);
        jfDeleteBook.add(tfName);
        jfDeleteBook.add(bSubmit);
        jfDeleteBook.add(bCancel);
        jfDeleteBook.setSize(500,500);
        jfDeleteBook.setLayout(null);
        jfDeleteBook.setVisible(true);
        return jfDeleteBook;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bSubmit)
        {
            String name = tfName.getText();
            try{
                Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                Statement stmt = con.createStatement();
                String q="select * from Books where BName = '"+name+"'";
                ResultSet r = stmt.executeQuery(q);
                stmt.executeUpdate("delete from Books where BName ='"+name+"'");
                while(r.next())
                {
                String BID = r.getString(1);
                String Bname = r.getString(2);
                String Author = r.getString(3);
                String Publication = r.getString(4);
                String NoOfCopies = r.getString(5);
                //String IssuedCopies = r.getString(6);
                String put = "";
                put="BID : "+BID+"\nBook Name :"+Bname+"\nAuthor : "+Author+"\nPublication :"+Publication+"\nNoOfCopies :"+NoOfCopies+"\n";
                JOptionPane.showMessageDialog(jfDeleteBook,put);
                }
                jfDeleteBook.dispose();
                AdminPage adminFrame = new AdminPage();
                adminFrame.getAdminFrame();
            }
            catch (SQLException ex) 
                   {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                   }
        }
        if(e.getSource() == bCancel)
        {
            jfDeleteBook.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
        
    }
    
}
