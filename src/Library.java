/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author sudhaker
 */
public class Library extends JFrame implements ActionListener
{
    JFrame jfStartFrame = new JFrame("Library Management System");;
    JButton bLibrarian  = new JButton("Librarian");
    JButton bMember = new JButton("Member");
    JLabel lbTitle = new JLabel("Welcome to University Library");
    Library()
    {
        lbTitle.setBounds(150,50,200,30);    
        bLibrarian.setBounds(150,100,150,30);
        bMember.setBounds(150,150,150,30);
        bLibrarian.addActionListener(this);
        bMember.addActionListener(this);    
    }
    public JFrame getMainFrame()
    {
        jfStartFrame.add(lbTitle);
        jfStartFrame.add(bLibrarian);
        jfStartFrame.add(bMember);
        jfStartFrame.setSize(500,500);
        jfStartFrame.setLayout(null);
        jfStartFrame.setVisible(true);
        jfStartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jfStartFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bLibrarian)
        {
            jfStartFrame.dispose();
            LibLogin libloginobj = new LibLogin();
            libloginobj.getLibLogin();    
        }
        if(e.getSource() == bMember)
        {
            jfStartFrame.dispose();
            MemLogin Memloginobj = new MemLogin();
            Memloginobj.getMemLogin();
        }
    }
    public static void main(String[] args)
    {
        Library library = new Library();
        library.getMainFrame();
    }
}