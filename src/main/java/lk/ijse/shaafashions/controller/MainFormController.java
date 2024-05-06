package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.shaafashions.repository.DashBoardRepo;

import java.io.IOException;
import java.sql.SQLException;

public class MainFormController {

    public AnchorPane dashboardAp;
    public AnchorPane dashChildAp;
    public Label lblNumOfOrders;
    public Label lblNumOfCustomers;
    public Label lblCurtainFabricStock;
    public Label lblClothFabricStock;
    public Text lblCustomerId;
    public Text lblCustomerName;
    public Text lablNumOfOrders;
    public Label lblOrders;
    public Text lblSupplierId;
    public Text lblSupplierName;
    public Text lblMaterial;


    public void initialize() throws SQLException {
        setNumOfOrders();
        setNumOfCustomers();
        setAmountOfClothFabric();
        setAmountOfCurtainFabric();
        setBestCustomer();
        setBestSupplier();
    }

    private void setBestSupplier(){
        try {
            String bestEmployee = DashBoardRepo.getBestEmployee();

            if (bestEmployee != null){
                String[] split = bestEmployee.split(" ");

                lblSupplierId.setText(split[0]);
                lblSupplierName.setText(split[1]);
                lblMaterial.setText(split[2]);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBestCustomer() throws SQLException {
        String bestCustomer = DashBoardRepo.getBestCustomer();

        String[] split = bestCustomer.split(" ");


        lblCustomerId.setText(split[0]);
        lblCustomerName.setText(split[1]);
        lablNumOfOrders.setText(split[2]);
    }

    private void setAmountOfClothFabric() throws SQLException {
        int clothFabricAmount = DashBoardRepo.getClothFabricAmount();

        if (clothFabricAmount == 0){
            lblClothFabricStock.setText("Empty Stock");
        } else {
            lblClothFabricStock.setText(clothFabricAmount + " meters");
        }
    }

    private void setAmountOfCurtainFabric() throws SQLException {
        int curtainFabricStock = DashBoardRepo.getCurtainFabricStock();
        if (curtainFabricStock == 0 ){
            lblCurtainFabricStock.setText("Empty curtain fabric stock");
        } else {
            lblCurtainFabricStock.setText(curtainFabricStock + " meters");
        }
    }

    private void setNumOfCustomers() throws SQLException {
        int numOfCustomers = DashBoardRepo.getNumOfCustomers();

        if (numOfCustomers != 0){
            lblNumOfCustomers.setText(String.valueOf(numOfCustomers));
        }
    }
    private void setNumOfOrders() throws SQLException {
        int numOfOrders = DashBoardRepo.getNumOfOrders();

        if (numOfOrders != 0){
            lblNumOfOrders.setText(String.valueOf(numOfOrders));
        }
    }


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

        Parent rootRaw = FXMLLoader.load(getClass().getResource("/view/RawMaterialSelectPhane.fxml"));

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
