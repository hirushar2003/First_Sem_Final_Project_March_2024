package lk.ijse.shaafashions.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class WorkingDetailsControl {

    public TextField txtWorkingDetailIdSearch;
    @FXML
    private TableView<?> tblWorkingDetails;

    @FXML
    private TableColumn<?, ?> colClothingOrder;

    @FXML
    private TableColumn<?, ?> colCurtainOrder;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colWdId;

    @FXML
    private ComboBox<?> comClothOrderId;

    @FXML
    private ComboBox<?> comCurtainOrderId;

    @FXML
    private ComboBox<?> comEmpId;

    public void imgWdSearchOnAction(MouseEvent mouseEvent) {

    }

    public void btnWdAddOnAction(ActionEvent actionEvent) {

    }

    public void btnWdUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnWdDeleteOnAction(ActionEvent actionEvent) {

    }
}
