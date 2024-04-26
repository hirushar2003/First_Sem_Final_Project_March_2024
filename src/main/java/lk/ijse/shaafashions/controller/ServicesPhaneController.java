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
import lk.ijse.shaafashions.model.Salary;
import lk.ijse.shaafashions.model.Service;
import lk.ijse.shaafashions.model.tm.ServiceTm;
import lk.ijse.shaafashions.repository.ServiceRepo;

import java.sql.SQLException;
import java.util.List;

public class ServicesPhaneController {

    public TextField txtDistrict;
    public TextField txtStockScale;
    public TextField txtDeliveryPrice;
    public TextField txtFittingPrice;
    public TextField txtTotalPrice;
    public TextField txtServiceIdSearch;
    @FXML
    private TableColumn<Service, Integer> colDeliveryPrice;

    @FXML
    private TableColumn<Service, String> colDistrict;

    @FXML
    private TableColumn<Service, Integer> colFittingPrice;

    @FXML
    private TableColumn<Service, Integer> colId;

    @FXML
    private TableColumn<Service, String> colStockScale;

    @FXML
    private TableColumn<Service, Integer> colTotalCost;

    @FXML
    private TableView<ServiceTm> tblService;

    public void initialize (){
        loadAllService();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colDistrict.setCellValueFactory(new PropertyValueFactory<>("district"));
        colStockScale.setCellValueFactory(new PropertyValueFactory<>("stockScale"));
        colDeliveryPrice.setCellValueFactory(new PropertyValueFactory<>("deliveryPrice"));
        colFittingPrice.setCellValueFactory(new PropertyValueFactory<>("fittingPrice"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void loadAllService() {
        ObservableList<ServiceTm> obList = FXCollections.observableArrayList();

        try {
            List<Service> serviceList = ServiceRepo.getAll();

            for(Service service : serviceList){
                ServiceTm serviceTm = new ServiceTm(

                service.getServiceId(),
                service.getDistrict(),
                service.getStockScale(),
                service.getDeliveryPrice(),
                service.getFittingPrice(),
                service.getTotalPrice()
                );

                obList.add(serviceTm);
            }
            tblService.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnServiceAddOnAction(ActionEvent actionEvent) {
        int serviceId = Integer.parseInt(txtServiceIdSearch.getText());
        String district = txtDistrict.getText();
        String stockScale = txtStockScale.getText();
        int delivery = Integer.parseInt(txtDeliveryPrice.getText());
        int fittingPrice = Integer.parseInt(txtFittingPrice.getText());
        int totalPrice = Integer.parseInt(txtTotalPrice.getText());

        Service service = new Service(serviceId, district, stockScale, delivery, fittingPrice, totalPrice);

        try {
            boolean isSaved = ServiceRepo.addService(service);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Service added sucessfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnServiceUpdateOnAction(ActionEvent actionEvent) {
        int serviceId = Integer.parseInt(txtServiceIdSearch.getText());
        String district = txtDistrict.getText();
        int deliveryPrice = Integer.parseInt(txtDeliveryPrice.getText());
        int totalPrice = Integer.parseInt(txtTotalPrice.getText());
        int fittingPrice = Integer.parseInt(txtFittingPrice.getText());
        String scale = txtStockScale.getText();

        Service service = new Service(serviceId, district, scale, deliveryPrice, fittingPrice, totalPrice);

        try {
            boolean isUpdate = ServiceRepo.update(service);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "service updated successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnServiceDeleteOnAction(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtServiceIdSearch.getText());

        try {
            boolean isDelete = ServiceRepo.delete(id);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Service deleted Sucessffully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtDistrict.clear();
        txtFittingPrice.clear();
        txtStockScale.clear();
        txtTotalPrice.clear();
        txtServiceIdSearch.clear();
        txtDeliveryPrice.clear();
    }

    public void imgGenerateOnAction(MouseEvent mouseEvent) {
        int deliveryPrice= Integer.parseInt(txtDeliveryPrice.getText());
        int fittingPrice = Integer.parseInt(txtFittingPrice.getText());

        int totalPrice = deliveryPrice + fittingPrice ;

        txtTotalPrice.setText(String.valueOf(totalPrice));
    }

    public void imgSearchOnAction(MouseEvent mouseEvent) throws SQLException {
        int id = Integer.parseInt(txtServiceIdSearch.getText());

        Service service = ServiceRepo.searchById(id);

        if (service != null){
            txtTotalPrice.setText(String.valueOf(service.getTotalPrice()));
            txtFittingPrice.setText(String.valueOf(service.getFittingPrice()));
            txtDeliveryPrice.setText(String.valueOf(service.getDeliveryPrice()));
            txtStockScale.setText(service.getStockScale());
            txtDistrict.setText(service.getDistrict());
            txtServiceIdSearch.setText(String.valueOf(service.getServiceId()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Cannot find service id ").show();
        }
    }
}
