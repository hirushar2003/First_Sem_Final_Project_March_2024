package lk.ijse.shaafashions.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Supplier {
    private int supplierId;
    private String name ;
    private int contact ;
    private String email ;
    private String location ;
    private String material;
    private int price ;


    public Supplier(String name, int contact, String email, String location, String material, int price) {
        this.name = name;
        this.contact = contact;
        this.email = email ;
        this.location = location ;
        this.material = material;
        this.price = price ;
    }

    public Supplier(int supplierId, String name, int contact, String location, String material, int price) {
        this.supplierId = supplierId;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.material = material;
        this.price = price;
    }
}
