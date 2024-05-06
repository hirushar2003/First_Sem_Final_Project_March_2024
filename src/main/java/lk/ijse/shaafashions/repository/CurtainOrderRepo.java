package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.CurtainOrder;
import lk.ijse.shaafashions.model.RawMaterialUsage;
import lk.ijse.shaafashions.model.tm.CurtainOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurtainOrderRepo {

    public static boolean addOrder(CurtainOrder curtainOrder) throws SQLException {
        String sql = "INSERT INTO curtainOrders (curtainOrderId, heldDate, finishingDate, totalLabourCost, paidAmount, description, numOfPieces, labourCostPerMeter, metersPerOnePiece, customerId, deliveryAndFittingId, lengthsInMeters, widthsInMeters, totalCost, leftToPay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, curtainOrder.getId());
        pstm.setObject(2, curtainOrder.getHeldDate());
        pstm.setObject(3, curtainOrder.getFinishingDate());
        pstm.setObject(4, curtainOrder.getTotalLabourCost());
        pstm.setObject(5, curtainOrder.getPaidAmount());
        pstm.setObject(6, curtainOrder.getDescription());
        pstm.setObject(7, curtainOrder.getNumOfPieces());
        pstm.setObject(8, curtainOrder.getLabourCostPerMeter());
        pstm.setObject(9, curtainOrder.getMetersPerPiece());
        pstm.setObject(10,curtainOrder.getCustomerId());
        pstm.setObject(11,curtainOrder.getServiceId());
        pstm.setObject(12,curtainOrder.getLengthInMeters());
        pstm.setObject(13,curtainOrder.getWidthInMeters());
        pstm.setObject(14,curtainOrder.getTotalCost());
        pstm.setObject(15, curtainOrder.getLeftToPay());

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

    public static boolean updateOrder(CurtainOrder curtainOrder) throws SQLException {

        String  sql = "UPDATE curtainOrders SET heldDate = ? , finishingDate = ? , totalLabourCost = ? , paidAmount = ?, description = ? , numOfPieces = ? , labourCostPerMeter = ?, metersPerOnePiece = ?, customerId = ? , deliveryAndFittingId = ? , lengthsInMeters = ?, widthsInMeters = ? , totalCost = ? , leftToPay = ? WHERE curtainOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, curtainOrder.getHeldDate());
        pstm.setObject(2, curtainOrder.getFinishingDate());
        pstm.setObject(3, curtainOrder.getTotalLabourCost());
        pstm.setObject(4, curtainOrder.getPaidAmount());
        pstm.setObject(5, curtainOrder.getDescription());
        pstm.setObject(6, curtainOrder.getNumOfPieces());
        pstm.setObject(7, curtainOrder.getLabourCostPerMeter());
        pstm.setObject(8, curtainOrder.getMetersPerPiece());
        pstm.setObject(9,curtainOrder.getCustomerId());
        pstm.setObject(10,curtainOrder.getServiceId());
        pstm.setObject(11,curtainOrder.getLengthInMeters());
        pstm.setObject(12,curtainOrder.getWidthInMeters());
        pstm.setObject(13, curtainOrder.getTotalCost());
        pstm.setObject(14, curtainOrder.getLeftToPay());
        pstm.setObject(15, curtainOrder.getId());


        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteOrder(String id) throws SQLException {
        String sql = "DELETE FROM curtainOrders WHERE curtainOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;

    }

    public static CurtainOrder searchById(String id) throws SQLException {
        String sql = "SELECT * FROM curtainOrders WHERE curtainOrderId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String curtainOrderId = resultSet.getString(1);
            LocalDate heldDate = resultSet.getDate(2).toLocalDate();
            LocalDate finishingDate = resultSet.getDate(3).toLocalDate();
            int totalLabourCost = resultSet.getInt(4);
            int paidAmount = resultSet.getInt(5);
            String description = resultSet.getString(6);
            int numOfPieces = resultSet.getInt(7);
            int labourCostPerMeter = resultSet.getInt(8);
            double metersPerPiece = resultSet.getDouble(9);
            int customerId = resultSet.getInt(10);
            String serviceId = resultSet.getString(11);
            double lengthInMeters = resultSet.getDouble(12);
            double widthInMeters = resultSet.getDouble(13);
            int totalCost = resultSet.getInt(14);
            int leftToPay = resultSet.getInt(15);

            CurtainOrder curtainOrder = new CurtainOrder(curtainOrderId, heldDate, finishingDate, totalLabourCost, paidAmount, description, numOfPieces, labourCostPerMeter, metersPerPiece, customerId, serviceId, lengthInMeters, widthInMeters, totalCost, leftToPay);

            return curtainOrder ;
        }

        return null;
    }

    public static int setRawMaterialIdInComboBoxWhenSearched(String id) throws SQLException {
        String sql = "SELECT rawMaterialId FROM rawMaterialUsage WHERE curtainOrderId = ?" ;

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
    public static List<String> getServiceIds() throws SQLException {

        String sql = "SELECT deliveryAndFittingId FROM deliveryAndFitting" ;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<String> serviceIdList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            serviceIdList.add(id);
        }
        return serviceIdList ;
    }

    public static List<Integer> getRawIds() throws SQLException {

        String sql = "SELECT rowMaterialId FROM rowmaterial";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Integer> rawIdList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            rawIdList.add(id);
        }
        return rawIdList;
    }

    public static int generateTotalCost(int id) throws SQLException {

        String sql = "SELECT pricePerUnit FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int pricePerUnit = resultSet.getInt("pricePerUnit");

            return pricePerUnit ;
        }
        return 0 ;
    }

    public static int getServiceCost(String serviceId) throws SQLException {
        String sql = "SELECT totalPrice FROM deliveryAndFitting WHERE deliveryAndFittingId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, serviceId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int totalServiceCost = resultSet.getInt("totalPrice");

            return totalServiceCost ;
        }
        return 0;
    }

    public static boolean updateRawMaterial(RawMaterialUsage rmUsage, int rawMaterialId) throws SQLException {
        String sql = "SELECT quantityOnHand FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterialId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String value = resultSet.getString("quantityOnHand");

            String[] split = value.split(" ");

            double qtyOnHand = Double.parseDouble(split[0]);

            double actualQty = qtyOnHand - rmUsage.getQuantityUsedInMeters();

            String  finalStringValue = actualQty + " " + split[1];

            boolean isUpdate = update(finalStringValue, rawMaterialId);

            if (isUpdate){
                return true ;
            }
        }
        return false;
    }

    public static boolean update(String finalStringValue, int rawMaterialId) throws SQLException {

        String  sql = "UPDATE rowMaterial SET quantityOnHand = ? WHERE rowMaterialId = ?";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, finalStringValue);
        pstm.setObject(2, rawMaterialId);


        return pstm.executeUpdate() > 0;

    }

    public static boolean reUpdateRawMaterial(RawMaterialUsage rmUsage, int rawMaterialId, double previousQtyUsed) throws SQLException {

        String sql = "SELECT quantityOnHand FROM rowMaterial WHERE rowMaterialId = ?";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterialId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String value = resultSet.getString("quantityOnHand");

            String[] split = value.split(" ");

            double qtyOnHand = Double.parseDouble(split[0]) + previousQtyUsed;

            double actualQty = qtyOnHand - rmUsage.getQuantityUsedInMeters();

            String  finalStringValue = actualQty + " " + split[1];

            boolean isUpdate = reUpdate(finalStringValue, rawMaterialId);

            if (isUpdate){
                return true ;
            }
        }
        return false;
    }

    private static boolean reUpdate(String finalStringValue, int rawMaterialId) throws SQLException {

        String  sql = "UPDATE rowMaterial SET quantityOnHand = ? WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, finalStringValue);
        pstm.setObject(2, rawMaterialId);

        return pstm.executeUpdate() > 0;
    }

    public static List<CurtainOrderTm> getAll() throws SQLException {

        String sql = "SELECT curtainOrderId, heldDate, finishingDate, customerId, totalCost, leftToPay FROM curtainOrders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<CurtainOrderTm> allOrders = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String curtainOrderId = resultSet.getString(1);
            LocalDate ohd = resultSet.getDate(2).toLocalDate();
            LocalDate ofd = resultSet.getDate(3).toLocalDate();
            int customerId = resultSet.getInt(4);
            int totalCost = resultSet.getInt(5);
            int leftToPay = resultSet.getInt(6);

            CurtainOrderTm curtainOrderTm = new CurtainOrderTm(curtainOrderId, ohd, ofd, customerId, totalCost, leftToPay);

            allOrders.add(curtainOrderTm);
        }
        return allOrders;
    }
}
