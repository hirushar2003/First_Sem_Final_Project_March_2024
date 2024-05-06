package lk.ijse.shaafashions.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSelectPhaneController {
    public AnchorPane empSelectAp;
    public Label lblTotalEmployees;

    public void initialize() throws SQLException {
        setEmployeeCount();
    }

    private void setEmployeeCount() throws SQLException {

        String sql = "SELECT COUNT(*) AS numberOfEmployees FROM employee;";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            lblTotalEmployees.setText(resultSet.getString("numberOfEmployees"));
        } else {
            lblTotalEmployees.setText("No employees");
        }
    }

    public void employeeOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootEmp = FXMLLoader.load(getClass().getResource("/view/employeeBase/EmployeePhane.fxml"));

        empSelectAp.getChildren().clear();
        empSelectAp.getChildren().add(rootEmp);

    }

    public void workingDetailsOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootWorkingDetail = FXMLLoader.load(getClass().getResource("/view/employeeBase/EmployeeWorkingDetail.fxml"));

        empSelectAp.getChildren().clear();
        empSelectAp.getChildren().add(rootWorkingDetail);
    }
}
