/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.service;

import dang.aplikasi.perpustakaan.entity.User;
import dang.aplikasi.perpustakaan.utility.DbUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ddgsptn
 */
public class LoginService {
    Connection conn;
    public LoginService(){
        conn=new DbUtility().getConn();
    }
    public Boolean isValid(User user){
        PreparedStatement stmt=null;
        try {
            stmt=conn.prepareStatement("select * from user where userid=? and password=?");
            stmt.setString(1, user.getUserid());
            stmt.setString(2, user.getPassword());
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
               return true;
            }
        } catch (Exception e) {
        }finally{try {if(stmt != null)stmt.close(); } catch (Exception e) { }}
                
        return false;
    }
}
