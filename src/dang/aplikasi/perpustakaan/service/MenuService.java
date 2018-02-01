/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.service;

import dang.aplikasi.perpustakaan.entity.Menu;
import dang.aplikasi.perpustakaan.utility.DbUtility;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author itbbk
 */
public class MenuService {
    Connection conn;
    public MenuService(){
        conn=new DbUtility().getConn();
    }
    
    public int getNumberRecord(String parentmenu,String userName){
        PreparedStatement stmt;
        int result=0;
        String sql="SELECT count(0) FROM usermenu a join menu b on a.menuid=b.id"
                   + " where a.userid=? and b.parentmenu=? ";
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, parentmenu);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                result=rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error when get number record "+e.getMessage());
        }
        return result;
    }
    
    public ArrayList<Menu> getMenu(String parentmenu,int intPage,int pageSize,String userName){
        ArrayList<Menu> listTabMenu=new ArrayList<>();
        PreparedStatement stmt;
        try{
           stmt=conn.prepareStatement("SELECT b.id,b.menuname,b.parentmenu,b.formName,b.icon FROM usermenu a join menu b on a.menuid=b.id"
                   + " where a.userid=? and b.parentmenu=? ORDER BY id ASC limit ?,?");
           stmt.setString(1, userName);
           stmt.setString(2, parentmenu);
           stmt.setInt(3, intPage);
           stmt.setInt(4, pageSize);
           ResultSet rs=stmt.executeQuery();
           while(rs.next()){
               Menu mn=new Menu(rs.getString("id"),
                                rs.getString("menuname"),
                                rs.getString("parentmenu"),
                                rs.getString("formName"));
               
               Blob blob = rs.getBlob("icon");             
               byte[] b=blob.getBytes(1,(int)blob.length());              
               ImageIcon ik = new ImageIcon(b);
               Image img = ik.getImage();
               Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_AREA_AVERAGING);
               ik = new ImageIcon(newimg);
               mn.setIcon(ik);
               
               listTabMenu.add(mn);
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error when retrive data"+ex.getMessage());
        }
        return listTabMenu;
    }
}
