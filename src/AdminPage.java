
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
public class AdminPage implements ActionListener
{
    JFrame jfAdmin = new JFrame("Welcome! ADMIN ");
    JButton bCreateMember = new JButton("Create Member");
    JButton bViewMember = new JButton("View Member");
    JButton bDeleteMember = new JButton("Delete Member");
    JButton bAddBooks= new JButton("Add Books");
    JButton bViewBooks = new JButton("View Books");  
    JButton bSearchBooks = new JButton("Search Books");
    JButton bIssueBooks = new JButton("Issue Books");
    JButton bDeleteBooks= new JButton("Delete Books");
    JButton bReturnBooks= new JButton("Return Books");
    JButton bSignOut = new JButton("Sign Out");
public AdminPage()
{
    bCreateMember.setBounds(150,50,150,30);
    bViewMember.setBounds(150,90,150,30);
    bDeleteMember.setBounds(150,130,150,30);
    bAddBooks.setBounds(150,170,150,30);
    bViewBooks.setBounds(150,210,150,30);
    bSearchBooks.setBounds(150,250,150,30);
    bIssueBooks.setBounds(150,290,150,30);
    bDeleteBooks.setBounds(150,330,150,30);
    bReturnBooks.setBounds(150,370,150,30);
    bSignOut.setBounds(150,410,150,30);
    bCreateMember.addActionListener(this);
    bViewMember.addActionListener(this);
    bDeleteMember.addActionListener(this);
    bAddBooks.addActionListener(this);
    bViewBooks.addActionListener(this); 
    bSearchBooks.addActionListener(this);
    bIssueBooks.addActionListener(this);
    bDeleteBooks.addActionListener(this);
    bReturnBooks.addActionListener(this);
    bSignOut.addActionListener(this);
}
public JFrame getAdminFrame()
{
    jfAdmin.add(bCreateMember);
    jfAdmin.add(bViewMember);
    jfAdmin.add(bDeleteMember);
    jfAdmin.add(bAddBooks);
    jfAdmin.add(bViewBooks);
    jfAdmin.add(bSearchBooks);
    jfAdmin.add(bIssueBooks);
    jfAdmin.add(bDeleteBooks);
    jfAdmin.add(bReturnBooks);
    jfAdmin.add(bSignOut);
    jfAdmin.setSize(500,500);
    jfAdmin.setLayout(null);
    jfAdmin.setVisible(true);
    return jfAdmin;
}
    @Override
    public void actionPerformed(ActionEvent e)
{
    if(e.getSource() == bCreateMember)
    {
        jfAdmin.dispose();
        Member memberObj = new Member();
        memberObj.getMemberFrame();
    }
    if(e.getSource()==bViewMember)
    {
        jfAdmin.dispose();
        ViewMember member = new ViewMember();
        member.getViewMember();
    }
    if(e.getSource()==bDeleteMember)
    {
        jfAdmin.dispose();
        DeleteMember deleteMemberobj = new DeleteMember();
        deleteMemberobj.getDeleteMember();
    }
    if(e.getSource()==bAddBooks)
    {
        jfAdmin.dispose();
        AddBooks addbooksobj = new AddBooks();
        addbooksobj.getAddBookFrame();
    }
    if(e.getSource()==bViewBooks)
    {
        jfAdmin.dispose();
        ViewBooks member = new ViewBooks();
        member.getViewBook();
    }
    if(e.getSource()==bIssueBooks)
    {
        jfAdmin.dispose();
        IssuedBooks books = new IssuedBooks();
        books.getIssueBook();
    }
    if(e.getSource()==bDeleteBooks)
    {
        jfAdmin.dispose();
        DeleteBook deleteBookobj = new DeleteBook();
        deleteBookobj.getDeleteBook();
    }
    if(e.getSource()==bReturnBooks)
    {
        jfAdmin.dispose();
        jfAdmin.dispose();
        ReturnBooks returnBookobj = new ReturnBooks();
        returnBookobj.getReturnFrame();
    }
    if(e.getSource()==bSignOut)
    {
        jfAdmin.dispose();
        LibLogin lib = new LibLogin();
        lib.getLibLogin();
    }
    if(e.getSource() == bSearchBooks)
    {
        jfAdmin.dispose();
        LibSearch search = new LibSearch();
        search.getSearchFrame();
    }
}
}
