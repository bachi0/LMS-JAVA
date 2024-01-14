
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class LibLogin implements ActionListener
{
    
    JFrame jfTitle = new JFrame("Univesrsity of Library");
    JLabel lbUsername = new JLabel("User-Name");
    JTextField tfUsername = new JTextField();
    JLabel lbPassword = new JLabel("Password");
    JPasswordField pfPassword = new JPasswordField();
    JButton bLogin = new JButton("Login");
    JButton bBack = new JButton("Back");
    public LibLogin()
    {
        lbUsername.setBounds(50,100,150,30);
        tfUsername.setBounds(150,100,150,30);
        lbPassword.setBounds(50,150,150,30);
        pfPassword.setBounds(150,150,150,30);
        bLogin.setBounds(150,200,75,30);
        bBack.setBounds(230,200,75,30);
        bLogin.addActionListener(this);
        bBack.addActionListener(this);
    }
    public JFrame getLibLogin()
    {
        jfTitle.add(bBack);
        jfTitle.add(bLogin);
        jfTitle.add(lbUsername);
        jfTitle.add(lbPassword);
        jfTitle.add(pfPassword);
        jfTitle.add(tfUsername);
        jfTitle.setSize(500,500);
        jfTitle.setLayout(null);
        jfTitle.setVisible(true);  
        return jfTitle;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String Username = tfUsername.getText();
        String Password = new String(pfPassword.getPassword());
        if(e.getSource() == bLogin)
        {
            if(Username.equalsIgnoreCase("Admin") && Password.equals("admin123"))
            {
                jfTitle.dispose();
                AdminPage adminFrame = new AdminPage();
                adminFrame.getAdminFrame();
//              AdminPage();
            }
            else
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
    }
}
