package lk.ijse.shaafashions.model;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class SetClothOrder {
    private ClothOrder clothOrder ;
    private RawMaterialUsage rawMaterialUsage ;
    private  RawMaterialUsage rmUsage ;
    private int rawMaterialId ;

}
