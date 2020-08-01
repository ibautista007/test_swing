package com.company;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	// write your code here

        test obj1 = new test();

        obj1.sss();
        obj1.updateTable();
        obj1.setVisible(true);



    }
}
