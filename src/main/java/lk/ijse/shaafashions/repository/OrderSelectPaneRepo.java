package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderSelectPaneRepo {
    public static int getUnPaidCurtainOrders() throws SQLException {

        String sql = "SELECT COUNT(curtainOrderId) AS countLeftToPayNotZero\n" +
                "FROM curtainOrders\n" +
                "WHERE leftToPay <> 0;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int unpaidCurtainOrders = resultSet.getInt("countLeftToPayNotZero");
            return unpaidCurtainOrders;
        }
        return 0;
    }

    public static int getUnpaidClothOrders() throws SQLException {

        String sql = "SELECT COUNT(clothOrderId) AS countLeftToPayNotZero\n" +
                "FROM clothOrders\n" +
                "WHERE leftToPay <> 0;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int unpaidClothOrders = resultSet.getInt("countLeftToPayNotZero");

            return unpaidClothOrders ;
        }
        return 0 ;
    }
}
