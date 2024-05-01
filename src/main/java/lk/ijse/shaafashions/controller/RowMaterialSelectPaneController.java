package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RowMaterialSelectPaneController {

    public AnchorPane rawSelectAp;

    public void rawMaterialOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootRaw = FXMLLoader.load(getClass().getResource("/view/RawMaterialPhane.fxml"));
        rawSelectAp.getChildren().clear();
        rawSelectAp.getChildren().add(rootRaw);
    }

    public void rawUsageOnAction(MouseEvent mouseEvent) throws IOException {
        Parent rootRmUsage = FXMLLoader.load(getClass().getResource("/view/RawMaterialUsage.fxml"));

        rawSelectAp.getChildren().clear();
        rawSelectAp.getChildren().add(rootRmUsage);
    }
}
