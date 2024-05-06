package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardRepo {

    public static int getNumOfOrders() throws SQLException {

        String sql = "SELECT (SELECT COUNT(*) FROM curtainOrders) + (SELECT COUNT(*) FROM clothOrders) AS totalOrderCount";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int totalOrderCount = resultSet.getInt("totalOrderCount");

            return totalOrderCount ;
        }
        return 0;
    }

    public static int getNumOfCustomers() throws SQLException {
        String sql = "SELECT COUNT(*) AS customerCount FROM customers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int customerCount = resultSet.getInt("customerCount");

            return customerCount;
        }
        return 0 ;
    }

    public static int getClothFabricAmount() throws SQLException {
        String sql = "SELECT \n" +
                "    SUM(SUBSTRING_INDEX(quantityOnHand, ' ', 1) + 0) AS total_cloth_fabric_quantity\n" +
                "FROM rowMaterial\n" +
                "WHERE materialType = 'cloth fabric';";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int clothFabricAmount = resultSet.getInt("total_cloth_fabric_quantity");

            return clothFabricAmount ;
        }
        return 0;
    }

    public static int getCurtainFabricStock() throws SQLException {
        String sql = "SELECT \n" +
                "    SUM(SUBSTRING_INDEX(quantityOnHand, ' ', 1) + 0) AS total_curtain_fabric_quantity\n" +
                "FROM rowMaterial\n" +
                "WHERE materialType = 'curtain fabric';";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int curtainFabricStock = resultSet.getInt("total_curtain_fabric_quantity");

            return curtainFabricStock ;
        }
        return 0;
    }

    public static String getBestCustomer() throws SQLException {

        String sql = "SELECT c.customerId, c.name, c.address, mrc.orderCount\n" +
                "FROM customers c\n" +
                "JOIN (\n" +
                "    SELECT customerId, COUNT(*) AS orderCount\n" +
                "    FROM (\n" +
                "        SELECT customerId FROM curtainOrders\n" +
                "        UNION ALL\n" +
                "        SELECT customerId FROM clothOrders\n" +
                "    ) AS combined_orders\n" +
                "    GROUP BY customerId\n" +
                "    ORDER BY orderCount DESC\n" +
                "    LIMIT 1\n" +
                ") AS mrc ON c.customerId = mrc.customerId;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String customerId = String.valueOf(resultSet.getInt("customerId"));
            String name = resultSet.getString("name");
            String orderCount = String.valueOf(resultSet.getInt("orderCount"));

            return customerId + " " + name + " " + orderCount ;

        }

        return null;
    }

    public static String getBestEmployee() throws SQLException {

        String sql = "SELECT supplierId, name, suppliyingMaterial\n" +
                "FROM suppliers\n" +
                "WHERE latestPricePerUnit = (\n" +
                "    SELECT MIN(latestPricePerUnit)\n" +
                "    FROM suppliers\n" +
                ");";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String supplierId = String.valueOf(resultSet.getInt("supplierId"));
            String name = resultSet.getString("name");
            String material = resultSet.getString("suppliyingMaterial");

            return supplierId + " " + name + " " + material ;
        }
        return null;
    }
}
