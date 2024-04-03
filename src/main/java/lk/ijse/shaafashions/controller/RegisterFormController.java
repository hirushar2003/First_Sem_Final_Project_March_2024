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
import lk.ijse.shaafashions.model.User;
import lk.ijse.shaafashions.repository.UserRepo;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterFormController {

    public AnchorPane registerAp;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtUserName;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String email = txtEmail.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        User user = new User(name, address, email, contact, userName, password);

        try {
            boolean isSaved = UserRepo.register(user);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "User Registerd Successfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void lblLoginOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootLog = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));

        Scene scene = new Scene(rootLog);
        Stage stage = (Stage) registerAp.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Shaa Fashions & Curtains | Login");
    }

    public void clearFields(){
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        txtUserName.clear();
        txtPassword.clear();
    }
}
