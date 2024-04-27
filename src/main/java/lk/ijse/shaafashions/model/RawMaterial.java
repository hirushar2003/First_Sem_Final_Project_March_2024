package lk.ijse.shaafashions.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class RawMaterial {
    private int rawMaterialId ;
    private String materialType ;
    private String description ;
    private String qtyBought ;
    private String qtyOnHand;
    private LocalDate lastRestockedDate;
    private int unitPrice;
    private int supplierId ;

    public RawMaterial(String materialType, String description, String qtyBought, String qtyOnHand, LocalDate value, int unitPrice, int supplierId) {
        this.materialType = materialType;
        this.description = description;
        this.qtyBought = qtyBought;
        this.qtyOnHand = qtyOnHand;
        this.lastRestockedDate = value;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
    }
}
