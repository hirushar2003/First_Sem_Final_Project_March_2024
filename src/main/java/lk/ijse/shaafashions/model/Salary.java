package lk.ijse.shaafashions.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Salary {
    private int salaryId;
    private String salaryType;
    private String description ;
    private int salaryPerMonth;
    private int advancableAmount;
    private int numberOfWorkingDaysPerMonth;

    public Salary(String salaryType, String description, int salaryPerMonth, int advancableAmount, int numberOfWorkingDaysPerMonth) {
        this.salaryType = salaryType;
        this.description = description;
        this.salaryPerMonth = salaryPerMonth;
        this.advancableAmount = advancableAmount;
        this.numberOfWorkingDaysPerMonth = numberOfWorkingDaysPerMonth;
    }
}
