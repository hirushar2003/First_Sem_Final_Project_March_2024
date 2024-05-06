package lk.ijse.shaafashions.model.tm;

import java.time.LocalDate;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothOrderTm {
    private String clothOrderId ;
    private LocalDate heldDate ;
    private LocalDate finishingDate ;
    private int customerId ;
    private int quantityOrdered ;
    private int totalCost ;
    private int leftToPay ;
}
