package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.model.RawMaterial;
import lk.ijse.shaafashions.repository.RawMaterialRepo;
import lk.ijse.shaafashions.repository.SupplierRepo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RawMaterialController {

    public TextField txtType;
    public TextField txtDescription;
    public TextField txtQuantityBought;
    public TextField txtQuantityOnHand;
    public DatePicker datePickerRestockedDate;
    public TextField txtPricePerUnit;
    public TextField txtRowIdSearch;
    public AnchorPane rowMaterialAp;
    @FXML
    private TableView<RawMaterial> tblRawMaterial;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMaterialType;

    @FXML
    private TableColumn<?, ?> colQuantityBought;

    @FXML
    private TableColumn<?, ?> colQuantityOnHand;

    @FXML
    private TableColumn<?, ?> colRawId;

    @FXML
    private TableColumn<?, ?> colRestockedDate;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<Integer> comSupplierId;

    public void initialize (){
        getSupplierIds();
        getAllRawMaterials();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colRawId.setCellValueFactory(new PropertyValueFactory<>("rawMaterialId"));
        colMaterialType.setCellValueFactory(new PropertyValueFactory<>("materialType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantityBought.setCellValueFactory(new PropertyValueFactory<>("qtyBought"));
        colQuantityOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colRestockedDate.setCellValueFactory(new PropertyValueFactory<>("lastRestockedDate"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    }

    private void getAllRawMaterials() {
        ObservableList<RawMaterial> obList = FXCollections.observableArrayList();

        try {
            List<RawMaterial> rawMaterialList = RawMaterialRepo.getAll();

            for(RawMaterial rM :rawMaterialList){
                RawMaterial rawMaterial = new RawMaterial(
                        rM.getRawMaterialId(),
                        rM.getMaterialType(),
                        rM.getDescription(),
                        rM.getQtyBought(),
                        rM.getQtyOnHand(),
                        rM.getLastRestockedDate(),
                        rM.getUnitPrice(),
                        rM.getSupplierId()
                );
                obList.add(rawMaterial);
            }
            tblRawMaterial.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getSupplierIds(){
        ObservableList<Integer> obList = FXCollections.observableArrayList();

        try {
            List<Integer> idList = SupplierRepo.getIds();

            for (Integer id :idList){
                obList.add(id);
            }
            comSupplierId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnRowUpdateOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtRowIdSearch.getText());
        String description = txtDescription.getText();
        String materialType = txtType.getText();
        int unitPrice = Integer.parseInt(txtPricePerUnit.getText());
        String qtyBought = txtQuantityBought.getText();
        String qtyOnHand = txtQuantityOnHand.getText();
        LocalDate date = datePickerRestockedDate.getValue();
        int supplierId = comSupplierId.getValue();

        RawMaterial rawMaterial = new RawMaterial(id, materialType, description, qtyBought, qtyOnHand, date, unitPrice, supplierId);

        try {
            boolean isUpdate = RawMaterialRepo.updateRaw(rawMaterial);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Row material updated sucessfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnRowDeleteOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtRowIdSearch.getText());

        try {
            boolean isDelete = RawMaterialRepo.deleteRawMaterial(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Raw material deleted sucessfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void imgRowSearchOnAction(MouseEvent mouseEvent) throws SQLException {

        String id = txtRowIdSearch.getText();

        RawMaterial rawMaterial = RawMaterialRepo.searchRawById(id);

        if (rawMaterial != null){
            txtRowIdSearch.setText(String.valueOf(rawMaterial.getRawMaterialId()));
            txtType.setText(rawMaterial.getMaterialType());
            txtDescription.setText(rawMaterial.getDescription());
            txtPricePerUnit.setText(String.valueOf(rawMaterial.getUnitPrice()));
            txtQuantityBought.setText(rawMaterial.getQtyBought());
            txtQuantityOnHand.setText(rawMaterial.getQtyOnHand());
            datePickerRestockedDate.setValue(rawMaterial.getLastRestockedDate());
            comSupplierId.setValue(rawMaterial.getSupplierId());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Cannot find Raw Material").show();
            clearFields();
            initialize();
        }
    }

    public void btnRowAddOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        String materialType = txtType.getText();
        int unitPrice = Integer.parseInt(txtPricePerUnit.getText());
        String qtyBought = txtQuantityBought.getText();
        String qtyOnHand = txtQuantityOnHand.getText();
        LocalDate value = datePickerRestockedDate.getValue();
        int supplierId = comSupplierId.getValue();

        RawMaterial rawMaterial = new RawMaterial(materialType, description, qtyBought, qtyOnHand, value, unitPrice, supplierId);

        try {
            Boolean isSaved = RawMaterialRepo.saveRawMaterial(rawMaterial);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Raw material Saved Successfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void clearFields(){
        txtDescription.clear();
        txtType.clear();
        txtPricePerUnit.clear();
        txtQuantityBought.clear();
        txtRowIdSearch.clear();
        txtQuantityOnHand.clear();
        datePickerRestockedDate.setValue(null);
        comSupplierId.setValue(null);
    }
}
