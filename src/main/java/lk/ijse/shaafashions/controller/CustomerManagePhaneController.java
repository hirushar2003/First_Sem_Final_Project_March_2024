package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.model.Customer;
import lk.ijse.shaafashions.repository.CustomerRepo;

import java.sql.SQLException;

public class CustomerManagePhaneController {
    public AnchorPane customerManagePhane;
    public TextField txtCustomerNameManage;
    public TextField txtCustomeAddresseManage;
    public TextField txtCustomerEmailManage;
    public TextField txtCustomerContactManage;
    public TextField txtCustomerIdEnter;

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCustomerIdEnter.getText();

        try {
            Boolean isDeleted = CustomerRepo.delete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted succesfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtCustomerIdEnter.getText());
        String name = txtCustomerNameManage.getText();
        String address = txtCustomeAddresseManage.getText();
        int contact = Integer.parseInt(txtCustomerContactManage.getText());
        String email = txtCustomerEmailManage.getText();

        Customer customer = new Customer(id, name, address, contact, email);

        try {
            boolean isUpdate = CustomerRepo.update(customer);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Sucessfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void imgSearchOnAction(MouseEvent mouseEvent) throws SQLException {
        String id = txtCustomerIdEnter.getText();

        Customer customer = CustomerRepo.searchById(id);

        if (customer != null){
            txtCustomerNameManage.setText(customer.getName());
            txtCustomeAddresseManage.setText(customer.getAddress());
            txtCustomerContactManage.setText(String.valueOf(customer.getContact()));
            txtCustomerEmailManage.setText(customer.getEmail());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Customer not found").show();
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) {

    }

    public void clearFields (){
        txtCustomerEmailManage.clear();
        txtCustomerContactManage.clear();
        txtCustomeAddresseManage.clear();
        txtCustomerNameManage.clear();
        txtCustomerIdEnter.clear();
    }
}
