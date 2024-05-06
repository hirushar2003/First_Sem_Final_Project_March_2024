package lk.ijse.shaafashions.model;

import java.time.LocalDate;

import javafx.scene.control.Alert;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class ClothOrder {
    private String ClothOrderId ;
    private LocalDate heldDate ;
    private LocalDate finishingDate ;
    private int labourCostPerUnit;
    private int numOfUnits ;
    private double length ;
    private double width ;
    private double usagePerUnit;
    private int totalLabourCost;
    private int paidAmount ;
    private int leftToPay;
    private String description ;
    private int customerId;
    private int totalCost;
}
