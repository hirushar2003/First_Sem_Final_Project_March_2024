package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.controller.util.Regex;
import lk.ijse.shaafashions.model.Customer;
import lk.ijse.shaafashions.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerPhaneController {
    public AnchorPane customerAddPane;
    public TextField txtFieldName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;

    public void phaneViewOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomerView = FXMLLoader.load(getClass().getResource("/view/customerBase/CustomerViewPhane.fxml"));

        customerAddPane.getChildren().clear();
        customerAddPane.getChildren().add(rootCustomerView);
    }

    public void phaneCustomerManageOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomerManage = FXMLLoader.load(getClass().getResource("/view/customerBase/CustomerManagePhane.fxml"));

        customerAddPane.getChildren().clear();
        customerAddPane.getChildren().add(rootCustomerManage);
    }

    public void phaneCustomerAddOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomer = FXMLLoader.load(getClass().getResource("/view/customerBase/CustomerPhane.fxml"));

        customerAddPane.getChildren().clear();
        customerAddPane.getChildren().add(rootCustomer);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        String name = txtFieldName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String email = txtEmail.getText();

        if (isValid()){
            Customer customer = new Customer(name, address, contact, email);
            try {
                boolean isSaved = CustomerRepo.customersave(customer);
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION , "Customer Saved Successfully").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "invalid ");
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void clearFields (){
        txtFieldName.clear();
        txtEmail.clear();
        txtContact.clear();
        txtAddress.clear();
    }


    public void txtNameKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.NAME, txtFieldName);
    }

    public void txtAddressKeyRealesedOnAction(KeyEvent keyEvent) {
        Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.ADDRESS, txtAddress);
    }

    public void txtEmailKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.EMAIL, txtEmail);
    }

    public void txtContactKeyRealeasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.CONTACT, txtContact);
    }

    public boolean isValid(){
        if (!Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.NAME,txtFieldName)) return false;
        if (!Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.CONTACT, txtContact)) return false;
        if (!Regex.setTextColour(lk.ijse.shaafashions.controller.util.TextField.EMAIL, txtEmail)) return false;
        return true ;
    }
}
