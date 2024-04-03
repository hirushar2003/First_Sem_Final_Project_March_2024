package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepo {
    public static boolean register (User user) throws SQLException {
        String sql = "INSERT INTO user (name, address, email, contact, userName, password, customerId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, user.getName());
        pstm.setObject(2, user.getAddress());
        pstm.setObject(3, user.getEmail());
        pstm.setObject(4, user.getContact());
        pstm.setObject(5, user.getUserName());
        pstm.setObject(6, user.getPassword());
        pstm.setObject(7, null);

        return pstm.executeUpdate() > 0;
    }
}
