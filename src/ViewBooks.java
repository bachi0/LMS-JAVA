
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sudhaker
 */
class ViewBooks implements ActionListener {
     JFrame jfViewBook;
    JTable jtView;
    JScrollPane pane;
    JButton bBack;
    JPanel backPanel;
    public ViewBooks()
    {
        try{
            jfViewBook = new JFrame("Member Details");
            bBack = new JButton("Back");
            backPanel = new JPanel();
            Connection con = DriverManager.getConnection("jdbc:ucanaccess:///E:\\Library.accdb");
            Statement stmt = con.createStatement();
            String q = "select * from Books";
            ResultSet rs = stmt.executeQuery(q);
            jtView = new JTable(buildTableModel(rs));
            jtView.setBounds(30,40,200,300);
            bBack.setBounds(80,30,300,50);
            bBack.addActionListener(this);
        }
        catch(Exception ex)
        {
        }
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
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

    }
    
    public JFrame getViewBook()
    {
        backPanel.add(bBack);
        pane = new JScrollPane(jtView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jfViewBook.add(pane, BorderLayout.CENTER);
        jfViewBook.add(backPanel, BorderLayout.SOUTH);
        jfViewBook.setSize(500,500);
        jfViewBook.setVisible(true);
        return jfViewBook;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == bBack)
        {
            jfViewBook.dispose();
            AdminPage adminFrame = new AdminPage();
            adminFrame.getAdminFrame();
        }
    }   
}
