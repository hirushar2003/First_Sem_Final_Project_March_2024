package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.ClothOrder;
import lk.ijse.shaafashions.model.tm.ClothOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClothOrderRepo {

    public static boolean addNewOrder(ClothOrder clothOrder) throws SQLException {

        String sql = "INSERT INTO clothOrders (clothOrderId, heldDate, finishingDate, labourCostPerUnit, quantityOrderd, fabricMeatersPerOneUnit, totalLabourCost, paidAmount, description, customerId, length, width, totalCost, leftToPay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothOrder.getClothOrderId());
        pstm.setObject(2, clothOrder.getHeldDate());
        pstm.setObject(3, clothOrder.getFinishingDate());
        pstm.setObject(4, clothOrder.getLabourCostPerUnit());
        pstm.setObject(5, clothOrder.getNumOfUnits());
        pstm.setObject(6, clothOrder.getUsagePerUnit());
        pstm.setObject(7, clothOrder.getTotalLabourCost());
        pstm.setObject(8, clothOrder.getPaidAmount());
        pstm.setObject(9, clothOrder.getDescription());
        pstm.setObject(10, clothOrder.getCustomerId());
        pstm.setObject(11,clothOrder.getLength());
        pstm.setObject(12, clothOrder.getWidth());
        pstm.setObject(13, clothOrder.getTotalCost());
        pstm.setObject(14, clothOrder.getLeftToPay());

        return pstm.executeUpdate() > 0;
    }

    public static ClothOrder searchOrderById(String clothorderId) throws SQLException {

        String sql = "SELECT * FROM clothOrders WHERE clothOrderId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothorderId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String clothOrderId = resultSet.getString(1);
            LocalDate heldDate = LocalDate.parse(resultSet.getString(2));
            LocalDate finishingDate = LocalDate.parse(resultSet.getString(3));
            int labourCostPerUnit = resultSet.getInt(4);
            int quantityOrderd = resultSet.getInt(5);
            double fabricMeatersPerOneUnit = resultSet.getDouble(6);
            int totalLabourCost = resultSet.getInt(7);
            int paidAmount = resultSet.getInt(8);
            String description = resultSet.getString(9);
            int customerId = resultSet.getInt(10);
            double length = resultSet.getDouble(11);
            double width = resultSet.getDouble(12);
            int totalCost = resultSet.getInt(13);
            int leftToPay = resultSet.getInt(14);

            ClothOrder clothOrder = new ClothOrder(clothOrderId, heldDate, finishingDate, labourCostPerUnit, quantityOrderd, length, width, fabricMeatersPerOneUnit, totalLabourCost, paidAmount, leftToPay, description, customerId, totalCost);

            return clothOrder ;
        }
        return null;
    }

    public static Integer setRawMaterialIdInComboBoxWhenSearched(String id) throws SQLException {

        String sql = "SELECT rawMaterialId FROM rawMaterialUsage WHERE clothOrderId = ?" ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int rawMaterialId = resultSet.getInt("rawMaterialId");
            return rawMaterialId ;
        }
        return 0 ;
    }

    public static boolean updateClothOrder(ClothOrder clothOrder) throws SQLException {

        String  sql = "UPDATE clothOrders SET heldDate = ? , finishingDate = ? , labourCostPerUnit = ? , quantityOrderd = ?, fabricMeatersPerOneUnit = ? , totalLabourCost = ? , paidAmount = ?, description = ?, customerId = ? , length = ? , width = ?, totalCost = ? , leftToPay = ? WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, clothOrder.getHeldDate());
        pstm.setObject(2, clothOrder.getFinishingDate());
        pstm.setObject(3, clothOrder.getLabourCostPerUnit());
        pstm.setObject(4, clothOrder.getNumOfUnits());
        pstm.setObject(5, clothOrder.getUsagePerUnit());
        pstm.setObject(6, clothOrder.getTotalLabourCost());
        pstm.setObject(7, clothOrder.getPaidAmount());
        pstm.setObject(8, clothOrder.getDescription());
        pstm.setObject(9, clothOrder.getCustomerId());
        pstm.setObject(10,clothOrder.getLength());
        pstm.setObject(11, clothOrder.getWidth());
        pstm.setObject(12, clothOrder.getTotalCost());
        pstm.setObject(13, clothOrder.getLeftToPay());
        pstm.setObject(14, clothOrder.getClothOrderId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteOrder(String clothOrderId) throws SQLException {
        String sql = "DELETE FROM clothOrders WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothOrderId);

        return pstm.executeUpdate() > 0;
    }

    public static List<ClothOrderTm> getAll() throws SQLException {

        String sql = "SELECT clothOrderId, heldDate, finishingDate, customerId, quantityOrderd, totalCost, leftToPay FROM clothOrders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<ClothOrderTm> allOrders = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            String clothOrderId = resultSet.getString(1);
            LocalDate ohd = resultSet.getDate(2).toLocalDate();
            LocalDate ofd = resultSet.getDate(3).toLocalDate();
            int customerId = resultSet.getInt(4);
            int qty = resultSet.getInt(5);
            int totalCost = resultSet.getInt(6);
            int leftToPay = resultSet.getInt(7);

            ClothOrderTm clothOrderTm = new ClothOrderTm(clothOrderId, ohd, ofd, customerId, qty, totalCost, leftToPay);

            allOrders.add(clothOrderTm);
        }
        return allOrders;
    }

    public static int generateTotalCost(int rmId) throws SQLException {

        String sql = "SELECT pricePerUnit FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rmId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int pricePerUnit = resultSet.getInt("pricePerUnit");

            return pricePerUnit ;
        }
        return 0 ;
    }
}
