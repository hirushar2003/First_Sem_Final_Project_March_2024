package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.RawMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialRepo {

    public static Boolean saveRawMaterial(RawMaterial rawMaterial) throws SQLException {

        String sql = "INSERT INTO rowmaterial (materialType, description, quantityBought, quantityOnHand, lastReStockedDate, pricePerUnit, supplierId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterial.getMaterialType());
        pstm.setObject(2, rawMaterial.getDescription());
        pstm.setObject(3, rawMaterial.getQtyBought());
        pstm.setObject(4, rawMaterial.getQtyOnHand());
        pstm.setObject(5, rawMaterial.getLastRestockedDate());
        pstm.setObject(6, rawMaterial.getUnitPrice());
        pstm.setObject(7, rawMaterial.getSupplierId());

        return pstm.executeUpdate() > 0 ;
    }

    public static RawMaterial searchRawById(String id) throws SQLException {
        String sql = "SELECT * FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int rawId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String desciption = resultSet.getString(3);
            String qtyBought = resultSet.getString(4);
            String qtyOnHand = resultSet.getString(5);
            LocalDate date = resultSet.getDate(6).toLocalDate();
            int unitPrice = resultSet.getInt(7);
            int supplierId = resultSet.getInt(8);

            RawMaterial rawMaterial = new RawMaterial(rawId, type, desciption, qtyBought, qtyOnHand, date, unitPrice, supplierId);

            return rawMaterial ;
        }
        return null ;
    }

    public static boolean deleteRawMaterial(int id) throws SQLException {
        String sql = "DELETE FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0 ;
    }

    public static boolean updateRaw(RawMaterial rawMaterial) throws SQLException {
        String sql = "UPDATE rowMaterial SET materialType = ?, description = ?, quantityBought = ?, quantityOnHand = ? , lastReStockedDate = ?, pricePerUnit = ?, supplierId = ? WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, rawMaterial.getMaterialType());
        pstm.setObject(2, rawMaterial.getDescription());
        pstm.setObject(3, rawMaterial.getQtyBought());
        pstm.setObject(4, rawMaterial.getQtyOnHand());
        pstm.setObject(5, rawMaterial.getLastRestockedDate());
        pstm.setObject(6, rawMaterial.getUnitPrice());
        pstm.setObject(7, rawMaterial.getSupplierId());
        pstm.setObject(8, rawMaterial.getRawMaterialId());

        return pstm.executeUpdate() > 0;
    }

    public static List<RawMaterial> getAll() throws SQLException {
        String sql = "SELECT * FROM rowMaterial";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<RawMaterial> rawMaterialList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int rawId = resultSet.getInt(1);
            String type = resultSet.getString(2);
            String description = resultSet.getString(3);
            String qtyBought = resultSet.getString(4);
            String qtyHand = resultSet.getString(5);
            LocalDate date = resultSet.getDate(6).toLocalDate();
            int price = resultSet.getInt(7);
            int supId = resultSet.getInt(8);

            RawMaterial rawMaterial = new RawMaterial(rawId, type, description, qtyBought, qtyHand, date, price, supId);

            rawMaterialList.add(rawMaterial);
        }
        return rawMaterialList;
    }

    public static boolean updateRm(int rawMaterialId, double quantityUsed) throws SQLException {

        String extarctSql = "SELECT quantityOnHand FROM rowMaterial WHERE rowMaterialId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(extarctSql);

        pstm.setObject(1, rawMaterialId);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String qtyOnHand = resultSet.getString("quantityOnHand");

            String[] splitQty = qtyOnHand.split(" ");

            double qty = Double.parseDouble(splitQty[0]) + quantityUsed;

            String quantity = qty + " " + splitQty[1];

            String updateSql = "UPDATE rowMaterial SET quantityOnHand = ? WHERE rowMaterialId = ?";

            PreparedStatement pstm1 = connection.prepareStatement(updateSql);

            pstm1.setObject(1, quantity);
            pstm1.setObject(2, rawMaterialId);

            return pstm1.executeUpdate() > 0;
        }
        return  false ;
    }
}
