package lk.ijse.shaafashions.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class WorkingDetail {
    private int workingDetailId ;
    private String clothOrderId;
    private String curtainOrderId;
    private int employeeId;

    public WorkingDetail(String clothOrderId, String curtainOrderId, int employeeId) {
        this.clothOrderId = clothOrderId;
        this.curtainOrderId = curtainOrderId;
        this.employeeId = employeeId;
    }
}
