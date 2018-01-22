/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.controller;

import dang.aplikasi.perpustakaan.entity.User;
import dang.aplikasi.perpustakaan.service.LoginService;
import dang.aplikasi.perpustakaan.view.FrmLogin;

/**
 *
 * @author ddgsptn
 */
public class UserController {
    public Boolean Login(User user){
        LoginService ls=new LoginService();
        return ls.isValid(user);
    }
}
