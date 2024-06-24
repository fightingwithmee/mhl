package com.hspedu.com.hspjava.mhl.service;

import com.hspedu.com.hspjava.mhl.dao.BillDAO;
import com.hspedu.com.hspjava.mhl.domain.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @autho 李子文
 * @versio 1.0
 */
public class BillService {

    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    /**
     * @param orderId     订单id
     * @param dishNum     菜品号
     * @param dishesCount 点餐数量
     */


    public boolean insertBill(int orderId, int dishNum, int dishesCount) {//插入账单

        double price = menuService.getDishMenuId(dishNum).getPrice();
        String billId = UUID.randomUUID().toString();//生成uuid
        String sql = "insert into bill values(null,?,?,?,?,?,now(),'未结账')";
        int insertBill = billDAO.update(sql, billId, dishNum, dishesCount, price * dishesCount, orderId);
        if (insertBill <= 0) {
            return false;
        }

        //跟新diningTable的state状态
        boolean updateState = diningTableService.updateDiningTableState(orderId, "就餐中");
        return updateState;
    }

    public List<Bill> list(){//查询全部账单
        List<Bill> bills = billDAO.manyQuery("select * from bill", Bill.class);
        return bills;
    }



    public boolean updateBillState(int orderId,String state) {//更新Bill的状态

        int payMenu = billDAO.update("update bill set state = ? where diningTableId = ? and state = '未结账'", "已结账", orderId);
        return payMenu > 0;

    }

    //查看是否有没结账账单
    public Bill getBillId(int orderId){
        Bill bill = billDAO.singleQuery("select * from bill where diningTableId = ? and state = '未结账'", Bill.class, orderId);
        return bill;
    }

    //根据orderId查询账单,买单
    public boolean payBill(int orderId){



        boolean updateBillState = updateBillState(orderId, "已结账");
        boolean updateDiningTableState = diningTableService.updateDiningTableState(orderId, "空");
        boolean updateDiningTableNameAndTel = diningTableService.updateDiningTableNameAndTel(orderId);
        if(!updateBillState){
            return false;
        }
        if(!updateDiningTableState){
            return false;
        }
        if(!updateDiningTableNameAndTel){
            return false;
        }
        return true;
    }



}


/*
public boolean payMe(int orderId){



        boolean updateBillState = updateBillState(orderId, "已结账");
        boolean updateDiningTableState = diningTableService.updateDiningTableState(orderId, "空");
        boolean updateDiningTableNameAndTel = diningTableService.updateDiningTableNameAndTel(orderId);
        if(!updateBillState){
            return false;
        }
        if(!updateDiningTableState){
            return false;
        }
        if(!updateDiningTableNameAndTel){
            return false;
        }
        return true;
    }
 */