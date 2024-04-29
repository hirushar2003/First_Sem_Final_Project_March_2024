package lk.ijse.shaafashions.model;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ClothOrder {
    private String orderId ;
    private LocalDate heldDate ;
    private LocalDate finishingDate ;
    private int labourCostPerUnit;
    private int qtyOrderd ;
    private String measurements ;
    private double fabricMeatersPerOneUnit;
    private String clothType ;
    private int totalLabourCost;
    private int paidAmount ;
    private String description ;
    private int customerId;

}
