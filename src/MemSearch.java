
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class MemSearch implements ActionListener 
{
    JFrame jfSearch = new JFrame("Searching a book");
    JLabel lbBname = new JLabel("Book Name");
    JTextField tfBname = new JTextField();
    JButton bCheck = new JButton("Check");
    JButton bBack = new JButton("Back");
    public MemSearch()
    {
        lbBname.setBounds(100,100,150,40);
        tfBname.setBounds(250,100,150,40);
        bCheck.setBounds(100,150,150,40);
        bBack.setBounds(260,150,140,40);
        bBack.addActionListener(this);
        bCheck.addActionListener(this);
    }
    public JFrame getSearchFrame()
    {
        jfSearch.add(lbBname);
        jfSearch.add(tfBname);
        jfSearch.add(bCheck);
        jfSearch.add(bBack);
        jfSearch.setSize(500,500);
        jfSearch.setLayout(null);
        jfSearch.setVisible(true);
        return jfSearch;
    }
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(ae.getSource() == bCheck)
            {
                int avail = 0;
                String BId = null;
                String msg = "";
                String bname = tfBname.getText();
                Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                Statement stmt = con.createStatement();
                String q = "select BID,AvailableCopies from Books where BName = '"+bname+"'";
                ResultSet rs = stmt.executeQuery(q);
                while(rs.next())
                {
                    BId = rs.getString(1);
                    int AC = rs.getInt(2);
                    avail = AC;
                }
                if(BId.equals(null))
                {
                    JOptionPane.showMessageDialog(jfSearch,"Book not found","Alert",JOptionPane.WARNING_MESSAGE);
                    jfSearch.dispose();
                    MemberPage page = new MemberPage();
                    page.getMemberFrame();
                }    
                else
                {
                    msg="BID : "+BId+"\nBook Name : "+bname+"\nAvailable Copies : "+avail;
                    JOptionPane.showMessageDialog(jfSearch,msg);
                    jfSearch.dispose();
                    MemberPage page = new MemberPage();
                    page.getMemberFrame();
                }
            }
            if(ae.getSource() == bBack)
            {
                jfSearch.dispose();
                MemberPage page = new MemberPage();
                page.getMemberFrame();
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
