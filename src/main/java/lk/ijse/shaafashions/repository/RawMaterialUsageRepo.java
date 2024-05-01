package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.RawMaterialUsage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        System.out.println(rawMaterialUsage.getQuantityUsedInMeters());
        System.out.println(rawMaterialUsage.getRawMaterialId());
        System.out.println(rawMaterialUsage.getCurtainOrderId());

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

        if (resultSet.next()){
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

        if (resultSet.next()){
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
}
