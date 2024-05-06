package lk.ijse.shaafashions.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.shaafashions.model.RawMaterialUsage;
import lk.ijse.shaafashions.repository.RawMaterialUsageRepo;

import java.sql.SQLException;
import java.util.List;

public class RawMaterialUsageController {

    @FXML
    private TableColumn<RawMaterialUsage, String> colClothOrderId;

    @FXML
    private TableColumn<RawMaterialUsage, String> colCurtainOrderId;

    @FXML
    private TableColumn<RawMaterialUsage, Double> colQtyUsed;

    @FXML
    private TableColumn<RawMaterialUsage, Integer> colRawMaterialId;

    @FXML
    private TableColumn<RawMaterialUsage, Integer> colRmUsageId;

    @FXML
    private TableView<RawMaterialUsage> tblRawMaterialUsage;

    public void initialize(){
        getAllRawMaterialUsageData();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colRmUsageId.setCellValueFactory(new PropertyValueFactory<>("rawMaterialUsageId"));
        colQtyUsed.setCellValueFactory(new PropertyValueFactory<>("quantityUsedInMeters"));
        colRawMaterialId.setCellValueFactory(new PropertyValueFactory<>("rawMaterialId"));
        colClothOrderId.setCellValueFactory(new PropertyValueFactory<>("clothOrderId"));
        colCurtainOrderId.setCellValueFactory(new PropertyValueFactory<>("curtainOrderId"));
    }

    private void getAllRawMaterialUsageData() {
        ObservableList<RawMaterialUsage> obList = FXCollections.observableArrayList();

        try {
            List<RawMaterialUsage> rawUsageAll = RawMaterialUsageRepo.getAll();
            for (RawMaterialUsage rawMaterialUsage : rawUsageAll){
                RawMaterialUsage rmUsage = new RawMaterialUsage(
                        rawMaterialUsage.getRawMaterialUsageId(),
                        rawMaterialUsage.getQuantityUsedInMeters(),
                        rawMaterialUsage.getRawMaterialId(),
                        rawMaterialUsage.getClothOrderId(),
                        rawMaterialUsage.getCurtainOrderId()
                );
                obList.add(rmUsage);
            }
            tblRawMaterialUsage.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
