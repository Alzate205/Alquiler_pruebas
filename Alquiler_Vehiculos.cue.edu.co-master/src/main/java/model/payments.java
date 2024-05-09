package model;

import lombok.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class payments {
    private int id;
    private Date paymentDate;
    private int paymentValue;
    private int idReservation;
    private String status;

}
