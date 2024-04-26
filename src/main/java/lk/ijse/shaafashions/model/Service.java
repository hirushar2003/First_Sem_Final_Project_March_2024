package lk.ijse.shaafashions.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Service {
    private int serviceId;
    private String district ;
    private String stockScale ;
    private int deliveryPrice ;
    private int fittingPrice ;
    private int totalPrice ;
}
