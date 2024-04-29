package lk.ijse.shaafashions.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OrderSelectPhaneController {

    public AnchorPane selectOrderAp;

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
