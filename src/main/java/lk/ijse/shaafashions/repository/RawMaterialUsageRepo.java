package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Employee;
import lk.ijse.shaafashions.model.RawMaterialUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialUsageRepo {

    public static boolean addDetail(RawMaterialUsage rawMaterialUsage) throws SQLException {

        String sql = "INSERT INTO rawMaterialUsage (quantityUsedInMeters, rawMaterialId, clothOrderId, curtainOrderId) VALUES (?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        rawMaterialUsage.getCurtainOrderId();

        pstm.setObject(1, rawMaterialUsage.getQuantityUsedInMeters());
        pstm.setObject(2, rawMaterialUsage.getRawMaterialId());
        pstm.setObject(3, null);
        pstm.setObject(4, rawMaterialUsage.getCurtainOrderId());

        return pstm.executeUpdate() > 0;

    }

    public static boolean updateDetail(RawMaterialUsage rawMaterialUsage) throws SQLException {
        String sql = "UPDATE rawMaterialUsage SET quantityUsedInMeters = ?, rawMaterialId = ?, clothOrderId = ? WHERE curtainOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, rawMaterialUsage.getQuantityUsedInMeters());
        pstm.setObject(2, rawMaterialUsage.getRawMaterialId());
        pstm.setObject(3, null);
        pstm.setObject(4, rawMaterialUsage.getCurtainOrderId());

        return pstm.executeUpdate() > 0;
    }

    public static double getPreviousQtyUsed(String curtainOrderId) throws SQLException {

        String sql = "SELECT quantityUsedInMeters FROM rawMaterialUsage WHERE curtainOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, curtainOrderId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("quantityUsedInMeters");
        }
        return 0.0;
    }

    public static double getQuantityUsed(String id) throws SQLException {

        String sql = "SELECT quantityUsedInMeters FROM rawMaterialUsage WHERE curtainOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("quantityUsedInMeters");
        }
        return 0;
    }

    public static boolean deleteRawMaterialUsage(int rawMaterialId) throws SQLException {

        String sql = "DELETE FROM rawMaterialUsage WHERE rawMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterialId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean createClothRawMaterialUsage(RawMaterialUsage rawMaterialUsage) throws SQLException {

        String sql = "INSERT INTO rawMaterialUsage (quantityUsedInMeters, rawMaterialId, clothOrderId, curtainOrderId) VALUES (?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterialUsage.getQuantityUsedInMeters());
        pstm.setObject(2, rawMaterialUsage.getRawMaterialId());
        pstm.setObject(3, rawMaterialUsage.getClothOrderId());
        pstm.setObject(4, null);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateClothRawMaterialUsage(RawMaterialUsage rawMaterialUsage) throws SQLException {

        System.out.println(rawMaterialUsage.getQuantityUsedInMeters());
        System.out.println(rawMaterialUsage.getRawMaterialId());
        System.out.println(rawMaterialUsage.getClothOrderId());

        String sql = "UPDATE rawMaterialUsage SET quantityUsedInMeters = ?, rawMaterialId = ?, curtainOrderId = ? WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, rawMaterialUsage.getQuantityUsedInMeters());
        pstm.setObject(2, rawMaterialUsage.getRawMaterialId());
        pstm.setObject(3, null);
        pstm.setObject(4, rawMaterialUsage.getClothOrderId());

        return pstm.executeUpdate() > 0;
    }

    public static double getPreviousQtyUsedClothOrder(String clothOrderId) throws SQLException {

        String sql = "SELECT quantityUsedInMeters FROM rawMaterialUsage WHERE clothOrderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, clothOrderId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("quantityUsedInMeters");
        }
        return 0.0;
    }

    public static List<RawMaterialUsage> getAll() throws SQLException {

        String sql = "SELECT * FROM rawMaterialUsage";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<RawMaterialUsage> usageAll = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            int rawMaterialUsageId = resultSet.getInt(1);
            double quantityUsedInMeters = resultSet.getDouble(2);
            int rawMaterialId = resultSet.getInt(3);
            String clothOrderId = resultSet.getString(4);
            String curtainOrderId = resultSet.getString(5);

            RawMaterialUsage rawMaterialUsage = new RawMaterialUsage(rawMaterialUsageId, quantityUsedInMeters, rawMaterialId, clothOrderId, curtainOrderId);

            usageAll.add(rawMaterialUsage);
        }
        return usageAll;
    }
}
