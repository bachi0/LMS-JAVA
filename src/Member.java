
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.*;
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
public class Member implements ActionListener{
    
    JFrame jfMemberFrame = new JFrame("New Registration");
    JLabel lbName = new JLabel("Name");
    JLabel lbPassword = new JLabel("Password");
    JLabel lbAge = new JLabel("Age");
    JLabel lbPhone = new JLabel("Phone Number");
    JLabel lbAddress = new JLabel("Address");
    JLabel lbMID = new JLabel("MID");
    JTextField tfName = new JTextField();
    JTextField tfMID = new JTextField();
    JPasswordField pfPassword = new JPasswordField();
    JTextField tfAge = new JTextField();
    JTextField tfPhoneNumber = new JTextField();
    JTextArea taAddress = new JTextArea();
    JButton bCreate = new JButton("Create");
    JButton bCancel = new JButton("Cancel");
        
    public Member()
    {
        lbName.setBounds(50,100,150,30);
        lbMID.setBounds(50,60,150,30);
        lbPassword.setBounds(50,140,150,30);
        lbAge.setBounds(50,180,150,30);
        lbPhone.setBounds(50,220,150,30);
        lbAddress.setBounds(50,260,150,30);
        tfName.setBounds(200,100,150,30); 
        tfMID.setBounds(200,60,150,30);
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
        jfMemberFrame.add(lbName);
        jfMemberFrame.add(lbMID);
        jfMemberFrame.add(tfMID);
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
            if(mid.equals("") || name.equals("") || pass.equals("") || age == 0 || phone.equals("") || Address.equals(""))
                JOptionPane.showMessageDialog(jfMemberFrame,"Enter each detail","Alert",JOptionPane.WARNING_MESSAGE);
            else
            {
            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
                Statement stmt = con.createStatement();
                stmt.executeUpdate("insert into Member (MID,MName,Password,Age,Phone,Address) values ('"+mid+"','"+name+"','"+pass+"',"+age+",'"+phone+"','"+Address+"')");
            } catch (SQLException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(jfMemberFrame,"Successfully Added");
            jfMemberFrame.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
            }
            
        }
        else if(e.getSource() == bCancel)
        {
            jfMemberFrame.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
    }

}
