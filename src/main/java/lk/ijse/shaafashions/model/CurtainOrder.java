package lk.ijse.shaafashions.model;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CurtainOrder {
    private String id ;
    private LocalDate heldDate ;
    private LocalDate finishingDate;
    private int totalLabourCost;
    private int paidAmount;
    private String description ;
    private int numOfPieces ;
    private int labourCostPerMeter ;
    private double metersPerPiece ;
    private int customerId ;
    private String serviceId ;
    private double lengthInMeters;
    private double widthInMeters ;
    private int totalCost;
    private int leftToPay;

}
