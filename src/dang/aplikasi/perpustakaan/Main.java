/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan;

import dang.aplikasi.perpustakaan.view.FrmLogin;
import dang.aplikasi.perpustakaan.view.FrmSplash;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddgsptn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FrmSplash fs=new FrmSplash();
        fs.setVisible(true);
        for(int i=0;i<=50;i++){
            try {
                fs.getProgressBar().setValue(i);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrmSplash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        fs.dispose();
        FrmLogin fl=new FrmLogin(null, true);
        fl.setVisible(true);
    }
    
}
