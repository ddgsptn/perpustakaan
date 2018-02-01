/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.utility;

/**
 *
 * @author itbbk
 */
public class PagingMenu {
    int noPage;
    public PagingMenu(int noPage){
        this.noPage=noPage;
    }
    
    public int getPosition(int limitPage){
        if(noPage==0){
            noPage=1;
            return 0;
        }
        else{
            return (noPage-1) * limitPage;
        }
    }
    
    public int getTotPage(int totRecord,int limitPage){
        return (int) Math.ceil(totRecord/limitPage);
    }
}
