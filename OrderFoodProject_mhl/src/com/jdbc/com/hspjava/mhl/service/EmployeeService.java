package com.hspedu.com.hspjava.mhl.service;

import com.hspedu.com.hspjava.mhl.dao.EmployeeDAO;
import com.hspedu.com.hspjava.mhl.domain.Employee;

/**
 * @autho 李子文
 * @versio 1.0
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeIDAndPwd(String empID,String pwd){
        String sql = "select * from employee where empID = ? and pwd = md5(?)";
        Employee employee = employeeDAO.singleQuery(sql, Employee.class, empID, pwd);
        return employee;


    }

}
