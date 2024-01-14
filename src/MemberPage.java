
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
public class MemberPage implements ActionListener
{
    JFrame jfMember = new JFrame("Welcome!  ");
    JButton bViewMember = new JButton("View Member");
    JButton bSearchBooks = new JButton("Search Books");
    JButton bOrderBooks = new JButton("Order Books");
    JButton bSignOut = new JButton("Sign Out");
public MemberPage()
{
    bViewMember.setBounds(150,90,150,30);
    bSearchBooks.setBounds(150,130,150,30);
    bOrderBooks.setBounds(150,170,150,30);
    bSignOut.setBounds(150,210,150,30);
    bViewMember.addActionListener(this);
    bSearchBooks.addActionListener(this);
    bOrderBooks.addActionListener(this);        
    bSignOut.addActionListener(this);
}
public JFrame getMemberFrame()
{
    jfMember.add(bViewMember);
    jfMember.add(bSearchBooks);
    jfMember.add(bOrderBooks);
    jfMember.add(bSignOut);
    jfMember.setSize(500,500);
    jfMember.setLayout(null);
    jfMember.setVisible(true);
    return jfMember;
}
    @Override
    public void actionPerformed(ActionEvent e)
{
    if(e.getSource()==bViewMember)
    {
        jfMember.dispose();
        MemberView member = new MemberView();
        member.getMemberView();
    }
    if(e.getSource()==bSearchBooks)
    {
        jfMember.dispose();
        MemSearch search = new MemSearch();
        search.getSearchFrame();
    }
    if(e.getSource()==bOrderBooks)
    {
        jfMember.dispose();
        OrderBooks Obooks = new OrderBooks();
        Obooks.getOBookFrame();
    }
   if(e.getSource()==bSignOut)
    {
        jfMember.dispose();
        MemLogin login = new MemLogin();
        login.getMemLogin();
    }
}
}
