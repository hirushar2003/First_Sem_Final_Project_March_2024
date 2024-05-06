package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.shaafashions.model.tm.ClothOrderTm;
import lk.ijse.shaafashions.model.tm.CurtainOrderTm;
import lk.ijse.shaafashions.repository.ClothOrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClothingOrderViewController {

    @FXML
    private TableColumn<ClothOrderTm, Integer> colCustomerId;

    @FXML
    private TableColumn<ClothOrderTm, LocalDate> colFinishingDate;

    @FXML
    private TableColumn<ClothOrderTm, LocalDate> colHeldDate;

    @FXML
    private TableColumn<ClothOrderTm, String> colId;

    @FXML
    private TableColumn<ClothOrderTm, Integer> colPendingPayment;

    @FXML
    private TableColumn<ClothOrderTm, Integer> colTotalCost;

    @FXML
    private TableColumn<ClothOrderTm, Integer> colUnitsOrderd;

    @FXML
    private TableView<ClothOrderTm> tblClothOrder;

    public void initialize(){
        getAllClothOrder();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("clothOrderId"));
        colHeldDate.setCellValueFactory(new PropertyValueFactory<>("heldDate"));
        colFinishingDate.setCellValueFactory(new PropertyValueFactory<>("finishingDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colUnitsOrderd.setCellValueFactory(new PropertyValueFactory<>("quantityOrdered"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colPendingPayment.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));
    }

    private void getAllClothOrder() {

        ObservableList<ClothOrderTm> obList = FXCollections.observableArrayList();

        try {
            List<ClothOrderTm> allOrders = ClothOrderRepo.getAll();

            for (ClothOrderTm clothOrderTm : allOrders){

                ClothOrderTm clTm = new ClothOrderTm(
                        clothOrderTm.getClothOrderId(),
                        clothOrderTm.getHeldDate(),
                        clothOrderTm.getFinishingDate(),
                        clothOrderTm.getCustomerId(),
                        clothOrderTm.getQuantityOrdered(),
                        clothOrderTm.getTotalCost(),
                        clothOrderTm.getLeftToPay()
                );
                obList.add(clTm);
            }
            tblClothOrder.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
