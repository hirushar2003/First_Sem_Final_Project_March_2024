package lk.ijse.shaafashions.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class User {
    private int userId ;
    private  String name ;
    private String address ;
    private String email ;
    private  int contact ;
    private String userName ;
    private String password ;
    private int customerId ;

    public User(String name, String address, String email, int contact, String userName, String password){
        this.name = name ;
        this.address = address ;
        this.email = email ;
        this.contact = contact ;
        this.userName = userName ;
        this.password = password ;
    }
}
