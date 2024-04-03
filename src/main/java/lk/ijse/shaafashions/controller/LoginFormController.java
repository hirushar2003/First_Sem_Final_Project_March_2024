package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane loginAp;

    public void btnLoginonAction(ActionEvent actionEvent) {

    }

    public void lblRegOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootReg = FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"));

        Scene scene = new Scene(rootReg);

        Stage stage = (Stage) loginAp.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Shaa Fashions & Curtains | Register");
    }
}
