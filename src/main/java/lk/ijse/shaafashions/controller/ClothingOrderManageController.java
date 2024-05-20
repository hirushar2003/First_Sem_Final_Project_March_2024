package lk.ijse.shaafashions.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.shaafashions.model.ClothOrder;
import lk.ijse.shaafashions.model.RawMaterialUsage;
import lk.ijse.shaafashions.model.SetClothOrder;
import lk.ijse.shaafashions.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClothingOrderManageController {

    @FXML
    private ComboBox<Integer> comCustomerId;

    @FXML
    private ComboBox<Integer> comRawMaterialID;

    @FXML
    private DatePicker datePickerOFD;

    @FXML
    private DatePicker datePickerOHD;

    @FXML
    private TextField txtClothOrderId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtLaborCostPerUnit;

    @FXML
    private TextField txtLeftToPay;

    @FXML
    private TextField txtLength;

    @FXML
    private TextField txtNumOfUnits;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txtTotalCost;

    @FXML
    private TextField txtTotalLabourCost;

    @FXML
    private TextField txtUsagePerUnit;

    @FXML
    private TextField txtWidth;

    public void initialize(){
        getCustomerIds();
        getRawMaterialIds();
    }

    @FXML
    void btnOrderAddOnAction(ActionEvent event) {
        int customerId = (int) comCustomerId.getValue();
        int rawMaterialID = (int) comRawMaterialID.getValue();
        LocalDate oFD = datePickerOFD.getValue();
        LocalDate oHD = datePickerOHD.getValue();
        String clothOrderId = txtClothOrderId.getText();
        String description = txtDescription.getText();
        int laborCostPerUnitText = Integer.parseInt(txtLaborCostPerUnit.getText());
        int leftToPay = Integer.parseInt(txtLeftToPay.getText());
        double length = Double.parseDouble(txtLength.getText());
        int numOfUnits = Integer.parseInt(txtNumOfUnits.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        int totalCost = Integer.parseInt(txtTotalCost.getText());
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        double usage = Double.parseDouble(txtUsagePerUnit.getText());
        double width = Double.parseDouble(txtWidth.getText());

        double totalRawMaterialUsed = length * width * numOfUnits ;

        ClothOrder clothOrder = new ClothOrder(clothOrderId, oHD, oFD, laborCostPerUnitText, numOfUnits, length, width, usage, totalLabourCost, paidAmount, leftToPay, description, customerId, totalCost);

        RawMaterialUsage rawMaterialUsage = new RawMaterialUsage(rawMaterialID, clothOrderId, totalRawMaterialUsed);

        RawMaterialUsage rawMaterialUsage1 = new RawMaterialUsage(totalRawMaterialUsed);

        SetClothOrder setClothOrder = new SetClothOrder(clothOrder, rawMaterialUsage, rawMaterialUsage1, rawMaterialID);

        try {
            boolean isSavedSuccessfully = SetClothOrderRepo.addClothOrder(setClothOrder);
            if (isSavedSuccessfully){
                new Alert(Alert.AlertType.CONFIRMATION, "Order placed sucessfully ! ").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "OOPs cannot place the order ! ").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


//        ClothOrder clothOrder = new ClothOrder(clothOrderId, oHD, oFD, laborCostPerUnitText, numOfUnits, length, width, usage, totalLabourCost, paidAmount, leftToPay, description, customerId, totalCost);
//
//        try {
//            boolean isSaved = ClothOrderRepo.addNewOrder(clothOrder);
//            if (isSaved){
//                new Alert(Alert.AlertType.CONFIRMATION, "Order created sucessfully").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//
//        RawMaterialUsage rawMaterialUsage = new RawMaterialUsage(rawMaterialID, clothOrderId, totalRawMaterialUsed);
//
//        try {
//            boolean isCreated = RawMaterialUsageRepo.createClothRawMaterialUsage(rawMaterialUsage);
//            if (isCreated){
//                new Alert(Alert.AlertType.CONFIRMATION, "Created raw material usage").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//
//        RawMaterialUsage rawMaterialUsage1 = new RawMaterialUsage(totalRawMaterialUsed);
//
//        try {
//            boolean isUpdateRawMaterial = CurtainOrderRepo.updateRawMaterial(rawMaterialUsage1, rawMaterialID);
//            if (isUpdateRawMaterial){
//                new Alert(Alert.AlertType.CONFIRMATION, "Updateed Raw Material").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }

    @FXML
    void btnOrderDeleteOnAction(ActionEvent event) {
        String clothOrderId = txtClothOrderId.getText();
        int rawMaterialId = comRawMaterialID.getValue();

        try {
            double quantityUsed = RawMaterialUsageRepo.getQuantityUsed(clothOrderId);
            boolean isUpdate = RawMaterialRepo.updateRm(rawMaterialId, quantityUsed);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, " Raw Material Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        try {
            boolean isDeleted = RawMaterialUsageRepo.deleteRawMaterialUsage(rawMaterialId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Raw Material Usage updated").show();
                initialize();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        try {
            System.out.println(clothOrderId);
            boolean isDelete = ClothOrderRepo.deleteOrder(clothOrderId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Cloth order deleted sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnOrderUpdateOnAction(ActionEvent event) throws SQLException {
        int customerId = (int) comCustomerId.getValue();
        int rawMaterialID = (int) comRawMaterialID.getValue();
        LocalDate oFD = datePickerOFD.getValue();
        LocalDate oHD = datePickerOHD.getValue();
        String clothOrderId = txtClothOrderId.getText();
        String description = txtDescription.getText();
        int laborCostPerUnitText = Integer.parseInt(txtLaborCostPerUnit.getText());
        int leftToPay = Integer.parseInt(txtLeftToPay.getText());
        double length = Double.parseDouble(txtLength.getText());
        int numOfUnits = Integer.parseInt(txtNumOfUnits.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());
        int totalCost = Integer.parseInt(txtTotalCost.getText());
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());
        double usage = Double.parseDouble(txtUsagePerUnit.getText());
        double width = Double.parseDouble(txtWidth.getText());

        double previousQtyUsed = RawMaterialUsageRepo.getPreviousQtyUsedClothOrder(clothOrderId);

        ClothOrder clothOrder = new ClothOrder(clothOrderId, oHD, oFD, laborCostPerUnitText, numOfUnits, length, width, usage, totalLabourCost, paidAmount, leftToPay, description, customerId, totalCost);

        try {
            boolean isUpdate = ClothOrderRepo.updateClothOrder(clothOrder);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Order updated sucessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        int rawMaterialId = comRawMaterialID.getValue() ;

        double qtyUsed = usage * numOfUnits ;

        System.out.println(qtyUsed);
        System.out.println(rawMaterialId);
        System.out.println(clothOrderId);

        RawMaterialUsage rawMaterialUsage = new RawMaterialUsage(rawMaterialId, clothOrderId, qtyUsed);

        try {
            boolean isUpdate = RawMaterialUsageRepo.updateClothRawMaterialUsage(rawMaterialUsage);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated raw material usage sucessfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        RawMaterialUsage rmUsage = new RawMaterialUsage(qtyUsed);

        System.out.println(previousQtyUsed);
        try {
            System.out.println(previousQtyUsed);
            boolean isUpdate = CurtainOrderRepo.reUpdateRawMaterial(rmUsage, rawMaterialId, previousQtyUsed);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Raw material updated sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void imgGenerateLeftToPay(MouseEvent event) {
        int totalCost = Integer.parseInt(txtTotalCost.getText());
        int paidAmount = Integer.parseInt(txtPaidAmount.getText());

        txtLeftToPay.setText(String.valueOf(totalCost - paidAmount));
    }

    @FXML
    void imgGenerateTotalCost(MouseEvent event) {
        int rmId = comRawMaterialID.getValue();

        double lengthInMeters = Double.parseDouble(txtLength.getText());
        double widthInMeters = Double.parseDouble(txtWidth.getText());
        int numOfPieces = Integer.parseInt(txtNumOfUnits.getText());
        int totalLabourCost = Integer.parseInt(txtTotalLabourCost.getText());

        double area = lengthInMeters * widthInMeters ;

        double wholeArea = area * numOfPieces;

        try {
            int pricePerMeter = ClothOrderRepo.generateTotalCost(rmId);
            int totalRmCost = (int) (wholeArea * pricePerMeter);
            final int totalCost = totalRmCost + totalLabourCost ;

            txtTotalCost.setText(String.valueOf(totalCost));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgGenerateTotalLabourCost(MouseEvent event) {
        int numOfPieces = Integer.parseInt(txtNumOfUnits.getText());
        int labourCostPerMeter = Integer.parseInt(txtLaborCostPerUnit.getText());
        double metersPerPiece = Double.parseDouble(txtUsagePerUnit.getText());

        int totalLabourCost = (int) (numOfPieces * labourCostPerMeter * metersPerPiece);

        txtTotalLabourCost.setText(String.valueOf(totalLabourCost));
    }

    @FXML
    void imgGenerateUsagePerUnit(MouseEvent event) {

        double length = Double.parseDouble(txtLength.getText());
        double width = Double.parseDouble(txtLength.getText());

        double usagePerUnit = length * width ;

        txtUsagePerUnit.setText(String.valueOf(usagePerUnit));
    }

    @FXML
    void imgSearchClothOrderId(MouseEvent event) {

        String clothOrderid = txtClothOrderId.getText();

        try {
            ClothOrder clothOrder = ClothOrderRepo.searchOrderById(clothOrderid);

            if (clothOrder != null){
                comCustomerId.setValue(clothOrder.getCustomerId());
                //comRawMaterialID.setValue(null);
                datePickerOFD.setValue(clothOrder.getFinishingDate());
                datePickerOHD.setValue(clothOrder.getHeldDate());
                txtClothOrderId.setText(clothOrder.getClothOrderId());
                txtDescription.setText(clothOrder.getDescription());
                txtLaborCostPerUnit.setText(String.valueOf(clothOrder.getLabourCostPerUnit()));
                txtLeftToPay.setText(String.valueOf(clothOrder.getLeftToPay()));
                txtLength.setText(String.valueOf(clothOrder.getLength()));
                txtNumOfUnits.setText(String.valueOf(clothOrder.getNumOfUnits()));
                txtPaidAmount.setText(String.valueOf(clothOrder.getPaidAmount()));
                txtTotalCost.setText(String.valueOf(clothOrder.getTotalCost()));
                txtTotalLabourCost.setText(String.valueOf(clothOrder.getTotalLabourCost()));
                txtUsagePerUnit.setText(String.valueOf(clothOrder.getUsagePerUnit()));
                txtWidth.setText(String.valueOf(clothOrder.getWidth()));

                comRawMaterialID.setValue(ClothOrderRepo.setRawMaterialIdInComboBoxWhenSearched(txtClothOrderId.getText()));
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot find cloth order").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public void clearFields(){
        comCustomerId.setValue(null);
        comRawMaterialID.setValue(null);
        datePickerOFD.setValue(null);
        datePickerOHD.setValue(null);
        txtClothOrderId.clear();
        txtDescription.clear();
        txtLaborCostPerUnit.clear();
        txtLeftToPay.clear();
        txtLength.clear();
        txtNumOfUnits.clear();
        txtPaidAmount.clear();
        txtTotalCost.clear();
        txtTotalLabourCost.clear();
        txtUsagePerUnit.clear();
        txtWidth.clear();
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

    private void getRawMaterialIds(){
        ObservableList<Integer> obList = FXCollections.observableArrayList();

        try {
            List<Integer> rawIds = CurtainOrderRepo.getRawIds();

            for (int id : rawIds){
                obList.add(id);
            }
            comRawMaterialID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
