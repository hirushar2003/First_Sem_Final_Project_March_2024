package lk.ijse.shaafashions.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Customer {
    private int customerId ;
    private  String name ;
    private String address ;
    private int contact ;
    private String email ;

    public Customer (String name , String address, int contact, String email){
        this.name = name ;
        this.address = address ;
        this.contact = contact ;
        this. email = email ;
    }
}
