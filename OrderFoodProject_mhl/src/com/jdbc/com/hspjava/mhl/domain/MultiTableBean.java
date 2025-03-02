package com.hspedu.com.hspjava.mhl.domain;

import java.util.Date;

/**
 * @autho 李子文
 * @versio 1.0
 */
public class MultiTableBean {

    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;
    private String name;
    public MultiTableBean() {
    }

    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, double money, Integer diningTableId, Date billDate, String state, String name) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return
                id  + "\t\t" +  menuId+ "\t\t" + name+ "\t\t\t"+ nums + "\t\t"+ money +"\t\t"+ diningTableId +"\t\t"+ billDate +"\t\t"+ state;
    }



}
