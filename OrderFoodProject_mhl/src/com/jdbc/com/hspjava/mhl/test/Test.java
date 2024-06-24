package com.hspedu.com.hspjava.mhl.test;

import com.hspedu.com.hspjava.mhl.utils.Utility;

import java.sql.SQLException;

/**
 * @autho 韩顺平
 * @versio 1.0
 */
public class Test {
    public static void main(String[] args) throws SQLException {

//        QueryRunner queryRunner = new QueryRunner();
//        Connection connection = JDBCUtilsByDruid.getConnection();
//        List<DiningTable> query = queryRunner.query(connection,"select id,state from diningTable", new BeanListHandler<DiningTable>(DiningTable.class));
//        for (DiningTable employee : query) {
//            System.out.println(employee);
//        }

//        String uuid = UUID.randomUUID().toString();
//        System.out.println(uuid);

        System.out.println("结账的方式(现金/支付宝/微信) 回车表示退出: ");
        String s = Utility.readString(3," ");
        if(s.equals(" ")){
            return ;
        }

    }
}
