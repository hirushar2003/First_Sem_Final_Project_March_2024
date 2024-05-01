package lk.ijse.shaafashions.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RawMaterialUsage {
    private int rawMaterialUsageId;
    private double quantityUsedInMeters ;
    private int rawMaterialId ;
    private String clothOrderId ;
    private String curtainOrderId ;

    public RawMaterialUsage(double quantityUsedInMeters, int rawMaterialId, String curtainOrderId) {
        this.quantityUsedInMeters = quantityUsedInMeters;
        this.rawMaterialId = rawMaterialId;
        this.curtainOrderId = curtainOrderId;
    }

    public RawMaterialUsage(double quantityUsedInMeters) {
        this.quantityUsedInMeters = quantityUsedInMeters;
    }
}
