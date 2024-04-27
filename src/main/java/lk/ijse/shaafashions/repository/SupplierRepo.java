package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Service;
import lk.ijse.shaafashions.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {

    public static boolean saveNewSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers (name, contact, email, businessLocation, suppliyingMaterial, latestPricePerUnit) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getContact());
        pstm.setObject(3, supplier.getEmail());
        pstm.setObject(4, supplier.getLocation());
        pstm.setObject(5, supplier.getMaterial());
        pstm.setObject(6, supplier.getPrice());

        return  pstm.executeUpdate() > 0 ;
    }

    public static Supplier searchSupplierById(int id) throws SQLException {
        String sql = "SELECT * FROM suppliers WHERE supplierId = ? ";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            int supplieId = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int contact = resultSet.getInt(3);
            String email = resultSet.getString(4);
            String location = resultSet.getString(5);
            String material = resultSet.getString(6);
            int price = resultSet.getInt(7);

            Supplier supplier = new Supplier(supplieId, name, contact, email, location, material, price);

            return supplier ;
        }
        return  null;
    }

    public static boolean deleteSupplier(int id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0 ;
    }

    public static boolean updateSupplier(Supplier supplier) throws SQLException {
        String  sql = "UPDATE suppliers SET name = ?, contact = ?, email = ? , businessLocation = ?, suppliyingMaterial = ?, latestPricePerUnit = ?  WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getName());
        pstm.setObject(2, supplier.getContact());
        pstm.setObject(3, supplier.getEmail());
        pstm.setObject(4, supplier.getLocation());
        pstm.setObject(5, supplier.getMaterial());
        pstm.setObject(6, supplier.getPrice());
        pstm.setObject(7, supplier.getSupplierId());

        return pstm.executeUpdate() > 0 ;
    }

    public static List <Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM suppliers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int contact = resultSet.getInt(3);
            String location = resultSet.getString(5);
            String material = resultSet.getString(6);
            int price = resultSet.getInt(7);

            Supplier supplier = new Supplier(id, name, contact, location, material, price);

            supplierList.add(supplier);
        }
        return supplierList ;
    }

    public static List<Integer> getIds() throws SQLException {

        String sql = "SELECT supplierId FROM suppliers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<Integer> supplierIds = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            supplierIds.add(id);
        }

        return supplierIds;
    }
}
