
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class MemberView implements ActionListener
{
    JFrame jfMemberView = new JFrame("Member Details");
    JTable jtView;
    JLabel lbMID = new JLabel("MID ");
    JTextField tfMID = new JTextField();
    JScrollPane pane;
    JButton bBack = new JButton("Back");
    JButton bView = new JButton("View");
    JPanel backPanel = new JPanel();
    public MemberView()
    {
        lbMID.setBounds(50,100,100,30);
        tfMID.setBounds(150,100,100,30);
        bView.setBounds(50,140,100,30);
        bBack.setBounds(160,140,100,30);
        bView.addActionListener(this);
        bBack.addActionListener(this);
    }
    
    /*public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }*/
    
    public JFrame getMemberView()
    {
        jfMemberView.add(lbMID);
        jfMemberView.add(tfMID);
        jfMemberView.add(bView);
        jfMemberView.add(bBack);
        jfMemberView.setLayout(null);
        jfMemberView.setSize(500,500);
        jfMemberView.setVisible(true);   
        return jfMemberView;
    }
    public void actionPerformed(ActionEvent e)
    {
        String put;
        try
        {
            //jfMemberView = new JFrame("Member Details");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
            Statement stmt = con.createStatement();
            String Mid = tfMID.getText();
            if(e.getSource() == bView)
            {
                String q = "select * from Member where MID = '"+Mid+"'";
                ResultSet rs = stmt.executeQuery(q);
                while(rs.next())
                {
                    String MId = rs.getString(1);
                    String MName = rs.getString(2);
                    String Pass = rs.getString(3);
                    String Age = rs.getString(4);
                    String phone = rs.getString(5);
                    String ADdress = rs.getString(6);
                    int Fine = rs.getInt(7);
                    put = "MID : "+MId+"\nMember name :"+MName+"\nPassword : "+Age+"\nAge :"+phone+"\nPhone :"+ADdress+"\n Address :"+Pass+"\n Fine :"+Fine;
                    JOptionPane.showMessageDialog(jfMemberView,put);
                    jfMemberView.dispose();
                    MemberPage page = new MemberPage();
                    page.getMemberFrame();
                }
                //jtView = new JTable(buildTableModel(rs));
                //jtView.setBounds(30,200,200,300);
                //backPanel.add(bBack);
                //pane = new JScrollPane(jtView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                //jfMemberView.add(pane, BorderLayout.CENTER);
                //jfMemberView.add(backPanel, BorderLayout.SOUTH);
            }
            if(e.getSource() == bBack)
            {
                jfMemberView.dispose();
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
