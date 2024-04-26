package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.shaafashions.model.Supplier;
import lk.ijse.shaafashions.model.tm.SupplierTm;
import lk.ijse.shaafashions.repository.SupplierRepo;

import java.sql.SQLException;
import java.util.List;

public class SupplierPhaneController {
    public TextField txtName;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtLocation;
    public TextField txtMaterial;
    public TextField txtPrice;
    public TextField txtIdSearch;
    @FXML
    private TableColumn<Supplier, Integer> colContact;

    @FXML
    private TableColumn<Supplier, Integer> colId;

    @FXML
    private TableColumn<Supplier, String> colLocation;

    @FXML
    private TableColumn<Supplier, String> colMaterial;

    @FXML
    private TableColumn<Supplier, String> colName;

    @FXML
    private TableColumn<Supplier, Integer> colPrice;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    public void initialize (){
        loadAllSuppliers();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();

            for (Supplier supplier:supplierList){
                SupplierTm supplierTm = new SupplierTm(

                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getLocation(),
                supplier.getMaterial(),
                supplier.getPrice()
                );

                obList.add(supplierTm);
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierAddOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String email = txtEmail.getText();
        String location = txtLocation.getText();
        String material = txtMaterial.getText();
        int price  = Integer.parseInt(txtPrice.getText());

        Supplier supplier = new Supplier(name, contact, email, location, material, price);

        try {
            boolean isSaved = SupplierRepo.saveNewSupplier(supplier);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier has been successfully saved").show();
                clearFielde();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnSupplierUpdateOnAction(ActionEvent actionEvent) {
        int contact = Integer.parseInt(txtContact.getText());
        String location = txtLocation.getText();
        String material = txtMaterial.getText();
        String name = txtName.getText();
        int price = Integer.parseInt(txtPrice.getText());
        String email = txtEmail.getText();
        int id = Integer.parseInt(txtIdSearch.getText());

        Supplier supplier = new Supplier(id, name, contact, email, location, material, price);

        try {
            boolean isUpdate = SupplierRepo.updateSupplier(supplier);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated sucessfully").show();
                clearFielde();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            clearFielde();
        }
    }

    public void btnSupplierDeleteOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtIdSearch.getText());

        try {
            boolean isDelete = SupplierRepo.deleteSupplier(id);

            if(isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted sucessfully").show();
                clearFielde();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void imgSuplierSearchOnAction(MouseEvent mouseEvent) throws SQLException {
        int id = Integer.parseInt(txtIdSearch.getText());

        Supplier supplier = SupplierRepo.searchSupplierById(id);

        if (supplier != null){
            txtContact.setText(String.valueOf(supplier.getContact()));
            txtLocation.setText(supplier.getLocation());
            txtMaterial.setText(supplier.getMaterial());
            txtName.setText(supplier.getName());
            txtPrice.setText(String.valueOf(supplier.getPrice()));
            txtEmail.setText(supplier.getEmail());
            txtIdSearch.setText(String.valueOf(supplier.getSupplierId()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found" ).show();
            clearFielde();
        }
    }

    public void clearFielde(){
        txtContact.clear();
        txtLocation.clear();
        txtMaterial.clear();
        txtName.clear();
        txtPrice.clear();
        txtEmail.clear();
        txtIdSearch.clear();
    }

}
