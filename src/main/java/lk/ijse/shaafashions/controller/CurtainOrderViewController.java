package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.shaafashions.model.CurtainOrder;
import lk.ijse.shaafashions.model.tm.CurtainOrderTm;
import lk.ijse.shaafashions.repository.CurtainOrderRepo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CurtainOrderViewController {

    @FXML
    private TableColumn<CurtainOrderTm, Integer> colCustomerId;

    @FXML
    private TableColumn<CurtainOrderTm, LocalDate> colFinishingDate;

    @FXML
    private TableColumn<CurtainOrderTm, LocalDate> colHeldDate;

    @FXML
    private TableColumn<CurtainOrderTm, String> colId;

    @FXML
    private TableColumn<CurtainOrderTm, Integer> colLeftToPay;

    @FXML
    private TableColumn<CurtainOrderTm, Integer> colTotalCost;

    @FXML
    private TableView<CurtainOrderTm> tblCurtainOrder;


    public void initialize(){
        getAllCurtainOrder();
        setCellValueFactory();
    }

    private void getAllCurtainOrder() {
        ObservableList<CurtainOrderTm> obList = FXCollections.observableArrayList();
        try {
            List<CurtainOrderTm> allCurtainOrders = CurtainOrderRepo.getAll();
            for (CurtainOrderTm curtainOrderTm : allCurtainOrders){

                CurtainOrderTm ctOrderTm = new CurtainOrderTm(
                        curtainOrderTm.getCurtainOrderId(),
                        curtainOrderTm.getHeldDate(),
                        curtainOrderTm.getFinishingDate(),
                        curtainOrderTm.getCustomerId(),
                        curtainOrderTm.getTotalCost(),
                        curtainOrderTm.getLeftToPay()
                );
                obList.add(ctOrderTm);
            }
            tblCurtainOrder.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("curtainOrderId"));
        colHeldDate.setCellValueFactory(new PropertyValueFactory<>("heldDate"));
        colFinishingDate.setCellValueFactory(new PropertyValueFactory<>("finishingDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colLeftToPay.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));

    }

}
