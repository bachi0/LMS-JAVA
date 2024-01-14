
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
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
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
public class MemMember implements ActionListener
{
    JFrame jfMemberFrame = new JFrame("New Registration");
    JLabel lbName = new JLabel("Name");
    JLabel lbPassword = new JLabel("Password");
    JLabel lbAge = new JLabel("Age");
    JLabel lbPhone = new JLabel("Phone Number");
    JLabel lbAddress = new JLabel("Address");
    JTextField tfName = new JTextField();
    JPasswordField pfPassword = new JPasswordField();
    JTextField tfAge = new JTextField();
    JTextField tfPhoneNumber = new JTextField();
    JTextArea taAddress = new JTextArea();
    JButton bCreate = new JButton("Create");
    JButton bCancel = new JButton("Cancel");
    JTextField tfMID = new JTextField();
    JLabel lbMID = new JLabel("MID");
        
    public MemMember()
    {
        lbMID.setBounds(50,60,150,30);
        tfMID.setBounds(200,60,150,30);
        lbName.setBounds(50,100,150,30);
        lbPassword.setBounds(50,140,150,30);
        lbAge.setBounds(50,180,150,30);
        lbPhone.setBounds(50,220,150,30);
        lbAddress.setBounds(50,260,150,30);
        tfName.setBounds(200,100,150,30); 
        pfPassword.setBounds(200,140,150,30);
        tfAge.setBounds(200,180,150,30);
        tfPhoneNumber.setBounds(200,220,150,30);
        taAddress.setBounds(200,260,150,50);
        bCreate.setBounds(200,320,75,30);
        bCancel.setBounds(280,320,75,30);
        bCreate.addActionListener(this);
        bCancel.addActionListener(this);
    }
        
    public JFrame getMemberFrame()
    {
        jfMemberFrame.add(tfMID);
        jfMemberFrame.add(lbMID);
        jfMemberFrame.add(lbName);        
        jfMemberFrame.add(lbPassword);
        jfMemberFrame.add(lbAge);
        jfMemberFrame.add(lbPhone);
        jfMemberFrame.add(lbAddress);    
        jfMemberFrame.add(tfName);
        jfMemberFrame.add(pfPassword);
        jfMemberFrame.add(tfAge);
        jfMemberFrame.add(tfPhoneNumber);
        jfMemberFrame.add(taAddress);
        jfMemberFrame.add(bCreate);
        jfMemberFrame.add(bCancel);
        jfMemberFrame.setSize(500,500);
        jfMemberFrame.setLayout(null);
        jfMemberFrame.setVisible(true);
        
        return jfMemberFrame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bCreate)
        {
            String mid = tfMID.getText();
            String name = tfName.getText();
            String pass = new String(pfPassword.getPassword());
            String Age = tfAge.getText();
            String phone = tfPhoneNumber.getText();
            String Address = taAddress.getText();
            int age = Integer.parseInt(Age);
            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("insert into Member (MID,MName,Password,Age,Phone,Address) values ('"+mid+"','"+name+"','"+pass+"',"+age+",'"+phone+"','"+Address+"')");
            } catch (SQLException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(jfMemberFrame,"Account Created.. Login again to continue");
            jfMemberFrame.dispose();
            
        }
        else if(e.getSource() == bCancel)
        {
            jfMemberFrame.dispose();
            MemLogin login = new MemLogin();
            login.getMemLogin();
        }
    }
    
}
