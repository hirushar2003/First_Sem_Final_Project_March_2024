package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {

    public static List<Integer> getSalaryIds() throws SQLException {

        String sql = "SELECT salaryId FROM salary";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Integer> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            idList.add(id);
        }
        return idList ;
    }

    public static boolean addNewEmployee(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee (name, address, contact, jobRole, yearsOfExperience, numOfWorkingDaysPerWeek, salaryId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAddress());
        pstm.setObject(3, employee.getContact());
        pstm.setObject(4, employee.getJobRole());
        pstm.setObject(5, employee.getYoe());
        pstm.setObject(6, employee.getNumOfWorkingDaysPerWeek());
        pstm.setObject(7, employee.getSalaryId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, address = ?, contact = ?, jobRole = ? , yearsOfExperience = ?, numOfWorkingDaysPerWeek = ?, salaryId = ? WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAddress());
        pstm.setObject(3, employee.getContact());
        pstm.setObject(4, employee.getJobRole());
        pstm.setObject(5, employee.getYoe());
        pstm.setObject(6, employee.getNumOfWorkingDaysPerWeek());
        pstm.setObject(7, employee.getSalaryId());
        pstm.setObject(8, employee.getEmployeeId());

        return pstm.executeUpdate() > 0;
    }

    public static Employee searchById(int id) throws SQLException {

        String sql = "SELECT * FROM employee WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int empId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);
            String jobRole = resultSet.getString(5);
            int yoe = resultSet.getInt(6);
            int wd = resultSet.getInt(7);
            int salId = resultSet.getInt(8);

            Employee employee = new Employee(empId, name, address, jobRole, contact, yoe, wd, salId);

            return employee ;
        }
        return null ;
    }

    public static boolean deleteEmployee(int id) throws SQLException {

        String sql = "DELETE FROM employee WHERE employeeId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0 ;
    }

    public static List<Employee> getAll() throws SQLException {

        String sql = "SELECT * FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Employee> empAll = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int empId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);
            String jobRole = resultSet.getString(5);
            int yearsOfExperience = resultSet.getInt(6);
            int numOfWorkingDaysPerWeek = resultSet.getInt(7);
            int salaryId = resultSet.getInt(8);

            Employee employee = new Employee(empId, name, address, jobRole, contact, yearsOfExperience, numOfWorkingDaysPerWeek, salaryId);

            empAll.add(employee);

        }
        return empAll;
    }
}
