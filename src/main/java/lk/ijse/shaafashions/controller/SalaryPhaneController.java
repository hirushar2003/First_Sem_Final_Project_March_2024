package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.shaafashions.model.Salary;
import lk.ijse.shaafashions.model.tm.SalaryTm;
import lk.ijse.shaafashions.repository.SalaryRepo;

import java.sql.SQLException;
import java.util.List;

public class SalaryPhaneController {
    public AnchorPane salaryAp;
    public TextField txtType;
    public TextField txtSalary;
    public TextField txtAdvance;
    public TextField txtDayCount;
    public TextField txtDescription;
    public TextField txtIdSearch;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TableColumn<SalaryTm, Integer> colAdvance;

    @FXML
    private TableColumn<SalaryTm, Integer> colDayCount;

    @FXML
    private TableColumn<SalaryTm, String> colDescription;

    @FXML
    private TableColumn<SalaryTm, Integer> colId;

    @FXML
    private TableColumn<SalaryTm, Integer> colSalary;

    @FXML
    private TableColumn<SalaryTm, String> colType;

    public void initialize (){
        loadAllCustomers();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("salaryType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salaryPerMonth"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advancableAmount"));
        colDayCount.setCellValueFactory(new PropertyValueFactory<>("numberOfWorkingDaysPerMonth"));
    }

    private void loadAllCustomers() {
        ObservableList<SalaryTm> salaryTmObservableList = FXCollections.observableArrayList();

        try {
            List<Salary> salaryList = SalaryRepo.getAll();

            for (Salary salary : salaryList){
                SalaryTm salaryTm = new SalaryTm(

                salary.getSalaryId(),
                salary.getSalaryType(),
                salary.getDescription(),
                salary.getSalaryPerMonth(),
                salary.getAdvancableAmount(),
                salary.getNumberOfWorkingDaysPerMonth()
                );
                salaryTmObservableList.add(salaryTm);
            }
            tblSalary.setItems(salaryTmObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String type = txtType.getText();
        int salaryPerMonth = Integer.parseInt(txtSalary.getText());
        int advancableAmount = Integer.parseInt(txtAdvance.getText());
        int numOfWorkingDaysPerMonth = Integer.parseInt(txtDayCount.getText());
        String description = txtDescription.getText();

        Salary salary = new Salary(type, description, salaryPerMonth, advancableAmount, numOfWorkingDaysPerMonth );

        try {
            boolean isSaved = SalaryRepo.save(salary);
            new Alert(Alert.AlertType.CONFIRMATION, "Salary sucessfully saved").show();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String type = txtType.getText();
        String description = txtDescription.getText();
        int dayCount = Integer.parseInt(txtDayCount.getText());
        int salPerMonth = Integer.parseInt(txtSalary.getText());
        int advance = Integer.parseInt(txtAdvance.getText());
        int id = Integer.parseInt(txtIdSearch.getText());

        Salary salary = new Salary(id, type, description, salPerMonth, advance, dayCount);

        try {
            boolean isUpdate = SalaryRepo.update(salary);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary updated succesfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        String id = txtIdSearch.getText();

        try {
            boolean isDeleted = SalaryRepo.delete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary deleted succesfully").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void imgSearchOnAction(MouseEvent mouseEvent) throws SQLException {
        String id = txtIdSearch.getText();

        Salary salary = SalaryRepo.searchById(id);

        if (salary != null){
            txtType.setText(salary.getSalaryType());
            txtDescription.setText(salary.getDescription());
            txtAdvance.setText(String.valueOf(salary.getAdvancableAmount()));
            txtDayCount.setText(String.valueOf(salary.getNumberOfWorkingDaysPerMonth()));
            txtSalary.setText(String.valueOf(salary.getSalaryPerMonth()));
            txtAdvance.setText(String.valueOf(salary.getAdvancableAmount()));
        } else {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    public void clearFields(){
        txtType.clear();
        txtDescription.clear();
        txtAdvance.clear();
        txtDayCount.clear();
        txtSalary.clear();
        txtAdvance.clear();
        txtIdSearch.clear();
    }
}
