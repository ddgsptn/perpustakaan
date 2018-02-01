/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.view;

import dang.aplikasi.perpustakaan.controller.MenuController;
import dang.aplikasi.perpustakaan.entity.Menu;
import dang.aplikasi.perpustakaan.entity.User;
import dang.aplikasi.perpustakaan.user.FrmUser;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import dang.aplikasi.perpustakaan.utility.ImageUtility;
import dang.aplikasi.perpustakaan.utility.PagingMenu;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.lang.reflect.*;

/**
 *
 * @author ddgsptn
 */
public class FrmMain extends javax.swing.JFrame {
    Map<Integer, String> arrayTab=new HashMap<>();
    JButton[] btnMenu;
    MenuController mc;
    
    private String userName;
    private final ActionListener btnlistener;
    
    
    private MouseAdapter tabListener ;
    
    /**
     * Creates new form FrmMain
     * @param user
     */
    public FrmMain(User user) {
        initComponents();
        this.userName=user.getUserid();
        setHeader();
        SetMenu();
        btnMenu = new JButton[10];
        btnlistener = (ActionEvent e) -> {
            btnMenu_Clicked(e);
        };
        
        for(int i=0;i<10;i++){
            btnMenu[i]=new JButton();
            btnMenu[i].addActionListener(btnlistener);
        }
        getDetailMenu(arrayTab.get(TabMenu.getSelectedIndex()),null);
        setExtendedState(MAXIMIZED_BOTH);
     
    }
    
    private void setHeader(){
        orglogo.setIcon(ImageUtility.setImgResize(new ImageIcon(getClass().getResource("/dang/aplikasi/perpustakaan/image/bbk.png")), orglogo.getWidth(), orglogo.getHeight()));
        logosystem.setIcon(ImageUtility.setImgResize(new ImageIcon(getClass().getResource("/dang/aplikasi/perpustakaan/image/icon.png")), logosystem.getWidth(), logosystem.getHeight()));
    }
    
    private void tabMenuClicked(MouseEvent ev){
        clearPanelMenu();
        mc.setLevelMenu(0);
        mc.setIntPage(0);
        String[] keyNow=mc.getKeyNow();
        keyNow[mc.getLevelMenu()]=arrayTab.get(TabMenu.getSelectedIndex());
        mc.setKeyNow(keyNow);
        getDetailMenu(arrayTab.get(TabMenu.getSelectedIndex()),null);
    }
    
    private void clearPanelMenu(){
        panelMenu.removeAll();
        panelMenu.revalidate();
        panelMenu.repaint();
    }
    
