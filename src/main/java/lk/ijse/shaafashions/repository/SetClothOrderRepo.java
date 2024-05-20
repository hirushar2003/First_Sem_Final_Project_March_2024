package lk.ijse.shaafashions.repository;

import lk.ijse.shaafashions.db.DbConnection;
import lk.ijse.shaafashions.model.SetClothOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class SetClothOrderRepo {

    public static boolean addClothOrder(SetClothOrder setClothOrder) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isSaved = ClothOrderRepo.addNewOrder(setClothOrder.getClothOrder());
            if (isSaved){
                boolean isUsageUpdate = RawMaterialUsageRepo.createClothRawMaterialUsage(setClothOrder.getRawMaterialUsage());
                if (isUsageUpdate){
                    boolean isRawMaterialUpdate = CurtainOrderRepo.updateRawMaterial(setClothOrder.getRmUsage(), setClothOrder.getRawMaterialId());
                    if (isRawMaterialUpdate){
                        connection.commit();
                        return true ;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
