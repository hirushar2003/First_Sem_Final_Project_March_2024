package lk.ijse.shaafashions.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CustomerOhaneController {
    public AnchorPane customerAddPane;

    public void phaneViewOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomerView = FXMLLoader.load(getClass().getResource("/view/CustomerViewPhane.fxml"));

        customerAddPane.getChildren().clear();
        customerAddPane.getChildren().add(rootCustomerView);
    }

    public void phaneCustomerManageOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomerManage = FXMLLoader.load(getClass().getResource("/view/CustomerManagePhane.fxml"));

        customerAddPane.getChildren().clear();
        customerAddPane.getChildren().add(rootCustomerManage);
    }
}
