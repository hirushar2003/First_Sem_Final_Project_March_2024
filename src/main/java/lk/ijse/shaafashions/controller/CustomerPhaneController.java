package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

        Customer customer = new Customer(name, address, contact, email);

        try {
            boolean isSaved = CustomerRepo.customersave(customer);
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Saved Successfully").show();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
}
