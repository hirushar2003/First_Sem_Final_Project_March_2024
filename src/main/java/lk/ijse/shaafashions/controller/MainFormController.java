package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        Parent rootCustomer = FXMLLoader.load(getClass().getResource("/view/customerBase/CustomerPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootCustomer);
    }

    public void lblOrdersOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootOrder = FXMLLoader.load(getClass().getResource("/view/orderBase/OrdersSelectForm.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootOrder);
    }

    public void lblRaowMaterialOnAction(MouseEvent mouseEvent) throws IOException {
        //Parent rootRaw = FXMLLoader.load(getClass().getResource("/view/RawMaterialPhane.fxml.fxml"));

        Parent rootRaw = FXMLLoader.load(getClass().getResource("/view/RawMaterialPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootRaw);
    }

    public void lblEmployeeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootEmp = FXMLLoader.load(getClass().getResource("/view/employeeBase/EmployeeSelectPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootEmp);
    }

    public void lblServiceOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootService = FXMLLoader.load(getClass().getResource("/view/ServicesPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootService);

    }

    public void lblSupplierOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootSup = FXMLLoader.load(getClass().getResource("/view/SupplierPhane.fxml"));

        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootSup);

    }

    public void lblAnalysisOnAction(MouseEvent mouseEvent) {

    }

    public void lblSalaryOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootSalary = FXMLLoader.load(getClass().getResource("/view/SalaryPhane.fxml"));
        dashChildAp.getChildren().clear();
        dashChildAp.getChildren().add(rootSalary);
    }
}
