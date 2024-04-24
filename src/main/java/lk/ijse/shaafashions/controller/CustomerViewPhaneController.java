package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.model.Customer;
import lk.ijse.shaafashions.model.tm.CustomerTm;
import lk.ijse.shaafashions.repository.CustomerRepo;

import java.sql.SQLException;
import java.util.List;

public class CustomerViewPhaneController {
    @FXML
    private TableView<CustomerTm> tblCoustomer;

    @FXML
    private TableColumn<Customer, String> colAddress;

    @FXML
    private TableColumn<Customer, Integer> colContact;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, Integer> colId;

    @FXML
    private TableColumn<Customer,String> colName;

    @FXML
    private AnchorPane customerViewPhane;

    public void initialize (){
        loadAllCustomers();
        setCellValueFactory();
    }

    private void setCellValueFactory (){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    private void loadAllCustomers (){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for(Customer customer :customerList) {
                CustomerTm tm = new CustomerTm(
                customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getContact(),
                customer.getEmail()
                );

                obList.add(tm);
            }
            tblCoustomer.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
