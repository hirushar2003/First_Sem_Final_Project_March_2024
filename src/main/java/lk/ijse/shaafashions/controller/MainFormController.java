package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {

    public AnchorPane dashboardAp;
    public AnchorPane dashChildAp;

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootLog = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));

        Scene scene = new Scene(rootLog);
        Stage stage  = (Stage) dashboardAp.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Shaa Fashions & Curtains | Login");
    }

    public void lblOverviewOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootOver = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(rootOver);

        Stage stage = (Stage) dashboardAp.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Shaa Fashions & Curtains | Dashboard");
    }

    public void lblCustomerOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootCustomer = FXMLLoader.load(getClass().getResource("/view/CustomerPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootCustomer);
    }

    public void lblOrdersOnAction(MouseEvent mouseEvent) {

    }

    public void lblRaowMaterialOnAction(MouseEvent mouseEvent) {

    }

    public void lblEmployeeOnAction(MouseEvent mouseEvent) {

    }

    public void lblServiceOnAction(MouseEvent mouseEvent) {

    }

    public void lblSupplierOnAction(MouseEvent mouseEvent) {

    }

    public void lblAnalysisOnAction(MouseEvent mouseEvent) {

    }
}
