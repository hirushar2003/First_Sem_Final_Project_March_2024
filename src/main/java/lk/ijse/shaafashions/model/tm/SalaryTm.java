package lk.ijse.shaafashions.model.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SalaryTm {
    private int salaryId;
    private String salaryType;
    private String description ;
    private int salaryPerMonth;
    private int advancableAmount;
    private int numberOfWorkingDaysPerMonth;
}