    private void btnMenu_Clicked(ActionEvent ae){
        String[] keyNow;
        
        if (ae.getSource() instanceof JButton) {
            String text = ((JButton) ae.getSource()).getActionCommand();
            switch (text) {
                case "BACK":
                    clearPanelMenu();
                    mc.setIntPage(mc.getIntPage()-mc.getPageSize());
                    if(mc.getIntPage()<0) mc.setIntPage(0);
                    mc.setLevelMenu(mc.getLevelMenu()-1);
                    if(mc.getLevelMenu()<0) mc.setLevelMenu(0);
                    mc.setNoPage(mc.getNoPage()-1);
                    keyNow=mc.getKeyNow();
                    getDetailMenu(keyNow[mc.getLevelMenu()], null);
                    break;
                case "NEXT":
                    clearPanelMenu();
                    mc.setIntPage(mc.getIntPage()+mc.getPageSize());
                    mc.setNoPage(mc.getNoPage()+1);
                    keyNow=mc.getKeyNow();
                    getDetailMenu(keyNow[mc.getLevelMenu()], null);
                    break;
                default:
                    String[] parentMenu=text.split("\\|");
                    //String formName=idMenu[1];
                    keyNow=mc.getKeyNow();
                    mc.setLevelMenu(mc.getLevelMenu()+1);
                    keyNow[mc.getLevelMenu()]=parentMenu[0];
                    mc.setKeyNow(keyNow);
                    getDetailMenu(parentMenu[0], parentMenu[1]);
                    break;
            }
               
        }
    }
  
    
    private void getDetailMenu(String parentMenu,String formName){
        ArrayList<Menu> listbtnmenu = mc.RetriveMenu(parentMenu,userName);       
        if(listbtnmenu.isEmpty()){
            mc.setLevelMenu(mc.getLevelMenu()-1);
            String className=formName;
            try {
                Class classRef = Class.forName(className);
                Object instance = classRef.newInstance();
                    try {
                        Method method = classRef.getDeclaredMethod("showForm");
                        method.invoke(instance);
                    } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                        //ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error when load method "+ex.getMessage());
                    }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "Error when load class module "+ex.getMessage());
            }   
        }
        else{
            FlowLayout gl =new FlowLayout(FlowLayout.LEFT,0,0);
            panelMenu.setLayout(gl);
            int idxButton=0;
            
            for(Menu btnitem:listbtnmenu){
                btnMenu[idxButton].setText(btnitem.getMenuName());
                btnMenu[idxButton].setHorizontalTextPosition(SwingConstants.CENTER);
                btnMenu[idxButton].setVerticalTextPosition(SwingConstants.BOTTOM);
                btnMenu[idxButton].setFont(new Font("Tahoma",Font.PLAIN,9));
                btnMenu[idxButton].setMargin(new Insets(1, 1, 1, 1));
                btnMenu[idxButton].setPreferredSize(new Dimension(90,70));
                btnMenu[idxButton].setIcon(btnitem.getIcon());
                btnMenu[idxButton].setActionCommand(btnitem.getId()+"|"+btnitem.getFormName());
                panelMenu.add(btnMenu[idxButton]);
                idxButton++;
            } 
            
            if(mc.getPageCount()>1){
                if((mc.getIntPage() > 1) && (mc.getIntPage() < mc.getPageCount())){
                    CreateBtnBack(idxButton);
                    idxButton++; 
                    CreateBtnNext(idxButton);
                    idxButton++;
                }else if((mc.getNoPage() > 0) && (mc.getNoPage() == mc.getPageCount())){
                    CreateBtnBack(idxButton);
                    idxButton++;
                }else{
                    CreateBtnNext(idxButton);
                    idxButton++;
                }  
            }else{
                if(mc.getLevelMenu()>0){
                    mc.setLevelMenu(mc.getLevelMenu()-1);
                    CreateBtnBack(idxButton);
                    idxButton++;
                }
            }
        }
        
    }
    
    private void CreateBtnBack(int idxButton){
        btnMenu[idxButton].setText("BACK");
        btnMenu[idxButton].setHorizontalTextPosition(SwingConstants.CENTER);
        btnMenu[idxButton].setVerticalTextPosition(SwingConstants.BOTTOM);
        btnMenu[idxButton].setFont(new Font("Tahoma",Font.PLAIN,9));
        btnMenu[idxButton].setMargin(new Insets(1, 1, 1, 1));
        btnMenu[idxButton].setPreferredSize(new Dimension(90,70));
        ImageIcon imgleft = new ImageIcon("src/dang/aplikasi/perpustakaan/image/Left.png");
        btnMenu[idxButton].setIcon(imgleft);
        btnMenu[idxButton].setActionCommand("BACK");
        panelMenu.add(btnMenu[idxButton]);
    }
    
    private void CreateBtnNext(int idxButton){
        btnMenu[idxButton].setText("NEXT");
        btnMenu[idxButton].setHorizontalTextPosition(SwingConstants.CENTER);
        btnMenu[idxButton].setVerticalTextPosition(SwingConstants.BOTTOM);
        btnMenu[idxButton].setFont(new Font("Tahoma",Font.PLAIN,9));
        btnMenu[idxButton].setMargin(new Insets(1, 1, 1, 1));
        btnMenu[idxButton].setPreferredSize(new Dimension(90,70));
        ImageIcon imgright = new ImageIcon("src/dang/aplikasi/perpustakaan/image/Right.png");
        btnMenu[idxButton].setIcon(imgright);
        btnMenu[idxButton].setActionCommand("NEXT");
        panelMenu.add(btnMenu[idxButton]);
    }

    private void SetMenu() {
        mc =new MenuController();
        tabListener = new MouseAdapter() {@Override public  void mouseClicked(MouseEvent ev){ tabMenuClicked(ev);}};
        TabMenu.addMouseListener(tabListener);
        
        TabMenu.removeAll();
        ArrayList<Menu> listtabmenu = mc.RetriveMenu("00000",userName);
        int countIndex=0;
        for(Menu item:listtabmenu){
            JLabel jl=new JLabel();
            arrayTab.put(countIndex, item.getId());
            countIndex++;
            TabMenu.setFont(new Font("Calibri",Font.PLAIN,12));
            TabMenu.add(item.getMenuName(),jl);
        }
        
        mc.setLevelMenu(0);
        String[] keyNow=mc.getKeyNow();
        keyNow[mc.getLevelMenu()]=arrayTab.get(TabMenu.getSelectedIndex());
        mc.setKeyNow(keyNow);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblSystem = new javax.swing.JLabel();
        orglogo = new javax.swing.JLabel();
        logosystem = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TabMenu = new javax.swing.JTabbedPane();
        panelMenu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(153, 153, 255));

        lblSystem.setText("Hplus Banking");

        orglogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dang/aplikasi/perpustakaan/image/bbk.png"))); // NOI18N

        logosystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dang/aplikasi/perpustakaan/image/icon.png"))); // NOI18N
        logosystem.setText("jLabel1");

        jLabel1.setText("PT. BPR Bumi Bandung Kencana");

        jLabel2.setText("Jl Melong Asih No 30 Cimahi");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addComponent(orglogo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
                        .addComponent(lblSystem))
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logosystem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(logosystem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSystem))
                            .addGroup(panelHeaderLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))))
                    .addComponent(orglogo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        TabMenu.setBackground(new java.awt.Color(255, 255, 255));
        TabMenu.setRequestFocusEnabled(false);

        panelMenu.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TabMenu)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 486, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblSystem;
    private javax.swing.JLabel logosystem;
    private javax.swing.JLabel orglogo;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
