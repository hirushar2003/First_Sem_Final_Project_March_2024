package lk.ijse.shaafashions.repository;

import javafx.scene.control.Alert;
import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean customersave(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, address, contact, email) VALUES (?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getContact());
        pstm.setObject(4, customer.getEmail());

        return pstm.executeUpdate() > 0;
    }

    public static List <Customer> getAll () throws SQLException {

        String sql = "SELECT * FROM customers";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);
            String email = resultSet.getString(5);

            Customer customer = new Customer(id, name, address, contact, email);
            customerList.add(customer);
        }
        return customerList;
    }
    public static Customer searchById (String id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            int customerId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);
            String email = resultSet.getString(5);

            Customer customer = new Customer(customerId, name, address, contact, email);

            return customer;
        }
        return null ;
    }
    public static boolean delete (String id) throws SQLException {
        String sql = "DELETE FROM customers WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, address = ?, contact = ?, email = ? WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getContact());
        pstm.setObject(4, customer.getEmail());
        pstm.setObject(5, customer.getCustomerId());

        return pstm.executeUpdate() > 0;
    }
}
