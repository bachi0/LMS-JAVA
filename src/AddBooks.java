
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
public class AddBooks implements ActionListener
{
    JFrame jfAddBooks = new JFrame("Insertion of New Book");
    JLabel lbBookId = new JLabel("Book ID");
    JLabel lbBookName = new JLabel("Book Name");
    JLabel lbAuthor = new JLabel("Author");
    JLabel lbCopies = new JLabel("No Of Copies");
    JLabel lbPublication = new JLabel("Publication");
    JTextField tfBookID = new JTextField();
    JTextField tfBookName = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfPublications = new JTextField();
    JTextField tfCopies = new JTextField();
    JButton bAdd = new JButton("ADD");
    JButton bCancel = new JButton("CANCEL");
    public AddBooks()
    {
        
        lbBookId.setBounds(50,100,150,30);
        lbBookName.setBounds(50,140,150,30);
        lbAuthor.setBounds(50,180,150,30);
        lbPublication.setBounds(50,220,150,30);
        lbCopies.setBounds(50,260,150,30);
        tfBookID.setBounds(200,100,150,30);
        tfBookName.setBounds(200,140,150,30);
        tfAuthor.setBounds(200,180,150,30);
        tfPublications.setBounds(200,220,150,30);
        tfCopies.setBounds(200,260,150,30);
        bAdd.setBounds(200,300,75,30);
        bCancel.setBounds(280,300,100,30);
        bAdd.addActionListener(this);
        bCancel.addActionListener(this);
    }
    public JFrame getAddBookFrame()
    {
        jfAddBooks.add(lbBookId);
        jfAddBooks.add(lbBookName);
        jfAddBooks.add(lbAuthor);
        jfAddBooks.add(lbPublication);
        jfAddBooks.add(tfBookID);
        jfAddBooks.add(tfBookName);
        jfAddBooks.add(tfAuthor);
        jfAddBooks.add(tfPublications);
        jfAddBooks.add(bAdd);
        jfAddBooks.add(bCancel);
        jfAddBooks.add(lbCopies);
        jfAddBooks.add(tfCopies);
        jfAddBooks.setSize(500,500);
        jfAddBooks.setLayout(null);
        jfAddBooks.setVisible(true);
        return jfAddBooks;
    }
    public void actionPerformed(ActionEvent e)
    {
        String Bid = tfBookID.getText();
        String Bname = tfBookName.getText();
        String author = tfAuthor.getText();
        String publication = tfPublications.getText();
        String Copies=tfCopies.getText();
        if(e.getSource() == bAdd)
        {
            if(Bid.equals("") || Bname.equals("") || author.equals("") || publication.equals("") || Copies.equals(""))
            {
                JOptionPane.showMessageDialog(jfAddBooks,"Enter Each Details of the Book","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
               try {
                   Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                   Statement stmt = con.createStatement();
                   int copies = Integer.parseInt(Copies);
                   int Avail = copies;
                   stmt.executeUpdate("insert into Books (BID,BName,Author,Publication,NoOfCopies,AvailableCopies) values ('"+Bid+"','"+Bname+"','"+author+"','"+publication+"',"+copies+","+Avail+")");
                   } 
                   catch (SQLException ex) 
                   {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                   }
            JOptionPane.showMessageDialog(jfAddBooks,"Successfully Added");
            jfAddBooks.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
            }
        }
        if(e.getSource() == bCancel)
        {
            jfAddBooks.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
    }
}
