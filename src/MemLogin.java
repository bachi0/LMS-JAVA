
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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
public class MemLogin implements ActionListener
{
    JFrame jfTitle = new JFrame("Univesrsity of Library");
    JLabel lbUsername = new JLabel("User-Name");
    JTextField tfUsername = new JTextField();
    JLabel lbPassword = new JLabel("Password");
    JPasswordField pfPassword = new JPasswordField();
    JButton bLogin = new JButton("Login");
    JButton bBack = new JButton("Back");
    JButton bCreateAcc = new JButton("Create New Account");
    public MemLogin()
    {
        lbUsername.setBounds(50,100,150,30);
        tfUsername.setBounds(150,100,150,30);
        lbPassword.setBounds(50,150,150,30);
        pfPassword.setBounds(150,150,150,30);
        bLogin.setBounds(150,200,75,30);
        bBack.setBounds(230,200,75,30);
        bCreateAcc.setBounds(150,250,150,40);
        bLogin.addActionListener(this);
        bBack.addActionListener(this);
        bCreateAcc.addActionListener(this);
    }
    public JFrame getMemLogin()
    {
        jfTitle.add(bBack);
        jfTitle.add(bLogin);
        jfTitle.add(lbUsername);
        jfTitle.add(lbPassword);
        jfTitle.add(pfPassword);
        jfTitle.add(tfUsername);
        jfTitle.add(bCreateAcc);
        jfTitle.setSize(500,500);
        jfTitle.setLayout(null);
        jfTitle.setVisible(true);  
        return jfTitle;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource() == bLogin)
        {
            int count=0;
        String Username = tfUsername.getText();
        String Password = new String(pfPassword.getPassword());
        try
        {
            Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
            Statement stmt = con.createStatement();
            String q = "select MName,Password from Member";
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next())
            {
                String User = rs.getString(1);
                String pass = rs.getString(2);
                if(Username.equalsIgnoreCase(User) && Password.equals(pass))
                {
                    count = 1;
                    jfTitle.dispose();
                    MemberPage MemberFrame = new MemberPage();
                    MemberFrame.getMemberFrame();
//                  MemberPage();
                }
            }    
        }
        catch(Exception ex)
        {
            
        }
        if(count == 0)
            {
                JOptionPane.showMessageDialog(jfTitle,"Wrong Credentials","Alert",JOptionPane.WARNING_MESSAGE);
                pfPassword.setText("");
            }
        }
        if(e.getSource() == bBack)
        {
            jfTitle.dispose();
            Library libraryobj = new Library();
            libraryobj.getMainFrame();
            
        }
        if(e.getSource() == bCreateAcc)
        {
            jfTitle.dispose();
            MemMember memberObj = new MemMember();
            memberObj.getMemberFrame();           
        }
    }
}


