/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author ddgsptn
 */
public class FrmSplash extends javax.swing.JDialog {

    /**
     * Creates new form FrmSplash
     */
    public FrmSplash() {
        setUndecorated(true);
        initComponents();    
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(dim.width-getWidth())/2;
        int y=(dim.height-getHeight())/2;
        setLocation(x, y);
        progressBar.setStringPainted(true);
    }
    
    public JProgressBar getProgressBar(){
        return progressBar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splashpanel = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();
        dangLabel1 = new dang.aplikasi.perpustakaan.component.DangLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        splashpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        progressBar.setMaximum(50);
        splashpanel.add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 225, -1));

        dangLabel1.setIconReflec(new javax.swing.ImageIcon(getClass().getResource("/dang/aplikasi/perpustakaan/image/icon.png"))); // NOI18N
        splashpanel.add(dangLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 110, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splashpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splashpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dang.aplikasi.perpustakaan.component.DangLabel dangLabel1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel splashpanel;
    // End of variables declaration//GEN-END:variables
}
