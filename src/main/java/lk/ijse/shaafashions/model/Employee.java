package lk.ijse.shaafashions.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Employee {
    private int employeeId ;
    private String name ;
    private String address ;
    private String jobRole ;
    private int contact ;
    private int yoe ;
    private int numOfWorkingDaysPerWeek ;
    private int salaryId ;

    public Employee(String name, String address, int contact, String jobRole, int yoe, int numOfWorkingDaysPerWeek, int salaryId) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.jobRole = jobRole;
        this.yoe = yoe;
        this.numOfWorkingDaysPerWeek = numOfWorkingDaysPerWeek;
        this.salaryId = salaryId;
    }
}
