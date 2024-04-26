package lk.ijse.shaafashions.model.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class SupplierTm {
    private int supplierId;
    private String name ;
    private int contact ;
    private String location ;
    private String material;
    private int price ;
}
