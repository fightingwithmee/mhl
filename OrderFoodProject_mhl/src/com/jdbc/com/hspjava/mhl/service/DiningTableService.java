package com.hspedu.com.hspjava.mhl.service;

import com.hspedu.com.hspjava.mhl.dao.DinningTableDAO;
import com.hspedu.com.hspjava.mhl.domain.DiningTable;

import java.util.List;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class DiningTableService {

    private DinningTableDAO dinningTableDAO = new DinningTableDAO();


    public List<DiningTable> list() {//返回餐桌状态
        List<DiningTable> diningTables = dinningTableDAO.manyQuery("select id,state from diningTable", DiningTable.class);
        return diningTables;
    }


    public DiningTable getDiningTableId(int id){//传入一个餐桌id返回一个餐桌对象可以使用DiningTable里面的方法
        String sql = "select * from diningTable where id = ?";
        return dinningTableDAO.singleQuery(sql, DiningTable.class, id);
    }

    public Boolean orderDiningTable(String name,String tel,int id){//预定餐桌
        String sql = "update diningTable set state = ?, orderName = ?,orderTel = ? where id = ?";
        int updateDiningTable = dinningTableDAO.update(sql, "预约", name, tel, id);
        return updateDiningTable > 0;
    }

    //修改state状态
    public boolean updateDiningTableState(int orderId,String state){
        String sql = "update diningTable set state = ? where id = ?";
        int update = dinningTableDAO.update(sql, state,orderId);
        return update > 0;
    }
    //置空name和tel
    public boolean updateDiningTableNameAndTel(int orderId){

        int update = dinningTableDAO.update("UPDATE diningTable SET orderName = '',orderTel = '' WHERE id = ?", orderId);
        return update > 0;

    }




}




/*

 Scanner scanner = new Scanner(System.in);
    private String stringKey = "";
    private boolean loop = true;
    private int key;

public void Scheduled() {
        while (loop) {
            System.out.println("==============预定餐桌================");
            System.out.println("请选择要预定餐桌的编号(-1退出): ");
            key = scanner.nextInt();
            if (key == -1) {
                return;
            }
            String state = "select state from diningTable where id = ?";
            Object diningState = dinningTableDAO.queryScalar(state, DiningTable.class, key);
            if (diningState.equals("预约")) {
                System.out.println("很抱歉,该餐桌已经有人预定,请选择另外一个餐桌");
                new MHLView().listDiningTable();//显示餐桌情况
            }else if(diningState.equals("空")){
                System.out.println("请确定是否预定(Y/N): ");
                stringKey = scanner.next();
                if (stringKey.equals("n")) {
                    return;
                }
                System.out.println("预定人名字: ");
                String name = scanner.next();
                System.out.println("预定人电话: ");
                String tel = scanner.next();
//                int update = dinningTableDAO.update("insert into diningTable values(null,'预约',?,?)",name,tel);
                int update = dinningTableDAO.update("update diningTable set state = ?, orderName = ?,orderTel = ? WHERE id = ?","预约",name,tel,key);
                System.out.println("预约成功");
                return;
            }else if(diningState == null){
                System.out.println("抱歉已经预约满了");
            }
        }



    }
 */