
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
public class OrderBooks implements ActionListener 
{
    JFrame jfOrderBooks = new JFrame("Ordering New Book");
    JLabel lbMID = new JLabel("Member ID");
    JLabel lbMName = new JLabel("Member Name");
    JLabel lbBookName = new JLabel("Book Name");
    JLabel lbCopies = new JLabel("No Of Copies");
    JLabel lbcost = new JLabel("Cost");
    JTextField tfMID = new JTextField();
    JTextField tfMName = new JTextField();
    JTextField tfBookName = new JTextField();
    JTextField tfCopies = new JTextField();
    JTextField tfcost = new JTextField();
    JButton bAdd = new JButton("ADD");
    JButton bCancel = new JButton("CANCEL");
    public OrderBooks()
    {
        
        lbMID.setBounds(50,100,150,30);
        lbBookName.setBounds(50,180,150,30);
        lbMName.setBounds(50,140,150,30);
        lbCopies.setBounds(50,220,150,30);
        lbcost.setBounds(50,260,150,30);
        tfBookName.setBounds(200,180,150,30);
        tfMName.setBounds(200,140,150,30);
        tfMID.setBounds(200,100,150,30);
        tfCopies.setBounds(200,220,150,30);
        tfcost.setBounds(200,260,150,30);
        bAdd.setBounds(200,300,75,30);
        bCancel.setBounds(280,300,100,30);
        bAdd.addActionListener(this);
        bCancel.addActionListener(this);
    }
    public JFrame getOBookFrame()
    {
        jfOrderBooks.add(lbMID);
        jfOrderBooks.add(lbBookName);
        jfOrderBooks.add(lbMName);
        jfOrderBooks.add(tfMID);
        jfOrderBooks.add(tfBookName);
        jfOrderBooks.add(tfMName);
        jfOrderBooks.add(bAdd);
        jfOrderBooks.add(lbcost);
        jfOrderBooks.add(tfcost);
        jfOrderBooks.add(bCancel);
        jfOrderBooks.add(lbCopies);
        jfOrderBooks.add(tfCopies);
        jfOrderBooks.setSize(500,500);
        jfOrderBooks.setLayout(null);
        jfOrderBooks.setVisible(true);
        return jfOrderBooks;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String Mid = tfMID.getText();
        String Bname = tfBookName.getText();
        String Mname = tfMName.getText();
        String Copies=tfCopies.getText();
        String COst = tfcost.getText();
        if(e.getSource() == bAdd)
        {
            if(Mid.equals("") || Bname.equals("") || Mname.equals("") || Copies.equals(""))
            {
                JOptionPane.showMessageDialog(jfOrderBooks,"Enter Each Details of the Book","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
               try {
                   Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                   Statement stmt = con.createStatement();
                   int copies = Integer.parseInt(Copies);
                   int cost = Integer.parseInt(COst);
                   stmt.executeUpdate("insert into Orders (MID,MName,BName,Quantity,cost) values ('"+Mid+"','"+Mname+"','"+Bname+"',"+copies+","+cost+")");
                   } 
                   catch (SQLException ex) 
                   {
                        Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
                   }
            JOptionPane.showMessageDialog(jfOrderBooks,"Successfully Ordered");
            jfOrderBooks.dispose();
            MemberPage page = new MemberPage();
            page.getMemberFrame();
            }
        }
        if(e.getSource() == bCancel)
        {
            jfOrderBooks.dispose();
            MemberPage page = new MemberPage();
            page.getMemberFrame();
        }
    }
}
