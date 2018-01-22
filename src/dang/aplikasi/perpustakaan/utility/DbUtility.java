/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.utility;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author ddgsptn
 */
public class DbUtility {
    Connection conn;
    MysqlDataSource ds;
    public Connection getConn(){
        if(conn==null){
            ds=new MysqlDataSource();
            ds.setDatabaseName("db_perpustakaan");
            try {
               conn=ds.getConnection("root", "root");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error when try connect to mysql server, "
                        + "check your configuration");
            }
        }
        return conn;
    }
}
