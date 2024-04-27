package lk.ijse.shaafashions.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.shaafashions.model.Employee;
import lk.ijse.shaafashions.repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePaneController {

    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtEmpIdSearch;
    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colJobRole;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private TableColumn<?, ?> colWorkingDays;

    @FXML
    private TableColumn<?, ?> colYOE;

    @FXML
    private ComboBox<String> comJobRole;

    @FXML
    private ComboBox<Integer> comSalaryId;

    @FXML
    private ComboBox<Integer> comWorkingDays;

    @FXML
    private ComboBox<Integer> comYoe;

    public void initialize (){
        getSalaryIds();
        getAllEmployees();
        setCellValueFactory();
        comJobRole.setItems(FXCollections.observableArrayList("Manager", "Swingstress", "Curtain Fitter"));
        comWorkingDays.setItems(FXCollections.observableArrayList(5, 6, 7));
        comYoe.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
    }

    private void getAllEmployees() {

        ObservableList<Employee> obList = FXCollections.observableArrayList();


        try {
            List<Employee> allEmp = EmployeeRepo.getAll();

            for (Employee employee : allEmp){
                Employee emp = new Employee(
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getAddress(),
                        employee.getJobRole(),
                        employee.getContact(),
                        employee.getYoe(),
                        employee.getNumOfWorkingDaysPerWeek(),
                        employee.getSalaryId()
                );
                obList.add(emp);
            }
            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory(){
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colWorkingDays.setCellValueFactory(new PropertyValueFactory<>("numOfWorkingDaysPerWeek"));
        colYOE.setCellValueFactory(new PropertyValueFactory<>("yoe"));
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
    }

    public void getSalaryIds(){

        ObservableList<Integer> idList = FXCollections.observableArrayList();

        try {
            List<Integer> salaryIds = EmployeeRepo.getSalaryIds();

            for (int id :salaryIds){
                idList.add(id);
            }
            comSalaryId.setItems(idList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void imgEmpSearchOnAction(MouseEvent mouseEvent) {
        int id = Integer.parseInt(txtEmpIdSearch.getText());

        try {
            Employee employee = EmployeeRepo.searchById(id);

            if (employee != null){
                txtEmpIdSearch.setText(String.valueOf(employee.getEmployeeId()));
                txtAddress.setText(employee.getAddress());
                txtContact.setText(String.valueOf(employee.getContact()));
                txtName.setText(employee.getName());
                comYoe.setValue(employee.getYoe());
                comSalaryId.setValue(employee.getSalaryId());
                comJobRole.setValue(employee.getJobRole());
                comWorkingDays.setValue(employee.getNumOfWorkingDaysPerWeek());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not Found").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Employee not Found").show();
        }
    }

    public void btnAddEmp(ActionEvent actionEvent) {
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String name = txtName.getText();
        int yoe = comYoe.getValue();
        int salId = comSalaryId.getValue();
        String jobRole = comJobRole.getValue();
        int workingDays = comWorkingDays.getValue();

        Employee employee = new Employee(name, address, contact, jobRole, yoe, workingDays, salId);

        try {
            boolean isSaved = EmployeeRepo.addNewEmployee(employee);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved Sucessfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateEmp(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtEmpIdSearch.getText());
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String name = txtName.getText();
        int yoe = comYoe.getValue();
        int salId = comSalaryId.getValue();
        String jobRole = comJobRole.getValue();
        int workingDays = comWorkingDays.getValue();

        Employee employee = new Employee(id, name, address, jobRole, contact, yoe, workingDays, salId);

        try {
            boolean isUpdate = EmployeeRepo.updateEmployee(employee);

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated Sucessfully").show();
                clearFields();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteEmp(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtEmpIdSearch.getText());

        try {
            boolean isDeleted = EmployeeRepo.deleteEmployee(id);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted Sucessfully").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void clearFields(){

        txtEmpIdSearch.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
        comYoe.setValue(null);
        comSalaryId.setValue(null);
        comJobRole.setValue(null);
        comWorkingDays.setValue(null);

    }
}
