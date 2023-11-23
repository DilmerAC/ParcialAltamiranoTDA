/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DbConnection {
    private final String DRIVER="com.mysql.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="dbproperties";
    private final String USER="root";
    private final String PASSWORD="";
    
    public Connection cursor;
    public static DbConnection instance;
    
    private DbConnection(){
        this.cursor=null;
    }
    
    public Connection connect(){
        try {
            Class.forName(DRIVER);
            this.cursor=DriverManager.getConnection(URL+DB,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cursor;
    }
    
    public void disconnect(){
        try {
            this.cursor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public synchronized static DbConnection getInstance(){
        if (instance==null){
            instance=new DbConnection();
        }
        return instance;
    }
}
