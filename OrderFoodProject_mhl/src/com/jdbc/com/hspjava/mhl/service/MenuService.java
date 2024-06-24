package com.hspedu.com.hspjava.mhl.service;

import com.hspedu.com.hspjava.mhl.dao.MenuDAO;
import com.hspedu.com.hspjava.mhl.domain.Menu;

import java.util.List;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> menu(){

        List<Menu> menus = menuDAO.manyQuery("select * from menu", Menu.class);
        return menus;
    }

    public Menu getDishMenuId(int dishMenuId){

        Menu menu = menuDAO.singleQuery("select * from menu where id = ?", Menu.class,dishMenuId);
        return menu;

    }






}
