package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.shaafashions.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane loginAp;

    public void btnLoginonAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            checkCredential(userName, password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void checkCredential(String userName, String password) throws SQLException, IOException {

        String sql = "SELECT userName, password FROM user WHERE userName = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject( 1, userName);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String dbpw = resultSet.getString("password");
            if (dbpw.equals(password)){
                Parent rootLog = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));

                Scene scene = new Scene(rootLog);
                Stage stage = (Stage) loginAp.getScene().getWindow();

                stage.setScene(scene);
                stage.setTitle("Shaa Fashions & Curtains | Dashboard");
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry incorrect password !").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "OOP's can't find username").show();
        }
    }

    public void lblRegOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootReg = FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"));

        Scene scene = new Scene(rootReg);

        Stage stage = (Stage) loginAp.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Shaa Fashions & Curtains | Register");
    }
}
