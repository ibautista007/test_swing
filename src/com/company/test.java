package com.company;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sat Aug 01 14:31:10 PDT 2020
 */
import java.sql.*;
import java.util.Vector;


/**
 * @author unknown
 */
public class test extends JFrame {
    public test() {
        initComponents();
    }

    Connection con1;
    PreparedStatement insert;


    private void button1ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        // TODO add your code here
        String catcode, catdesc;



        catcode = textField1.getText();
        catdesc = textField2.getText();



        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost:3308/inventory","root","");


        if(e.getSource()==button1) {


            insert = con1.prepareStatement("Select * from category where catcode = ?");

            insert.setString(1, catcode);



            ResultSet rs = insert.executeQuery();

            if(rs.isBeforeFirst()){          //res.isBeforeFirst() is true if the cursor

                JOptionPane.showMessageDialog(null,"The catcode you are trying to enter already exists ");

                textField1.setText("");
                textField2.setText("");
                textField1.requestFocus();

                return;
            }


            insert = con1.prepareStatement("insert into category values(?,?)");

            insert.setString(1, catcode);
            insert.setString(2, catdesc);

            insert.executeUpdate();

            JOptionPane.showMessageDialog(null, "Record added");

            textField1.setText("");
            textField2.setText("");
            textField1.requestFocus();





            updateTable();


        }


        if(e.getSource()==table1) {





        }









    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Isaias
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter category Number:");
        contentPane.add(label1, "cell 2 1");
        contentPane.add(textField1, "cell 4 1 4 1");

        //---- label2 ----
        label2.setText("Enter category Name:");
        contentPane.add(label2, "cell 2 4");
        contentPane.add(textField2, "cell 4 4 4 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 1 6 6 7");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(button1, "cell 1 14");

        //---- button2 ----
        button2.setText("Update");
        contentPane.add(button2, "cell 3 14");

        //---- button3 ----
        button3.setText("Print");
        contentPane.add(button3, "cell 6 14");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Isaias
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void updateTable() throws ClassNotFoundException, SQLException {

        PreparedStatement query;
        Class.forName("com.mysql.jdbc.Driver");

        Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3308/inventory","root","");

        query = con1.prepareStatement("SELECT * FROM category");

        ResultSet rs = query.executeQuery();

        int c;
        ResultSetMetaData res = rs.getMetaData();

        c = res.getColumnCount();

        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        df.setRowCount(0);

        while(rs.next()){

            Vector v2 = new Vector();


            for(int a =1;a<=c;a++){

                v2.add(rs.getString("catCode"));
                v2.add(rs.getString("catDesc"));


            }

            df.addRow(v2);

        }


    }

    public void sss(){

        String[] cols = {"Category Code", "Category Description"};
        String[][] data = {{"d1", "d1.1"},{"d2", "d2.1"}};
        DefaultTableModel model = new DefaultTableModel(data, cols);
        table1.setModel(model);

        //  add(table1);

    }





}
