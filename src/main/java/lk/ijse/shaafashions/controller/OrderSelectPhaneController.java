package lk.ijse.shaafashions.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.repository.OrderSelectPaneRepo;

import java.io.IOException;
import java.sql.SQLException;

public class OrderSelectPhaneController {

    public AnchorPane selectOrderAp;
    public Label lblPaymentUnfinishedCurtainOrders;
    public Label lblPaymentUnFinishedClothOrders;

    public void initialize() throws SQLException {
        setUnpaidCurtainOrders();
        setUnpaidClothOrders();
    }

    private void setUnpaidCurtainOrders() throws SQLException {
        int unPaidCurtainOrders = OrderSelectPaneRepo.getUnPaidCurtainOrders();

        if (unPaidCurtainOrders != 0){
            lblPaymentUnfinishedCurtainOrders.setText(String.valueOf(unPaidCurtainOrders));
        } else {
            lblPaymentUnfinishedCurtainOrders.setText("All orders are paid");
        }
    }

    private void setUnpaidClothOrders() throws SQLException {
        int unpaidClothOrders = OrderSelectPaneRepo.getUnpaidClothOrders();

        if (unpaidClothOrders != 0){
            lblPaymentUnFinishedClothOrders.setText(String.valueOf(unpaidClothOrders));
        } else {
            lblPaymentUnFinishedClothOrders.setText("All orders are paid");
        }
    }

    public void curtainOrderViewOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootCUOV = FXMLLoader.load(getClass().getResource("/view/orderBase/CurtainOrdersView.fxml"));

        selectOrderAp.getChildren().clear();
        selectOrderAp.getChildren().add(rootCUOV);
    }

    public void clothingOrderViewOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootCLOV = FXMLLoader.load(getClass().getResource("/view/orderBase/ClothOrdersView.fxml"));

        selectOrderAp.getChildren().clear();
        selectOrderAp.getChildren().add(rootCLOV);
    }

    public void curtainOrderManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootCUOM = FXMLLoader.load(getClass().getResource("/view/orderBase/CurtainOrderManage.fxml"));

        selectOrderAp.getChildren().clear();
        selectOrderAp.getChildren().add(rootCUOM);
    }

    public void clothingOrderManageOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootCLOM = FXMLLoader.load(getClass().getResource("/view/orderBase/ClothOrderManage.fxml"));

        selectOrderAp.getChildren().clear();
        selectOrderAp.getChildren().add(rootCLOM);
    }
}
