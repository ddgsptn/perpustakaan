/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.controller;

import dang.aplikasi.perpustakaan.entity.Menu;
import dang.aplikasi.perpustakaan.service.MenuService;
import dang.aplikasi.perpustakaan.view.FrmMain;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 *
 * @author itbbk
 */
public class MenuController {
    private int intPage;
    private int pageSize;
    private int pageCount;
    private String[] keyNow;
    private int levelMenu;
    private int noPage;
    
    public MenuController(){
        intPage=0;
        pageSize=8;
        pageCount=0; 
        levelMenu=0;
        noPage=1;
        keyNow = new String[11];
        levelMenu=0;
    }
    public ArrayList<Menu> RetriveMenu(String parentMenu,String userName){
        MenuService mn=new MenuService();  
        int totrecord=mn.getNumberRecord(parentMenu, userName);
        this.pageCount=0;
        while(totrecord>0){
            this.pageCount++;
            totrecord=totrecord-pageSize;
        }
        //setPageCount((int)Math.ceil(mn.getNumberRecord(parentMenu, userName)/pageSize));
        return mn.getMenu(parentMenu, this.intPage, this.pageSize,userName);
    }

    /**
     * @return the intPage
     */
    public int getIntPage() {
        return intPage;
    }

    /**
     * @param intPage the intPage to set
     */
    public void setIntPage(int intPage) {
        this.intPage = intPage;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the keyNow
     */
    public String[] getKeyNow() {
        return keyNow;
    }

    /**
     * @param keyNow the keyNow to set
     */
    public void setKeyNow(String[] keyNow) {
        this.keyNow = keyNow;
    }

    /**
     * @return the levelMenu
     */
    public int getLevelMenu() {
        return levelMenu;
    }

    /**
     * @param levelMenu the levelMenu to set
     */
    public void setLevelMenu(int levelMenu) {
        this.levelMenu = levelMenu;
    }

    /**
     * @return the noPage
     */
    public int getNoPage() {
        return noPage;
    }

    /**
     * @param noPage the noPage to set
     */
    public void setNoPage(int noPage) {
        this.noPage = noPage;
    }
}
