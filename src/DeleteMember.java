import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
public class DeleteMember implements ActionListener
{
    JFrame jfDeleteMember = new JFrame("Deleting Member");
    JLabel lbMname = new JLabel("Name");
    JLabel lbMID = new JLabel("MID");
    JTextField tfName = new JTextField();
    JTextField tfMID = new JTextField();
    JButton bSubmit = new JButton("Submit");
    JButton bCancel = new JButton("Cancel");
    public DeleteMember()
    {
        lbMname.setBounds(100,100,200,40);
        lbMID.setBounds(100,50,200,40);
        tfMID.setBounds(300,50,200,40);
        tfName.setBounds(300,100,200,40);
        bSubmit.setBounds(100,150,150,40);
        bCancel.setBounds(280,150,150,40);
        bSubmit.addActionListener(this);
        bCancel.addActionListener(this);
    }
    public JFrame getDeleteMember()
    {
        jfDeleteMember.add(lbMname);
        jfDeleteMember.add(lbMID);
        jfDeleteMember.add(tfMID);
        jfDeleteMember.add(tfName);
        jfDeleteMember.add(bSubmit);
        jfDeleteMember.add(bCancel);
        jfDeleteMember.setSize(700,700);
        jfDeleteMember.setLayout(null);
        jfDeleteMember.setVisible(true);
        return jfDeleteMember;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bSubmit)
        {
            String name = tfName.getText();
            String mid = tfMID.getText();
            try{
                Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                Statement stmt = con.createStatement();
                String q="select * from Member where MName = '"+name+"' and MID = '"+mid+"'";
                ResultSet r = stmt.executeQuery(q);
                stmt.executeUpdate("delete from Member where MName = '"+name+"' and MID = '"+mid+"'");
                while(r.next())
                {
                String MID = r.getString(1);
                String Mname = r.getString(2);
                String Age = r.getString(3);
                String Phone = r.getString(4);
                String Address = r.getString(5);
                String put = "";
                put="MID : "+MID+"\nMember Name :"+Mname+"\nAge : "+Age+"\nPhone :"+Phone+"\nAddress :"+Address+"\n";
                JOptionPane.showMessageDialog(jfDeleteMember,put);
                }
                jfDeleteMember.dispose();
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
            jfDeleteMember.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
        
    }
    
}
