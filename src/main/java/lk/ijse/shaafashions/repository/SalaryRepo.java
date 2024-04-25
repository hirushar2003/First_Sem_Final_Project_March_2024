package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Customer;
import lk.ijse.shaafashions.model.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepo {

    public static boolean save(Salary salary) throws SQLException {

        String sql = "INSERT INTO salary (type, description, salaryPerMonth, advancableAmount, numOfWorkingDaysPerMonth) VALUES (?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, salary.getSalaryType());
        pstm.setObject(2, salary.getDescription());
        pstm.setObject(3, salary.getSalaryPerMonth());
        pstm.setObject(4, salary.getAdvancableAmount());
        pstm.setObject(5, salary.getNumberOfWorkingDaysPerMonth());

        return pstm.executeUpdate() > 0;
    }

    public static Salary searchById(String id) throws SQLException {
        String sql = "SELECT * FROM salary WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            int salaryId = resultSet.getInt(1);
            String salaryType = resultSet.getString(2);
            String description = resultSet.getString(3);
            int salaryPerMonth = resultSet.getInt(4);
            int advncableAmount = resultSet.getInt(5);
            int numOfWorkingDaysPerMonth = resultSet.getInt(6);

            Salary salary = new Salary(salaryId, salaryType, description, salaryPerMonth, advncableAmount, numOfWorkingDaysPerMonth);

            return salary;
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM salary WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Salary salary) throws SQLException {
        String sql = "UPDATE salary SET type = ?, description = ?, salaryPerMonth = ?, advancableAmount = ? , numOfWorkingDaysPerMonth = ? WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, salary.getSalaryType());
        pstm.setObject(2, salary.getDescription());
        pstm.setObject(3, salary.getSalaryPerMonth());
        pstm.setObject(4, salary.getAdvancableAmount());
        pstm.setObject(5, salary.getNumberOfWorkingDaysPerMonth());
        pstm.setObject(6, salary.getSalaryId());

        return pstm.executeUpdate() > 0;
    }

    public static List<Salary> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Salary> salaryArrayList = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String description = resultSet.getString(3);
            int salaryPerMonth = resultSet.getInt(4);
            int advancableAmount = resultSet.getInt(5);
            int numOfWorkingDaysPerMonth = resultSet.getInt(6);

            Salary salary = new Salary(id, type, description, salaryPerMonth, advancableAmount, numOfWorkingDaysPerMonth);

            salaryArrayList.add(salary);
        }
        return salaryArrayList;
    }
}
