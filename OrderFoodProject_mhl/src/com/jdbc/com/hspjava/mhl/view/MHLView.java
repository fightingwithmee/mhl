package com.hspedu.com.hspjava.mhl.view;

import com.hspedu.com.hspjava.mhl.domain.*;
import com.hspedu.com.hspjava.mhl.service.*;
import com.hspedu.com.hspjava.mhl.utils.Utility;



import java.util.List;
import java.util.Scanner;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class MHLView {
    private boolean loop = true;
    private int key;
    Scanner scanner = null;
    private String stringKey;
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();
    private MultiTableBeanService multiTableBeanService = new MultiTableBeanService();

    public static void main(String[] args) {
        MHLView mhlView = new MHLView();
        mhlView.mainMenu();
    }

    public void listDiningTable() {
        System.out.println("\n餐桌编号\t\t餐桌状态");
        List<DiningTable> list = diningTableService.list();
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("==============显示完毕================");
    }

    public void Scheduled() {
        System.out.println("==============预定餐桌================");
        System.out.println("请选择要预定餐桌的编号(-1退出): ");
        int orderId = Utility.readInt();
        if (orderId == -1) {
            System.out.println("==============取消订餐================");
            return;
        }

        char selection = Utility.readConfirmSelection();//根据订单号验证是不是预定的桌位是不是存在
        if (selection == 'Y') {
            DiningTable diningTableId = diningTableService.getDiningTableId(orderId);
            if (diningTableId == null) {
                System.out.println("抱歉没有该座位");
            }

            if (!("空".equals(diningTableId.getState()))) {//没预定不能订餐
                System.out.println("=============该餐桌已经被预定或在就餐中===============");
                return;
            }
            System.out.println("预定人名字: ");
            String name = Utility.readString(50);
            System.out.println("预定人电话: ");
            String tel = Utility.readString(11);
            Boolean aBoolean = diningTableService.orderDiningTable(name, tel, orderId);
            System.out.println("==============预约成功================");

        } else {
            System.out.println("==============取消订餐================");
        }


    }

    public void menu() {
        System.out.println("菜品编号" +"\t\t"+"菜品名称"+"\t"+"类型"+"\t"+"价格");
        List<Menu> menu = menuService.menu();
        for (Menu menu1 : menu) {
            System.out.println(menu1);
        }
        System.out.println("====================显示完毕=========================");
    }

    public void orderFood(){
        System.out.println("========================点餐服务=========================");
        System.out.println("请选择自己所在点餐的桌号(-1退出)");
        int orderId = Utility.readInt();
        if(orderId == -1){
            return;
        }
        DiningTable diningTableId = diningTableService.getDiningTableId(orderId);
        if(diningTableId == null){
            System.out.println("你选择的餐桌号有误");
            return;
        }

        if(("空".equals(diningTableId.getState()))){//空表示没预约，这个是预约完才来这里就餐
            System.out.println("你选择的餐桌号有误");
            return;
        }

        System.out.println("请选择要的菜品编号(-1退出)");
        int dishNum = Utility.readInt();//菜品编号
        if(dishNum == -1){
            System.out.println("==============取消订餐================");
            return;
        }

        Menu dishMenuId = menuService.getDishMenuId(dishNum);
        if(dishMenuId == null){
            System.out.println("======================抱歉没有该菜品======================");
            return;
        }
        System.out.println("请选择要菜品数量(-1退出)");
        int dishesCount = Utility.readInt();//菜品份数
        if(dishesCount == -1){
            return;
        }
        System.out.println("确认是否点这个菜(Y/N)");

        char selection = Utility.readConfirmSelection();
        if(selection == 'Y'){
            boolean b = billService.insertBill(orderId, dishNum, dishesCount);
            System.out.println("点餐结果" + b);
        }else{
            System.out.println("==============取消订餐================");
            return;
        }
    }

    public void disPlayBill(){
        System.out.println("编号" +"\t\t"+ "菜品号"+"\t\t"+"菜品名"+"\t\t"+"菜品量"+"\t\t"+"金额"+"\t\t"+"桌号"+"\t\t"+"日期"+"\t\t\t\t\t\t\t"+"状态");
//        List<Bill> list = billService.list();
//        for (Bill bill : list) {
//            System.out.println(bill);
//        }
        List<MultiTableBean> multiTableBeans = multiTableBeanService.mulList();
        for (MultiTableBean multiTableBean : multiTableBeans) {
            System.out.println(multiTableBean);
        }
        System.out.println("==============显示完毕================");
    }
    public void payMenu(){

        System.out.println("=================结账服务========================");
        System.out.println("请选择要结账的餐桌编号(-1退出): ");
        int orderId = Utility.readInt();
        if(orderId == -1){
            return;
        }
        DiningTable diningTableId = diningTableService.getDiningTableId(orderId);
        if(diningTableId == null){
            System.out.println("===================该餐桌不存在=============================");
        }
        Bill billId = billService.getBillId(orderId);
        if(billId == null){
            System.out.println("===================没有需要结账=============================");
            return;
        }
        System.out.println("结账的方式(现金/支付宝/微信) 回车表示退出: ");
        String s = Utility.readString(3," ");
        if(s.equals(" ")){
            return ;
        }
        System.out.println("确认是否结账(Y/N): ");
        char selection = Utility.readConfirmSelection();
        if(selection == 'Y'){
            boolean b = billService.payBill(orderId);
            System.out.println(b?"买单成功":"买单失败");
            System.out.println("==================结账完成==============================");
        }

    }


    public void mainMenu() {
        while (loop) {
            scanner = new Scanner(System.in);
            System.out.println("==============满汉楼================");
            System.out.println("\t\t1 登录满汉楼");
            System.out.println("\t\t2 退出满汉楼");
            System.out.println("请输入你的选择: ");
            key = scanner.nextInt();
            switch (key) {

                case 1:
                    System.out.println("请输入员工号: ");
                    String empId = Utility.readString(50);
                    System.out.println("请输入密  码: ");
                    String password = Utility.readString(50);
                    Employee employee = employeeService.getEmployeeIDAndPwd(empId, password);
                    if (employee != null) {
                        System.out.println("==============登录成功================\n");
                        secondMenu();
                    } else {
                        System.out.println("==============登录失败================");
                    }
                    break;
                case 2:
                    System.out.println("你已退出满汉楼系统~");
                    loop = false;
                    break;
                default:
                    System.out.println("您输入的信息有误~");

            }
        }
    }

    public void secondMenu() {
        while (loop) {
            scanner = new Scanner(System.in);
            System.out.println("==============满汉楼二级菜单================");
            System.out.println("\t\t1 显示餐桌状态");
            System.out.println("\t\t2 预定餐桌");
            System.out.println("\t\t3 显示所有菜品");
            System.out.println("\t\t4 点餐服务");
            System.out.println("\t\t5 查看账单");
            System.out.println("\t\t6 结账");
            System.out.println("\t\t9 退出满汉楼");
            System.out.println("请输入你的选择: ");
            key = scanner.nextInt();
            switch (key) {

                case 1:
                    listDiningTable();
                    break;
                case 2:
                    Scheduled();
                    listDiningTable();
                    break;
                case 3:
                    menu();
                    break;
                case 4:
                    orderFood();
                    break;
                case 5:
                    disPlayBill();
                    break;
                case 6:
                    payMenu();
                    break;
                case 9:
                    System.out.println("你已退出满汉楼系统~");
                    loop = false;
                    break;
                default:
                    System.out.println("您输入的信息有误~");
                    break;
            }
        }
    }


}
