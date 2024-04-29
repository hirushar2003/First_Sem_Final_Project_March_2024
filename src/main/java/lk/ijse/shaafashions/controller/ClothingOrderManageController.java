package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.shaafashions.model.ClothOrder;
import lk.ijse.shaafashions.repository.ClothOrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClothingOrderManageController {


    public DatePicker datePickerOHD;
    public DatePicker datePickerOFD;
    public TextField txtMeasurements;
    public TextField txtQtyOrdered;
    public TextField txtDescription;
    public TextField txtClotheType;
    public TextField txtLabourCostPerUnit;
    public TextField txtTotalLabourCost;
    public TextField txtFabricMetersPerUnit;
    public TextField txtPaidAmount;
    public ComboBox<Integer> comCustomerId;
    public TextField txtSearchOrderID;

    public void initialize (){
        getCustomerIds();
        loadAllCustomers();
        setCellValueFactory();
        loadCutomerIds();
    }

    private void loadCutomerIds() {

    }

    private void setCellValueFactory() {

    }

    private void loadAllCustomers() {

    }

    public void btnOrderAddOnAction(ActionEvent actionEvent) {
        LocalDate dateOHD = datePickerOHD.getValue();
        LocalDate dateOfd = datePickerOFD.getValue();
        String measurements = txtMeasurements.getText();
        int qty = Integer.parseInt(txtQtyOrdered.getText());
        String desciption = txtDescription.getText();
        String clothType = txtClotheType.getText();
        int labourCostPerUnit = Integer.parseInt(txtLabourCostPerUnit.getText());
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        double fabricMetersPerUnit = Double.parseDouble(txtFabricMetersPerUnit.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        int customerId = (int) comCustomerId.getValue();
        String orderId = txtSearchOrderID.getText();

        ClothOrder clothOrder = new ClothOrder(orderId, dateOHD, dateOfd, labourCostPerUnit, qty, measurements, fabricMetersPerUnit, clothType, totalLabourCost, paidAmount, desciption, customerId);

        try {
            boolean isSaved = ClothOrderRepo.addNewOrder(clothOrder);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added Sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void imgSearchOnAction(MouseEvent mouseEvent) throws SQLException {

        String id = txtSearchOrderID.getText();

        ClothOrder clothOrder = ClothOrderRepo.searchById(id);

        if (clothOrder != null){
            datePickerOHD.setValue(clothOrder.getHeldDate());
            datePickerOFD.setValue(clothOrder.getFinishingDate());
            txtMeasurements.setText(clothOrder.getMeasurements());
            txtQtyOrdered.setText(String.valueOf(clothOrder.getQtyOrderd()));
            txtDescription.setText(clothOrder.getDescription());
            txtClotheType.setText(clothOrder.getClothType());
            txtLabourCostPerUnit.setText(String.valueOf(clothOrder.getLabourCostPerUnit()));
            txtTotalLabourCost.setText(String.valueOf(clothOrder.getTotalLabourCost()));
            txtFabricMetersPerUnit.setText(String.valueOf(clothOrder.getFabricMeatersPerOneUnit()));
            txtPaidAmount.setText(String.valueOf(clothOrder.getPaidAmount()));
            comCustomerId.setValue(clothOrder.getCustomerId());
            txtSearchOrderID.setText(clothOrder.getOrderId());
        } else {
            new Alert(Alert.AlertType.ERROR, " Cloth Order not Found !").show();
        }
    }

    public void btnOrderUpdateOnAction(ActionEvent actionEvent) {
        LocalDate dateOHD = datePickerOHD.getValue();
        LocalDate dateOfd = datePickerOFD.getValue();
        String measurements = txtMeasurements.getText();
        int qty = Integer.parseInt(txtQtyOrdered.getText());
        String desciption = txtDescription.getText();
        String clothType = txtClotheType.getText();
        int labourCostPerUnit = Integer.parseInt(txtLabourCostPerUnit.getText());
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        double fabricMetersPerUnit = Double.parseDouble(txtFabricMetersPerUnit.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        int customerId = (int) comCustomerId.getValue();
        String orderId = txtSearchOrderID.getText();

        ClothOrder clothOrder = new ClothOrder(orderId, dateOHD, dateOfd, labourCostPerUnit, qty, measurements, fabricMetersPerUnit, clothType, totalLabourCost, paidAmount, desciption, customerId);

        try {
            boolean isUpdate = ClothOrderRepo.updateOrder(clothOrder);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Updated Sucessfully").show();
                initialize();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, " Cannot update Cloth Order!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOrdrDeleteOnAction(ActionEvent actionEvent) {
        String id = txtSearchOrderID.getText();

        try {
            boolean isDelete = ClothOrderRepo.deleteClothingOrder(id);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Cloth order deleted sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void imgShowCustomerTable(MouseEvent mouseEvent) {

    }

    public void imgGenerateLabourCost(MouseEvent mouseEvent) {

    }

    public void imgGenerateFabricMetersPerUnit(MouseEvent mouseEvent) {

    }

    public void clearFields(){
        datePickerOHD.setValue(null);
        datePickerOFD.setValue(null);
        txtMeasurements.clear();
        txtQtyOrdered.clear();
        txtDescription.clear();
        txtClotheType.clear();
        txtLabourCostPerUnit.clear();
        txtTotalLabourCost.clear();
        txtFabricMetersPerUnit.clear();
        txtPaidAmount.clear();
        comCustomerId.setValue(null);
        txtSearchOrderID.clear();
    }

    private void getCustomerIds(){

        ObservableList<Integer> obList = FXCollections.observableArrayList();

        try {
            List<Integer> cusIdList = ClothOrderRepo.getIds();

            for (int id :cusIdList){
                obList.add(id);
            }
            comCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
