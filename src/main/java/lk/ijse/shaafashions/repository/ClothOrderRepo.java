package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.ClothOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClothOrderRepo {

    public static boolean addNewOrder(ClothOrder clothOrder) throws SQLException {
        String sql = "INSERT INTO clothOrders (heldDate, finishingDate, labourCostPerUnit, quantityOrderd, measurements, fabricMeatersPerOneUnit, clothType, totalLabourCost, paidAmount, description, customerId, clothOrderId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothOrder.getHeldDate());
        pstm.setObject(2, clothOrder.getFinishingDate());
        pstm.setObject(3, clothOrder.getLabourCostPerUnit());
        pstm.setObject(4, clothOrder.getQtyOrderd());
        pstm.setObject(5, clothOrder.getMeasurements());
        pstm.setObject(6, clothOrder.getFabricMeatersPerOneUnit());
        pstm.setObject(7, clothOrder.getClothType());
        pstm.setObject(8, clothOrder.getTotalLabourCost());
        pstm.setObject(9, clothOrder.getPaidAmount());
        pstm.setObject(10, clothOrder.getDescription());
        pstm.setObject(11,clothOrder.getCustomerId());
        pstm.setObject(12, clothOrder.getOrderId());


        return pstm.executeUpdate() > 0 ;
    }

    public static ClothOrder searchById(String id) throws SQLException {

        String sql = "SELECT * FROM clothOrders WHERE clothOrderId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String orderId = resultSet.getString(1);
            LocalDate heldDate = LocalDate.parse(resultSet.getString(2));
            LocalDate finishingDate = LocalDate.parse(resultSet.getString(3));
            int labourCostPerUnit = resultSet.getInt(4);
            int quantityOrderd = resultSet.getInt(5);
            String measurements = resultSet.getString(6);
            double fabricMeatersPerOneUnit = resultSet.getInt(7);
            String clothType = resultSet.getString(8);
            int totalLabourCost = resultSet.getInt(9);
            int paidAmount = resultSet.getInt(10);
            String description = resultSet.getString(11);
            int customerId = resultSet.getInt(12);

            ClothOrder clothOrder = new ClothOrder(orderId, heldDate, finishingDate, labourCostPerUnit, quantityOrderd, measurements, fabricMeatersPerOneUnit, clothType, totalLabourCost, paidAmount, description, customerId);

            return clothOrder;
        }

        return null;
    }

    public static boolean updateOrder(ClothOrder clothOrder) throws SQLException {

        String  sql = "UPDATE clothOrders SET heldDate = ?, finishingDate = ?, labourCostPerUnit = ? , quantityOrderd = ? , measurements = ?, fabricMeatersPerOneUnit = ? , clothType = ?, totalLabourCost = ? , paidAmount = ?, description = ? , customerId = ? WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothOrder.getHeldDate());
        pstm.setObject(2, clothOrder.getFinishingDate());
        pstm.setObject(3, clothOrder.getLabourCostPerUnit());
        pstm.setObject(4, clothOrder.getQtyOrderd());
        pstm.setObject(5, clothOrder.getMeasurements());
        pstm.setObject(6, clothOrder.getFabricMeatersPerOneUnit());
        pstm.setObject(7, clothOrder.getClothType());
        pstm.setObject(8, clothOrder.getTotalLabourCost());
        pstm.setObject(9, clothOrder.getPaidAmount());
        pstm.setObject(10, clothOrder.getDescription());
        pstm.setObject(11,clothOrder.getCustomerId());
        pstm.setObject(12, clothOrder.getOrderId());

        return pstm.executeUpdate() > 0;

    }

    public static boolean deleteClothingOrder(String id) throws SQLException {
        String sql = "DELETE FROM clothOrders WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Integer> getIds() throws SQLException {

        String sql = "SELECT customerId FROM customers " ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Integer> cusList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            cusList.add(id);
        }
        return cusList;
    }
}
