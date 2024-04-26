package lk.ijse.shaafashions.model.tm;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ServiceTm {
        private int serviceId;
        private String district ;
        private String stockScale ;
        private int deliveryPrice ;
        private int fittingPrice ;
        private int totalPrice ;

}
