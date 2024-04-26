package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo {

    public static boolean addService(Service service) throws SQLException {

        String sql = "INSERT INTO deliveryandfitting (deliveryandfittingId, district, stockScale, deliveryPrice, fittingPrice, totalPrice) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, service.getServiceId());
        pstm.setObject(2, service.getDistrict());
        pstm.setObject(3, service.getStockScale());
        pstm.setObject(4, service.getDeliveryPrice());
        pstm.setObject(5, service.getFittingPrice());
        pstm.setObject(6, service.getTotalPrice());

        return pstm.executeUpdate() > 0 ;
    }

    public static Service searchById(int id) throws SQLException {
        String sql = "SELECT * FROM deliveryAndFitting WHERE deliveryAndFittingId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            int deliveryAndFittingId = resultSet.getInt(1);
            String district = resultSet.getString(2);
            String scale = resultSet.getString(3);
            int delPrice = resultSet.getInt(4);
            int fitPrice = resultSet.getInt(5);
            int totalPrice = resultSet.getInt(6);

            Service service = new Service(deliveryAndFittingId, district, scale, delPrice, fitPrice, totalPrice);

            return service ;
        }
        return null ;
    }

    public static boolean update(Service service) throws SQLException {
        String  sql = "UPDATE deliveryAndFitting SET district = ?, stockScale = ?, deliveryPrice = ? , fittingPrice = ?, totalPrice = ?  WHERE deliveryAndFittingId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, service.getDistrict());
        pstm.setObject(2, service.getStockScale());
        pstm.setObject(3, service.getDeliveryPrice());
        pstm.setObject(4, service.getFittingPrice());
        pstm.setObject(5, service.getTotalPrice());
        pstm.setObject(6, service.getServiceId());

        return pstm.executeUpdate() > 0 ;
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM deliveryAndFitting WHERE deliveryAndFittingId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static List <Service> getAll() throws SQLException {

        String sql = "SELECT * FROM deliveryAndFitting";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Service> serviceList = new ArrayList<>();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String district = resultSet.getString(2);
            String scale = resultSet.getString(3);
            int deliveryPrice = resultSet.getInt(4);
            int fitPrice = resultSet.getInt(5);
            int total = resultSet.getInt(6);

            Service service = new Service(id, district, scale, deliveryPrice, fitPrice, total);

            serviceList.add(service);
        }
        return serviceList ;
    }
}
