package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.shaafashions.model.CurtainOrder;
import lk.ijse.shaafashions.repository.CurtainOrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CurtainOrderManageController {

    public DatePicker datePickerOHD;
    public DatePicker datePickerOFD;
    public TextField txtDescription;
    public TextField txtLength;
    public TextField txtWidth;
    public TextField txtNumOfPieces;
    public TextField txtAreaPerPieces;
    public TextField txtLaborCostPerMeter;
    public TextField txtTotalLabourCost;
    public TextField txtPaidAmount;
    public TextField txtCurtainOrderId;
    public ComboBox<Integer> comCustomerId;
    public ComboBox<String> comServiceId;

    public void initialize(){
        loadAllData();
        setCellValueAfctory();
        getCustomerIds();
        getServiceIds();
    }



    private static void setCellValueAfctory() {

    }

    private static void loadAllData() {

    }

    public void imgGenerateArea(MouseEvent mouseEvent) {

    }

    public void imgGenerateTotalLabourCost(MouseEvent mouseEvent) {

    }

    public void imgSearchCurtainOrderId(MouseEvent mouseEvent) throws SQLException {
        String id = txtCurtainOrderId.getText();

        CurtainOrder curtainOrder = CurtainOrderRepo.searchById(id);

        if (curtainOrder != null){
            datePickerOHD.setValue(curtainOrder.getHeldDate());
            datePickerOFD.setValue(curtainOrder.getFinishingDate());
            txtDescription.setText(curtainOrder.getDescription());
            txtLength.setText(String.valueOf(curtainOrder.getLengthInMeters()));
            txtWidth.setText(String.valueOf(curtainOrder.getWidthInMeters()));
            txtNumOfPieces.setText(String.valueOf(curtainOrder.getNumOfPieces()));
            txtAreaPerPieces.setText(String.valueOf(curtainOrder.getMetersPerPiece()));
            txtLaborCostPerMeter.setText(String.valueOf(curtainOrder.getLabourCostPerMeter()));
            txtTotalLabourCost.setText(String.valueOf(curtainOrder.getTotalLabourCost()));
            txtPaidAmount.setText(String.valueOf(curtainOrder.getPaidAmount()));
            txtCurtainOrderId.setText(curtainOrder.getId());
            comCustomerId.setValue(curtainOrder.getCustomerId());
            comServiceId.setValue(curtainOrder.getServiceId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Cannot find curtain order Id").show();
            clearFields();
        }
    }

    public void btnOrderAddOnAction(ActionEvent actionEvent) {
        String id = txtCurtainOrderId.getText();
        LocalDate heldDate = datePickerOHD.getValue();
        LocalDate finishingDate = datePickerOFD.getValue();
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        String description = txtDescription.getText();
        int numOfPieces = Integer.parseInt(txtNumOfPieces.getText());
        int labourCostPerMeter = Integer.parseInt(txtLaborCostPerMeter.getText());
        double metersPerPiece = Double.parseDouble(txtAreaPerPieces.getText());
        int customerId = (int) comCustomerId.getValue();
        String serviceId = String.valueOf(comServiceId.getValue());
        double lengthInMeters = Double.parseDouble(txtLength.getText());
        double widthInMeters = Double.parseDouble(txtWidth.getText());

        CurtainOrder curtainOrder = new CurtainOrder(id, heldDate, finishingDate, totalLabourCost, paidAmount, description, numOfPieces, labourCostPerMeter, metersPerPiece, customerId, serviceId, lengthInMeters, widthInMeters);

        try {
            boolean isSaved = CurtainOrderRepo.addOrder(curtainOrder);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Curtain Order saved sucessfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOrderUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCurtainOrderId.getText();
        LocalDate heldDate = datePickerOHD.getValue();
        LocalDate finishingDate = datePickerOFD.getValue();
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        String description = txtDescription.getText();
        int numOfPieces = Integer.parseInt(txtNumOfPieces.getText());
        int labourCostPerMeter = Integer.parseInt(txtLaborCostPerMeter.getText());
        double metersPerPiece = Double.parseDouble(txtAreaPerPieces.getText());
        int customerId = (int) comCustomerId.getValue();
        String serviceId = String.valueOf(comServiceId.getValue());
        double lengthInMeters = Double.parseDouble(txtLength.getText());
        double widthInMeters = Double.parseDouble(txtWidth.getText());

        CurtainOrder curtainOrder = new CurtainOrder(id, heldDate, finishingDate, totalLabourCost, paidAmount, description, numOfPieces, labourCostPerMeter, metersPerPiece, customerId, serviceId, lengthInMeters, widthInMeters);

        try {
            boolean isUpdate = CurtainOrderRepo.updateOrder(curtainOrder);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Curtain order updated sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnOrderDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCurtainOrderId.getText();

        try {
            boolean isDelete = CurtainOrderRepo.deleteOrder(id);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Curtain order deleted sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void getCustomerIds(){

        ObservableList<Integer> obList = FXCollections.observableArrayList();

        try {
            List<Integer> cusIdList = CurtainOrderRepo.getIds();

            for (int id :cusIdList){
                obList.add(id);
            }
            comCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getServiceIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> serviceIds = CurtainOrderRepo.getServiceIds();

            for (String id :serviceIds){
                obList.add(id);
            }
            comServiceId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearFields(){
        datePickerOHD.setValue(null);
        datePickerOFD.setValue(null);
        txtDescription.clear();
        txtLength.clear();
        txtWidth.clear();
        txtNumOfPieces.clear();
        txtAreaPerPieces.clear();
        txtLaborCostPerMeter.clear();
        txtTotalLabourCost.clear();
        txtPaidAmount.clear();
        txtCurtainOrderId.clear();
        comCustomerId.setValue(null);
        comServiceId.setValue(null);
    }
}
