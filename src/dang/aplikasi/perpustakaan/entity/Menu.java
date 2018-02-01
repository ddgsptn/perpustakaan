/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.entity;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import javax.swing.ImageIcon;

/**
 *
 * @author itbbk
 */
public class Menu {
    private String id;
    private String parentMenu;
    private String menuName; 
    private ImageIcon icon;
    private String formName;
    
    public Menu(String id,String menuName,String parentMenu,String formName){
       this.id=id;
       this.menuName=menuName;
       this.parentMenu=parentMenu;
       this.formName=formName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return the parentMenu
     */
    public String getParentMenu() {
        return parentMenu;
    }

    /**
     * @param parentMenu the parentMenu to set
     */
    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    /**
     * @return the icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * @return the formName
     */
    public String getFormName() {
        return formName;
    }

    /**
     * @param formName the formName to set
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }
}
