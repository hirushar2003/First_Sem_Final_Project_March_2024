package lk.ijse.shaafashions.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmployeeSelectPhaneController {
    public AnchorPane empSelectAp;

    public void employeeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootEmp = FXMLLoader.load(getClass().getResource("/view/EmployeePhane.fxml"));

        empSelectAp.getChildren().clear();
        empSelectAp.getChildren().add(rootEmp);

    }

    public void workingDetailsOnAction(MouseEvent mouseEvent) {

    }
}