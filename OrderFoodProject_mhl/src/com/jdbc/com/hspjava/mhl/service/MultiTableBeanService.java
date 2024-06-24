package com.hspedu.com.hspjava.mhl.service;

import com.hspedu.com.hspjava.mhl.dao.MultiTableBeanDAO;
import com.hspedu.com.hspjava.mhl.domain.MultiTableBean;

import java.util.List;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class MultiTableBeanService {

    private MultiTableBeanDAO multiTableBeanDAO = new MultiTableBeanDAO();

    public List<MultiTableBean> mulList(){//查询全部账单
        String sql = "SELECT bill.id,menuId,`name` ,nums,money,diningTableId,billDate,state FROM menu,bill WHERE menu.id = bill.menuId";
        List<MultiTableBean> multiTableBeans = multiTableBeanDAO.manyQuery(sql, MultiTableBean.class);
        return multiTableBeans;
    }


}
